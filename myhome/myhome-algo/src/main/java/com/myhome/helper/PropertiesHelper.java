package com.myhome.helper;


import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public final class PropertiesHelper {

	/** properties */
	private Properties properties;

	/**
	 * Constructor
	 */
	public PropertiesHelper() {
	}

	/**
	 * Load property file
	 * 
	 * @param resourcePackage
	 * @param resourceName
	 */
	public boolean loadProperties(String resource) {
		boolean result = true;
		// Load prop file
		try {
			if (properties == null) {
				final ClassLoader loader = Thread.currentThread()
						.getContextClassLoader();
				final InputStream in = loader.getResourceAsStream(resource);
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
	 * @param propName
	 * @return property
	 */
	public String getProperty(String propName) {
		return properties == null ? "" : properties.getProperty(propName);
	}

	/**
	 * Find property by key
	 * 
	 * @param propName
	 * @param params
	 * @return property
	 */
	public String getProperty(String propName, Object[] params) {
		return MessageFormat.format(getProperty(propName), params);
	}

	/**
	 * List properties keys
	 * 
	 * @return result
	 */
	public List<String> listPropertyKeys() {
		final List<String> result = new ArrayList<String>();
		final Enumeration<?> enumeration = properties.propertyNames();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			result.add(key);
		}
		return result;
	}
}