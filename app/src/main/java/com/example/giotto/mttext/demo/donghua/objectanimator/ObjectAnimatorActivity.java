package com.example.giotto.mttext.demo.donghua.objectanimator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giotto.mttext.demo.R;

/**
 * 新的动画显示
 */
public class ObjectAnimatorActivity extends Activity {
    TextView oa_tv;
    TextView tv1, tv2, tv3, tv4, tv5, tv6;
    //隐藏布局
    LinearLayout hidden_view;
    private float mDensity;
    private int mHiddenViewMeasuredHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oalayout);
        oa_tv = (TextView) findViewById(R.id.oa_tv);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setObjectAnimator(oa_tv);//位移
            }
        });
        tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPropertyValuesHolder(oa_tv);//平移动画(同时改变X,Y的缩放)
            }
        });
        tv3 = (TextView) findViewById(R.id.tv3);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAnimate(oa_tv, oa_tv.getWidth(), 500);//监听数值的变化,从而完成动画的变化
            }
        });
        tv4 = (TextView) findViewById(R.id.tv4);
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ObjectAnimatorActivity.this, PropertyTest.class));
            }
        });
        tv5 = (TextView) findViewById(R.id.tv5);
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNum(oa_tv);
            }
        });
        hidden_view = (LinearLayout) findViewById(R.id.hidden_view);
        hidden_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ObjectAnimatorActivity.this, "imageView_b",
                        Toast.LENGTH_SHORT).show();
            }
        });
        // 获取像素密度
        mDensity = getResources().getDisplayMetrics().density;
        // 获取布局的高度
        mHiddenViewMeasuredHeight = (int) (mDensity * 40 + 0.5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hidden_view.getVisibility() == View.GONE) {
                    // 打开动画
                    openHiddenView(hidden_view);
                } else {
                    // 关闭动画
                    closeHiddenView(hidden_view);
                }
            }
        });
    }

    private void setObjectAnimator(View view) {
        //平移
        ObjectAnimator.ofFloat(view, "translationX", 300).setDuration(1000).start();
    }

    private void setPropertyValuesHolder(View view) {
        //平移
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("translationX", 300f);
        //围绕着支点(中心点)进行2D旋转
        //由1(本身大小)缩小到0(最小)再放大2倍(X,Y轴)
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f, 2f);
        PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f, 2f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvh1, pvh2, pvh3).setDuration(1000).start();
    }

    private void performAnimate(final View target, final int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            //持有一个IntEvaluator对象，方便下面估值的时候使用
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                //获得当前动画的进度值，整型，1-100之间
                int currentValue = (Integer) animator.getAnimatedValue();
                Log.i("currentValue", "currentValue-->" + currentValue);
                //计算当前进度占整个动画过程的比例，浮点型，0-1之间
                float fraction = currentValue / 100f;
                //直接调用整型估值器通过比例计算出宽度，然后再设给Button
                target.getLayoutParams().width = mEvaluator.evaluate(fraction, start, end);
                target.requestLayout();
            }
        });
        valueAnimator.setDuration(5000).start();
    }

    private void setNum(final View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            //持有一个IntEvaluator对象，方便下面估值的时候使用
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                //获得当前动画的进度值，整型，0-100之间
                int currentValue = (Integer) animator.getAnimatedValue();
                ((TextView) view).setText(currentValue + "");
            }
        });
        valueAnimator.setDuration(5000).start();
    }

    private void openHiddenView(final View view) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator valueAnimator = creatDropAnimator(view, 0, mHiddenViewMeasuredHeight);
        valueAnimator.start();
    }

    private void closeHiddenView(final View view) {
        ValueAnimator valueAnimator = creatDropAnimator(view, view.getHeight(), 0);
        //动画结束之后执行隐藏
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        valueAnimator.start();
    }

    private ValueAnimator creatDropAnimator(final View view, int start, int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int Value = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = Value;
                view.setLayoutParams(layoutParams);
            }
        });
        return valueAnimator;
    }

}