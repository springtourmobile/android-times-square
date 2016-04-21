package com.squareup.timessquare;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.timessquare.stickyheadersrecyclerview.AnimalsAdapter;
import com.squareup.timessquare.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * 作者：wangdakuan
 * 主要功能: 日历适配器
 * 创建时间：2016/4/20 18:04
 */
public class AnimalsHeadersMonthAdapter extends AnimalsAdapter<RecyclerView.ViewHolder>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

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

    public AnimalsHeadersMonthAdapter(DateFormat weekdayNameFormat, MonthView.Listener listener, Calendar today, int dividerColor,
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_view, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NumberFormat numberFormatter = NumberFormat.getInstance(locale);
        DayViewHolder dayViewHolder = (DayViewHolder) holder;
        dayViewHolder.calendarGrid.setDayViewAdapter(adapter);
        dayViewHolder.calendarGrid.setDividerColor(dividerColor);
        dayViewHolder.calendarGrid.setDisplayHeader(displayHeader);
        dayViewHolder.calendarGrid.setDayTextColor(dayTextColorResId);
        dayViewHolder.calendarGrid.setHeaderTextColor(headerTextColor);
        if (dayBackgroundResId != 0) {
            dayViewHolder.calendarGrid.setDayBackground(dayBackgroundResId);
        }
        Log.e("春秋旅游", "-----------|=" + months.get(position).getLabel());
        final int numRows = cells.get(position).size();
        dayViewHolder.calendarGrid.setNumRows(numRows);
        for (int i = 0; i < 6; i++) {
            CalendarRowView weekRow = (CalendarRowView) dayViewHolder.calendarGrid.getChildAt(i);
            weekRow.setListener(listener);
            if (i < numRows) {
                weekRow.setVisibility(LinearLayout.VISIBLE);
                List<MonthCellDescriptor> week = cells.get(position).get(i);
                for (int c = 0; c < week.size(); c++) {
                    MonthCellDescriptor cell = week.get(isRtl ? 6 - c : c);
                    Log.e("春秋旅游", "-----------|=" + cell.toString());
                    CalendarCellView cellView = (CalendarCellView) weekRow.getChildAt(c);

                    String cellDate = numberFormatter.format(cell.getValue());
                    if (!cellView.getDayOfMonthTextView().getText().equals(cellDate)) {
                        cellView.getDayOfMonthTextView().setText(cellDate);
                    }
                    cellView.setEnabled(cell.isCurrentMonth());
                    cellView.setClickable(!displayOnly);

                    cellView.setSelectable(cell.isSelectable());
                    cellView.setSelected(cell.isSelected());
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
        if (dateTypeface != null) {
            dayViewHolder.calendarGrid.setTypeface(dateTypeface);
        }
    }

    @Override
    public int getItemCount() {
        return null != cells ? cells.size() : 0;
    }

    @Override
    public String getItem(int position) {
        if (null != months && months.size() > 0) {
            return months.get(position).getMonth() + "";
        }
        return "";
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public long getHeaderId(int position) {
        if (position == 0) {
            return -1;
        } else {
            if (null != months) {
                return months.get(position).getMonth();
            } else {
                return 1;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.moth_title_view, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
//        TextView textView = (TextView) holder.itemView;
//        textView.setText(String.valueOf(getItem(position).charAt(0)));
//        holder.itemView.setBackgroundColor(getRandomColor());
        HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
        viewHolder.calendarGrid.setDayViewAdapter(adapter);
        viewHolder.calendarGrid.setDividerColor(dividerColor);
        viewHolder.calendarGrid.setDayTextColor(dayTextColorResId);
        viewHolder.titleYears.setTextColor(titleTextColor);
        viewHolder.calendarGrid.setDisplayHeader(displayHeader);
        viewHolder.calendarGrid.setHeaderTextColor(headerTextColor);
        if (dayBackgroundResId != 0) {
            viewHolder.calendarGrid.setDayBackground(dayBackgroundResId);
        }

        final int originalDayOfWeek = today.get(Calendar.DAY_OF_WEEK);
        int firstDayOfWeek = today.getFirstDayOfWeek();
        final CalendarRowView headerRow = (CalendarRowView) viewHolder.calendarGrid.getChildAt(0);
        for (int offset = 0; offset < 7; offset++) {
            today.set(Calendar.DAY_OF_WEEK, getDayOfWeek(firstDayOfWeek, offset, this.isRtl));
            final TextView textView = (TextView) headerRow.getChildAt(offset);
            textView.setText(weekdayNameFormat.format(today.getTime()));
        }
        today.set(Calendar.DAY_OF_WEEK, originalDayOfWeek);
        if (null != months && months.size() > 0) {
            viewHolder.titleYears.setText(months.get(position).getLabel());
        }
        if (titleTypeface != null) {
            viewHolder.calendarGrid.setTypeface(titleTypeface);
        }
    }

    public class DayViewHolder extends RecyclerView.ViewHolder {
        CalendarGridView calendarGrid;

        public DayViewHolder(View itemView) {
            super(itemView);
            calendarGrid = (CalendarGridView) itemView.findViewById(R.id.calendar_grid);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView titleYears;//年月显示
        CalendarGridView calendarGrid;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            titleYears = (TextView) itemView.findViewById(R.id.title_years);
            calendarGrid = (CalendarGridView) itemView.findViewById(R.id.calendar_grid);
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
