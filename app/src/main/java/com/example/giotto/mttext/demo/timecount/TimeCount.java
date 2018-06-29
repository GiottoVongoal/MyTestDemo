package com.example.giotto.mttext.demo.timecount;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;


/**
 * @author GiottoVongoal杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.timecount
 * @date 2017-02-17 11:48
 * @description
 */

public class TimeCount extends CountDownTimer {
    private Context context;
    private TextView tvGetcode;

    public TimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
    }

    public void init(Context context, TextView tvGetcode) {
        this.context = context;
        this.tvGetcode = tvGetcode;
    }

    @Override
    public void onFinish() {// 计时完毕时触发
        tvGetcode.setText("重新获取");
        tvGetcode.setClickable(true);
        tvGetcode.setTextColor(context.getResources().getColor(
                R.color.white));
        tvGetcode.setBackgroundColor(context.getResources()
                .getColor(R.color.blue3));
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示
        tvGetcode.setClickable(false);
        tvGetcode.setText(millisUntilFinished / 1000 + "s后重新发送");
        tvGetcode.setTextColor(context.getResources().getColor(
                R.color.white));
        tvGetcode.setBackgroundColor(context.getResources().getColor(
                R.color.grey9));
    }
}
