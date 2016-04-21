package com.squareup.timessquare;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * 作者：wangdakuan
 * 主要功能: 日历标题（显示年与星期）
 * 创建时间：2016/4/20 15:16
 */
public class MonthTitleView extends LinearLayout {

    TextView title; //显示年与月（2016年4月）

    public MonthTitleView(Context context) {
        super(context);
    }

    public MonthTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MonthTitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static MonthTitleView create(ViewGroup parent, LayoutInflater inflater, Calendar today, int titleTextColor, int dayBackgroundResId) {
        final MonthTitleView view = (MonthTitleView) inflater.inflate(R.layout.moth_title_view, parent, false);
        view.setTitleTextColor(titleTextColor);
        if (dayBackgroundResId != 0) {
            view.setDayBackground(dayBackgroundResId);
        }
        final int originalDayOfWeek = today.get(Calendar.DAY_OF_WEEK);
        today.set(Calendar.DAY_OF_WEEK, originalDayOfWeek);
        return view;
    }

    public MonthTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        title = (TextView) findViewById(R.id.text_years_title);
    }


    public void setDayBackground(int resId) {
        title.setBackgroundResource(resId);
    }

    public void setTitleTextColor(int color) {
        title.setTextColor(color);
    }

    public void init(MonthDescriptor month) {
        title.setText(month.getLabel());
    }
}
