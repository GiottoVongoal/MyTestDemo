package com.example.giotto.mttext.demo.materialdesign;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.giotto.mttext.demo.R;

public class DesignTabLayoutActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager tab_viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_tab_layout);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 4dasf"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 5"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 6"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 7"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 73543523"));
////        tabLayout.addTab(tabLayout.newTab().setText("Tab 1").setIcon(R.mipmap.ic_launcher));
//        //默认选中某项(从0开始)
//        tabLayout.getTabAt(2).select();
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                // Tab被选择
//                switch (tab.getPosition()) { // 我在这里处理选择不同的Tab触发不同事件
//                    case 0:
//                        break;
//                    case 1:
//                        break;
//                    case 2:
//                        break;
//                    case 3:
//                        break;
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                //未选中tab的逻辑
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                //再次选中tab的逻辑
//
//            }
//        });

        //
        tab_viewPager = (ViewPager) findViewById(R.id.tab_viewPager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this);
        tab_viewPager.setAdapter(adapter);
        //TabLayout关联ViewPager
        tabLayout.setupWithViewPager(tab_viewPager);
    }

}
