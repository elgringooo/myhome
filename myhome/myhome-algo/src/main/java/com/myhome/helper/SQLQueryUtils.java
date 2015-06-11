package com.myhome.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class SQLQueryUtils.
 */
public class SQLQueryUtils {

	// Class used to append parameters so as to be able to execute an sql query
	// based on those parameters

	/**
	 * Transforms an list of ids into a string of ids separated by commas.
	 * 
	 * @param ids
	 *            the ids
	 * 
	 * @return the string
	 */

	public static String convertLongListToSqlFormat(List<Long> ids) {
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
	 * Transforms a list of ids into a string of ids separated by commas and
	 * betweek brackets
	 */
	public static String convertToSqlFormat_IN(ArrayList<Long> Ids) {
		String listeIds = "(";
		if (Ids != null && Ids.size() > 0) {
			for (int i = 0; i < Ids.size(); i++) {
				listeIds += Ids.get(i).longValue();
				if (i < Ids.size() - 1)
					listeIds += ",";
			}
		} else {
			listeIds += "''";
		}
		listeIds += ")";

		return listeIds;
	}

	/**
	 * Transforms an array of ids into a string of ids separated by commas and
	 * betweek brackets
	 */
	public static String convertToSqlFormat_IN(Long[] Ids) {
		String listeIds = "(";
		if (Ids != null && Ids.length > 0) {
			for (int i = 0; i < Ids.length; i++) {
				listeIds += Ids[i].longValue();
				if (i < Ids.length - 1)
					listeIds += ",";
			}
		} else {
			listeIds += "''";
		}
		listeIds += ")";

		return listeIds;
	}

	/**
	 * Transforms an array of ids into a string of ids separated by commas and
	 * betweek brackets
	 */
	public static String convertToSqlFormat_IN(String[] Ids) {
		String listeIds = "(";
		if (Ids != null && Ids.length > 0) {
			for (int i = 0; i < Ids.length; i++) {
				listeIds += "'";
				listeIds += Ids[i];
				listeIds += "'";
				if (i < Ids.length - 1)
					listeIds += ",";
			}
		} else {
			listeIds += "''";
		}
		listeIds += ")";

		return listeIds;
	}

	/**
	 * Transforms an arraylist of ids into a string of ids separated by commas
	 * and betweek brackets
	 */

	public static String convertStringListToSqlFormat(List<String> Ids) {
		String listeIds = "(";
		if (Ids != null && Ids.size() > 0) {
			for (int i = 0; i < Ids.size(); i++) {
				listeIds += "'";
				listeIds += Ids.get(i);
				listeIds += "'";
				if (i < Ids.size() - 1)
					listeIds += ",";
			}
		} else {
			listeIds += "''";
		}
		listeIds += ")";

		return listeIds;
	}
}
