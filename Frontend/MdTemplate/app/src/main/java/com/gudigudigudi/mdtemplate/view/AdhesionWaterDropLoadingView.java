package com.gudigudigudi.mdtemplate.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ColorUtils;
import com.gudigudigudi.mdtemplate.R;


/**
 * Created by gu on 1/27/18.
 */

public class AdhesionWaterDropLoadingView extends View {

    // loading animation
    boolean isLoading = true;

    // width & height
    private float mWidth = 100.0f;
    private float mHeight = 100.0f;

    // paint
    public Paint mPaint = new Paint();

    float time = 0;

    // furthest distance of the water drops
    float distance = 100.0f;

    public AdhesionWaterDropLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint.setAntiAlias(true);
        mPaint.setColor(ColorUtils.getColor(R.color.colorPrimary));
    }

    /**
     * measure:
     * aspect ratio is 4
     * distance is 1.2f of mHeight
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // aspect ratio: 4
        if (widthMeasureSpec / heightMeasureSpec < 4) {
            widthMeasureSpec = 4 * heightMeasureSpec;
        }

        distance = 1.2f * mHeight;

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isLoading) {
            // radius of the bigger water drop
            float bigR = mHeight * 0.32f + mHeight * 0.03f * Math.abs((float) Math.sin(Math.toRadians(time)));
            float smallR = mHeight * 0.22f + mHeight * 0.03f * Math.abs((float) Math.cos(Math.toRadians(time)));
            float bigx = (float) getWidth() / 2;
            // draw the bigger water drop
            canvas.drawCircle(bigx, mHeight / 2, bigR, mPaint);
            // draw the smaller water drop
            float smallx = getSmallCenterX();
            canvas.drawCircle(smallx, mHeight / 2, smallR, mPaint);
            // when the smaller water drop is on the right side of the bigger one
            if (smallx > bigx) {
                canvas.drawPath(genPathIfbigxLessThansmallx(bigx, bigR, smallx, smallR), mPaint);
            }
            // when the smaller water drop is on the left side of the bigger one
            if (smallx < bigx) {
                canvas.drawPath(genPathIfsmallxLessThanbigx(bigx, bigR, smallx, smallR), mPaint);
            }
        }
        postInvalidate();
    }

    private Path genPathIfsmallxLessThanbigx(float bigx, float bigR, float smallx, float smallR) {
        Path path = new Path();
        // the first point of bezier curve( in the bigger one)
        float x1 = bigx + bigR * (float) Math.cos(Math.toRadians(time));
        float y1 = mHeight / 2 - bigR * (float) Math.sin(Math.toRadians(time));
        if (y1 > mHeight / 2 - smallR) {
            y1 = mHeight / 2 - smallR;
            x1 = bigx - (float) (Math.sqrt(bigR * bigR - smallR * smallR));
        }
        // the second point of bezier curve( in the smaller one)
        float x2 = smallx - smallR * (float) Math.cos(Math.toRadians(time));
        float y2 = mHeight / 2 - smallR * (float) Math.sin(Math.toRadians(time));
        if (y2 > mHeight / 2 - smallR * 0.8) {
            y2 = mHeight / 2 - smallR * 0.8f;
            x2 = smallx + smallR * (float) (Math.sqrt(1 - 0.64f));
        }
        // the third point of bezier curve( in the smaller one)
        float x3 = smallx - smallR * (float) Math.cos(Math.toRadians(time));
        float y3 = mHeight / 2 + smallR * (float) Math.sin(Math.toRadians(time));
        if (y3 < mHeight / 2 + smallR * 0.8) {
            y3 = mHeight / 2 + smallR * 0.8f;
            x3 = smallx + smallR * (float) (Math.sqrt(1 - 0.64f));
        }
        // the fourth point of bezier curve( int the bigger one)
        float x4 = bigx + bigR * (float) Math.cos(Math.toRadians(time));
        float y4 = mHeight / 2 + bigR * (float) Math.sin(Math.toRadians(time));
        if (y4 < mHeight / 2 + smallR) {
            y4 = mHeight / 2 + smallR;
            x4 = bigx - (float) (Math.sqrt(bigR * bigR - smallR * smallR));
        }

        // draw the bezier curve
        path.moveTo(x1, y1);
        path.quadTo((bigx + smallx) / 2, mHeight / 2, x2, y2);
        path.lineTo(x3, y3);
        path.quadTo((bigx + smallx) / 2, mHeight / 2, x4, y4);

        return path;
    }

    private Path genPathIfbigxLessThansmallx(float bigx, float bigR, float smallx, float smallR) {
        Path path = new Path();

        // the first point of bezier curve( in the bigger water drop)
        float x1 = bigx + bigR * (float) Math.cos(Math.toRadians(time));
        float y1 = mHeight / 2 - bigR * (float) Math.sin(Math.toRadians(time));

        if (y1 > mHeight / 2 - smallR) {
            y1 = mHeight / 2 - smallR;
            x1 = bigx + (float) (Math.sqrt(bigR * bigR - smallR * smallR));
        }

        // the second point of bezier curve( in the smaller water drop)
        float x2 = smallx - smallR * (float) Math.cos(Math.toRadians(time));
        float y2 = mHeight / 2 - smallR * (float) Math.sin(Math.toRadians(time));
        if (y2 > mHeight / 2 - smallR * 0.8) {
            y2 = mHeight / 2 - smallR * 0.8f;
            x2 = smallx - smallR * (float) (Math.sqrt(0.36f));
        }

        // the third point of bezier curve( in the smaller water drop)
        float x3 = smallx - smallR * (float) Math.cos(Math.toRadians(time));
        float y3 = mHeight / 2 + smallR * (float) Math.sin(Math.toRadians(time));
        if (y3 < mHeight / 2 + smallR * 0.8) {
            y3 = mHeight / 2 + smallR * 0.8f;
            x3 = smallx - smallR * (float) (Math.sqrt(0.36f));
        }

        // the fourth point of bezier curve( int the bigger water drop)
        float x4 = bigx * bigR * (float) Math.cos(Math.toRadians(time));
        float y4 = mHeight / 2 + bigR * (float) Math.sin(Math.toRadians(time));
        if (y4 < mHeight / 2 + smallR) {
            y4 = mHeight / 2 + smallR;
            x4 = bigx + (float) (Math.sqrt(bigR * bigR - smallR * smallR));
        }

        // draw the bezier curve
        path.moveTo(x1, y1);
        path.quadTo((bigx + smallx) / 2, mHeight / 2, x2, y2);
        path.lineTo(x3, y3);
        path.quadTo((bigx + smallx) / 2, mHeight / 2, x4, y4);

        return path;
    }

    /**
     * get x coordinate of the smaller water drop
     *
     * @return
     */
    private float getSmallCenterX() {
        time = time + 2.5f;
        return mWidth / 2 + distance * (float) Math.cos(Math.toRadians(time));
    }
}


