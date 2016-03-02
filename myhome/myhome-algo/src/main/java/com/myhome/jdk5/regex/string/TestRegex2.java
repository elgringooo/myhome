/* 
 * @(#)TestRegex.java
 *
 * Copyright (c) 2008 DCN SA. All rights reserved.
 * DCN PROPRIETARY/CONFIDENTIAL.  Use is subject to license terms.
 *
 * This file, together  with  its accompanying  software product  and
 * documentation, is  protected by the  intellectual  property rights
 * in  France  and  other  countries, any  applicable  copyrights  or
 * patent rights, and international treaty provisions. No part may be
 * reproduced  in  any  form  by  any  mean  without   prior  written
 * authorization of DCN.
 */
package com.myhome.jdk5.regex.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex2 {
    private static Pattern pattern;
    private static Matcher matcher;

    public static void main(String args[]) {
       // pattern = Pattern.compile("(?i)(^Close$)|(^Cancelled$)");
        pattern = Pattern.compile("\\b3ds-acs\\b");
        
        matcher = pattern.matcher("https://3ds-acx.test.modirum.com/mdpayacs/pareq");
        if (matcher.find()) {
           
   

        System.out.println("Trouvé !" );}
        
        
        
        Pattern p = Pattern.compile("(a((b)(c)))");
        Matcher m = p.matcher("abc");
        if( m.matches())
           for(int i= 0; i<= m.groupCount(); ++i)
              System.out.println("\n"+"groupe "+i+" :"+m.group(i));
        

    }
   
}