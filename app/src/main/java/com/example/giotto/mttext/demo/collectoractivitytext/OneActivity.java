package com.example.giotto.mttext.demo.collectoractivitytext;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.activitys.CommonContentDialog;
import com.example.giotto.mttext.demo.utils.InterfaceManager;
import com.example.giotto.mttext.demo.utils.MyUtils;

/**
 * @author SHAOS.
 * @PackageName com.example.giotto.mttext.demo.collectoractivitytext
 * @date 2016-09-14 11:26
 * @description
 */
public class OneActivity extends Activity {
    TextView collector_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collector_common_layout);
        ActivityCollector.addActivity(this);
        collector_tv = (TextView) findViewById(R.id.collector_tv);
        collector_tv.setText("OneActivity");
        collector_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(OneActivity.this, TwoActivity.class);
            }
        });

//        String promptLanguage = "abcdefghijklmno";
//        // 测量文本 text 所占的长度
//        float tWidth = getTextPaint().measureText(promptLanguage);

//        CommonContentDialog commonContentDialog = new CommonContentDialog.Builder(this)
//                .setTitle("测试")
//                .setContent("我是内容")
////                .setContent("我是内容我是内容我是内容我是内容我是内容我是内容我是内容")
//                .build();
//        commonContentDialog.show();

        showMyDialog();
    }

    public void showMyDialog() {
        View.OnClickListener onLeftCancel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        };
        View.OnClickListener onRightOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.showToast(OneActivity.this, "好啊");
            }
        };
        CommonContentDialog commonContentDialog = new CommonContentDialog.Builder(OneActivity.this)
                .setContent(R.layout.can_show_call_phone_dialog_layout)
                .setViewInterface(new InterfaceManager.CallBackView() {
                    @Override
                    public void getCallBackView(View view) {
                        TextView tv1 = (TextView) view.findViewById(R.id.can_show_call_phone_dialog_layout_tv1);
                        TextView tv2 = (TextView) view.findViewById(R.id.can_show_call_phone_dialog_layout_tv2);
                        TextView tv3 = (TextView) view.findViewById(R.id.can_show_call_phone_dialog_layout_tv3);
                        tv1.setText("您的签约申请正在审核中，暂时无法使用此功能！您可到基本信息页面查看审核状态！");
                        tv1.setTextSize(16);
                        tv2.setText("如有问题，请联系客服：");
                        tv3.setText("4000268115");
                        tv3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MyUtils.showToast(OneActivity.this, "4000268115");
                            }
                        });
                    }
                })
                .setLeftString("取消")
                .setLeftBtnOnClickListener(onLeftCancel)
                .setRightString("好的")
                .setRightBtnOnClickListener(onRightOk)
                .build();
        commonContentDialog.show();
    }

    public Paint getTextPaint() {
        Paint mTextPaint = new Paint();
        mTextPaint.setARGB(225, 75, 75, 75);
        Paint.Style style = mTextPaint.getStyle();
        mTextPaint.setStyle(style);
        mTextPaint.setTextSize(18.0f); // 指定字体大小
        mTextPaint.setColor(getResources().getColor(R.color.black3));
        mTextPaint.setAntiAlias(true); // 非锯齿效果
        return mTextPaint;
    }
}
