package com.squareup.timessquare.sample;

import android.view.LayoutInflater;
import android.view.View;

import com.squareup.timessquare.CalendarCellView;
import com.squareup.timessquare.CalendarTextView;
import com.squareup.timessquare.DayViewAdapter;

public class SampleDayViewAdapter<T> implements DayViewAdapter {

    private T t;

    @Override
    public void makeCellView(CalendarCellView parent) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_view_custom, null);
        parent.addView(layout);
        parent.setDayOfMonthTextView((CalendarTextView) layout.findViewById(R.id.day_view));
    }
}
