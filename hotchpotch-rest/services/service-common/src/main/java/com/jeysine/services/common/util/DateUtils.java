package com.jeysine.services.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author jxyao
 * @create 2018-07-30 9:01
 **/
public class DateUtils {
    public static String getDateFormatter(Date date, String partten) {
        SimpleDateFormat formatter = new SimpleDateFormat(partten);
        return formatter.format(date);
    }


    public static Date getStartTimeOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return  calendar.getTime();
    }

    public static Date getEndTimeOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        return  calendar.getTime();
    }

    public static Date getStartTimeOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return  calendar.getTime();
    }

    public static Date getEndTimeOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return  calendar.getTime();
    }

    public static Long getDiffMinute(Date start, Date end) {
        long minute = (end.getTime() - start.getTime()) / (1000 * 60);
        return minute;
    }

    public static void main(String[] args) {
        Date start = new Date();
        Date end = getEndTimeOfDate(new Date());
        System.out.println(start);
        System.out.println(end);
        System.out.println(getDiffMinute(start, end));
    }
}
