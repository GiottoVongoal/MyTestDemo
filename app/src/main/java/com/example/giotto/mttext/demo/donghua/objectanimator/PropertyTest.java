package com.example.giotto.mttext.demo.donghua.objectanimator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.giotto.mttext.demo.R;

import java.util.ArrayList;
import java.util.List;

public class PropertyTest extends Activity implements View.OnClickListener {
    private int[] mRes = {R.id.imageView_a, R.id.imageView_b, R.id.imageView_c,
            R.id.imageView_d, R.id.imageView_e};
    private List<ImageView> mImageViews = new ArrayList<ImageView>();
    private boolean mFlag = true;//是否开始动画

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property);
        for (int i = 0; i < mRes.length; i++) {
            ImageView imageView = (ImageView) findViewById(mRes[i]);
            imageView.setOnClickListener(this);
            mImageViews.add(imageView);
        }
        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                te();
            }
        });
    }

    private void startAnim() {
        //中心点(透明度更改)
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(mImageViews.get(0), "alpha", 1f, 0.5f);
        //向Y轴往下移动200
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(mImageViews.get(1), "translationY", 200f);
        //向X轴往右移200
        ObjectAnimator oa3 = ObjectAnimator.ofFloat(mImageViews.get(2), "translationX", 200f);
        //向Y轴往上移动200
        ObjectAnimator oa4 = ObjectAnimator.ofFloat(mImageViews.get(3), "translationY", -200f);
        //向X轴往左移200
        ObjectAnimator oa5 = ObjectAnimator.ofFloat(mImageViews.get(4), "translationX", -200f);
        //同时作用多个属性动画效果
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(oa1, oa2, oa3, oa4, oa5);
        set.start();
        mFlag = false;
    }

    private void closeAnim() {
        //中心点(透明度更改)
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(mImageViews.get(0), "alpha", 0.5f, 1f);
        //向Y轴往上移动200
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(mImageViews.get(1), "translationY", -200f, 0);
        //向X轴往左移200
        ObjectAnimator oa3 = ObjectAnimator.ofFloat(mImageViews.get(2), "translationX", -200f, 0);
        //向Y轴往下移动200
        ObjectAnimator oa4 = ObjectAnimator.ofFloat(mImageViews.get(3), "translationY", 200f, 0);
        ObjectAnimator oa41 = ObjectAnimator.ofFloat(mImageViews.get(3), "translationX", 200f, 0);//又向右移动200,变到方形右下角的位置
        //向X轴往右移200
        ObjectAnimator oa5 = ObjectAnimator.ofFloat(mImageViews.get(4), "translationX", 200f, 0);
        //同时作用多个属性动画效果
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(oa1, oa2, oa3, oa4, oa41, oa5);
        set.start();
        mFlag = true;
    }

    private void te() {
        //中心点(透明度更改)
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(iv, "alpha", 1f, 0.5f);
        //缩放
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(iv, "scaleX", 1, 2);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(iv, "scaleY", 1, 2);
        //同时作用多个属性动画效果
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.setInterpolator(new DecelerateInterpolator());
        set.play(oa1).with(scaleX).with(scaleY);
        set.start();
        //事件
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Toast.makeText(PropertyTest.this, "完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_a:
                if (mFlag) {
                    startAnim();
                } else {
                    closeAnim();
                }
                break;
            case R.id.imageView_b:
                Toast.makeText(PropertyTest.this, "imageView_b",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_c:
                Toast.makeText(PropertyTest.this, "imageView_c",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_d:
                Toast.makeText(PropertyTest.this, "imageView_d",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_e:
                Toast.makeText(PropertyTest.this, "imageView_e",
                        Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
