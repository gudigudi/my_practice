package com.gudigudigudi.mdtemplate.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.gudigudigudi.mdtemplate.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 * 雷达图
 */
public class RadarView extends View {

    // 默认的主题颜色
    private int defaultColor = Color.parseColor("#91D7F4");
    // 圆圈和交叉线的颜色
    private int mCircleColor = defaultColor;
    // 圆圈的数量，最小为1
    private int mCircleNum = 3;
    // 扫描颜色，附带渐变透明处理
    private int mSweepColor = defaultColor;
    // 水滴的颜色
    private int mRaindropColor = defaultColor;
    // 水滴最多数目
    private int mRaindropNum = 4;
    // 是否显示交叉线
    private boolean isShowCross = true;
    // 是否显示水滴
    private boolean isShowRaindrop = true;
    // 扫描转速
    private float mSpeed = 3.0f;
    // 水滴显示和消失的速度
    private float mFlicker = 3.0f;

    // 圆的画笔
    private Paint mCirclePaint;
    // 扫描效果的画笔
    private Paint mSweepPaint;
    // 水滴的画笔
    private Paint mRaindropPaint;

    // 扫描时的扫描旋转角度
    private float mDegrees;
    // 是否扫描
    private boolean isScanning = false;

    // 保存水滴的数据
    private ArrayList<Raindrop> mRaindrops = new ArrayList<>();

    private Random random = new Random();

    public RadarView(Context context) {
        super(context);
        init();
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs);
        init();
    }


    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs);
        init();
    }

    /**
     * 获取自定义属性值
     *
     * @param context
     * @param attrs
     */
    private void getAttrs(Context context, @NonNull AttributeSet attrs) {
        TypedArray mTypeArray = context.obtainStyledAttributes(attrs, R.styleable.RadarView);
        mCircleColor = mTypeArray.getColor(R.styleable.RadarView_circleColor, defaultColor);
        mCircleNum = mTypeArray.getInt(R.styleable.RadarView_circleNum, mCircleNum);
        if (mCircleNum < 1) {
            mCircleNum = 3;
        }
        mSweepColor = mTypeArray.getColor(R.styleable.RadarView_sweepColor, defaultColor);
        mRaindropColor = mTypeArray.getColor(R.styleable.RadarView_raindropColor, defaultColor);
        mRaindropNum = mTypeArray.getInt(R.styleable.RadarView_raindropNum, mRaindropNum);
        isShowCross = mTypeArray.getBoolean(R.styleable.RadarView_showCross, true);
        isShowRaindrop = mTypeArray.getBoolean(R.styleable.RadarView_showRaindrop, true);
        mSpeed = mTypeArray.getFloat(R.styleable.RadarView_speed, mSpeed);
        if (mSpeed <= 0) {
            mSpeed = 3;
        }
        mFlicker = mTypeArray.getFloat(R.styleable.RadarView_flicker, mFlicker);
        if (mFlicker <= 0) {
            mFlicker = 3;
        }
        mTypeArray.recycle();
    }

    /**
     * 初始化
     */
    private void init() {
        // 初始化:圆的画笔
        mCirclePaint = new Paint();
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStrokeWidth(1);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setAntiAlias(true);

        // 初始化:水滴的画笔
        mRaindropPaint = new Paint();
        mRaindropPaint.setStyle(Paint.Style.FILL);
        mRaindropPaint.setAntiAlias(true);

        // 初始化:扫描的画笔
        mSweepPaint = new Paint();
        mSweepPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 设置宽高,默认200dp
        int defaultSize = ConvertUtils.dp2px(200);
        setMeasuredDimension(measureWidth(widthMeasureSpec, defaultSize), measureHeight(heightMeasureSpec, defaultSize));
    }

    /**
     * 测量宽
     *
     * @param measureWidth
     * @param defaultSize
     * @return
     */
    private int measureWidth(int measureWidth, int defaultSize) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureWidth);
        int specSize = MeasureSpec.getSize(measureWidth);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = defaultSize + getPaddingLeft() + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return Math.max(result, getSuggestedMinimumWidth());
    }

    /**
     * 测量高
     *
     * @param measureHeight
     * @param defaultSize
     * @return
     */
    private int measureHeight(int measureHeight, int defaultSize) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureHeight);
        int specSize = MeasureSpec.getSize(measureHeight);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = defaultSize + getPaddingTop() + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return Math.max(result, getSuggestedMinimumWidth());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 计算圆的半径
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();
        int radius = Math.min(width, height) / 2;

        // 计算圆的圆心
        int cx = getPaddingLeft() + (getWidth() - getPaddingLeft() - getPaddingRight()) / 2;
        int cy = getPaddingTop() + (getHeight() - getPaddingTop() - getPaddingBottom()) / 2;

        drawCircle(canvas, cx, cy, radius);

        if (isShowCross) {
            drawCross(canvas, cx, cy, radius);
        }

        // 正在扫描
        if (isScanning) {
            if (isShowRaindrop) {
                drawRaindrop(canvas, cx, cy, radius);
            }
            drawSweep(canvas, cx, cy, radius);
            mDegrees = (mDegrees + (360 / mSpeed / 60)) % 360;

            invalidate();
        }
    }

    /**
     * 画圆
     *
     * @param canvas
     * @param cx
     * @param cy
     * @param radius
     */
    private void drawCircle(Canvas canvas, int cx, int cy, int radius) {
        for (int i = 0; i < mCircleNum; i++) {
            canvas.drawCircle(cx, cy, radius - (radius / mCircleNum * i), mCirclePaint);
        }
    }

    /**
     * 画交叉图
     *
     * @param canvas
     * @param cx
     * @param cy
     * @param radius
     */
    private void drawCross(Canvas canvas, int cx, int cy, int radius) {
        // 水平线
        canvas.drawLine(cx - radius, cy, cx + radius, cy, mCirclePaint);
        // 垂直线
        canvas.drawLine(cx, cy - radius, cx, cy + radius, mCirclePaint);
    }

    private void generateRaindrop(int cx, int cy, int radius) {
        // 最多只能同时存在mRaindropNum个水滴
        if (mRaindrops.size() < mRaindropNum) {
            // 随机一个20以内的数字，如果这个数字刚好是0，就生成一个水滴。
            // 用于控制水滴生成的概率。
            boolean probability = (random.nextInt() * 20) == 0;
            if (probability) {
                int x = 0;
                int y = 0;
                int xOffset = random.nextInt() * (radius - 20);
                int yOffset = random.nextInt() * (int) Math.sqrt(1.0 * (radius - 20) * (radius - 20) - xOffset * xOffset);
                if ((random.nextInt() * 2) == 0) {
                    x = cx - xOffset;
                } else {
                    x = cx + xOffset;
                }

                if ((random.nextInt() * 2) == 0) {
                    y = cy - yOffset;
                } else {
                    y = cy + yOffset;
                }

                mRaindrops.add(new Raindrop(x, y, 0, mRaindropColor));
            }
        }
    }

    /**
     * 删除水滴
     */
    private void removeRaindrop() {
        Iterator<Raindrop> iterator = mRaindrops.iterator();

        while (iterator.hasNext()) {
            Raindrop raindrop = iterator.next();
            if (raindrop.radius > 20 || raindrop.alpha < 0) {
                iterator.remove();
            }
        }
    }

    /**
     * 画雨点
     *
     * @param canvas
     * @param cx
     * @param cy
     * @param radius
     */
    private void drawRaindrop(Canvas canvas, int cx, int cy, int radius) {
        generateRaindrop(cx, cy, radius);

        for (Raindrop raindrop : mRaindrops) {
            mRaindropPaint.setColor(raindrop.changeAlpha());
            canvas.drawCircle(raindrop.x, raindrop.y, raindrop.radius, mRaindropPaint);
            // 水滴的扩散和透明的渐变效果
            raindrop.radius += 1.0f * 20 / 60 / mFlicker;
            raindrop.alpha -= 1.0f * 255 / 60 / mFlicker;
        }
        removeRaindrop();
    }

    private void drawSweep(Canvas canvas, int cx, int cy, int radius) {
        // 扇形的透明的渐变效果
        SweepGradient sweepGradient = new SweepGradient(cx, cy, new int[]{
                Color.TRANSPARENT, ColorUtils.setAlphaComponent(mSweepColor, 0.0f), ColorUtils.setAlphaComponent(mSweepColor, 168 / 255.0f),
                ColorUtils.setAlphaComponent(mSweepColor, 1.0f), ColorUtils.setAlphaComponent(mSweepColor, 1.0f)
        }, new float[]{0.0f, 0.6f, 0.99f, 0.998f, 1f});
        mSweepPaint.setShader(sweepGradient);
        // 先旋转画布，再回执扫描的颜色渲染，实现扫描时的旋转效果
        canvas.rotate(-90 + mDegrees, cx, cy);
        canvas.drawCircle(cx, cy, radius, mSweepPaint);
    }

    /**
     * 开始扫描
     */
    public void start() {
        if (!isScanning) {
            isScanning = true;
            invalidate();
        }
    }

    /**
     * 停止扫描
     */
    public void stop() {
        if (isScanning) {
            isScanning = false;
            mRaindrops.clear();
            mDegrees = 0.0f;
        }
    }

    private static class Raindrop {
        int x;
        int y;
        float radius;
        int color;
        float alpha = 255;

        public Raindrop(int x, int y, float radius, int color) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.color = color;
        }

        public int changeAlpha() {
            return ColorUtils.setAlphaComponent(color, alpha / 255.0f);
        }
    }
}
