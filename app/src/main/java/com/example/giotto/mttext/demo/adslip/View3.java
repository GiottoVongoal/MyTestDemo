package com.example.giotto.mttext.demo.adslip;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.materialdesign.MaterialDesignActivity;

import org.greenrobot.eventbus.EventBus;

import static com.example.giotto.mttext.demo.R.id.tv2_next;

/**
 * @author 杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.adslip
 * @date 2017-08-02 15:43
 * @description
 */
public class View3 extends LinearLayout {
    TextView tv3_next;
    View view;

    public View3(Context context) {
        super(context);
        view = inflate(context, R.layout.fragment_3_layout, this);
        tv3_next = (TextView) view.findViewById(R.id.tv3_next);
        tv3_next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                EventBus.getDefault().post(new MessageEvent(1));
                Intent intent = new Intent(getContext(), MaterialDesignActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
}
