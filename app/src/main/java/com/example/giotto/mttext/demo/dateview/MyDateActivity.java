package com.example.giotto.mttext.demo.dateview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.dateview.schedule.ScheduleLayout;
import com.example.giotto.mttext.demo.utils.MyUtils;

import java.util.Calendar;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-24 18:35
 * @description 测试日历
 */
public class MyDateActivity extends Activity {
    ScheduleLayout slSchedule;

    private TextView tvTitleMonth, tvTitleDay;
    private String[] mMonthText;
    private int mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_date_layout);

        slSchedule = (ScheduleLayout) findViewById(R.id.schedule_layout);
        tvTitleMonth = (TextView) findViewById(R.id.tvTitleMonth);
        tvTitleDay = (TextView) findViewById(R.id.tvTitleDay);

        mMonthText = getResources().getStringArray(R.array.calendar_month);
        tvTitleMonth.setText(mMonthText[Calendar.getInstance().get(Calendar.MONTH)]);
        Calendar calendar = Calendar.getInstance();
        resetMainTitleDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        slSchedule.setOnCalendarClickListener(new OnCalendarClickListener() {
            @Override
            public void onClickDate(int year, int month, int day) {
                //监听获得点击的年月日
                resetMainTitleDate(year, month, day);
                MyUtils.showToast(MyDateActivity.this, year + "年" + (month + 1) + "月" + day + "日");
            }

            @Override
            public void onPageChange(int year, int month, int day) {

            }
        });
        slSchedule.getMonthCalendar().setTodayToView();
    }

    public void resetMainTitleDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        if (year == calendar.get(Calendar.YEAR) &&
                month == calendar.get(Calendar.MONTH) &&
                day == calendar.get(Calendar.DAY_OF_MONTH)) {
            tvTitleMonth.setText(mMonthText[month]);
            tvTitleDay.setText("今天");
        } else {
            if (year == calendar.get(Calendar.YEAR)) {
                tvTitleMonth.setText(mMonthText[month]);
            } else {
                tvTitleMonth.setText(String.format("%s%s", String.format(getString(R.string.calendar_year), year),
                        mMonthText[month]));
            }
            tvTitleDay.setText(String.format(getString(R.string.calendar_day), day));
        }
        setCurrentSelectDate(year, month, day);
    }

    private void setCurrentSelectDate(int year, int month, int day) {
        mCurrentSelectYear = year;
        mCurrentSelectMonth = month;
        mCurrentSelectDay = day;
    }
}
