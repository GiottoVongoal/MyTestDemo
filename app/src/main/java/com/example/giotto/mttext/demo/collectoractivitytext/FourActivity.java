package com.example.giotto.mttext.demo.collectoractivitytext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.utils.MyUtils;

/**
 * @author SHAOS.
 * @PackageName com.example.giotto.mttext.demo.collectoractivitytext
 * @date 2016-09-14 11:26
 * @description
 */
public class FourActivity extends Activity {
    TextView collector_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collector_common_layout);
        ActivityCollector.addActivity(this);
        collector_tv = (TextView) findViewById(R.id.collector_tv);
        collector_tv.setText("FourActivity");
        //使用代码添加TextView
        LinearLayout layout = (LinearLayout) findViewById(R.id.collector_ll);
        TextView tv1 = new TextView(this);
        tv1.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(tv1, lp1);
        tv1.setText("结束所有Activity,跳到第一个OneActivity");
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCollector.finishAll();
                MyUtils.intentStartActivity(FourActivity.this, OneActivity.class);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);// 右往左推出效果
            }
        });
    }
}
