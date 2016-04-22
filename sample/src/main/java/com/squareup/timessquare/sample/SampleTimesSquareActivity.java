package com.squareup.timessquare.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.SelectionMode;
import com.squareup.timessquare.DayBackForthDayViewAdapter;
import com.squareup.timessquare.DefaultDayViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static android.widget.Toast.LENGTH_SHORT;

public class SampleTimesSquareActivity extends Activity {
    private static final String TAG = "SampleTimesSquareActivi";
    private CalendarPickerView calendar;
    private AlertDialog theDialog;
    private CalendarPickerView dialogView;
    private final Set<Button> modeButtons = new LinkedHashSet<Button>();

    private List<ProductPrice> prices = new ArrayList<>();
    private Map<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_calendar_picker);
        setDateList();
        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);

        Calendar today = Calendar.getInstance();

        today.add(Calendar.DATE, 1);

        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        calendar.setCustomDayView(new SampleDayViewAdapter());
        calendar.init(lastYear.getTime(), nextYear.getTime()) //
                .inMode(SelectionMode.SINGLE) //
                .withSelectedDate(new Date())
                .withHighlightedDate(today.getTime());
        initButtonListeners(nextYear, lastYear);
    }

    public void setDateList() {
        map.put("2016-04-20", "110");
        map.put("2016-04-20", "110");
        map.put("2016-04-21", "120");
        map.put("2016-04-22", "130");
        map.put("2016-04-23", "150");
        map.put("2016-04-24", "180");
        map.put("2016-04-25", "170");
        map.put("2016-04-26", "190");
        map.put("2016-04-27", "150");
        map.put("2016-04-28", "1010");
        map.put("2016-04-29", "109");
        map.put("2016-04-30", "1005");
        map.put("2016-05-01", "120");
        map.put("2016-05-02", "1080");
        map.put("2016-05-03", "105");
        map.put("2016-05-04", "109");
        map.put("2016-05-05", "104");
        map.put("2016-05-06", "105");
        map.put("2016-05-07", "104");
        map.put("2016-05-08", "107");
        map.put("2016-05-09", "103");
        map.put("2016-05-10", "101");
        map.put("2016-05-11", "180");
        map.put("2016-05-12", "150");
        map.put("2016-05-13", "1060");
        map.put("2016-05-14", "104");
        map.put("2016-05-15", "180");
        map.put("2016-05-16", "180");
        map.put("2016-05-17", "150");
        map.put("2016-05-18", "1050");
        map.put("2016-05-19", "15");
        map.put("2016-05-20", "190");
        map.put("2016-05-21", "110");
    }

    private void initButtonListeners(final Calendar nextYear, final Calendar lastYear) {
        final Button single = (Button) findViewById(R.id.button_single);
        final Button multi = (Button) findViewById(R.id.button_multi);
        final Button range = (Button) findViewById(R.id.button_range);
        final Button displayOnly = (Button) findViewById(R.id.button_display_only);
        final Button dialog = (Button) findViewById(R.id.button_dialog);
        final Button customized = (Button) findViewById(R.id.button_customized);
        final Button decorator = (Button) findViewById(R.id.button_decorator);
        final Button hebrew = (Button) findViewById(R.id.button_hebrew);
        final Button arabic = (Button) findViewById(R.id.button_arabic);
        final Button customView = (Button) findViewById(R.id.button_custom_view);

        modeButtons.addAll(Arrays.asList(single, multi, range, displayOnly, decorator, customView));

        single.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonsEnabled(single);

                calendar.setCustomDayView(new SampleDayViewAdapter());
                calendar.setDecorators(Arrays.<CalendarCellDecorator>asList());
                calendar.init(new Date(), nextYear.getTime()) //
                        .inMode(SelectionMode.SINGLE) //
                        .withSelectedDate(new Date());
            }
        });

        multi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonsEnabled(multi);

                calendar.setCustomDayView(new DefaultDayViewAdapter());
                Calendar today = Calendar.getInstance();
                ArrayList<Date> dates = new ArrayList<Date>();
                for (int i = 0; i < 5; i++) {
                    today.add(Calendar.DAY_OF_MONTH, 3);
                    dates.add(today.getTime());
                }
                calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
                calendar.init(new Date(), nextYear.getTime()) //
                        .inMode(SelectionMode.MULTIPLE) //
                        .withSelectedDates(dates);
            }
        });

        range.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonsEnabled(range);

                calendar.setCustomDayView(new DayBackForthDayViewAdapter());
                Calendar today = Calendar.getInstance();
                ArrayList<Date> dates = new ArrayList<Date>();
                today.add(Calendar.DATE, 3);
                dates.add(today.getTime());
                today.add(Calendar.DATE, 5);
                dates.add(today.getTime());
                calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
                calendar.init(new Date(), nextYear.getTime()) //
                        .inMode(SelectionMode.RANGE) //
                        .withSelectedDates(dates)
                        .withHighlightedDates(calendar.getlunarCalendarDateList());
            }
        });

        displayOnly.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonsEnabled(displayOnly);

                calendar.setCustomDayView(new DefaultDayViewAdapter());
                calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
                calendar.init(new Date(), nextYear.getTime()) //
                        .inMode(SelectionMode.SINGLE) //
                        .withSelectedDate(new Date()) //
                        .displayOnly();
            }
        });

        dialog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "I'm a dialog!";
                showCalendarInDialog(title, R.layout.dialog);
                dialogView.init(lastYear.getTime(), nextYear.getTime()) //
                        .withSelectedDate(new Date());
            }
        });

        customized.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendarInDialog("Pimp my calendar!", R.layout.dialog_customized);
                dialogView.init(lastYear.getTime(), nextYear.getTime())
                        .withSelectedDate(new Date());
            }
        });

        decorator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonsEnabled(decorator);

                calendar.setCustomDayView(new DefaultDayViewAdapter());
                calendar.setDecorators(Arrays.<CalendarCellDecorator>asList(new SampleDecorator()));
                calendar.init(lastYear.getTime(), nextYear.getTime()) //
                        .inMode(SelectionMode.SINGLE) //
                        .withSelectedDate(new Date());
            }
        });

        hebrew.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendarInDialog("I'm Hebrew!", R.layout.dialog);
                dialogView.init(lastYear.getTime(), nextYear.getTime(), new Locale("iw", "IL")) //
                        .withSelectedDate(new Date());
            }
        });

        arabic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendarInDialog("I'm Arabic!", R.layout.dialog);
                dialogView.init(lastYear.getTime(), nextYear.getTime(), new Locale("ar", "EG")) //
                        .withSelectedDate(new Date());
            }
        });

        customView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonsEnabled(customView);

                calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
                calendar.setCustomDayView(new SampleDayViewAdapter());
                calendar.init(lastYear.getTime(), nextYear.getTime())
                        .inMode(SelectionMode.SINGLE)
                        .withSelectedDate(new Date());
            }
        });

        findViewById(R.id.done_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Selected time in millis: " + calendar.getSelectedDate().getTime());
                String toast = "Selected: " + calendar.getSelectedDate().getTime();
                Toast.makeText(SampleTimesSquareActivity.this, toast, LENGTH_SHORT).show();
            }
        });
    }

    private void showCalendarInDialog(String title, int layoutResId) {
        dialogView = (CalendarPickerView) getLayoutInflater().inflate(layoutResId, null, false);
        theDialog = new AlertDialog.Builder(this) //
                .setTitle(title)
                .setView(dialogView)
                .setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        theDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Log.d(TAG, "onShow: fix the dimens!");
                dialogView.fixDialogDimens();
            }
        });
        theDialog.show();
    }

    private void setButtonsEnabled(Button currentButton) {
        for (Button modeButton : modeButtons) {
            modeButton.setEnabled(modeButton != currentButton);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        boolean applyFixes = theDialog != null && theDialog.isShowing();
        if (applyFixes) {
            Log.d(TAG, "Config change: unfix the dimens so I'll get remeasured!");
            dialogView.unfixDialogDimens();
        }
        super.onConfigurationChanged(newConfig);
        if (applyFixes) {
            dialogView.post(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "Config change done: re-fix the dimens!");
                    dialogView.fixDialogDimens();
                }
            });
        }
    }
}
