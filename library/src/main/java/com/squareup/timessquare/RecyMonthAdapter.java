package com.squareup.timessquare;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * 作者：wangdakuan
 * 主要功能:
 * 创建时间：2016/4/21 12:48
 */
public class RecyMonthAdapter extends RecyclerView.Adapter<RecyMonthAdapter.ViewHolder> {
    private DateFormat weekdayNameFormat; //日期转换
    private MonthView.Listener listener; //监听器
    private Calendar today; //数据
    private int dividerColor; //分割线颜色
    private int dayBackgroundResId; //背景
    private int dayTextColorResId; //当天背景
    private int titleTextColor; //
    private boolean displayHeader; // 是否显示标题
    private int headerTextColor;
    private List<CalendarCellDecorator> decorators; //回调监听（用于自定义）
    private Locale locale; //时区
    private DayViewAdapter adapter;

    /**
     * 年月
     */
    private List<MonthDescriptor> months; //年月数据集合
    private Typeface titleTypeface; //字体样式
    /**
     * 日期
     */
    private List<List<List<MonthCellDescriptor>>> cells; //日期数据
    private boolean displayOnly; //是否只做展示不可点击
    private Typeface dateTypeface; //日期字体样式

    private boolean isRtl;

    public RecyMonthAdapter(DateFormat weekdayNameFormat, MonthView.Listener listener, Calendar today, int dividerColor,
                            int dayBackgroundResId, int dayTextColorResId, int titleTextColor, boolean displayHeader,
                            int headerTextColor, List<CalendarCellDecorator> decorators, Locale locale,
                            DayViewAdapter adapter) {
        this.weekdayNameFormat = weekdayNameFormat;
        this.listener = listener;
        this.today = today;
        this.dividerColor = dividerColor;
        this.dayBackgroundResId = dayBackgroundResId;
        this.dayTextColorResId = dayTextColorResId;
        this.titleTextColor = titleTextColor;
        this.displayHeader = displayHeader;
        this.headerTextColor = headerTextColor;
        this.decorators = decorators;
        this.locale = locale;
        this.adapter = adapter;

        this.isRtl = isRtl(locale);
    }

    /**
     * 设置年月数据集合
     *
     * @param months        年月数据
     * @param titleTypeface 字体样式
     */
    public void initYearsOrMonthData(List<MonthDescriptor> months, Typeface titleTypeface) {
        this.months = months;
        this.titleTypeface = titleTypeface;
    }

    /**
     * 设置日期数据
     *
     * @param cells        日期数据集合
     * @param dateTypeface 字体样式
     * @param displayOnly  是否只做展示不可点击
     */
    public void initDayMonthData(List<List<List<MonthCellDescriptor>>> cells, Typeface dateTypeface, boolean displayOnly) {
        this.cells = cells;
        this.dateTypeface = dateTypeface;
        this.displayOnly = displayOnly;
    }

    @Override
    public RecyMonthAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy_month, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyMonthAdapter.ViewHolder holder, int position) {
        final int originalDayOfWeek = today.get(Calendar.DAY_OF_WEEK);
        int firstDayOfWeek = today.getFirstDayOfWeek();
        final CalendarRowView headerRow = (CalendarRowView) holder.calendarGrid.getChildAt(0);
        for (int offset = 0; offset < 7; offset++) {
            today.set(Calendar.DAY_OF_WEEK, getDayOfWeek(firstDayOfWeek, offset, isRtl));
            final TextView textView = (TextView) headerRow.getChildAt(offset);
            textView.setText(weekdayNameFormat.format(today.getTime()).replace("星期", ""));
        }
        today.set(Calendar.DAY_OF_WEEK, originalDayOfWeek);

        holder.title.setText(months.get(position).getLabel());
        NumberFormat numberFormatter = NumberFormat.getInstance(locale);

        final int numRows = cells.get(position).size();
        holder.calendarGrid.setNumRows(numRows);
        for (int i = 0; i < 6; i++) {
            CalendarRowView weekRow = (CalendarRowView) holder.calendarGrid.getChildAt(i + 1);
            weekRow.setListener(listener);
            if (i < numRows) {
                weekRow.setVisibility(LinearLayout.VISIBLE);
                List<MonthCellDescriptor> week = cells.get(position).get(i);
                for (int c = 0; c < week.size(); c++) {
                    MonthCellDescriptor cell = week.get(isRtl ? 6 - c : c);
                    CalendarCellView cellView = (CalendarCellView) weekRow.getChildAt(c);

                    String cellDate = numberFormatter.format(cell.getValue());
                    if (!cellView.getDayOfMonthTextView().getText().equals(cellDate)) {
                        cellView.getDayOfMonthTextView().setText(cellDate);
                        if (cell.isToday()) {
                            cellView.getDayOfMonthTextView().setText("今天");
                        }
                    }
                    cellView.setEnabled(cell.isCurrentMonth());
                    cellView.setClickable(!displayOnly);

                    cellView.setSelectable(cell.isSelectable());
                    if (null != cellView.getCustomView()) {
                        cellView.getCustomView().setText("");
                        cellView.getCustomView().setVisibility(View.VISIBLE);
                    }
                    if (null == cell.getRangeState() || cell.getRangeState() == MonthCellDescriptor.RangeState.NONE) {
                        cellView.setSelected(cell.isSelected());
                        if (null != cellView.getHolidayTextView()) {
                            cellView.getHolidayTextView().setGravity(Gravity.CENTER);
                        }
                    } else {
                        if (null != cellView.getCustomView()) {
                            cellView.getCustomView().setVisibility(View.VISIBLE);
                            if (cell.getRangeState() == MonthCellDescriptor.RangeState.FIRST) {
                                cellView.getCustomView().setText("去");
                                if (null != cellView.getHolidayTextView()) {
                                    cellView.getHolidayTextView().setGravity(Gravity.LEFT);
                                }
                            }
                            if (cell.getRangeState() == MonthCellDescriptor.RangeState.LAST) {
                                cellView.getCustomView().setText("返");
                                if (null != cellView.getHolidayTextView()) {
                                    cellView.getHolidayTextView().setGravity(Gravity.LEFT);
                                }
                            }
                        }
                    }

                    if (null != cellView.getPriceTextView()) {
                        cellView.getPriceTextView().setText("￥100" + (position * i));
                    }

                    cellView.setCurrentMonth(cell.isCurrentMonth());
                    cellView.setToday(cell.isToday());
                    cellView.setRangeState(cell.getRangeState());
                    cellView.setHighlighted(cell.isHighlighted());
                    cellView.setTag(cell);

                    if (null != decorators) {
                        for (CalendarCellDecorator decorator : decorators) {
                            decorator.decorate(cellView, cell.getDate());
                        }
                    }
                }
            } else {
                weekRow.setVisibility(View.GONE);
            }
        }

        if (titleTypeface != null) {
            holder.title.setTypeface(titleTypeface);
        }
        if (dateTypeface != null) {
            holder.calendarGrid.setTypeface(dateTypeface);
        }

    }

    @Override
    public int getItemCount() {
        return null != months ? months.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;//标题
        CalendarGridView calendarGrid;//日期

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            calendarGrid = (CalendarGridView) itemView.findViewById(R.id.calendar_grid);
            calendarGrid.setDayViewAdapter(adapter);
            calendarGrid.setDividerColor(dividerColor);
            calendarGrid.setDayTextColor(dayTextColorResId);
            title.setTextColor(titleTextColor);
            calendarGrid.setDisplayHeader(displayHeader);
            calendarGrid.setHeaderTextColor(headerTextColor);
            if (dayBackgroundResId != 0) {
                ((MonthView) itemView).setDayBackground(dayBackgroundResId);
            }
        }
    }

    private static boolean isRtl(Locale locale) {
        // TODO convert the build to gradle and use getLayoutDirection instead of this (on 17+)?
        final int directionality = Character.getDirectionality(locale.getDisplayName(locale).charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT
                || directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }

    private static int getDayOfWeek(int firstDayOfWeek, int offset, boolean isRtl) {
        int dayOfWeek = firstDayOfWeek + offset;
        if (isRtl) {
            return 8 - dayOfWeek;
        }
        return dayOfWeek;
    }
}
