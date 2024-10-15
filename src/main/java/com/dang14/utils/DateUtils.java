package com.dang14.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 *
 * @author wuxiongdeng
 * @date 2020-05-25
 */
public class DateUtils {

    /**
     * 获取时间戳
     *
     * @return
     */
    public static String getTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * 把datetime转化为string
     *
     * @param d
     * @return
     */
    public static String formatDateTime(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fd = sdf.format(d);
        return fd;
    }

    public static String formatDate(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fd = sdf.format(d);
        return fd;
    }

    /**
     * 把string转化为datetime
     *
     * @param source
     * @return
     */
    public static Date parseDateTime(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = null;
        try {
            dt = sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }

    public static Date parseDate(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;
        try {
            dt = sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }

    public static String timeDifference(Date date1, Date date2) {
        long leftTime = date1.getTime() - date2.getTime();
        long sTime = leftTime / 1000;//时间差，单位：秒
        long mTime = sTime / 60;
        long hTime = mTime / 60;
        return hTime % 24 + "时" + mTime % 60 + "分";
    }

    /*
        格式化时间
     */
    public static String getFormatTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    //时间加多少天
    public static Date addDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    public static Date minusDay(Date date, int day) {
        return addDay(date, -day);
    }

    /**
     * 比较两个时间相差几天
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static int dayCompare(Date fromDate, Date toDate) {
        Calendar from = Calendar.getInstance();
        from.setTime(fromDate);
        Calendar to = Calendar.getInstance();
        to.setTime(toDate);
        return (int) ((to.getTimeInMillis() - from.getTimeInMillis()) / (24 * 3600 * 1000));
    }


    /**
     * 获取昨天的年月日
     */
    public static String getLastDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date d = cal.getTime();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
        String lastday = sp.format(d);//获取昨天日期
        return lastday;
    }

    /**
     * 获取过去或者未来 任意天内的日期数组
     *
     * @param intervals intervals天内
     * @return 日期数组
     */
    public static ArrayList<String> test(int intervals) {
        ArrayList<String> pastDaysList = new ArrayList<String>();
        ArrayList<String> fetureDaysList = new ArrayList<String>();
        for (int i = 0; i < intervals; i++) {
            pastDaysList.add(getPastDate(i));
            fetureDaysList.add(getFetureDate(i));
        }
        return pastDaysList;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取未来 第 past 天的日期
     *
     * @param past
     * @return
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取两个时间段内的所有时间
     *
     * @param begin
     * @param end
     * @return
     */
    public static List<String> getBetweenDate(String begin, String end) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<String> betweenList = new ArrayList<String>();

        try {
            Calendar startDay = Calendar.getInstance();
            startDay.setTime(format.parse(begin));
            startDay.add(Calendar.DATE, -1);

            while (true) {
                startDay.add(Calendar.DATE, 1);
                Date newDate = startDay.getTime();
                String newend = format.format(newDate);
                betweenList.add(newend);
                if (end.equals(newend)) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return betweenList;
    }

    /**
     * 秒转时分秒
     */
    public static String secondsToTime(int seconds){
        int h = seconds / 3600;
        int m = (seconds % 3600) / 60;
        int s = (seconds % 3600) % 60;
        if (h > 0) {
            return h + ":" + m + ":" + s;
        }
        if (m > 0) {
            return "00:" + m + ":" + s;
        }
        return "00:00:" + s;
    }
}
