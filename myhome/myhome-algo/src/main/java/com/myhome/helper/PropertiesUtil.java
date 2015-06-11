/**
 * 
 */
package com.myhome.helper;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * @author 
 * 
 */
public final class PropertiesUtil {

	/** Properties properties */
	private static Properties properties;

	/**
	 * Constructor
	 */
	private PropertiesUtil() {
	}

	/**
	 * Load property file
	 * 
	 * @param String
	 *            resourcePackage
	 * @param String
	 *            resourceName
	 */
	public static boolean loadProperties(String resourcePackage,
			String resourceName) {
		boolean result = true;
		// Load prop file
		try {
			if (properties == null) {
				final ClassLoader loader = Thread.currentThread()
						.getContextClassLoader();
				final InputStream in = loader
						.getResourceAsStream(resourcePackage + "/"
								+ resourceName);
				properties = new Properties();
				properties.load(in);
			}
		} catch (final Exception e) {
			result = false;
		}
		return result;
	}

	/**
	 * Find property by key
	 * 
	 * @param String
	 *            propName
	 * @return String property
	 */
	public static String getProperty(String propName) {
		return properties.getProperty(propName);
	}

	/**
	 * Find property by key
	 * 
	 * @param String
	 *            propName
	 * @param Object
	 *            [] params
	 * @return String property
	 */
	public static String getProperty(String propName, Object[] params) {
		return MessageFormat.format(getProperty(propName), params);
	}
}