package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class ChangeDate {
	public long changeDate(String input) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
		LocalDate now = LocalDate.now();
		Date secondDate = sdf.parse(input);
		Date firstDate = new Date(java.sql.Date.valueOf(now).getTime());
		long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
}
