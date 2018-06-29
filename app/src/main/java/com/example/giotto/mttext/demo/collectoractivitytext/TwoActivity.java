package com.example.giotto.mttext.demo.collectoractivitytext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.utils.MyUtils;

/**
 * @author SHAOS.
 * @PackageName com.example.giotto.mttext.demo.collectoractivitytext
 * @date 2016-09-14 11:26
 * @description
 */
public class TwoActivity extends Activity {
    TextView collector_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collector_common_layout);
        ActivityCollector.addActivity(this);
        collector_tv = (TextView) findViewById(R.id.collector_tv);
        collector_tv.setText("TwoActivity");
        collector_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TwoActivity.this, ThreeActivity.class);
            }
        });
    }
}
