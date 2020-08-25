package com.ktds.esign.client.etc;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class RangedLocalDateTime {

    private static final Logger log = LoggerFactory.getLogger(RangedLocalDateTime.class);

    @Test
    void makeRangedLocalDateTime() {
        List<LocalDateTime> localDateTimes = getLocalDateTimeList("2020-08-01 00:00:00", "2020-08-15 00:00:00");
        localDateTimes.forEach(t -> log.info("@LDT====>{}", t));
    }

    public List<LocalDateTime> getLocalDateTimeList(String startDate, String endDate) {
        final LocalDateTime start = LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        final LocalDateTime end = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        final long days = start.until(end, ChronoUnit.DAYS);
        return LongStream.rangeClosed(0, days)
                .mapToObj(start::plusDays)
                .collect(Collectors.toList());
    }


    public List<LocalDate> getDateTimeList(String startDate, String endDate) {
        final LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        final LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        final long days = start.until(end, ChronoUnit.DAYS);
        return LongStream.rangeClosed(0, days)
                .mapToObj(start::plusDays)
                .collect(Collectors.toList());
    }

}
