package com.myhome.jdk5.regex.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex3 {

    // CARD#CTxx#PTxxx#SAx[x]#SFx#BBxxxxxx##

    public static void main(String[] args) {

        String value = "CARD#CTMC#PTMDS#SA2#SFO#BB513283##CARD#CTMC#PTMDS#SA-1#SFN#BB513284";

        String[] split = value.split("##");

        for (String v : split) {
            String patternVal = "(\\w*)#CT([A-Z]{2})#PT([A-Z]{3})#SA(-?[0-9]{1})#SF([O|N])#BB([0-9]{6})";
            // String patternVal = "([A-Z]{4})#([A-Z]{4})";
            Pattern p = Pattern.compile(patternVal);
            Matcher m = p.matcher(v);
            if (m.matches())
                for (int i = 0; i <= m.groupCount(); ++i)
                    System.out.println("\n" + "groupe " + i + " :" + m.group(i));
        }

        // String value = "CARD#CTMC";
        
        
       System.out.println("5001...".indexOf("...")>0);

    }
    
    
    
    
}
