package com.myhome.properties;

import java.text.MessageFormat;
import java.util.Properties;
import java.util.regex.Pattern;

public class MergeProperties {

    public static void main(String[] args) {

        String blackListStr = "3xcb_l|1euro_l";

        String whileListStr = "3xcb|1euro";

        Pattern blackList = Pattern.compile(blackListStr);
        Pattern whilteList = Pattern.compile(whileListStr);

        final String locale = "fr";
        final String pattern = "{0}_{1}.properties";

        String bundleSrc = "ApplicationResources";
        String bundleTgt = "traduction";

        Properties source = PropertiesUtils.loadProperties(MessageFormat.format(pattern, new String[] { bundleSrc, locale }), CommentedProperties.class);

        Properties target = PropertiesUtils.loadProperties(MessageFormat.format(pattern, new String[] { bundleTgt, locale }), CommentedProperties.class);

        Properties sourceFiltered = new Properties();
        for (Object o : source.keySet()) {
            String s = (String) o;
            if (whilteList.matcher(s).find() && !blackList.matcher(s).find()) {
                sourceFiltered.put(s, source.get(s));
            }
        }

        System.out.println(sourceFiltered);

        for (Object o : sourceFiltered.keySet()) {
            source.remove(o);
        }

        target.putAll(sourceFiltered);

        PropertiesUtils.saveProperties(source, "src/main/resources/Application_fr_merge.properties");
        PropertiesUtils.saveProperties(target, "src/main/resources/traduction_fr_merge.properties");
    }
}
