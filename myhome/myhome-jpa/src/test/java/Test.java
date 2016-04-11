import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

public class Test {

    public static void main(String[] args) {
        Locale fr = Locale.FRANCE;

        System.out.println(fr.getDisplayCountry());
        System.out.println(fr.getDisplayLanguage());
        System.out.println(fr.getDisplayName());
        System.out.println(fr.getLanguage());

        String[] languages = Locale.getISOLanguages();
        Map<String, Locale> localeMap = new HashMap<String, Locale>(languages.length);
        for (String language : languages) {
            Locale locale = new Locale(language);
            localeMap.put(locale.getISO3Language(), locale);
        }
        System.out.println(localeMap);

        System.out.println(Test.format("12345678", 3));
        
        
        System.out.println(StringEscapeUtils.escapeJava("I agree with order content updates, billing address, shipping address and amount of cart."));
        

    }

    public static String format(String str, int grouplength) {
        String tmp = new StringBuilder(str).reverse().toString();
        tmp = tmp.replaceAll("\\w{" + grouplength + "}", "$0 ");
        return new StringBuilder(tmp).reverse().toString();

    }

}
