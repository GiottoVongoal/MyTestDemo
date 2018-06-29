package com.example.giotto.mttext.demo.linechart;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.linechart
 * @date 2017-08-10 14:06
 * @description 画折线图页面
 */
public class DrawLineChartActivity extends Activity {
    private SuitLines suitlines, suitlines2;
    private int[] color = new int[5];
    private int[] color2 = {Color.RED, Color.GRAY, Color.BLACK, Color.BLUE, 0xFFF76055, 0xFF9B3655, 0xFFF7A055};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_line_chart_layout);
        suitlines = (SuitLines) findViewById(R.id.suitlines);
        suitlines2 = (SuitLines) findViewById(R.id.suitlines2);

        color[0] = getResources().getColor(R.color.blue_0090FF);
        color[1] = getResources().getColor(R.color.holiday_text_color);
        color[2] = getResources().getColor(R.color.select_circle_color);
        color[3] = getResources().getColor(R.color.green_aed79b);
        color[4] = getResources().getColor(R.color.color_item_press);
        suitlines.setDefaultOneLineColor(color);
        suitlines.setLineForm(true);
        List<Unit> lines = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            lines.add(new Unit(new SecureRandom().nextInt(10), i + ""));
        }
        suitlines.feedWithAnim(lines);

        //画多条线时必须保证UI已加载完成,否则会崩溃(加1秒延迟)
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    show2Lines();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void show2Lines() {
        //多条线
        SuitLines.LineBuilder builder = new SuitLines.LineBuilder();
        for (int j = 0; j < 2; j++) {
            List<Unit> lines2 = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                lines2.add(new Unit(new SecureRandom().nextInt(128), "" + i));
            }
            builder.add(lines2, new int[]{color2[new SecureRandom().nextInt(7)], Color.WHITE});
        }
        builder.build(suitlines2, true);
    }
}
