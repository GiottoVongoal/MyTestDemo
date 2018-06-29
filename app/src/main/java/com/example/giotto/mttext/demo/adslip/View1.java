package com.example.giotto.mttext.demo.adslip;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;

import org.greenrobot.eventbus.EventBus;

/**
 * @author 杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.adslip
 * @date 2017-08-02 15:43
 * @description
 */
public class View1 extends LinearLayout {
    EditText editText1, editText2;
    TextView tv1_next;
    View view;

    public View1(Context context) {
        super(context);
        view = inflate(context, R.layout.fragment_1_layout, this);
        editText1 = (EditText) view.findViewById(R.id.editText1);
        editText2 = (EditText) view.findViewById(R.id.editText2);
        tv1_next = (TextView) view.findViewById(R.id.tv1_next);
        tv1_next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MessageEvent(1));
            }
        });
    }
}
