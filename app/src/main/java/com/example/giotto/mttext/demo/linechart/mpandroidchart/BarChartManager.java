package com.example.giotto.mttext.demo.linechart.mpandroidchart;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yly
 * @Data 2019/4/28 16:04
 * @Description  
 */
public class BarChartManager {
    private BarChart mBarChart;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;
    private Context mContext;
    //颜色集合
    List<Integer> colors = new ArrayList<>();

    public BarChartManager(BarChart barChart, Context context) {
        this.mBarChart = barChart;
        this.mContext = context;
        leftAxis = mBarChart.getAxisLeft();
        rightAxis = mBarChart.getAxisRight();
        xAxis = mBarChart.getXAxis();
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.CYAN);
        initBarChart();
    }

    /**
     * 初始化LineChart
     */
    private void initBarChart() {
        //背景颜色
        mBarChart.setBackgroundColor(Color.WHITE);
        //网格
        mBarChart.setDrawGridBackground(false);
        //背景阴影
        mBarChart.setDrawBarShadow(false);
        //设置是否可以拖拽
        mBarChart.setDragEnabled(true);
        mBarChart.setHighlightFullBarEnabled(false);
        // 是否可以触摸
        mBarChart.setTouchEnabled(true);

        //显示边界
        mBarChart.setDrawBorders(false);
        //图表的描述
        mBarChart.getDescription().setText("");

        //XY轴的设置
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(0.3f);//设置最小的区间，避免标签的迅速增多
        xAxis.setDrawGridLines(false);//设置竖状的线是否显示
        xAxis.setCenterAxisLabels(true);//设置标签居中
        xAxis.setLabelRotationAngle(20);//X轴标签的倾斜角度

        leftAxis.setDrawGridLines(true);//设置横状的线是否显示
//        leftAxis.enableGridDashedLine(6f, 3f, 0);//虚线

        leftAxis.setAxisLineWidth(0.3f);
        leftAxis.setEnabled(true);
//        leftAxis.setGridColor(0xacb3e5fc);
        //   leftAxis.setTextColor(0xb3e5fc);//设置左边Y轴文字的颜色
        //   leftAxis.setAxisLineColor(0xb3e5fc);//设置左边Y轴的颜色

        // rightAxis.setDrawGridLines(false);//设置横状的线是否显示
        rightAxis.setEnabled(false);//隐藏右边轴和数字

        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
        rightAxis.setAxisMinimum(0f);
        mBarChart.setDoubleTapToZoomEnabled(false); // 设置为false以禁止通过在其上双击缩放图表。
        mBarChart.setBorderWidth(0.3f);//设置边界宽度

    }

    /**
     * 展示柱状图(一条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param label
     * @param color
     */

    public void showBarChart(final List<String> xAxisValues, List<Float> yAxisValues, String label, int color) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < yAxisValues.size(); i++) {
            entries.add(new BarEntry(i, yAxisValues.get(i)));
        }
        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, label);

        barDataSet.setColor(color);
        barDataSet.setValueTextSize(9f);
        barDataSet.setFormLineWidth(0.5f);
        barDataSet.setFormSize(0.7f);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        BarData data = new BarData(dataSets);
        data.setBarWidth(0.4f);

        //x坐标轴设置
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setLabelCount(xAxisValues.size());
        xAxis.setCenterAxisLabels(true);//设置标签居中
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        xAxis.setAxisMinimum(-0.5f);//设置离左边位置0.5个柱状图的宽度
        xAxis.setAxisMaximum(xAxisValues.size());

        Legend legend = mBarChart.getLegend();
        legend.setTextColor(Color.WHITE);

        //设置X轴的刻度数
        mBarChart.setData(data);
        //设置动画效果
        mBarChart.animateY(1000, Easing.EasingOption.Linear);
        mBarChart.animateX(1000, Easing.EasingOption.Linear);
        //如果点击柱形图，会弹出pop提示框.XYMarkerView为自定义弹出框
        XYMarkerView mv = new XYMarkerView(mContext);
        mv.setChartView(mBarChart);
        mBarChart.setMarker(mv);
        mBarChart.invalidate();
    }

    /**
     * 展示柱状图(多条)
     */

    public void showBarChart(List<String> xValues, List<List<Float>> yValuesList, List<String> labels) {
        BarData data = new BarData();
        for (int i = 0; i < yValuesList.size(); i++) {
            ArrayList<BarEntry> entries = new ArrayList<>();
            for (int j = 0; j < yValuesList.get(i).size(); j++) {
                entries.add(new BarEntry(i, yValuesList.get(i).get(j)));
            }
            // y轴的数据集合
            BarDataSet barDataSet = new BarDataSet(entries, labels.get(i));
            barDataSet.setColor(colors.get(i));
            barDataSet.setValueTextColor(Color.BLACK);
            barDataSet.setValueTextSize(8f);
            data.addDataSet(barDataSet);
        }

        int amount = yValuesList.size();
        float groupSpace = 0.12f; //柱状图组之间的间距
        float barSpace = (float) ((1 - 0.12) / amount / 10); // x4 DataSet
        float barWidth = (float) ((1 - 0.12) / amount / 10 * 9); // x4 DataSet

        //x坐标轴设置
        //自定义适配器，适配于X轴
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setLabelCount(xValues.size());
        xAxis.setCenterAxisLabels(true);//设置标签居中
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        xAxis.setAxisMinimum(-0.3f);
        xAxis.setAxisMaximum(xValues.size());

        //折线图例 标签 设置
        Legend legend = mBarChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);//图示 标签的形状。   正方形
        legend.setTextSize(11f);
        legend.setTextColor(Color.BLACK);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        data.setBarWidth(barWidth);
        if (yValuesList.size() > 1) {
            data.groupBars(0, groupSpace, barSpace);
        }
        mBarChart.setData(data);
        //设置动画效果
        mBarChart.animateY(1000, Easing.EasingOption.Linear);
        mBarChart.animateX(1000, Easing.EasingOption.Linear);
        //设置点击事件
        XYMarkerView mv = new XYMarkerView(mContext);
        mv.setChartView(mBarChart);
        mBarChart.setMarker(mv);
        mBarChart.invalidate();
    }

    /**
     * 设置高限制线
     *
     * @param high
     * @param name
     */
    public void setHightLimitLine(float high, String name, int color) {
        if (name == null) {
            name = "高限制线";
        }
        LimitLine hightLimit = new LimitLine(high, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        hightLimit.setLineColor(color);
        hightLimit.setTextColor(color);
        leftAxis.addLimitLine(hightLimit);
        mBarChart.invalidate();
    }

    /**
     * 设置低限制线
     *
     * @param low
     * @param name
     */
    public void setLowLimitLine(int low, String name) {
        if (name == null) {
            name = "低限制线";
        }
        LimitLine hightLimit = new LimitLine(low, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        leftAxis.addLimitLine(hightLimit);
        mBarChart.invalidate();
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
        mBarChart.setDescription(description);
        mBarChart.invalidate();
    }
}
