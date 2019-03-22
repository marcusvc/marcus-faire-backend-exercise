package com.faire.marcus.exercise.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZoneDateTimeUtils {
	
	private ZoneDateTimeUtils() {
	}
	
	public static String now() {
		return DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSXXX").format(ZonedDateTime.now(ZoneId.of("UTC")));
	}
	
	public static LocalDateTime toLocalDate(String zoneDateTime) {
		return ZonedDateTime.from(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSXXX").parse(zoneDateTime)).toLocalDateTime();
	}

}
