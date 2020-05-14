package com.b3tuning.b3console.view.utils;

import lombok.extern.slf4j.XSlf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/*
 *  Created on:  Apr 16, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class TimeConverter {

	/**
	 * The suffix for plural unit
	 */
	private static final String S = "s";

	/**
	 * Used to indicate amount of unit
	 */
	private static final int ONE = 1;

	private static final String SPACE  = " ";
	private static final String DAY    = "day";
	private static final String HOUR   = "hour";
	private static final String MINUTE = "minute";
	private static final String SECOND = "second";
	private static final String MILLI  = "milli";

	/**
	 * Text for converted date
	 */
	private static final String AGO = " ago";

	/**
	 * Text for converted date for zero seconds ago
	 */
	private static final String ZERO_SECONDS_AGO = "0 seconds ago";

	/**
	 * The times map with key containing the amount of milliseconds and the
	 * value the name of the time unit
	 */
	private static Map<Long, String> times = new LinkedHashMap<>();

	static {
		times.put(millisecondsPerUnit(TimeUnit.DAYS), DAY);
		times.put(millisecondsPerUnit(TimeUnit.HOURS), HOUR);
		times.put(millisecondsPerUnit(TimeUnit.MINUTES), MINUTE);
		times.put(millisecondsPerUnit(TimeUnit.SECONDS), SECOND);
		times.put(millisecondsPerUnit(TimeUnit.MILLISECONDS), MILLI);
	}

	private static long millisecondsPerUnit(TimeUnit timeUnit) {
		return timeUnit.toMillis(ONE);
	}

	/**
	 * Gets the date in the format "x timeUnit ago"
	 *
	 * @param date the date to convert
	 * @return the string with the format x time ago
	 */
	public static String getTimeAgo(Date date) {
		if (date == null) {
			log.warn("not date provided");
			return "";
		}
		Date current  = new Date();
		long duration = current.getTime() - date.getTime();

		StringBuilder res = new StringBuilder();
		for (Entry<Long, String> time : times.entrySet()) {
			long number = duration / time.getKey();
			if (number > 0) {
				res.append(number).append(" ").append(time.getValue()).append(number != ONE
				                                                              ? S
				                                                              : "").append(AGO);
				break;
			}
		}

		if ("".equals(res.toString())) {
			return ZERO_SECONDS_AGO;
		} else {
			return res.toString();
		}
	}

	/**
	 * Gets the duration in the shortest format of
	 * "dd day(s) HH hour(s) mm minute(s) ss.SSS seconds"
	 *
	 * @param duration the Duration to convert
	 * @return the string with the format "dd day(s) HH hour(s) mm minute(s) ss.SSS seconds"
	 * <p>
	 * Example : 1 day 14 hours 3.234 seconds
	 * Example : 3 hours 1 minute 0.012 seconds
	 */
	public static String getReadableDuration(Duration duration) {
		if (duration == null) {
			log.warn("not date provided");
			return "";
		}

		long d       = duration.toMillis();
		long days    = TimeUnit.MILLISECONDS.toDays(d);
		long hours   = TimeUnit.MILLISECONDS.toHours(d) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(d));
		long minutes = TimeUnit.MILLISECONDS.toMinutes(d) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(d));
		long seconds =
				TimeUnit.MILLISECONDS.toSeconds(d) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(d));

		StringBuilder res = new StringBuilder();

		if (days > 0) {
			assembleReadableUnit(res, days, DAY);
		}
		if (hours > 0) {
			assembleReadableUnit(res, hours, HOUR);
		}
		if (minutes > 0) {
			assembleReadableUnit(res, minutes, MINUTE);
		}
		if (seconds > 0) {
			assembleReadableUnit(res, seconds, SECOND);
		}
		return res.toString();
	}

	private static void assembleReadableUnit(StringBuilder res, long number, String unit) {
		res.append(number).append(SPACE).append(unit).append(number != ONE
		                                                     ? S
		                                                     : "").append(SPACE);
	}

	public static Duration getDuration(Date startTime, Date endTime) {
		if (startTime != null && endTime != null) {
			return Duration.between(startTime.toInstant().adjustInto(Instant.EPOCH),
			                        endTime.toInstant().adjustInto(Instant.EPOCH));
		}
		return null;
	}
}