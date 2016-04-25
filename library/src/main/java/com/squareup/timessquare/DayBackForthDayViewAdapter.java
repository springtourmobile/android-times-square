package com.squareup.timessquare;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * 作者：wangdakuan * 主要功能: 默认的往返控件 * 创建时间：2016/4/21 13:22
 */
public class DayBackForthDayViewAdapter implements DayViewAdapter {

    @Override
    public void makeCellView(CalendarCellView parent) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_day_back_forth, null);
        parent.addView(layout);
        parent.setDayOfMonthTextView((CalendarTextView) layout.findViewById(R.id.day_view));
        parent.setHolidayTextView((CalendarTextView) layout.findViewById(R.id.text_holiday));
        parent.setPriceTextView((CalendarTextView) layout.findViewById(R.id.text_price));
        parent.setCustomView((TextView) layout.findViewById(R.id.text_first_last));
    }
}