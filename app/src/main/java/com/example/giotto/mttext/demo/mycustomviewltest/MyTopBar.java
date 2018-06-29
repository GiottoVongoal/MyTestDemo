package com.example.giotto.mttext.demo.mycustomviewltest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;

/**
 * @Author ywy
 * @Data 2018/4/3 17:17
 * @PackageName com.example.giotto.mttext.demo.a_mycasualtest
 * @Email 954347696@qq.com
 * @Description  复合控件
 */
public class MyTopBar extends RelativeLayout {
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;

    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;

    private String mTitle;
    private float mTitleTextSize;
    private int mTitleTextColor;

    //使用Button时显示的文字居中,使用TextView时显示的文字不居中-_____-
    private Button mLeftBtn, mRightBtn;
    private TextView mTitleTv;// mLeftTv, mRightTv,
    private LayoutParams mLeftParams, mRightParams, mTitleParams;

    private TopbarClickListener listener;

    public MyTopBar(Context context) {
        super(context);
    }

    public MyTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //通过这个方法将attrs.xml中定义的declare-styleable所有属性值存储到TypedArray中
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTopBar);

        //从TypedArray中取出对应的值来为重要的属性赋值
        mLeftTextColor = ta.getColor(R.styleable.MyTopBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(R.styleable.MyTopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.MyTopBar_leftText);

        mRightTextColor = ta.getColor(R.styleable.MyTopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.MyTopBar_rightBackground);
        mRightText = ta.getString(R.styleable.MyTopBar_rightText);

        mTitle = ta.getString(R.styleable.MyTopBar_title);
        mTitleTextSize = ta.getDimension(R.styleable.MyTopBar_titleTextSize, 10);
        mTitleTextColor = ta.getColor(R.styleable.MyTopBar_titleTextColor, 0);

        //获取完TypedArray的值后,一般要调用recycle方法来避免创建时候的错误
        ta.recycle();//完成资源的回收

//        mLeftTv = new TextView(context);
//        mRightTv = new TextView(context);
        mLeftBtn = new Button(context);
        mRightBtn = new Button(context);
        mTitleTv = new TextView(context);

        //为创建的组件元素赋值(xml中对应属性的赋值)
//        mLeftTv.setText(mLeftText);
//        mLeftTv.setTextColor(mLeftTextColor);
//        mLeftTv.setBackground(mLeftBackground);
        mLeftBtn.setText(mLeftText);
        mLeftBtn.setTextColor(mLeftTextColor);
        mLeftBtn.setBackground(mLeftBackground);

//        mRightTv.setText(mRightText);
//        mRightTv.setTextColor(mRightTextColor);
//        mRightTv.setBackground(mRightBackground);
        mRightBtn.setText(mRightText);
        mRightBtn.setTextColor(mRightTextColor);
        mRightBtn.setBackground(mRightBackground);

        mTitleTv.setText(mTitle);
        mTitleTv.setTextColor(mTitleTextColor);
        mTitleTv.setTextSize(mTitleTextSize);
        mTitleTv.setGravity(Gravity.CENTER);

        //为组件元素设置相应的布局元素
        mLeftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
//        addView(mLeftTv, mLeftParams);
        addView(mLeftBtn, mLeftParams);

        mRightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
//        addView(mRightTv, mRightParams);
        addView(mRightBtn, mRightParams);

        mTitleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleTv, mTitleParams);

        mLeftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });
        mRightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
    }

    //暴露一个方法给调用者来注册接口的回调
    public void setTopbarClickListener(TopbarClickListener listener) {
        this.listener = listener;
    }

    /**
     * @param id
     * @param flag 是否显示
     */
    public void setTextViewVisiable(int id, boolean flag) {
        if (flag) {
            switch (id) {
                case 0:
                    mLeftBtn.setVisibility(View.VISIBLE);
                    break;
                default:
                    mRightBtn.setVisibility(View.VISIBLE);
                    break;
            }
        } else {
            switch (id) {
                case 0:
                    mLeftBtn.setVisibility(View.GONE);
                    break;
                default:
                    mRightBtn.setVisibility(View.GONE);
                    break;
            }
        }
    }

}
