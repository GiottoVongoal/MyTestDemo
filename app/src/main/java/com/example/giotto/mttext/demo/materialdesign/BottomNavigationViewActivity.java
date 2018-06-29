package com.example.giotto.mttext.demo.materialdesign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.giotto.mttext.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.materialdesign
 * @date 2017-07-05 10:59
 * @description
 */
public class BottomNavigationViewActivity extends AppCompatActivity {
    private BottomNavigationView botton_navi_view;
    private ViewPager botton_viewpager;
    private MenuItem menuItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_view_layout);
        botton_navi_view = (BottomNavigationView) findViewById(R.id.botton_navi_view);

//        botton_navi_view.setItemIconTintList(null);//不是使用主题中的颜色，恢复图标本身颜色
        BottomNavigationViewHelper.disableShiftMode(botton_navi_view);//点击效果和三个item时的效果相同
        botton_navi_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_one:
                        botton_viewpager.setCurrentItem(0);
                        break;
                    case R.id.menu_item_two:
                        botton_viewpager.setCurrentItem(1);
                        break;
                    case R.id.menu_item_three:
                        botton_viewpager.setCurrentItem(2);
                        break;

                    case R.id.menu_item_four:
                        botton_viewpager.setCurrentItem(3);
                        break;
                }
                return true;//返回 true 使点击有效
            }
        });

        botton_viewpager = (ViewPager) findViewById(R.id.botton_viewpager);
        botton_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    botton_navi_view.getMenu().getItem(0).setChecked(false);
                }
                botton_navi_view.getMenu().getItem(position).setChecked(true);
                menuItem = botton_navi_view.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // 如果想禁止滑动，可以把下面的代码取消注释
        botton_viewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        ViewpagerAdapter adapter = new ViewpagerAdapter(getSupportFragmentManager());
        adapter.addFragment(BottonNavigationViewFragment.newInstance("拨号"));
        adapter.addFragment(BottonNavigationViewFragment.newInstance("信息"));
        adapter.addFragment(BottonNavigationViewFragment.newInstance("联系人"));
        adapter.addFragment(BottonNavigationViewFragment.newInstance("我的"));
        botton_viewpager.setAdapter(adapter);
    }


    class ViewpagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> list = new ArrayList<>();

        public ViewpagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        public void addFragment(Fragment fragment) {
            list.add(fragment);
        }
    }
}
