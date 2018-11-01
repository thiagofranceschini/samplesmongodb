package br.com.thiago.mongodb.controller.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UrlUtils {

	public static String decodedParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
