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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {
    private static Pattern pattern;
    private static Matcher matcher;

    public static void main(String args[]) {
       // pattern = Pattern.compile("(?i)(^Close$)|(^Cancelled$)");
        pattern = Pattern.compile("(^Close$)");
        
        matcher = pattern.matcher("Init");
        if (! matcher.matches())
            System.out.println("Trouvé !");

        java.util.Date myDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            // 12-->16
            myDate = formatter.parse("02.03.2010");
            Calendar calendar2 = new GregorianCalendar();
            java.util.Date currentDate = formatter.parse("18.02.2010");

            System.out.println(currentDate);

            System.out.println("oppppen "
                    + getNbOpenedDaysSinceDaytime(Calendar.getInstance().getTime(), myDate));

            System.out.println("oppppen2 "
                    + getNbOpenedDaysSinceDaytime2(Calendar.getInstance().getTime(), myDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public static int getNbOpenedDaysSinceDaytime2(
            java.util.Date theEarlierDate, java.util.Date theLaterDate) {
        int nbOpenedDays = 0;

        Calendar calendarStart = new GregorianCalendar();
        calendarStart.setTime(theEarlierDate);

        Calendar calendarEnd = new GregorianCalendar();
        calendarEnd.setTime(theLaterDate);

        long diff = theLaterDate.getTime() - theEarlierDate.getTime();
        if (diff < 0) {
            return -1;
        }

        while (calendarStart.getTime().compareTo(calendarEnd.getTime()) < 0) {
            System.out.println(calendarStart.get(Calendar.DAY_OF_WEEK));
            if (calendarStart.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                    && calendarStart.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                nbOpenedDays++;
            }
            calendarStart.add(Calendar.DAY_OF_MONTH, 1);
        }

        System.out.println("  ---->" + nbOpenedDays);
        return nbOpenedDays;

    }
    public static int getNbOpenedDaysSinceDaytime(
            java.util.Date theEarlierDate, java.util.Date theLaterDate) {
        int nbOpenedDays = 0;

        Calendar calendarStart = new GregorianCalendar();
        calendarStart.setTime(theEarlierDate);

        Calendar calendarEnd = new GregorianCalendar();
        calendarEnd.setTime(theLaterDate);

        long diff = theLaterDate.getTime() - theEarlierDate.getTime();
        if (diff < 0) {
            return -1;
        }
        long numberOfDay = (long) diff / 86400000;

        if (numberOfDay != 0) {
            for (int i = 1; i <= numberOfDay; i++) {
                if (calendarStart.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                        && calendarStart.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                    nbOpenedDays++;
                }
                calendarStart.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
        System.out.println(numberOfDay + "  ---->" + nbOpenedDays);
        return nbOpenedDays;

    }

    public static double getDaysBetweenDates(Date theEarlierDate,
            Date theLaterDate) {
        double result = Double.POSITIVE_INFINITY;
        if (theEarlierDate != null && theLaterDate != null) {
            final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
            Calendar aCal = Calendar.getInstance();
            aCal.setTime(theEarlierDate);
            long aFromOffset = aCal.get(Calendar.DST_OFFSET);
            aCal.setTime(theLaterDate);
            long aToOffset = aCal.get(Calendar.DST_OFFSET);
            long aDayDiffInMili = (theLaterDate.getTime() + aToOffset)
                    - (theEarlierDate.getTime() + aFromOffset);
            result = ((double) aDayDiffInMili / MILLISECONDS_PER_DAY);
        }
        return result;
    }
}