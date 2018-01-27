package com.gudigudigudi.mdtemplate.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.gudigudigudi.mdtemplate.R;

/**
 * Created by gu on 1/27/18.
 */

public class AdhesionWaterDropLoadingView extends View {

    private final static String TAG = "AdhesionWaterDropLoadingView";

    // loading animation
    boolean isLoading = true;

    Context mContext;

    /**
     * attributes
     */
    // width & height
    private int mWidth = 100;
    private int mHeight = 100;


    // color & paint
    public int mColor;
    public Paint mPaint = new Paint();

    float time = 0;

    // furthest distance of the water drops
    float distance = 100;

    public AdhesionWaterDropLoadingView(Context context) {
        super(context);

        mContext = context;
    }

    public AdhesionWaterDropLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mColor = context.getResources().getColor(R.color.colorPrimary, null);
        } else {
            mColor = context.getResources().getColor(R.color.colorPrimary);
        }

        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
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

        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

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
            float bigx = (getWidth()) / 2;

            // draw the bigger water drop
            canvas.drawCircle(bigx, mHeight / 2, bigR, mPaint);


            // draw the smaller water drop
            float smallx = getSmallCenterX();
            canvas.drawCircle(smallx, mHeight / 2, smallR, mPaint);

            // when the smaller water drop is on the right side of the bigger one
            if (smallx > bigx) {
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
                canvas.drawPath(path, mPaint);
            }

            // when the smaller water drop is on the left side of the bigger one
            if (smallx < bigx) {
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
                canvas.drawPath(path, mPaint);
            }
        }
        postInvalidate();
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


