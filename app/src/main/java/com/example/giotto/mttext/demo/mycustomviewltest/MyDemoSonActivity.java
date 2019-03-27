package com.example.giotto.mttext.demo.mycustomviewltest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.segmenttab.SegmentTabLayout;
import com.example.giotto.mttext.demo.utils.MyUtils;

/**
 * @Author ywy
 * @Data 2018/3/28 22:03
 * @PackageName com.example.giotto.mttext.demo.aaaaaaaaaaaaaaaa
 * @Email 954347696@qq.com
 * @Description  
 */
public class MyDemoSonActivity extends MyDemoBaseActivity {
    private TextView my_demo_son_tv;
    private MyTopBar topBar;

    private String[] mTitles = {"LA180554", "LA180554", "LA180554", "LA180554", "LA180554", "LA180554"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_demo_son_layout);
        my_demo_son_tv = (TextView) findViewById(R.id.my_demo_son_tv);
        topBar = (MyTopBar) findViewById(R.id.view_top_bar);

        setText();

        topBar.setTopbarClickListener(new TopbarClickListener() {
            @Override
            public void leftClick() {
                MyUtils.showToast(MyDemoSonActivity.this, "left");
            }

            @Override
            public void rightClick() {
                MyUtils.showToast(MyDemoSonActivity.this, "right");

            }
        });
        //只显示左边
        topBar.setTextViewVisiable(0, true);
        topBar.setTextViewVisiable(1, false);

        SegmentTabLayout tabLayout_1 = (SegmentTabLayout) findViewById(R.id.tl_1);
        tabLayout_1.setTabData(mTitles);
    }

    private void setText() {
        int sss = sum(2, 8);
        my_demo_son_tv.setText(String.valueOf(sss));
    }

}
