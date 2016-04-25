package com.squareup.timessquare.sample;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * 作者：wangdakuan
 * 主要功能:
 * 创建时间：2016/4/22 17:25
 */
public class PriceDayDecorator implements CalendarCellDecorator {
    private LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();

    @Override
    public void decorate(CalendarCellView cellView, Date date) {
        String price = hashMap.get(DateToString(date));
        if (null != cellView.getPriceTextView()) {
            if (!cellView.getPriceTextView().getText().equals("￥" + price)) {
                if (null == price || "" == price || "".equals(price)) {
                    cellView.getPriceTextView().setText("");
                } else {
                    cellView.getPriceTextView().setText("￥" + price);
                }
            }
        }
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date 日期
     * @return 日期字符串
     */
    public static String DateToString(Date date) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat("yyyy-MM-dd").format(date);
            } catch (Exception e) {
            }
        }
        return dateString;
    }

    public boolean isDate(Date date) {
        String price = hashMap.get(DateToString(date));
        return null != price && "" != price && !"".equals(price);
    }

    /**
     * 获取SimpleDateFormat
     *
     * @param parttern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
        return new SimpleDateFormat(parttern);
    }

    public void initData() {
        hashMap.put("2016-04-22", "560");
        hashMap.put("2016-04-23", "560");
        hashMap.put("2016-04-24", "700");
//        hashMap.put("2016-04-25", "460");
//        hashMap.put("2016-04-26", "490");
//        hashMap.put("2016-04-27", "560");
        hashMap.put("2016-04-28", "560");
        hashMap.put("2016-04-29", "560");
        hashMap.put("2016-04-30", "560");
        hashMap.put("2016-05-01", "560");
        hashMap.put("2016-05-02", "560");
//        hashMap.put("2016-05-03", "560");
//        hashMap.put("2016-05-04", "560");
//        hashMap.put("2016-05-05", "560");
//        hashMap.put("2016-05-06", "560");
//        hashMap.put("2016-05-07", "560");
        hashMap.put("2016-05-08", "560");
        hashMap.put("2016-05-09", "560");
        hashMap.put("2016-05-11", "560");
        hashMap.put("2016-05-12", "560");
        hashMap.put("2016-05-13", "560");
        hashMap.put("2016-05-14", "560");
        hashMap.put("2016-05-15", "560");
        hashMap.put("2016-05-16", "560");
        hashMap.put("2016-05-17", "560");
//        hashMap.put("2016-05-18", "560");
//        hashMap.put("2016-05-19", "560");
//        hashMap.put("2016-05-20", "560");
//        hashMap.put("2016-05-21", "560");
//        hashMap.put("2016-05-22", "560");
        hashMap.put("2016-05-23", "560");
        hashMap.put("2016-05-24", "560");
        hashMap.put("2016-05-25", "560");
        hashMap.put("2016-05-26", "560");
        hashMap.put("2016-05-27", "560");
        hashMap.put("2016-05-28", "560");
        hashMap.put("2016-05-29", "560");
        hashMap.put("2016-05-30", "560");
        hashMap.put("2016-05-31", "560");
        hashMap.put("2016-06-01", "560");
//        hashMap.put("2016-06-02", "560");
//        hashMap.put("2016-06-03", "560");
//        hashMap.put("2016-06-04", "560");
//        hashMap.put("2016-06-05", "560");
//        hashMap.put("2016-06-06", "560");
        hashMap.put("2016-06-07", "560");
        hashMap.put("2016-06-08", "560");
        hashMap.put("2016-06-09", "560");
        hashMap.put("2016-06-10", "560");
        hashMap.put("2016-06-11", "560");
        hashMap.put("2016-06-12", "560");
        hashMap.put("2016-06-13", "560");
        hashMap.put("2016-06-14", "560");
        hashMap.put("2016-06-15", "560");
        hashMap.put("2016-06-16", "560");
        hashMap.put("2016-06-17", "560");
        hashMap.put("2016-06-18", "560");
        hashMap.put("2016-06-19", "560");
        hashMap.put("2016-06-20", "560");
        hashMap.put("2016-06-21", "560");
        hashMap.put("2016-06-22", "560");
        hashMap.put("2016-06-23", "560");
        hashMap.put("2016-06-24", "560");
//        hashMap.put("2016-06-25", "560");
//        hashMap.put("2016-06-26", "560");
//        hashMap.put("2016-06-27", "560");
//        hashMap.put("2016-06-28", "560");
//        hashMap.put("2016-06-29", "560");
//        hashMap.put("2016-06-30", "560");
        hashMap.put("2016-07-01", "560");
        hashMap.put("2016-07-02", "560");
        hashMap.put("2016-07-03", "560");
        hashMap.put("2016-07-04", "560");
        hashMap.put("2016-07-05", "560");
        hashMap.put("2016-07-06", "560");
        hashMap.put("2016-07-07", "560");
    }
}
