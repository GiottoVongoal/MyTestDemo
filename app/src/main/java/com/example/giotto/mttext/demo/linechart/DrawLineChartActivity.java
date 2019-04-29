package com.example.giotto.mttext.demo.linechart;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.linechart.mpandroidchart.BarChartManager;
import com.example.giotto.mttext.demo.linechart.mpandroidchart.MpChartBean;
import com.example.giotto.mttext.demo.utils.GetJsonToName;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.google.gson.Gson;

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
    //SuitLines
    private SuitLines suitlines, suitlines2;
    private int[] color = new int[5];
    private int[] color2 = {Color.RED, Color.GRAY, Color.BLACK, Color.BLUE, 0xFFF76055, 0xFF9B3655, 0xFFF7A055};

    //MPAndroidChart
    private BarChart mChart1, mChart2;
    private TextView tv1, tv2;
    private BarChartManager barChartManager1, barChartManager2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_line_chart_layout);
//        suitlines = (SuitLines) findViewById(R.id.suitlines);
//        suitlines2 = (SuitLines) findViewById(R.id.suitlines2);

        color[0] = getResources().getColor(R.color.blue4);
        color[1] = getResources().getColor(R.color.holiday_text_color);
        color[2] = getResources().getColor(R.color.select_circle_color);
        color[3] = getResources().getColor(R.color.green_aed79b);
        color[4] = getResources().getColor(R.color.color_item_press);
//        suitlines.setDefaultOneLineColor(color);
//        suitlines.setLineForm(true);
//        List<Unit> lines = new ArrayList<>();
//        for (int i = 0; i < 8; i++) {
//            lines.add(new Unit(new SecureRandom().nextInt(10), i + ""));
//        }
//        suitlines.feedWithAnim(lines);
//
//        //画多条线时必须保证UI已加载完成,否则会崩溃(加1秒延迟)
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                    show2Lines();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        //MPAndroidChart
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        mChart1 = (BarChart) findViewById(R.id.chart1);
        mChart2 = (BarChart) findViewById(R.id.chart2);
        barChartManager1 = new BarChartManager(mChart1, this);
        barChartManager2 = new BarChartManager(mChart2, this);
        setBarChartData1();
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建多条柱状的图表
                setBarChartData1();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBarChartData2();
            }
        });
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

    /**********************************************MPAndroidChart********************************************/

    /**
     * 折线图bean1条
     */
    public void setBarChartData1() {
        mChart1.setVisibility(View.VISIBLE);
        mChart2.setVisibility(View.GONE);
        String jsonString = GetJsonToName.getJson(this.getBaseContext(), "mpandroidchartjsonbean.json");
        Gson gson = new Gson();
        MpChartBean bean = gson.fromJson(jsonString, MpChartBean.class);

        //在这里设置自己的数据源,BarEntry 只接收float的参数，
        //图形横纵坐标默认为float形式，如果想展示文字形式，需要自定义适配器，
        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<Float> dataSets = new ArrayList<>();
        for (int i = 0; i < bean.getRspBody().getList1().size(); i++) {
            xVals.add(bean.getRspBody().getList1().get(i).getName());
            dataSets.add(bean.getRspBody().getList1().get(i).getJe());
        }

        YAxis leftAxis = mChart1.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f);
        barChartManager1.showBarChart(xVals, dataSets, bean.getRspBody().getList1().get(0).getName(), color[0]);
    }

    /**
     * 折线图bean多条
     */
    public void setBarChartData2() {
        mChart1.setVisibility(View.GONE);
        mChart2.setVisibility(View.VISIBLE);
        String jsonString = GetJsonToName.getJson(this.getBaseContext(), "mpandroidchartjsonbean.json");
        Gson gson = new Gson();
        MpChartBean bean = gson.fromJson(jsonString, MpChartBean.class);

        //设置XY值
        List<String> nameX = new ArrayList<>();
//        List<String> nameY = new ArrayList<>();
        int size = 0;
        for (int i = 0; i < bean.getRspBody().getList2().size(); i++) {
//            for (int j = 0; j < bean.getRspBody().getList2().get(i).getListx().size(); j++) {
//                nameY.add(bean.getRspBody().getList2().get(i).getListx().get(j).getXqyName());
//            }
            if (bean.getRspBody().getList2().get(i).getListx().size() > size) {
                size = bean.getRspBody().getList2().get(i).getListx().size();
            }
            nameX.add(bean.getRspBody().getList2().get(i).getQyName());
        }
        //填充数据
        List<MpChartBean.RspBodyBean.List2Bean> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<MpChartBean.RspBodyBean.List2Bean.ListxBean> l = new ArrayList<>();
            for (int j = 0; j < bean.getRspBody().getList2().size(); j++) {
                l.add(j, new MpChartBean.RspBodyBean.List2Bean.ListxBean());
            }
            list.add(i, new MpChartBean.RspBodyBean.List2Bean("", "", l));
        }

        for (int i = 0; i < size; i++) {
            MpChartBean.RspBodyBean.List2Bean t2Bean = new MpChartBean.RspBodyBean.List2Bean();
            List<MpChartBean.RspBodyBean.List2Bean.ListxBean> listxBean = new ArrayList<>();
            for (int j = 0; j < bean.getRspBody().getList2().size(); j++) {
                MpChartBean.RspBodyBean.List2Bean.ListxBean xBean = new MpChartBean.RspBodyBean.List2Bean.ListxBean();
                if (bean.getRspBody().getList2().get(j).getListx().size() > i) {
                    xBean = bean.getRspBody().getList2().get(j).getListx().get(i);
                } else {
                    xBean = new MpChartBean.RspBodyBean.List2Bean.ListxBean();
                }
                listxBean.add(xBean);
            }
            t2Bean.setListx(listxBean);
            list.set(i, t2Bean);
        }

        //设置点的值
        List<List<Float>> yValues = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<Float> y = new ArrayList<>();
            for (int j = 0; j < list.get(i).getListx().size(); j++) {
                y.add(list.get(i).getListx().get(j).getXqyFrze());
            }
            yValues.add(y);
        }

        List<String> nameY = new ArrayList<>();
        nameY.add("一区");
        nameY.add("二区");
        nameY.add("三区");
        barChartManager2.showBarChart(nameX, yValues, nameY);
    }
}
