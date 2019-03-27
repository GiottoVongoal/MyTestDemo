package com.example.giotto.mttext.demo.scrollviewtop;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.example.giotto.mttext.demo.R;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-31 11:05
 * @description ScrollView向上滑动标题栏渐变效果
 */
public class HuaDongActivity extends Activity implements ObservableScrollView.OnObservableScrollViewListener {
    LinearLayout head_title;
    ObservableScrollView cypuserScrollView;
    LinearLayout mUserLayout;
    private int mHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hua_dong_fragment);
        head_title = (LinearLayout) findViewById(R.id.head_title);
        cypuserScrollView = (ObservableScrollView) findViewById(R.id.cyp_user_sv);
        mUserLayout = (LinearLayout) findViewById(R.id.cyp_user_lt);

        head_title.setAlpha(0);

        //获取标题栏高度
        ViewTreeObserver viewTreeObserver = mUserLayout.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mUserLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                mHeight = mUserLayout.getHeight() - head_title.getHeight();//这里取的高度应该为图片的高度-标题栏
                mHeight = mUserLayout.getHeight();
                //注册滑动监听
                cypuserScrollView.setOnObservableScrollViewListener(HuaDongActivity.this);
            }
        });
    }

    @Override
    public void onObservableScrollViewListener(int l, int t, int oldl, int oldt) {
        if (t <= 0) {
            //顶部图处于最顶部，标题栏透明
            head_title.setAlpha(0);
//            head_title.setBackgroundColor(Color.argb(0, 255, 255, 255));
        } else if (t > 0 && t < mHeight) {
            //滑动过程中，渐变
            float scale = (float) t / mHeight;//算出滑动距离比例
            head_title.setAlpha(scale);
//            float alpha = (255 * scale);//得到透明度
//            head_title.setAlpha(alpha / 100);
//            head_title.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
        } else {
            //过顶部图区域，标题栏定色
            head_title.setAlpha(1);
//            head_title.setBackgroundColor(Color.argb(255, 255, 255, 255));
        }
    }
}
