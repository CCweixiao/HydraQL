package com.hydraql.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author leojie 2021/6/13 8:52 下午
 */
public final class DateAndTimeUtil {
    public static final String MS_FORMAT = "yyyy-MM-dd_HH:mm:ss:SSS";
    public static final String SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String MINUTE_FORMAT = "yyyy-MM-dd_HH:mm";
    public static final String HOUR_FORMAT = "yyyy-MM-dd_HH";
    public static final String DAY_FORMAT = "yyyy-MM-dd";


    public static String parseDatetimeToStr(LocalDateTime localDateTime){
        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern(SECOND_FORMAT);
        return dateTimeFormatter.format(localDateTime);
    }
    public static String parseTimestampToTimeStr(long timestamp){
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zone);

        return parseDatetimeToStr(dateTime);
    }

    public static void main(String[] args) {
        System.out.println(parseTimestampToTimeStr(1623586470408L));
    }


}
