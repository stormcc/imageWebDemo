package org.example.imagewebdemo.demos.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Create By: Jimmy Song
 * Create At: 2025-03-20 15:40
 */
public final class DateUtil {

    private DateUtil(){};

    // 将 LocalDateTime 转换为 Date
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        // 获取系统默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        // 将 LocalDateTime 转换为 ZonedDateTime
        java.time.ZonedDateTime zdt = localDateTime.atZone(zoneId);
        // 将 ZonedDateTime 转换为 Date
        return Date.from(zdt.toInstant());
    }

    // 将 Date 转换为 LocalDateTime
    public static LocalDateTime dateToLocalDateTime(Date date) {
        // 获取系统默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        // 将 Date 转换为 Instant
        java.time.Instant instant = date.toInstant();
        // 将 Instant 转换为 ZonedDateTime
        java.time.ZonedDateTime zdt = instant.atZone(zoneId);
        // 将 ZonedDateTime 转换为 LocalDateTime
        return zdt.toLocalDateTime();
    }

    public static Date plusHour(Date date, int hour) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        localDateTime = localDateTime.plusHours(hour);
        return localDateTimeToDate(localDateTime);
    }
}
