/* 
 * @(#)UseDateFormatter.java
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
package com.myhome.jdk5.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class UseDateFormatter {

    public static void main(String[] args) throws ParseException {

        Date result = null;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        result = formatter.parse("22/12/2006");

        System.out.println(new java.sql.Date(result.getTime()));

        StringBuffer buffer = new StringBuffer();
        buffer.append("Identification Information\n");
        buffer.append("Aircraft Type: ");

        System.out.println(buffer.toString());
        
        
        
        try {
            System.out.println(nbOfMonthsBetweenTwoDates(null, null));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static int nbOfMonthsBetweenTwoDates(java.sql.Date dateSql1, java.sql.Date dateSql2) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2009-01-01");
        GregorianCalendar gc1 = new GregorianCalendar();
        gc1.setTime(date1);
        Date date2 = sdf.parse("2010-01-01");
        GregorianCalendar gc2 = new GregorianCalendar();
        gc2.setTime(date2);
        int gap = 0;
         while (gc1.getTimeInMillis() < gc2.getTimeInMillis()) {
            gap++;
            gc1.add(GregorianCalendar.MONTH, 1);
        }
        return gap;
    }

}
