package com.homefood.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeParser {

	public LocalDateTime parseString(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
		System.out.println("*****************************" + localDateTime + "*************************************");
		return localDateTime;
	}
}
