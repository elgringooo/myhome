package com.myhome.helper;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * The Class StringUtils.
 */
public abstract class StringUtils {

	/** NEWLINE */
	public static final String NEWLINE = System.getProperty("line.separator");

	/** FILE_SEPARATOR */
	public static final String FILE_SEPARATOR = File.separator;

	/** TAB */
	public static final String TAB = "\t";

	/**
	 * Checks if a String is empty (blank) or null.
	 * 
	 * @param s
	 *            the string
	 * 
	 * @return true, if is value empty
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	/**
	 * Checks if a String is not blank and not null.
	 * 
	 * @param s
	 *            the string
	 * 
	 * @return true, if is value empty
	 */
	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	/**
	 * @param string
	 * @return result
	 */
	public static String notNull(String string) {
		return (string == null) ? "" : string;
	}

	/**
	 * @param file
	 * @return result
	 */
	public static String getFileExtension(File file) {
		final String fileName = file.getName();
		return fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(
				fileName.lastIndexOf(".") + 1, fileName.length());
	}

	/**
	 * @param regex
	 * @param string
	 * @return result
	 */
	public static boolean isRegular(String regex, String string) {
		return Pattern.matches(regex, string);
	}

	/**
	 * @param string
	 * @param part
	 * @return result
	 */
	public static boolean contains(String string, String part) {
		return string.matches("(?i).*" + part + ".*");
	}

	/**
	 * @param string
	 * @return result
	 */
	public static boolean unicity(String string) {
		boolean result = true;
		char[] chars = string.toCharArray();
		int count = 0;
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			for (int j = 0; j < chars.length; j++) {
				if (c == chars[j]) {
					count++;
				}
				if (count > 1) {
					result = false;
					break;
				}
			}
			count = 0;
		}
		return result;
	}

	/**
	 * @param string
	 * @param exp
	 * @return parts
	 */
	public static String[] split(String string, String exp) {
		final StringTokenizer tokens = new StringTokenizer(string, exp);
		final String[] parts = new String[tokens.countTokens()];
		int i = 0;
		while (tokens.hasMoreElements()) {
			parts[i++] = (String) tokens.nextElement();
		}
		return parts;
	}

	/**
	 * @param string
	 * @return result
	 */
	public static Integer toInteger(String string) {
		try {
			return new Integer(Integer.parseInt(string));
		} catch (final NumberFormatException ignore) {
		}
		return null;
	}

	/**
	 * @param string
	 * @return result
	 */
	public static Float toFloat(String string) {
		try {
			return new Float(Float.parseFloat(string));
		} catch (final NumberFormatException ignore) {
		}
		return null;
	}

	/**
	 * @param string
	 * @return result
	 */
	public static Double toDouble(String string) {
		try {
			return new Double(Double.parseDouble(string));
		} catch (final NumberFormatException ignore) {
		}
		return null;
	}

	/**
	 * @param string
	 * @return result
	 */
	public static boolean isBlank(String string) {
		return string == null ? true : (string.equals("") ? true : false);
	}

	/**
	 * @param date
	 * @param format
	 * @return result
	 */
	public static String dateToString(Date date, String format) {
		try {
			if (date != null) {
				return new SimpleDateFormat(format).format(date);
			}
		} catch (final IllegalArgumentException ignore) {
		}
		return null;
	}

	/**
	 * @param string
	 * @return result
	 */
	public static String reverse(String string) {
		StringBuilder buffer = new StringBuilder(string);
		buffer = buffer.reverse();
		return buffer.toString();
	}

	/**
	 * Upper case.
	 * 
	 * @param value
	 *            the value
	 * 
	 * @return the string
	 */
	public static String upperCase(String value) {
		if (value != null) {
			return value.toUpperCase();
		}
		return null;

	}

	/**
	 * Returns a copy of the string, with leading and trailing zero omitted.
	 * 
	 * @param str
	 *            the string
	 * @param c
	 *            the character
	 * 
	 * @return A copy of this string with leading and trailing zero removed, or
	 *         this string if it has no leading or trailing zero.
	 */
	public static String leftTrimCharacter(String str, char c) {

		String result = null;
		if (str != null) {
			int len = str.length();
			int st = 0;
			char[] val = str.toCharArray();
			while ((st < len) && (val[st] <= c)) {
				st++;
			}
			result = ((st > 0)) ? str.substring(st, len) : str;
		}
		return result;
	}

	/**
	 * String to boolean.
	 * 
	 * @param value
	 *            the value
	 * 
	 * @return true, if successful
	 */
	public static boolean stringToBoolean(java.lang.String value) {
		if (value == null) {
			return false;
		} else {
			return value.trim().equalsIgnoreCase("X");
		}
	}

	public static String getFileExtensionName(String NomFichier) {
		File tmpFichier = new File(NomFichier);
		tmpFichier.getName();
		int posPoint = tmpFichier.getName().lastIndexOf('.');
		if (0 < posPoint && posPoint <= tmpFichier.getName().length() - 2) {
			return tmpFichier.getName().substring(posPoint + 1);
		}
		return "";
	}

	/**
	 * Left add character.
	 * 
	 * @param stringToFormat
	 *            the string to format
	 * @param minLength
	 *            the min length
	 * @param c
	 *            the character
	 * 
	 * @return the string
	 */
	public static String leftAddCharacter(String stringToFormat, int minLength,
			char c) {

		// String result = String.valueOf(value);
		while (stringToFormat.length() < minLength) {
			stringToFormat = c + stringToFormat;
		}
		return stringToFormat;

	}

	/**
	 * Left add character.
	 * 
	 * @param stringToFormat
	 *            the string to format
	 * @param minLength
	 *            the min length
	 * @param c
	 *            the character
	 * 
	 * @return the string
	 */
	public static String leftAddCharacter(int value, int minLength, char c) {

		String result = String.valueOf(value);
		while (result.length() < minLength) {
			result = c + result;
		}
		return result;

	}

	public static int getNbOpenedDays() {
		int retard = 0;

		// Date de retour prévu

		Calendar calendar1 = new GregorianCalendar();
		calendar1.set(Calendar.YEAR, 2010);
		calendar1.set(Calendar.MONTH, 1);
		calendar1.set(Calendar.DAY_OF_MONTH, 13);
		java.util.Date date1 = calendar1.getTime();

		// Date du jour
		Calendar calendar2 = new GregorianCalendar();
		java.util.Date date2 = calendar2.getTime();
		calendar2.setTime(date2);

		System.out.println(date2);

		// Différence
		long diff = Math.abs(date2.getTime() - date1.getTime());
		long numberOfDay = (long) diff / 86400000;

		if (numberOfDay != 0) {
			for (int i = 1; i <= numberOfDay; i++) {
				if (calendar1.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
						&& calendar1.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)
					retard++;
				calendar1.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
		System.out.println("Nombre de jours de retard : " + retard);

		return retard;

	}

	public static boolean hasChange(boolean... booleanArray) {

		boolean result = false;
		for (int i = 0; i < booleanArray.length; i++) {
			result |= booleanArray[i];
		}

		return result;
	}

	/**
	 * Clean special characters.
	 * 
	 * @param pStringToBeCleaned
	 *            the string to be cleaned
	 * 
	 * @return the string
	 */
	public static String cleanSpecialCharacters(String pStringToBeCleaned) {
		StringBuffer tmp = new StringBuffer();
		char car;

		int i = 0;
		while (i < pStringToBeCleaned.length()) {
			car = pStringToBeCleaned.charAt(i);

			if (Character.isJavaIdentifierPart(car)
					&& Character.getNumericValue(car) >= 0) {
				tmp.append(car);
			} else {
				tmp.append("_");
			}
			i++;
		}

		return tmp.toString().toLowerCase();
	}

	/**
	 * Checks if is equal ignore case.
	 * 
	 * @param value
	 *            the value
	 * @param expected
	 *            the expected
	 * 
	 * @return true, if is equal
	 */
	public static boolean isEqual(String value, String expected) {
		return (isEmpty(value) && isEmpty(expected))
				|| (value != null && value.equalsIgnoreCase(expected));
	}

	/**
	 * Checks if is equal ignore case with one of array value.
	 * 
	 * @param value
	 *            the value
	 * @param expectedArray
	 *            the expected array
	 * 
	 * @return true, if is equal
	 */
	public static boolean isEqual(String value, String... expectedArray) {

		if (expectedArray.length == 0) {
			throw new IllegalArgumentException("There is no value to compare.");
		}
		boolean result = false;

		for (int i = 0; i < expectedArray.length; i++) {

			result = (isEmpty(value) && isEmpty(expectedArray[i]))
					|| (value != null && value
							.equalsIgnoreCase(expectedArray[i]));

			if (result) {
				break;
			}
		}
		return result;
	}

	/**
	 * Converts the SAP value of a "yes/no" field into a suitable portal value.
	 * 
	 * @param sapValue
	 *            the SAP value ("Y", "N" or "")
	 * 
	 * @return "yes", "no" or ""
	 */
	public static String formatYN(String sapValue) {
		String result = "";
		if (sapValue != null) {
			if (sapValue.equalsIgnoreCase("Y")) {
				result = "yes";
			}
			if (sapValue.equalsIgnoreCase("N")) {
				result = "no";
			}
		}
		return result;
	}

	/**
	 * Converts the portal value of a "yes/no" field into a suitable SAP value.
	 * 
	 * @param portalValue
	 *            the portal value
	 * 
	 * @return "Y", "N" or ""
	 */
	public static String formatYesNo(String portalValue) {
		String result = "";
		if (portalValue != null) {
			if (portalValue.equalsIgnoreCase("yes")) {
				result = "Y";
			}
			if (portalValue.equalsIgnoreCase("no")) {
				result = "N";
			}
		}
		return result;
	}

	/**
	 * Boolean to x.
	 * 
	 * @param b
	 *            the b
	 * 
	 * @return the java.lang. string
	 */
	public static java.lang.String booleanToX(boolean b) {
		if (b) {
			return "X";
		}
		return "";

	}

	/**
	 * Yes no to boolean.
	 * 
	 * @param yesNo
	 *            the yes no
	 * 
	 * @return true, if successful
	 */
	public static boolean yesNoToBoolean(java.lang.String yesNo) {
		return (yesNo != null && yesNo.equalsIgnoreCase("yes"));
	}

	/**
	 * Capitalize first letters tokenizer.
	 * 
	 * @param s
	 *            the s
	 * 
	 * @return the string
	 */
	public static String capitalizeFirstLettersTokenizer(String s,
			String limiter) {

		final StringTokenizer st = new StringTokenizer(s.toLowerCase(),
				limiter, true);
		final StringBuilder sb = new StringBuilder();

		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			token = String.format("%s%s",
					Character.toUpperCase(token.charAt(0)), token.substring(1));
			sb.append(token);
		}

		return sb.toString();

	}

	/**
	 * Transforms an list of ids into a string of ids separated by commas
	 */

	private static double round(double dd, int decimalPlace) {
		double pow = Math.pow(10, decimalPlace);
		return Math.round(dd * pow) / pow;
	}

	public static String convertStringListToSqlFormat(List<Long> ids) {
		StringBuilder listeIds = new StringBuilder();
		if (ids != null) {
			for (int i = 0; i < ids.size(); i++) {

				listeIds.append(ids.get(i));
				if (i < ids.size() - 1)
					listeIds.append(",");
			}
		}
		return listeIds.toString();
	}

	/**
	 * Array to string.
	 * 
	 * @param elements
	 *            the elements
	 * 
	 * @return the string
	 */
	public static String arrayToString(List<String> elements) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; elements != null && i < elements.size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append("'");
			sb.append(elements.get(i));
			sb.append("'");
		}
		return sb.toString();
	}

	/**
	 * Array to string.
	 * 
	 * @param elements
	 *            the elements
	 * 
	 * @return the string
	 */
	public static String arrayToString(String[] elements) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; elements != null && i < elements.length; i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append("'");
			sb.append(elements[i]);
			sb.append("'");
		}
		return sb.toString();
	}

	/**
	 * This function is used to replace NULL value with another value
	 * 
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * 
	 * @return the t
	 */
	public static <T> T nvl(T a, T b) {
		return (a == null) ? b : a;
	}

	/**
	 * This function is used to replace NULL string value with white space
	 * 
	 * @param str
	 *            the str
	 * 
	 * @return the string
	 */
	public static String nvlWS(String str) {
		return nvl(str, "");
	}

}
