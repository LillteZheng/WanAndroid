package com.zhengsr.wanandroid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @auther by zhengshaorui on 2020/4/5
 * describe:
 */
public class GrayFrameLayout extends FrameLayout {
    private Paint mPaint;
    public GrayFrameLayout(Context context) {
        this(context,null);
    }

    public GrayFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GrayFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(cm));

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int count = canvas.saveLayer(null,mPaint,Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(count);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int count = canvas.saveLayer(null,mPaint,Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
        canvas.restoreToCount(count);
    }
}
