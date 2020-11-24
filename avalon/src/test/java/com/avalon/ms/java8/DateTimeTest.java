package com.avalon.ms.java8;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年9月25日 下午5:15:07
 *@version
 */
public class DateTimeTest {

	public static void main(String[] args) {
		final Clock clock  = Clock.systemUTC();
		System.out.println(clock.instant());
		System.out.println(clock.millis());
		
		final LocalDate date = LocalDate.now();
		final LocalDate dateFromClock = LocalDate.now(clock);
		System.out.println(date);
		System.out.println(dateFromClock);
		
		final LocalTime time = LocalTime.now();
		final LocalTime timeFromClock = LocalTime.now(clock);
		
		System.out.println(time);
		System.out.println(timeFromClock);
		
		final LocalDateTime dateTime = LocalDateTime.now();
		final LocalDateTime dateTimeFromClock = LocalDateTime.now(clock);
		
		System.out.println(dateTime);
		System.out.println(dateTimeFromClock);
		
		final ZonedDateTime zonedDateTime = ZonedDateTime.now();
		final ZonedDateTime zDateTimeFromClock = ZonedDateTime.now(clock);
		final ZonedDateTime zDateTimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
		
		System.out.println(zonedDateTime);
		System.out.println(zDateTimeFromClock);
		System.out.println(zDateTimeFromZone);
		
		final LocalDateTime from = LocalDateTime.of(2014,Month.APRIL, 16,0,0,0);
		final LocalDateTime to = LocalDateTime.of(2015,Month.APRIL,16,23,59,59);
		
		final java.time.Duration duration = java.time.Duration.between(from, to);
		System.out.println("duration in days "+duration.toDays());
		System.out.println("duration in hours "+duration.toHours());
		
	}
}
