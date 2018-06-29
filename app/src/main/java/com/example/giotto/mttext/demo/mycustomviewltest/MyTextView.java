package com.example.giotto.mttext.demo.mycustomviewltest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;

/**
 * @Author ywy
 * @Data 2018/4/3 16:00
 * @PackageName com.example.giotto.mttext.demo.aaaaaaaaaaaaaaaa
 * @Email 954347696@qq.com
 * @Description  带矩形方框的TextVIew
 */
public class MyTextView extends TextView {
    private Paint mPaint1;
    private Paint mPaint2;

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化
        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(R.color.blue4));
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制外层矩形
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint1);
        //绘制内层矩形
        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, mPaint2);
        canvas.save();
        //绘制文字前平移10像素
//        canvas.translate(10, 10);
        //父类完成的方法,即绘制文本
        super.onDraw(canvas);
    }
}
