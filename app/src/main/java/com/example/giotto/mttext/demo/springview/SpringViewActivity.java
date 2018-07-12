package com.example.giotto.mttext.demo.springview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.giotto.mttext.demo.R;

/**
 * @Author yly
 * @Data 2018/7/12 14:47
 * @PackageName com.example.giotto.mttext.demo.springview
 * @Description  带粘性的ScrollView,上滑下滑都显示粘性效果
 */
public class SpringViewActivity extends Activity {
    private SpringView spring_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_view);

        spring_view = (SpringView) findViewById(R.id.spring_view);
        spring_view.setGive(SpringView.Give.NONE);
        spring_view.setHeader(new AcFunHeader(this, 0));
        spring_view.setFooter(new AcFunHeader(this, 0));
    }
}
