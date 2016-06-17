/**
 * 
 */
package com.myhome.properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author
 */
public final class PropertiesUtils {

    /**
     * Constructor
     */
    private PropertiesUtils() {
    }

    public static Properties loadProperties(String resourceName) {
        return loadProperties(resourceName, null);

    }

    /**
     * Load property file
     * @param String resourcePackage
     * @param String resourceName
     */
    public static Properties loadProperties(String resourceName, Class clazz) {
        // Load prop file
        Properties properties = null;
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final InputStream in = loader.getResourceAsStream(resourceName);

        if (clazz == null) {
            properties = new Properties();
        } else {
            try {
                properties = (Properties) clazz.newInstance();
            } catch (Exception e1) {
                e1.printStackTrace();
                properties = new Properties();
            }
        }
        try {
            properties.load(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return properties;
    }

    public static void saveProperties(final Properties props, final String resourceName) {
        try {
            File f = new File(resourceName);
            OutputStream out = new FileOutputStream(f);
            props.store(out, "This is an optional header comment string");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
