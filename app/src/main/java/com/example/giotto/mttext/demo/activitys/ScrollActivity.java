package com.example.giotto.mttext.demo.activitys;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giotto.mttext.demo.R;

/**
 * @author SHAOS.
 * @PackageName com.example.giotto.mttext.demo.activitys
 * @date 2016-09-18 16:54
 * @description
 */
public class ScrollActivity extends Activity {
    TextView car_order_detail_btn_zhengyi_tv;
    private boolean mFlag = true;//是否开始动画
    //隐藏布局
    private TextView tv_hidden;

    private ObjectAnimator oa;
    private FrameLayout flayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_layout);
        init();
    }

    private void init() {
        car_order_detail_btn_zhengyi_tv = (TextView) findViewById(R.id.car_order_detail_btn_zhengyi_tv);
        flayout = (FrameLayout) this.findViewById(R.id.flayout);
        tv_hidden = (TextView) findViewById(R.id.tv_hidden);
        tv_hidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ScrollActivity.this, "imageView_b",
                        Toast.LENGTH_SHORT).show();
            }
        });
        car_order_detail_btn_zhengyi_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFlag) {
                    flayout.setVisibility(View.VISIBLE);
                    //向Y轴往上移动200
                    oa = ObjectAnimator.ofFloat(tv_hidden, "translationY", -tv_hidden.getHeight());
                    mFlag = false;
                } else {
                    //向Y轴往下移动200
                    oa = ObjectAnimator.ofFloat(tv_hidden, "translationY", -tv_hidden.getHeight(), 0);
                    flayout.setVisibility(View.GONE);
                    mFlag = true;
                }
                oa.setDuration(50);
                oa.start();
            }
        });
        flayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flayout.getVisibility() == View.VISIBLE) {
                    oa = ObjectAnimator.ofFloat(tv_hidden, "translationY", -tv_hidden.getHeight(), 0);
                    flayout.setVisibility(View.GONE);
                    mFlag = true;
                    oa.setDuration(50);
                    oa.start();
                }
            }
        });
    }
}
