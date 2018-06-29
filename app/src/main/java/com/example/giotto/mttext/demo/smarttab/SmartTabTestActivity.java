package com.example.giotto.mttext.demo.smarttab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.giotto.mttext.demo.R;

/**
 * @Author yly
 * @Data 2018/5/9 15:25
 * @PackageName com.example.giotto.mttext.demo.smarttab
 * @Description  vivi中带粘性的viewPager滑动效果
 */
public class SmartTabTestActivity extends AppCompatActivity {

    private SmartTabLayout tab_layout;
    private ViewPager vp_circle;
    private TabAdapter pagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smart_tab_test_layout);

        tab_layout = (SmartTabLayout) findViewById(R.id.tab_layout);
        vp_circle = (ViewPager) findViewById(R.id.vp_circle);

        pagerAdapter = new TabAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new TestFragment(), "Footer");
        pagerAdapter.addFragment(new TestFragment(), "Header");
        vp_circle.setOffscreenPageLimit(2);
        vp_circle.setAdapter(pagerAdapter);
        vp_circle.setCurrentItem(0);
        tab_layout.setViewPager(vp_circle);
        vp_circle.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
//                    if (position == 0) {
//                        iv_post.setVisibility(View.GONE);
//                    } else {
//                        iv_post.setVisibility(View.VISIBLE);
//                    }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
