package br.com.thiago.mongodb.controller.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UrlUtils {

	public static String decodedParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static Date dateConverter(String textDate, Date defaultValue) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return dateFormat.parse(textDate);
		} catch (ParseException e) {
			return defaultValue;
		}
	}
}
