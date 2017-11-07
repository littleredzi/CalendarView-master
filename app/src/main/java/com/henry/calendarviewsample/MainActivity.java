package com.henry.calendarviewsample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.henry.calendarview.DatePickerController;
import com.henry.calendarview.DayPickerView;
import com.henry.calendarview.SimpleMonthAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DayPickerView dayPickerView;
    Context context;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        dayPickerView = (DayPickerView) findViewById(R.id.dpv_calendar);

        final DayPickerView.DataModel dataModel = new DayPickerView.DataModel();
        dataModel.yearStart = 2017;
        dataModel.monthStart = 10;
        dataModel.monthCount = 2;
        dataModel.defTag = "￥100";
        dataModel.leastDaysNum = 2;
        dataModel.mostDaysNum = 100;

//        List<SimpleMonthAdapter.CalendarDay> invalidDays = new ArrayList<>();
//        SimpleMonthAdapter.CalendarDay invalidDay1 = new SimpleMonthAdapter.CalendarDay(2016, 8, 10);
//        SimpleMonthAdapter.CalendarDay invalidDay2 = new SimpleMonthAdapter.CalendarDay(2016, 8, 11);
//        SimpleMonthAdapter.CalendarDay invalidDay3 = new SimpleMonthAdapter.CalendarDay(2016, 8, 12);
//        invalidDays.add(invalidDay1);
//        invalidDays.add(invalidDay2);
//        invalidDays.add(invalidDay3);
//        dataModel.invalidDays = invalidDays;
//
        List<SimpleMonthAdapter.CalendarDay> busyDays = new ArrayList<>();
        SimpleMonthAdapter.CalendarDay busyDay1 = new SimpleMonthAdapter.CalendarDay(2017, 10, 20);
        SimpleMonthAdapter.CalendarDay busyDay2 = new SimpleMonthAdapter.CalendarDay(2017, 10, 21);
        SimpleMonthAdapter.CalendarDay busyDay3 = new SimpleMonthAdapter.CalendarDay(2017, 10, 22);
        busyDays.add(busyDay1);
        busyDays.add(busyDay2);
        busyDays.add(busyDay3);
        dataModel.busyDays = busyDays;

        SimpleMonthAdapter.CalendarDay startDay = new SimpleMonthAdapter.CalendarDay(2017, 10, 5);
        SimpleMonthAdapter.CalendarDay endDay = new SimpleMonthAdapter.CalendarDay(2017, 10, 20);
        SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays = new SimpleMonthAdapter.SelectedDays<>(startDay, endDay);
        dataModel.selectedDays = selectedDays;
//        dataModel.busyDays = selectedDays;
//        SimpleMonthAdapter.CalendarDay tag = new SimpleMonthAdapter.CalendarDay(2016, 7, 15);
//        tag.setTag("标签1");
//
//        SimpleMonthAdapter.CalendarDay tag2 = new SimpleMonthAdapter.CalendarDay(2016, 8, 15);
//        tag2.setTag("标签2");
//        List<SimpleMonthAdapter.CalendarDay> tags = new ArrayList<>();
//        tags.add(tag);
//        tags.add(tag2);
//        dataModel.tags = tags;

        dayPickerView.setParameter(dataModel, new DatePickerController() {
            @Override
            public void onDayOfMonthSelected(SimpleMonthAdapter.CalendarDay calendarDay) {
                Toast.makeText(context, "onDayOfMonthSelected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDateRangeSelected(List<SimpleMonthAdapter.CalendarDay> selectedDays) {
                Toast.makeText(context, "onDateRangeSelected", Toast.LENGTH_SHORT).show();
                for (int i = 0; i <selectedDays.size(); i++) {
                    SimpleMonthAdapter.CalendarDay calendarDay = selectedDays.get(i);
                    Date date = calendarDay.getDate();
                    int day = date.getDate();
                    Log.e("day",day+"");
                }
            }

            @Override
            public void alertSelectedFail(FailEven even) {
                Toast.makeText(context, "alertSelectedFail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
