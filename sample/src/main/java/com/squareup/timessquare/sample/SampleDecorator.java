package com.squareup.timessquare.sample;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.util.Log;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SampleDecorator implements CalendarCellDecorator {
    private List<ProductPrice> productPrices;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private Map<String, String> map;
    @Override
    public void decorate(CalendarCellView cellView, Date date) {
        String dateString = Integer.toString(date.getDate());
        date.getTime();

        Log.e("春秋旅游", "-------------|=" + map.get(formatter.format(date)));
        SpannableString string = new SpannableString(dateString + "\ntitle");
        string.setSpan(new RelativeSizeSpan(0.5f), 0, dateString.length(),
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        cellView.getDayOfMonthTextView().setText(string);
    }

    public void setData(Map<String, String> map) {
        this.map = map;
    }
}
