package com.example.giotto.mttext.demo.materialdesign;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.glidetest.GlideImgManager;
import com.example.giotto.mttext.demo.utils.MyUtils;

public class TransitionTwo_Activity extends AppCompatActivity {
    private String path;
    private ImageView transition_two_iv;
    private Transition transition;
    private LinearLayout transition_two_view_ll;

    public TransitionTwo_Activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_two_layout);

        transition_two_iv = (ImageView) findViewById(R.id.transition_two_iv);
        transition_two_view_ll = (LinearLayout) findViewById(R.id.transition_two_view_ll);

//        final float finalRadius = (float) Math.hypot(transition_two_rl.getWidth(), transition_two_rl.getHeight());//计算三角形斜边长度

        path = getIntent().getStringExtra("path");
        if (!MyUtils.isEmpty(path)) {
            GlideImgManager.loadCircleImage(TransitionTwo_Activity.this, path, transition_two_iv);
            transition_two_view_ll.setVisibility(View.INVISIBLE);
            transition = getWindow().getSharedElementEnterTransition();
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    performCircleReveal();
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }

    }

    private void performCircleReveal() {
        /**
         * createCircularReveal
         * 是你要进行圆形缩放的 view
         * 分别是开始缩放点的 x 和 y 坐标
         * 分别是开始的半径和结束的半径
         */
        Animator anim = ViewAnimationUtils.createCircularReveal(transition_two_view_ll
                , transition_two_view_ll.getWidth() / 2
                , transition_two_iv.getBottom() / 2
                , 0
                , transition_two_view_ll.getHeight());
        anim.setDuration(1000);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                transition_two_view_ll.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        anim.start();
    }
}
