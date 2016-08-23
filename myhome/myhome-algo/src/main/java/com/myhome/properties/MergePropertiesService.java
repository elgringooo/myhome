package com.myhome.properties;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class MergePropertiesService {

    public final static String PATTERN_PROPERTIES_FILES = "{0}_{1}.properties";

    public static void main(String[] args) {

        MergePropertiesService service = new MergePropertiesService();

        try {
            service.merge();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * @param service
     * @throws UnsupportedEncodingException 
     */
    public void merge() throws UnsupportedEncodingException {
        String stringUTF8  ="mýtné";
        String stringISO = new String ( stringUTF8.getBytes(), "ISO-8859-1" );
        
        System.out.println(stringISO);
        
        String[] locales =
            { "default", "cs", "da", "de", "el", "en", "es", "et", "fi", "fr", "he", "hu", "it", "nl", "no", "pl", "pt", "ru", "sk", "sv", "uk" };

        String[] repertoires = { "admin", "batch", "tpev", "webp", "widget" };
        List<String> whileListWord = Arrays.asList("3xcb", "1euro", "4xcb", "uneuro", "troisXcb");
        List<String> blackListWord = Arrays.asList("3xcb_l", "1euro_l");
        String bundleSrc = "ApplicationResources";
        String bundleTgt = "traduction";

        for (String locale : locales) {
            String targetBundleName = buildPropertyFileName(bundleTgt, locale);
            Properties target = PropertiesUtils.loadProperties(targetBundleName, OrderProperties.class);
            if (target == null) {
                target = new Properties();
            }
            for (String rep : repertoires) {
                String src = "source" + "/" + rep + "/" + bundleSrc;
                final String finalSourceBundleName = buildPropertyFileName(src, locale);
                Properties source = PropertiesUtils.loadProperties(finalSourceBundleName, OrderProperties.class);
                mergeProperties(source, target, whileListWord, blackListWord);
                cleanFile(finalSourceBundleName, whileListWord, blackListWord);

            }

            PropertiesUtils.saveProperties(target, "src/main/resources/target/" + targetBundleName);
        }
    }

    private void cleanFile(String resourceName, List<String> whileListWord, List<String> blackListWord) {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final InputStream in = loader.getResourceAsStream(resourceName);
        if (in != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String read;
            List<String> lines = new ArrayList<String>();

            try {
                while ((read = br.readLine()) != null) {
                    lines.add(read);
                    lines.add("\n");
                }
                String name = "src/main/resources/" + resourceName.replaceFirst("source", "target");
                // Path file = Paths.get(name);
                // Files.write(file, lines, Charset.forName("ISO-8859-1"));

                FileWriter writer = new FileWriter(name);
                for (String str : lines) {
                    writer.write(str    );
                }
                writer.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * @param sourceBundleName
     * @param targetBundleName
     * @param whileListWord
     * @param blackListWord
     * @param locale
     */
    private void mergeProperties(Properties source, Properties target, List<String> whileListWord, List<String> blackListWord) {

        Pattern whilteList = Pattern.compile(StringUtils.join(whileListWord, '|'));
        Pattern blackList = Pattern.compile(StringUtils.join(blackListWord, '|'));

        if (source != null) {
            Properties sourceFiltered = new Properties();
            for (Object o : source.keySet()) {
                // if (((String) o).contains("noUnEuroBOId")) {
                // System.out.println("ddz");
                //
                // }
                String s = (String) o;
                if (whilteList.matcher(s.toLowerCase()).find() && !blackList.matcher(s.toLowerCase()).find()) {
                    System.out.println(s);
                    sourceFiltered.put(s, source.get(s));
                }
            }

            // for (Object o : sourceFiltered.keySet()) {
            // source.remove(o);
            // }

            target.putAll(sourceFiltered);

        }

    }

    /**
     * @param sourceBundleName
     * @param locale
     * @return
     */
    public final static String buildPropertyFileName(String sourceBundleName, final String locale) {
        if (locale.equalsIgnoreCase("default")) {
            return sourceBundleName + ".properties";
        }
        return MessageFormat.format(PATTERN_PROPERTIES_FILES, new String[] { sourceBundleName, locale });
    }
}
