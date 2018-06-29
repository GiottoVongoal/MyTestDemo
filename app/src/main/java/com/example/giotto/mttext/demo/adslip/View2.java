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
public class View2 extends LinearLayout {
    EditText editText3, editText4;
    TextView tv2_next;
    View view;

    public View2(Context context) {
        super(context);
        view = inflate(context, R.layout.fragment_2_layout, this);
        editText3 = (EditText) view.findViewById(R.id.editText3);
        editText4 = (EditText) view.findViewById(R.id.editText4);
        tv2_next = (TextView) view.findViewById(R.id.tv2_next);
        tv2_next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MessageEvent(2));
            }
        });
    }
}
