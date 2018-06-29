package com.example.giotto.mttext.demo.fragmentdemo;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.giotto.mttext.demo.R;


/**
 * @author GiottoVongoal杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.fragmentdemo
 * @date 2016-11-23 16:16
 * @description
 */
public class FragmentDemoActivity extends Activity {

    private Button button01, button02, button03, button04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentdemo_layuout);
        setview();
    }

    private void setview() {
        button01 = (Button) findViewById(R.id.button01);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取到FragmentManager，在V4包中通过getSupportFragmentManager，
                //在系统中原生的Fragment是通过getFragmentManager获得的。
                FragmentManager manager = getFragmentManager();
                //2.开启一个事务，通过调用beginTransaction方法开启。
                FragmentTransaction transaction = manager.beginTransaction();
                //把自己创建好的fragment创建一个对象
                FragmentFooter fragmentFooter = new FragmentFooter();
                //向容器内加入Fragment，一般使用add或者replace方法实现，需要传入容器的id和Fragment的实例。
                transaction.add(R.id.fragment_buju, fragmentFooter, "f1");
                //提交事务，调用commit方法提交。
                transaction.commit();
            }
        });
        button02 = (Button) findViewById(R.id.button02);
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager FMs = getFragmentManager();
                FragmentTransaction MfragmentTransactions = FMs.beginTransaction();
                FragmentHeader f2 = new FragmentHeader();
                MfragmentTransactions.add(R.id.fragment_buju, f2, "f2");
                //提交事务，调用commit方法提交。
                MfragmentTransactions.commit();
            }
        });
        button03 = (Button) findViewById(R.id.button03);
        button03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager FMs = getFragmentManager();
                Fragment fragment = FMs.findFragmentByTag("f2");
                FragmentTransaction MfragmentTransactions = FMs.beginTransaction();
                MfragmentTransactions.remove(fragment);
                MfragmentTransactions.commit();
            }
        });

        button04 = (Button) findViewById(R.id.button04);
        button04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager FMs = getFragmentManager();
                FragmentTransaction MfragmentTransactions = FMs.beginTransaction();
                FragmentHeader f2 = new FragmentHeader();
                MfragmentTransactions.replace(R.id.fragment_buju, f2, "f2");
                MfragmentTransactions.commit();
            }
        });
    }

}
