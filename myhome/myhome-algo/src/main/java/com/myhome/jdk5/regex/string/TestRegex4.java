package com.myhome.jdk5.regex.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex4 {

    // CARD#CTxx#PTxxx#SAx[x]#SFx#BBxxxxxx##

    public static void main(String[] args) {
        String GENERIC_PREFIX = "G_";
        String g = "G_4XCB_COFIDIS_ORDER_NUMBER";
        if (g.startsWith(GENERIC_PREFIX)) {
            g = g.replaceFirst(GENERIC_PREFIX, "");
        }

        System.out.println(g.substring(0, g.indexOf("_")));

    }

    // String value = "CARD#CTMC";

    public static int nthIndexOf(final String string, final String token, final int index) {
        int j = 0;

        for (int i = 0; i < index; i++) {
            j = string.indexOf(token, j + 1);
            if (j == -1)
                break;
        }

        return j;
    }
}
