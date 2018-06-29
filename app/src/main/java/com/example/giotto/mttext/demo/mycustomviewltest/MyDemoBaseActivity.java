package com.example.giotto.mttext.demo.mycustomviewltest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @Author ywy
 * @Data 2018/3/28 22:01
 * @PackageName com.example.giotto.mttext.demo.aaaaaaaaaaaaaaaa
 * @Email 954347696@qq.com
 * @Description  
 */
public class MyDemoBaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    int sum(int a, int b) {
        return a + b;
    }
}
