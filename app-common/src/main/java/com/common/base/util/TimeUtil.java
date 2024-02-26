package com.common.base.util;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 所有时间相隔的工具类
 *
 * @Description:
 * @author:Heshuanghua create at 2016-10-15
 */
public class TimeUtil {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.CHINA);
    public static String dateFormat_month = "MM-dd";  //格式化月份
    public static String dateFormat_day = "HH:mm";

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String genBillNum() {
        return simpleDateFormat.format(new Date());
    }

    /**
     * 时间转换成字符串,默认为"yyyy-MM-dd HH:mm:ss"
     *
     * @param time 时间
     */
    public static String dateToString(long time) {
        return dateToString(time, "yyyy.MM.dd HH:mm");
    }

    /**
     * 时间转换成字符串,指定格式
     *
     * @param time   时间
     * @param format 时间格式
     */
    public static String dateToString(long time, String format) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取系统当前日期
     *
     * @return
     */
    public static String GetNowDate() {
        String temp_str = "";
        Date dt = new Date();
        // 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        temp_str = sdf.format(dt);
        return temp_str;
    }


    public static Date stringToDate(String dateString) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateValue = simpleDateFormat.parse(dateString, position);
        return dateValue;
    }


    /**
     * 获取前n天日期、后n天日期
     *
     * @param distanceDay 前几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @return
     */
    public static String getAnyDate(int distanceDay) {
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;

        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("输出日期" + dft.format(endDate));
        return dft.format(endDate);
    }


    public static String GetNowTime() {
        String temp_str = "";
        Date dt = new Date();
        // 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        temp_str = sdf.format(dt);
        return temp_str;
    }

    /**
     * 获取今天的年
     *
     * @return
     */
    public static String GetNowYear() {
        String temp_str = "";
        Date dt = new Date();
        // 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        temp_str = sdf.format(dt);
        return temp_str;
    }

    /**
     * 获取今天的天
     *
     * @return
     */
    public static String GetNowDay() {
        String temp_str = "";
        Date dt = new Date();
        // 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        temp_str = sdf.format(dt);
        return temp_str;
    }

    /**
     * 获取今天的月
     *
     * @return
     */
    public static String GetNowMonth() {
        String temp_str = "";
        Date dt = new Date();
        // 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        temp_str = sdf.format(dt);
        return temp_str;
    }

    /**
     * 获取明天的时间
     *
     * @return
     */
    public static String GetTomorrow() {
        Date date = new Date();// 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取任意时间后num天的时间
     *
     * @param date java.util.Date
     * @param num
     * @return
     */
    public static String nextDate(String date, int num) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cla = null;
        try {
            Date dt1 = df.parse(date);
            cla = Calendar.getInstance();
            cla.setTime(dt1);
            cla.add(Calendar.DAY_OF_YEAR, num);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(cla.getTime());
        return dateString;
    }

    /**
     * 获取星期
     *
     * @return
     */
    public static String getWeek(String temp_str) {
        // 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        try {
            Date dt1 = sdf.parse(temp_str);
            cal.setTime(dt1);
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0)
                w = 0;
            return weekDays[w];
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "星期日";
    }


    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate2(String dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = sdf.parse(dt);
            cal.setTime(dt1);
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0)
                w = 0;
            return weekDays[w];
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "星期日";
    }

    /**
     * 获取当前日期是第几周
     *
     * @param dt
     * @return 当前日期是第几周
     */
    public static String getWeekOfyear(String dt) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = sdf.parse(dt);
            cal.setTime(dt1);
            return cal.get(Calendar.WEEK_OF_YEAR) + "";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "24";
    }

    /**
     * 检查数据正确
     *
     * @return
     */
    public static String checkData(String year, String month, String day, String hour, String minute) {
        String year_text = year;
        if (year_text == null || year_text.length() == 0) {
            // return "请输入年份信息";
            return "请选择出生日期";
        }
        int syear = Integer.parseInt(year_text);
        if (syear > 2028 || syear < 1936) {
            // return "请输入正确的年份信息";
            return "请选择出生日期";
        }

        String smonth_text = month;
        if (smonth_text == null || smonth_text.length() == 0) {
            // return "请输入正确的月份信息";
            return "请选择出生日期";
        }
        int smonth = Integer.parseInt(smonth_text);
        if (smonth > 12 || smonth < 0) {
            // return "请输入正确的月份信息";
            return "请选择出生日期";
        }
        String day_text = day;
        if (day_text == null || day_text.length() == 0) {
            // return "请输入正确的日份信息";
            return "请选择出生日期";
        }
        int sday = Integer.parseInt(day_text);
        if (sday > 31 || sday < 0) {
            // return "请输入正确的日份信息";
            return "请选择出生日期";
        }

        String hour_text = hour;
        if (hour_text == null || hour_text.length() == 0) {
            // return "请输入正确的小时信息";
            return "请选择出生日期";
        }
        int shour = Integer.parseInt(hour_text);
        if (shour > 23 || shour < 0) {
            // return "请输入正确的小时信息";
            return "请选择出生日期";
        }

        String minute_text = minute;
        if (minute_text == null || minute_text.length() == 0) {
            // return "请输入正确的分钟信息";
            return "请选择出生日期";
        }
        int sminute = Integer.parseInt(minute_text);
        if (sminute > 59 || sminute < 0) {
            // return "请输入正确的分钟信息";
            return "请选择出生日期";
        }
        return null;
    }


    /**
     * 获取季节
     *
     * @param _month
     * @return
     */
    public static String getJijie(int _month) {
        String jijie = "春";
        if (_month <= 5 && _month >= 3) {
            jijie = "春";
        } else if (_month <= 8 && _month >= 6) {
            jijie = "夏";
        } else if (_month <= 11 && _month >= 9) {
            jijie = "秋";
        } else if (_month == 12 || _month == 1 || _month == 2) {
            jijie = "冬";
        }
        return jijie;
    }

    /**
     * 获取时间间隔
     *
     * @param nowTime
     * @param time
     * @return
     */
    public static String getTimeInterval(String nowTime, String time) {
        String timeInterval = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Date now = df.parse(nowTime);

            Date date = df.parse(time);

            long l = now.getTime() - date.getTime();
            long min = l / (60 * 1000);

            long hour = min / 60;

            long day = hour / 24;

            long month = day / 31;

            long year = day / 365;

            timeInterval = "1分钟前";
            if (year > 0) {
                timeInterval = year + "年前";
            } else if (month > 0) {
                timeInterval = month + "月前";
            } else if (day > 0) {
                timeInterval = day + "天前";
            } else {
                if (hour > 0) {
                    timeInterval = hour + "小时前";
                } else if (min > 0) {
                    timeInterval = min + "分钟前";
                }
            }
        } catch (Exception e) {
        }
        return timeInterval;
    }


    public static boolean isNight(String sunrise, String sunset) {
        if (TextUtils.isEmpty(sunrise) || TextUtils.isEmpty(sunset))
            return false;

        int sunRiseH = Integer.parseInt(sunrise.split(":")[0]);
        int sunRiseM = Integer.parseInt(sunrise.split(":")[1]);
        int sunDownH = Integer.parseInt(sunset.split(":")[0]);
        int sunDownM = Integer.parseInt(sunset.split(":")[1]);
        Calendar calendar = Calendar.getInstance();
        long now = calendar.getTimeInMillis();
        //Log.d("now", calendar.getTime().toString());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, sunRiseH);
        calendar.set(Calendar.MINUTE, sunRiseM);
        // Log.d("start", calendar.getTime().toString());
        long start = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY, sunDownH);
        calendar.set(Calendar.MINUTE, sunDownM);
        long end = calendar.getTimeInMillis();
        // Log.d("end", calendar.getTime().toString());
        boolean isNight = false;
        if (now > end || now < start) {
            isNight = true;
        }
        return isNight;
    }

    /**
     * 计算时间差
     *
     * @param starTime
     * @param endTime
     * @return 返回时间差 毫秒值
     */
    public static String getTimeDifference(String starTime, String endTime) {
        String timediffer = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endTime);
            long diff = parse1.getTime() - parse.getTime();
            timediffer = diff + "";

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timediffer;
    }

    public static long getTimeGap(String starTime, String endTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long diff = 0;
        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endTime);
            diff = parse1.getTime() - parse.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

    /**
     * 计算时间差(精确到分)
     *
     * @param starTime
     * @param endTime
     * @return 返回时间差 毫秒值
     */
    public static String getTimeMinutesDifference(String starTime, String endTime) {
        String timediffer = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endTime);
            long diff = parse1.getTime() - parse.getTime();
            timediffer = diff + "";

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timediffer;

    }

    /**
     * 获取指定格式的系统当前时间
     *
     * @return
     */
    public static String getNowTimeName() {
        String temp_str = "";
        Date dt = new Date();
        // 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
        temp_str = sdf.format(dt);
        return temp_str;
    }

}
