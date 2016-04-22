// Copyright 2013 Square, Inc.

package com.squareup.timessquare;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.squareup.timessquare.MonthCellDescriptor.RangeState;

public class CalendarCellView extends FrameLayout {
    private static final int[] STATE_SELECTABLE = {
            R.attr.tsquare_state_selectable
    };
    private static final int[] STATE_CURRENT_MONTH = {
            R.attr.tsquare_state_current_month
    };
    private static final int[] STATE_TODAY = {
            R.attr.tsquare_state_today
    };
    private static final int[] STATE_HIGHLIGHTED = {
            R.attr.tsquare_state_highlighted
    };
    private static final int[] STATE_RANGE_FIRST = {
            R.attr.tsquare_state_range_first
    };
    private static final int[] STATE_RANGE_MIDDLE = {
            R.attr.tsquare_state_range_middle
    };
    private static final int[] STATE_RANGE_LAST = {
            R.attr.tsquare_state_range_last
    };

    private boolean isSelectable = false;
    private boolean isCurrentMonth = false;
    private boolean isToday = false;
    private boolean isHighlighted = false;
    private RangeState rangeState = RangeState.NONE;
    private CalendarTextView dayOfMonthTextView; //日期控件
    private CalendarTextView priceTextView; //价格
    private CalendarTextView holidayTextView; //节日
    private TextView mCustomView; //自定义控件

    @SuppressWarnings("UnusedDeclaration") //
    public CalendarCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSelectable(boolean isSelectable) {
        if (this.isSelectable != isSelectable) {
            this.isSelectable = isSelectable;
            dayOfMonthTextView.setSelectable(isSelectable);
            if(null != holidayTextView){
                holidayTextView.setSelectable(isSelectable);
            }
            if(null != priceTextView){
                priceTextView.setSelectable(isSelectable);
            }
            refreshDrawableState();
        }
    }

    public void setCurrentMonth(boolean isCurrentMonth) {
        if (this.isCurrentMonth != isCurrentMonth) {
            this.isCurrentMonth = isCurrentMonth;
            dayOfMonthTextView.setCurrentMonth(isCurrentMonth);
            if(null != holidayTextView){
                holidayTextView.setCurrentMonth(isCurrentMonth);
            }
            if(null != priceTextView){
                priceTextView.setCurrentMonth(isCurrentMonth);
            }
            refreshDrawableState();
        }
    }

    public void setToday(boolean isToday) {
        if (this.isToday != isToday) {
            this.isToday = isToday;
            dayOfMonthTextView.setToday(isToday);
            if(null != holidayTextView){
                holidayTextView.setToday(isToday);
            }
            if(null != priceTextView){
                priceTextView.setToday(isToday);
            }
            refreshDrawableState();
        }
    }

    public void setRangeState(MonthCellDescriptor.RangeState rangeState) {
        if (this.rangeState != rangeState) {
            this.rangeState = rangeState;
            dayOfMonthTextView.setRangeState(rangeState);
            if(null != holidayTextView){
                holidayTextView.setRangeState(rangeState);
            }
            if(null != priceTextView){
                priceTextView.setRangeState(rangeState);
            }
            refreshDrawableState();
        }
    }

    public void setHighlighted(boolean isHighlighted) {
        if (this.isHighlighted != isHighlighted) {
            this.isHighlighted = isHighlighted;
            dayOfMonthTextView.setHighlighted(isHighlighted);
            if(null != holidayTextView){
                holidayTextView.setHighlighted(isHighlighted);
            }
            if(null != priceTextView){
                priceTextView.setHighlighted(isHighlighted);
            }
            refreshDrawableState();
        }
    }

    public boolean isCurrentMonth() {
        return isCurrentMonth;
    }

    public boolean isToday() {
        return isToday;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 5);

        if (isSelectable) {
            mergeDrawableStates(drawableState, STATE_SELECTABLE);
        }

        if (isCurrentMonth) {
            mergeDrawableStates(drawableState, STATE_CURRENT_MONTH);
        }

        if (isToday) {
            mergeDrawableStates(drawableState, STATE_TODAY);
        }

        if (isHighlighted) {
            mergeDrawableStates(drawableState, STATE_HIGHLIGHTED);
        }
        if (rangeState == MonthCellDescriptor.RangeState.FIRST) {
            mergeDrawableStates(drawableState, STATE_RANGE_FIRST);
        } else if (rangeState == MonthCellDescriptor.RangeState.MIDDLE) {
            mergeDrawableStates(drawableState, STATE_RANGE_MIDDLE);
        } else if (rangeState == RangeState.LAST) {
            mergeDrawableStates(drawableState, STATE_RANGE_LAST);
        }

        return drawableState;
    }

    public void setDayOfMonthTextView(CalendarTextView textView) {
        dayOfMonthTextView = textView;
    }

    public CalendarTextView getDayOfMonthTextView() {
        if (dayOfMonthTextView == null) {
            throw new IllegalStateException(
                    "You have to setDayOfMonthTextView in your custom DayViewAdapter."
            );
        }
        return dayOfMonthTextView;
    }

    public CalendarTextView getHolidayTextView() {
        return holidayTextView;
    }

    public void setHolidayTextView(CalendarTextView holidayTextView) {
        this.holidayTextView = holidayTextView;
    }

    public CalendarTextView getPriceTextView() {
        return priceTextView;
    }

    public void setPriceTextView(CalendarTextView priceTextView) {
        this.priceTextView = priceTextView;
    }

    public TextView getCustomView() {
        return mCustomView;
    }

    public void setCustomView(TextView mCustomView) {
        this.mCustomView = mCustomView;
    }
}
