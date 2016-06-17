/**
 * 
 */
package com.myhome.helper;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author
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
     * @param String resourcePackage
     * @param String resourceName
     */
    public static boolean loadProperties(String resourcePackage, String resourceName) {
        boolean result = true;
        // Load prop file
        try {
            if (properties == null) {
                final ClassLoader loader = Thread.currentThread().getContextClassLoader();
                final InputStream in = loader.getResourceAsStream(resourcePackage + "/" + resourceName);
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
     * @param String propName
     * @return String property
     */
    public static String getProperty(String propName) {
        return properties.getProperty(propName);
    }

    /**
     * Find property by key
     * @param String propName
     * @param Object [] params
     * @return String property
     */
    public static String getProperty(String propName, Object[] params) {
        return MessageFormat.format(getProperty(propName), params);
    }

    public static String getMessageFromResourceBundle(Locale locale, final String key) {
        String message;
        try {
            // Récupération des properties de la Locale
            ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources", locale);

            // Si non trouvée, récupération des properties anglais
            if (resourceBundle == null) {
                // logger.info("Pas de ResourceBundle pour la locale : " + locale.toString() + " -> récupération du ResourceBundle EN");
                locale = new Locale("en");
                resourceBundle = ResourceBundle.getBundle("ApplicationResources", locale);

                // Si non trouvée, récupération
                if (resourceBundle == null) {
                    resourceBundle = ResourceBundle.getBundle("ApplicationResources");
                }
            }
            message = resourceBundle.getString(key);
        } catch (final Exception e) {
            // logger.debug(e.getMessage(), e);
            message = key;
        }
        return message;
    }

}
