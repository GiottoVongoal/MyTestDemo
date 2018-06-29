package com.example.giotto.mttext.demo.dateview;

/**
 * Created by Jimmy on 2016/10/7 0007.
 */
public interface OnCalendarClickListener {
    void onClickDate(int year, int month, int day);
    void onPageChange(int year, int month, int day);
}
