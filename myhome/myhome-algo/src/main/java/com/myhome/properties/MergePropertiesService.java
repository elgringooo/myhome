package com.myhome.properties;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class MergePropertiesService {

    final String PATTERN_PROPERTIES_FILES = "{0}_{1}.properties";

    public static void main(String[] args) {

        String[] locales = { "cs", "da", "de", "el", "en", "es", "et", "fi", "fr", "he", "hu", "it", "nl", "no", "pl", "pt", "ru", "sk", "sv", "uk" };

        List<String> whileListWord = Arrays.asList("3xcb", "1euro", "4xcb","unEuro");
        List<String> blackListWord = Arrays.asList("3xcb_l", "1euro_l");
        String bundleSrc = "ApplicationResources";
        String bundleTgt = "traduction";
        MergePropertiesService service = new MergePropertiesService();

        for (String locale : locales) {
            service.merge(bundleSrc, bundleTgt, whileListWord, blackListWord, locale);
        }
    }

    /**
     * @param sourceBundleName
     * @param targetBundleName
     * @param whileListWord
     * @param blackListWord
     * @param locale
     */
    public void merge(String sourceBundleName, String targetBundleName, List<String> whileListWord, List<String> blackListWord, final String locale) {

        Pattern whilteList = Pattern.compile(StringUtils.join(whileListWord, '|'));
        Pattern blackList = Pattern.compile(StringUtils.join(blackListWord, '|'));

        Properties source = PropertiesUtils.loadProperties(buildPropertyFileName(sourceBundleName, locale), CommentedProperties.class);

        Properties target = PropertiesUtils.loadProperties(buildPropertyFileName(targetBundleName, locale), CommentedProperties.class);

        if (source != null) {
            Properties sourceFiltered = new Properties();
            for (Object o : source.keySet()) {
                String s = (String) o;
                if (whilteList.matcher(s).find() && !blackList.matcher(s).find()) {
                    sourceFiltered.put(s, source.get(s));
                }
            }

            for (Object o : sourceFiltered.keySet()) {
                source.remove(o);
            }

            target.putAll(sourceFiltered);

            PropertiesUtils.saveProperties(source, "src/main/resources/merge/app/" + buildPropertyFileName(sourceBundleName, locale));
        }
        if (target != null) {
            PropertiesUtils.saveProperties(target, "src/main/resources/merge/trad/" + buildPropertyFileName(targetBundleName, locale));
        }
        System.out.println("done");
    }

    /**
     * @param sourceBundleName
     * @param locale
     * @return
     */
    private String buildPropertyFileName(String sourceBundleName, final String locale) {
        return MessageFormat.format(PATTERN_PROPERTIES_FILES, new String[] { sourceBundleName, locale });
    }
}
