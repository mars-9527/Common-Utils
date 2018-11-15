package com.marcel.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author J.L 对时间的一些操作工具
 */
public class DateUtils {

    public static final int YEAR = 1;
    public static final int MONTH = 2;
    public static final int DAY = 3;
    public static final int HOUR = 4;
    public static final int MINUTE = 5;
    public static final int SECOND = 6;

    /**
     * yyyy-MM-dd HH:mm:ss
     **/
    public static final String DATE_STANDARD = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd
     **/
    public static final String DATE_SIMPLE = "yyyy-MM-dd";
    /**
     * yyyy/MM/dd
     **/
    public static final String DATE_SIMPLE_BACKSLASH = "yyyy/MM/dd";
    /**
     * yyyyMMdd
     **/
    public static final String DATE_SIMPLE_NOINTERVAL = "yyyyMMdd";

    /**
     * 格式转化 YYYY-MM-DD HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String parseDateForStandard(Date date) {
        return parseDate(date, DATE_STANDARD);
    }

    /**
     * 格式转化 YYYY-MM-DD HH:mm:ss
     *
     * @param dateStr
     * @return
     */
    public static Date parseDateForStandard(String dateStr) {
        return parseDate(dateStr, DATE_STANDARD);
    }

    /**
     * 自定义模版
     *
     * @param date
     * @return
     */
    public static String parseDate(Date date, String pattern) {
        if (date == null) return "";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 格式转化 YYYY-MM-DD
     *
     * @param date
     * @return
     */
    public static String parseDateForSimple(Date date) {
        return parseDate(date, DATE_SIMPLE);
    }

    /**
     * 格式转化 YYYY-MM-DD
     *
     * @param dateStr
     * @return
     */
    public static Date parseDateForSimple(String dateStr) {
        return parseDate(dateStr, DATE_SIMPLE);
    }

    /**
     * @param date
     * @return
     * @description 格式化时间YYYYMMDD
     * @version 1.0
     * @author shower
     * @update Jun 16, 2014 12:11:54 PM
     */
    public static String parseDateForSImpleNoInterval(Date date) {
        return parseDate(date, DATE_SIMPLE_NOINTERVAL);
    }


    /**
     * @param dateStr
     * @param dateSimple
     * @return
     */
    public static Date parseDate(String dateStr, String dateSimple) {
        try {
            return new SimpleDateFormat(dateSimple).parse(dateStr);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 格式转化 YYYY/MM/DD
     *
     * @param date
     * @return
     */
    public static String parseDateForSimpleBackslash(Date date) {
        return parseDate(date, DATE_SIMPLE_BACKSLASH);
    }

    /**
     * 根据类型 返回 提前当前时间 date 前 time （如 ：3） type （如：天）
     *
     * @param type 本类常量
     * @param date 操作时间
     * @param time 提前量
     * @return
     */
    public static Date getBeforeTime(int type, Date date, int time) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        if (type == YEAR) {
            cl.set(Calendar.YEAR, cl.get(Calendar.YEAR) - time);
        } else if (type == MONTH) {
            cl.set(Calendar.MONTH, cl.get(Calendar.MONTH) - time);
        } else if (type == DAY) {
            cl.set(Calendar.DAY_OF_MONTH, cl.get(Calendar.DAY_OF_MONTH) - time);
        }
        return cl.getTime();
    }

    /**
     * 根据类型 返回 延后当前时间 date 后 time （如 ：3） type （如：天）
     *
     * @param type 本类常量
     * @param date 操作时间
     * @param time 退后量
     * @return
     */
    public static Date getEndTime(int type, Date date, int time) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        if (type == YEAR) {
            cl.add(Calendar.YEAR, time);
        } else if (type == MONTH) {
            cl.add(Calendar.MONTH, time);
        } else if (type == DAY) {
            cl.add(Calendar.DAY_OF_MONTH, time);
        } else if (type == HOUR) {
            cl.add(Calendar.HOUR, time);
        } else if (type == MINUTE) {
            cl.add(Calendar.MINUTE, time);
        } else if (type == SECOND) {
            cl.add(Calendar.SECOND, time);
        }
        return cl.getTime();
    }

    /**
     * 两个时间 之间 差的天数（两时间都将 时 分 秒 清零）
     *
     * @param d1
     * @param d2
     * @return 一个int数组 int[0]标识 d1 和 d2 的大小 d1>d2 ==1 d1<d2==-1 d1==d2==0 int[1]
     * 为 差距天数
     */
    public static int[] getDaybetweenTwoDate(Date d1, Date d2) {
        d1 = cleanDate(d1);
        d2 = cleanDate(d2);
        long day1 = d1.getTime();
        long day2 = d2.getTime();
        return getInts(day1, day2);
    }

    private static int[] getInts(long l1, long l2) {
        int[] ints = new int[2];
        long between = 0;
        if (l1 > l2) {
            between = (l1 - l2) / (24 * 60 * 60 * 1000);
            ints[0] = 1;
        } else if (l2 > l1) {
            between = (l2 - l1) / (24 * 60 * 60 * 1000);
            ints[0] = -1;
        } else {
            between = 0;
            ints[0] = 0;
        }
        if (between == 0) {
            ints[0] = 0;
        }
        ints[1] = (int) between;
        return ints;
    }

    /**
     * 两个时间 之间 差的天数（保持两时间的 时 分 秒 ）
     *
     * @param d1
     * @param d2
     * @return 一个int数组 int[0]标识 d1 和 d2 的大小 d1>d2 ==1 d1<d2==-1 d1==d2==0 int[1]
     * 为 差距天数
     */
    public static int[] getFullDaybetweenTwoDate(Date d1, Date d2) {
        long day1 = d1.getTime();
        long day2 = d2.getTime();
        return getInts(day1, day2);
    }

    /**
     * 两个时间 之间 差的天数（保持两时间的 时 分 秒 ）
     *
     * @param l1
     * @param l2
     * @return 一个int数组 int[0]标识 d1 和 d2 的大小 d1>d2 ==1 d1<d2==-1 d1==d2==0 int[1]
     * 为 差距天数
     */
    public static int[] getDaybetweenTwoDate(long l1, long l2) {
        Date d1 = new Date(l1);
        Date d2 = new Date(l2);
        d1 = cleanDate(d1);
        d2 = cleanDate(d2);
        long day1 = d1.getTime();
        long day2 = d2.getTime();
        return getInts(day1, day2);
    }

    /**
     * 两个时间 之间 差的天数
     *
     * @param l1
     * @param l2
     * @return 一个int数组 int[0]标识 d1 和 d2 的大小 d1>d2 ==1 d1<d2==-1 d1==d2==0 int[1]
     * 为 差距天数
     */
    public static int[] getFullDaybetweenTwoDate(long l1, long l2) {
        Date d1 = new Date(l1);
        Date d2 = new Date(l2);
        long day1 = d1.getTime();
        long day2 = d2.getTime();
        return getInts(day1, day2);
    }

    /**
     * 将 时 分 秒 清零
     *
     * @param date
     * @return
     */
    public static Date cleanDate(Date date) {
        Calendar cl = Calendar.getInstance();

        cl.setTime(date);
        cl.set(Calendar.HOUR_OF_DAY, 0);
        cl.set(Calendar.MINUTE, 0);
        cl.set(Calendar.SECOND, 0);
        return cl.getTime();
    }

    /**
     * 将时间 变为 此天的最后一刻
     *
     * @param date
     * @return
     */
    public static Date lastMoment(Date date) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);

        cl.set(Calendar.HOUR_OF_DAY, 23);
        cl.set(Calendar.MINUTE, 59);
        cl.set(Calendar.SECOND, 59);
        cl.set(Calendar.MILLISECOND, 0);
        return cl.getTime();
    }

    /**
     * 獲得精確的年齡
     *
     * @param birthday 生日
     * @return 計算所得的年齡
     */
    public static int getAge(Date birthday) {

        Calendar birth = Calendar.getInstance();
        if (birthday != null) {
            birth.setTime(birthday);
        } else {
            return 0;
        }
        return getAge(birth);
    }

    /**
     * 獲得精確到毫秒的年齡
     *
     * @param birth 生日
     * @return 計算所得的年齡
     */
    public static int getAge(Calendar birth) {
        Calendar now = Calendar.getInstance();
        if (birth.after(now)) {
            return -1;
        }
        if (birth.equals(now)) {
            return 0;
        }
        int age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        if (now.get(Calendar.MONTH) - birth.get(Calendar.MONTH) < 0) {
            return age - 1;
        }
        if (now.get(Calendar.MONTH) - birth.get(Calendar.MONTH) > 0) {
            return age;
        }
        if (now.get(Calendar.DAY_OF_MONTH) - birth.get(Calendar.DAY_OF_MONTH) < 0) {
            return age - 1;
        }
        if (now.get(Calendar.DAY_OF_MONTH) - birth.get(Calendar.DAY_OF_MONTH) > 0) {
            return age;
        }
        if (now.get(Calendar.HOUR_OF_DAY) - birth.get(Calendar.HOUR_OF_DAY) < 0) {
            return age - 1;
        }
        if (now.get(Calendar.HOUR_OF_DAY) - birth.get(Calendar.HOUR_OF_DAY) > 0) {
            return age;
        }
        if (now.get(Calendar.MINUTE) - birth.get(Calendar.MINUTE) < 0) {
            return age - 1;
        }
        if (now.get(Calendar.MINUTE) - birth.get(Calendar.MINUTE) > 0) {
            return age;
        }
        if (now.get(Calendar.SECOND) - birth.get(Calendar.SECOND) < 0) {
            return age - 1;
        }
        if (now.get(Calendar.SECOND) - birth.get(Calendar.SECOND) > 0) {
            return age;
        }
        if (now.get(Calendar.MILLISECOND) - birth.get(Calendar.MILLISECOND) < 0) {
            return age - 1;
        }
        if (now.get(Calendar.MILLISECOND) - birth.get(Calendar.MILLISECOND) > 0) {
            return age;
        }
        return age;
    }

    /**
     * get the months from a past time to now
     *
     * @param date
     * @return 過去到現在的月數
     */
    public static int getMonthsFromNow(Date date) {
        Calendar now = Calendar.getInstance();
        Calendar past = Calendar.getInstance();
        past.setTime(date);
        int months = now.get(Calendar.MONTH) - past.get(Calendar.MONTH);
        int years = now.get(Calendar.YEAR) - past.get(Calendar.YEAR);
        return years * 12 + months;
    }

    public static String getMonthsBeginDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);// 设为当前月的1号
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return sdf.format(calendar.getTime());
    }

    public static String getMonthsEnd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return sdf.format(calendar.getTime());
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     * @description 获得两date相差的年月日
     * @version 1.0
     * @author shower
     * @update May 14, 2014 3:23:26 PM
     * @update 2014-06-05 标的详情页右侧商户信息的合作时间精确到月即可
     */
    public static String getSubYMDStrByDates(Date startDate, Date endDate) {
        if (startDate == null) {
            startDate = new Date();
        }
        if (endDate == null) {
            endDate = new Date();
        }
        long l = endDate.getTime() - startDate.getTime();//相差毫秒数
        long sumDays = l / (24 * 60 * 60 * 1000);//相差总天数
        long years = sumDays / (12 * 30);//相差多少年
        long months = (sumDays - years * 12 * 30) / 30;//相差多少年多少月
        long days = sumDays - years * 12 * 30 - months * 30;//相差多少年多少月多少日
        if (years < 1) {
            if (months < 1) {
                if (days < 1) {
                    return "0天";//+days+"天";//相差时间
                } else {
                    return days + "天";//+days+"天";//相差时间
                }
            } else {
                if (days < 1) {
                    return months + "个月";//+days+"天";//相差时间
                } else {
                    if (months >= 11) {
                        return "1年";//+days+"天";//相差时间
                    } else {
                        return (months + 1) + "个月";//+days+"天";//相差时间
                    }
                }
            }
        } else {
            if (days < 1) {
                return years + "年" + months + "个月";//+days+"天";//相差时间
            } else {
                if (months >= 11) {
                    return (years + 1) + "年";//+days+"天";//相差时间
                } else {
                    return years + "年" + (months + 1) + "个月";//+days+"天";//相差时间
                }
            }
        }
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     * @description 获得两date相差的年月日时分秒
     * @version 1.0
     * @author shower
     * @update May 14, 2014 3:23:57 PM
     */
    public static String getSubYMDHMSStrByDates(Date startDate, Date endDate) {
        if (startDate == null) {
            startDate = new Date();
        }
        if (endDate == null) {
            endDate = new Date();
        }
        long l = endDate.getTime() - startDate.getTime();//相差毫秒数
        long sumDays = l / (24 * 60 * 60 * 1000);//相差总天数
        long years = sumDays / (12 * 30);//相差多少年
        long months = (sumDays - years * 12 * 30) / 30;//相差多少年多少月
        long days = sumDays - years * 12 * 30 - months * 30;//相差多少年多少月多少日
        long hour = (l / (60 * 60 * 1000) - sumDays * 24);//相差多少天多少小时
        long min = ((l / (60 * 1000)) - sumDays * 24 * 60 - hour * 60);//相差多少天多少小时多少分
        long s = (l / 1000 - sumDays * 24 * 60 * 60 - hour * 60 * 60 - min * 60);//相差多少天多少小时多少分多少秒
        return years + "年" + months + "月" + days + "天" + hour + "小时" + min + "分" + s + "秒";//相差时间
    }

    /**
     * 获得两date相差的时分秒
     *
     * @param startDate
     * @param endDate
     * @return
     * @version 1.0
     * @author shower
     * @update 2015年9月28日 下午2:53:47
     */
    public static long[] getSubHMSStrByDates(Date startDate, Date endDate) {
        long[] result = new long[6];
        if (startDate == null) {
            startDate = new Date();
        }
        if (endDate == null) {
            endDate = new Date();
        }
        long l = endDate.getTime() - startDate.getTime();//相差毫秒数
        long hour = l / (60 * 60 * 1000);//相差多少天多少小时
        long min = ((l / (60 * 1000)) - hour * 60);//相差多少天多少小时多少分
        long s = (l / 1000 - hour * 60 * 60 - min * 60);//相差多少天多少小时多少分多少秒
        if (hour >= 0 && hour < 10) {
            result[0] = 0;
            result[1] = hour;
        } else if (hour > 10) {
            result[0] = hour / 10;
            result[1] = hour % 10;
        } else if (hour > -10 && hour < 0) {
            result[0] = 0;
            result[1] = hour;
        } else if (hour <= -10) {
            result[0] = hour / 10;
            result[1] = hour % 10;
        }
        if (min >= 0 && min < 10) {
            result[2] = 0;
            result[3] = min;
        } else if (min >= 10) {
            result[2] = min / 10;
            result[3] = min % 10;
        } else if (min > -10 && min < 0) {
            result[2] = 0;
            result[3] = min % 10;
        } else if (min <= -10) {
            result[2] = min / 10;
            result[3] = min % 10;
        }
        if (s >= 0 && s < 10) {
            result[4] = 0;
            result[5] = s;
        } else if (s >= 10) {
            result[4] = s / 10;
            result[5] = s % 10;
        } else if (s > -10 && s <= 0) {
            result[4] = 0;
            result[5] = s % 10;
        } else if (s <= -10) {
            result[4] = s / 10;
            result[5] = s % 10;
        }
        return result;
    }

    /**
     * 得到date天上一个月的月开始时间
     * eg:传入"yyyy-MM-dd " 2017-03-21 得到2017-02-01
     **/
    public static String getLastMonthBeginTime(String simpleDateFormatExpression, Date date) {
        String beginDayOfMonth = DateUtils.getBeginDayOfMonth("yyyy-MM-dd");
        Date beginDay = DateUtils.parseDateForSimple(beginDayOfMonth);
        //往前提前一天即为上月
        Date lastMonthDay = DateUtils.getBeforeTime(3, beginDay, 1);
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat(simpleDateFormatExpression);//yyyy-MM-dd
        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(lastMonthDay); //把时间传入
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        str = sdf.format(lastDate.getTime());
        return str;
    }

    /**
     * 得到date天上一个月的月开始时间
     * eg:传入"yyyy-MM-dd " 2017-03-21 得到2017-02-28
     */
    public static String getLastMonthEndTime(String simpleDateFormatExpression, Date date) {
        String beginDayOfMonth = DateUtils.getBeginDayOfMonth(DateUtils.DATE_SIMPLE, date);
        Date beginDay = DateUtils.parseDateForSimple(beginDayOfMonth);
        //往前提前一天即为上月
        Date lastMonthDay = DateUtils.getBeforeTime(3, beginDay, 1);
        String str1 = "";
        SimpleDateFormat sdf1 = new SimpleDateFormat(simpleDateFormatExpression);//yyyy-MM-dd
        Calendar lastDate1 = Calendar.getInstance();
        lastDate1.setTime(lastMonthDay); //把时间传入
        lastDate1.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate1.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        lastDate1.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        str1 = sdf1.format(lastDate1.getTime());
        return str1;
    }

    /**
     * 得到date天这个月开始第一天 例如 2015-11-09，返回2015-11-01
     */
    public static String getBeginDayOfMonth(String simpleDateFormatExpression, Date date) {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat(simpleDateFormatExpression);//yyyy-MM-dd
        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(date); //把时间传入
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        str = sdf.format(lastDate.getTime());
        return str;
    }

    /**
     * 得到date天这个月月底最后 例如 2015-11-09，返回2015-11-31
     */
    public static String getEndDayOfMonth(String simpleDateFormatExpression, Date date) {
        String str1 = "";
        SimpleDateFormat sdf1 = new SimpleDateFormat(simpleDateFormatExpression);//yyyy-MM-dd
        Calendar lastDate1 = Calendar.getInstance();
        lastDate1.setTime(date); //把时间传入
        lastDate1.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate1.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        lastDate1.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        str1 = sdf1.format(lastDate1.getTime());
        return str1;
    }

    /**
     * 得到每个月开第一天 例如 2015-11-09，返回2015-11-01,上面也有一个方法，但是不太准确
     */
    public static String getBeginDayOfMonth(String simpleDateFormatExpression) {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat(simpleDateFormatExpression);//yyyy-MM-dd
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        str = sdf.format(lastDate.getTime());
        return str;
    }

    /**
     * 得到每个月月底最后一天例如 2015-11-09，返回2015-11-30,上面也有一个方法，但是不太准确
     */
    public static String getEndDayOfMonth(String simpleDateFormatExpression) {
        String str1 = "";
        SimpleDateFormat sdf1 = new SimpleDateFormat(simpleDateFormatExpression);//yyyy-MM-dd
        Calendar lastDate1 = Calendar.getInstance();
        lastDate1.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate1.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        lastDate1.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        str1 = sdf1.format(lastDate1.getTime());
        return str1;
    }

    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        int dayCount = cal.get(Calendar.DATE);
        return dayCount;
    }

    /**
     * 忽略时间比较日期是否相同
     *
     * @param d1
     * @param d2
     * @return
     * @author 娄建膑
     * @date 2017年2月13日
     */
    public static boolean sameDate(Date d1, Date d2) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        //fmt.setTimeZone(new TimeZone()); // 如果需要设置时间区域，可以在这里设置  
        return fmt.format(d1).equals(fmt.format(d2));
    }

/*    public static void main(String[] args) {
        System.out.println(DateUtils.getMonthsBeginDate());
        System.out.println(DateUtils.getMonthsEnd());

        Date d1 = getBeforeTime(DAY, new Date(), 1);
        Date d2 = new Date();
        long[] ls = getSubHMSStrByDates(d2, d1);
        for (long l : ls) {
            System.out.println(l);
        }
        String getLastMonthBeginTime = getLastMonthBeginTime("yyyy-MM-dd", new Date());
        String getLastMonthEndTimeTime = getLastMonthEndTime("yyyy-MM-dd", new Date());
        System.out.println(getLastMonthBeginTime);
        System.out.println(getLastMonthEndTimeTime);
		System.out.println(parseDateForStandard(getBeforeTime(Calendar.MONTH, new Date(), 24)));
    }*/
}
