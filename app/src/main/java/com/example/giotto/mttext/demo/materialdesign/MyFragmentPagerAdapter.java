package com.example.giotto.mttext.demo.materialdesign;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author 杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.materialdesign
 * @date 2017-06-15 17:20
 * @description
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{"Tab1", "Tab22", "Tab333", "Tab4444", "Tab55555", "Tab666666", "Tab7777777"};
    private Context context;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
