package com.example.giotto.mttext.demo.linechart.mpandroidchart;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Author yly
 * @Data 2019/4/28 14:23
 * @Description  
 */
public class XYMarkerView extends MarkerView {
    private TextView tvContent;
    private Context context;

    public XYMarkerView(Context context) {
        super(context, R.layout.custom_marker_view);
        tvContent = (TextView) findViewById(R.id.tvContent);
        this.context = context;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(addComma(String.valueOf(e.getY())));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }

    /**
     * 千分位
     *
     * @param m
     * @return
     */
    public static String addComma(String m) {
        if (!TextUtils.isEmpty(m)) {
            BigDecimal a = new BigDecimal(m);
            DecimalFormat df = new DecimalFormat(",###,##0"); //保留整数
            return df.format(a);
        } else {
            return "";
        }
    }
}
