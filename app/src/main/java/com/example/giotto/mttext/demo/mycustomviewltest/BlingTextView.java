package com.example.giotto.mttext.demo.mycustomviewltest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @Author ywy
 * @Data 2018/4/3 16:42
 * @PackageName com.example.giotto.mttext.demo.aaaaaaaaaaaaaaaa
 * @Email 954347696@qq.com
 * @Description  动态文字闪动的TextView
 */
public class BlingTextView extends TextView {
    private int mViewWidth;
    private TextPaint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTranslate;

    public BlingTextView(Context context) {
        super(context);
    }

    public BlingTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BlingTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0
                        , new int[]{Color.BLUE, 0Xffffffff, Color.YELLOW, 0Xffffffff, Color.GRAY, 0Xffffffff, Color.GREEN}
                        , null, Shader.TileMode.MIRROR);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradientMatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(200);
        }
    }
}
