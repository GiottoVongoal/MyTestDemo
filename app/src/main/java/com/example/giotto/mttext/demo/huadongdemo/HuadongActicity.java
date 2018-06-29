package com.example.giotto.mttext.demo.huadongdemo;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.fragmentdemo.FragmentFooter;

/**
 * @author GiottoVongoal杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.huadongdemo
 * @date 2016-12-12 13:49
 * @description
 */
public class HuadongActicity extends Activity implements View.OnClickListener {
    public TextView rl_tv1_bottom, rl_tv2_bottom, rl_tv3_bottom, rl_tv4_bottom;
    public RelativeLayout rl1, rl2, rl3, rl4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.huadong_layout);
        init();
        FragmentFooter fragmentFooter = new FragmentFooter();
        setFragmentLayout(fragmentFooter);
    }

    private void init() {
        rl_tv1_bottom = (TextView) findViewById(R.id.rl_tv1_bottom);
        rl_tv2_bottom = (TextView) findViewById(R.id.rl_tv2_bottom);
        rl_tv3_bottom = (TextView) findViewById(R.id.rl_tv3_bottom);
        rl_tv4_bottom = (TextView) findViewById(R.id.rl_tv4_bottom);
        rl_tv1_bottom.setOnClickListener(this);
        rl_tv2_bottom.setOnClickListener(this);
        rl_tv3_bottom.setOnClickListener(this);
        rl_tv4_bottom.setOnClickListener(this);
        rl1 = (RelativeLayout) findViewById(R.id.rl1);
        rl2 = (RelativeLayout) findViewById(R.id.rl2);
        rl3 = (RelativeLayout) findViewById(R.id.rl3);
        rl4 = (RelativeLayout) findViewById(R.id.rl4);
    }


    private void setFragmentLayout(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        //2.开启一个事务，通过调用beginTransaction方法开启。
        FragmentTransaction transaction = manager.beginTransaction();
//        把自己创建好的fragment创建一个对象
//        FragmentFooter fragmentFooter = new FragmentFooter();
        //向容器内加入Fragment，一般使用add或者replace方法实现，需要传入容器的id和Fragment的实例。
        transaction.replace(R.id.huadong_fragment_ll, fragment);
        //提交事务，调用commit方法提交。
        transaction.commit();
    }


    @Override
    public void onClick(View view) {
        FragmentOne fragmentOne = new FragmentOne();
        FragmentTwo fragmentTwo = new FragmentTwo();
        switch (view.getId()) {
            case R.id.rl_tv1_bottom:
                setFragmentLayout(fragmentOne);
                break;
            case R.id.rl_tv2_bottom:
                setFragmentLayout(fragmentTwo);
                break;
            case R.id.rl_tv3_bottom:
                setFragmentLayout(fragmentOne);
                break;
            case R.id.rl_tv4_bottom:
                setFragmentLayout(fragmentTwo);
                break;
        }
    }
}

