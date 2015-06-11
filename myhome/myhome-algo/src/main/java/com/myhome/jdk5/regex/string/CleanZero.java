package com.myhome.jdk5.regex.string;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanZero {

    public static void main(String[] args) {
        System.out.println(isEqual("AS566", null, "AS565", "EC155"));
        System.out.println(getShortFileName("C:/User/temp/src/ReadMe.txt",
                "3232", "par"));

        System.out.println(getFileExtensionName("gege.txt"));

        System.out.println(leftTrim("00000000000000éèûr000", '0'));

    }

    public static boolean isValueEmpty(String str) {
        return str == null || str.equals("");
    }

    public static boolean isEqual(String value, String expected) {
        return value != null && value.equalsIgnoreCase(expected);
    }

    public static boolean isEqual(String value, String... expectedArray) {

        boolean result = false;

        if (value == null)
            return false;

        for (int i = 0; i < expectedArray.length; i++) {

            result |= value.equalsIgnoreCase(expectedArray[i]);
        }

        return result;
    }

    public static String leftTrim(String str, char c) {

        if (str == null || str.equalsIgnoreCase("")) {
            return "";
        } else {
            // search for leading chars
            Pattern p = Pattern.compile(c + "+");
            Matcher m = p.matcher(str);
            boolean toTrim = m.lookingAt();

            // return the string if no leading char
            if (!toTrim)
                return str;

            // trim the string
            int trimEnd = m.end();
            StringBuffer res = new StringBuffer(str);
            res.delete(0, trimEnd);
            return res.toString();
        }
    }

    public static String leftTrimCharacter(String str, char c) {

        if (str == null || str.equalsIgnoreCase("")) {
            return "";
        } else {
            int len = str.length();
            int st = 0;
            char[] val = str.toCharArray();
            while ((st < len) && (val[st] <= c)) {
                st++;
            }
            return ((st > 0)) ? str.substring(st, len) : str;
        }
    }

    /**

	 */
    public static String getShortFileName(String pathname, String number,
            String part) {

        // A ? B : C est un opérateur ternaire:
        // si A est vrai alors la valeur rentournée est B, sinon C.
        StringBuilder result = new StringBuilder();
        String filename = new File(pathname).getName();

        if (filename != null) {
            int index = -1;

            String name = null;
            String extension = "";
            if ((index = filename.lastIndexOf(".")) == -1) {
                name = filename;

            } else {

                name = filename.substring(0, index);
                extension = filename.substring(index, filename.length());
            }

            result.append(name);
            result.append("_");
            result.append(number);
            result.append("_");
            result.append(part);
            result.append(extension);

        } else
            return "";

        return result.toString();
    }

    public static String translate(String src) {
        StringBuffer result = new StringBuffer();
        if (src != null && src.length() != 0) {
            int index = -1;
            char c = (char) 0;
            String chars = "àâäéèêëîïôöùûüç";
            String replace = "aaaeeeeiioouuuc";
            for (int i = 0; i < src.length(); i++) {
                c = src.charAt(i);
                if ((index = chars.indexOf(c)) != -1)
                    result.append(replace.charAt(index));
                else
                    result.append(c);
            }
        }
        ;

        return result.toString();
    }

    public static String getCleanedString(String pStringToBeCleaned) {
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

    public static String getFileExtensionName(String filename) {
        String extension = "";
        if (filename != null) {
            int index = -1;

            if (!((index = filename.lastIndexOf(".")) == -1)) {
                extension = filename.substring(index + 1, filename
                        .length());
            }
        }

        return extension;
    }

}
