package com.example.giotto.mttext.demo.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-06-05 14:52
 * @description 当按钮移出屏幕时, 按钮显示到底部
 */
public class CheckViewIsShowActivity extends Activity implements MyScrollView.OnScrollListener {
    TextView check_tv;
    TextView check_tv_bottom;

    MyScrollView check_myscrollview;

    private int btnLayoutTop;//按钮与其父类布局的顶部距离
    private int btnLayoutHeight;//按钮的高度


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_view_is_show_layout);
        check_tv = (TextView) findViewById(R.id.check_tv);
        check_tv_bottom = (TextView) findViewById(R.id.check_tv_bottom);

        check_myscrollview = (MyScrollView) findViewById(R.id.check_myscrollview);
        check_myscrollview.setOnScrollListener(this);
    }

    /**
     * 窗口有焦点的时候，即所有的布局绘制完毕的时候，我们来获取按钮的高度和myScrollView距离父类布局的顶部位置
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            btnLayoutHeight = check_tv.getHeight();
            btnLayoutTop = check_tv.getTop();
        }
    }

    /**
     * 滚动的回调方法
     * 当滚动的Y距离大于或者等于 按钮距离父类布局顶部的位置加上按钮的高度(已划出屏幕)
     */
    @Override
    public void onScroll(int scrollY) {
        if (scrollY >= btnLayoutTop + btnLayoutHeight) {
            // 控件已不在屏幕可见区域（已滑出屏幕）
            check_tv_bottom.setVisibility(View.VISIBLE);
        } else {
            // 控件在屏幕可见区域
            check_tv_bottom.setVisibility(View.GONE);
        }
    }
}
