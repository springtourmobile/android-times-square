// Copyright 2012 Square, Inc.

package com.squareup.timessquare;

import java.util.Date;

/**
 * Describes the state of a particular date cell in a {@link MonthView}.
 */
class MonthCellDescriptor {
    public enum RangeState {
        NONE, FIRST, MIDDLE, LAST
    }

    private final Date date;
    private final int value;
    private final boolean isCurrentMonth;
    private boolean isSelected;
    private final boolean isToday;
    private final boolean isSelectable;
    private boolean isHighlighted;
    private RangeState rangeState;

    /**
     * @param date 日期
     * @param currentMonth 不可点击或是不重要的日期字体颜色和背景
     * @param selectable 是否可选择
     * @param selected 是否选中
     * @param today 是否是今天
     * @param highlighted 设置某天背景颜色改变（true改变，false不变）
     * @param value
     * @param rangeState  MonthCellDescriptor.RangeState.MIDDLE中间颜色
     */
    MonthCellDescriptor(Date date, boolean currentMonth, boolean selectable, boolean selected,
                        boolean today, boolean highlighted, int value, RangeState rangeState) {
        this.date = date;
        isCurrentMonth = currentMonth;
        isSelectable = selectable;
        isHighlighted = highlighted;
        isSelected = selected;
        isToday = today;
        this.value = value;
        this.rangeState = rangeState;
    }

    public Date getDate() {
        return date;
    }

    public boolean isCurrentMonth() {
        return isCurrentMonth;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    boolean isHighlighted() {
        return isHighlighted;
    }

    void setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
    }

    public boolean isToday() {
        return isToday;
    }

    public RangeState getRangeState() {
        return rangeState;
    }

    public void setRangeState(RangeState rangeState) {
        this.rangeState = rangeState;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "MonthCellDescriptor{"
                + "date="
                + date
                + ", value="
                + value
                + ", isCurrentMonth="
                + isCurrentMonth
                + ", isSelected="
                + isSelected
                + ", isToday="
                + isToday
                + ", isSelectable="
                + isSelectable
                + ", isHighlighted="
                + isHighlighted
                + ", rangeState="
                + rangeState
                + '}';
    }
}
