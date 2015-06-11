/* 
 * @(#)ReportComparator.java
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
package com.myhome.jdk5.collection.comparable;

import java.util.Calendar;
import java.util.Comparator;

public class ReportComparator implements Comparator<ReportVO> {

    @Override
    public int compare(ReportVO report0, ReportVO report1) {

        Calendar date0 = report0.getUploadedDate();
        Calendar date1 = report1.getUploadedDate();

        int dateComp = -compareTo(date0, date1);

        return ((dateComp == 0) ? compareTo(report0.getName(),
                report1.getName()) : dateComp);
    }

    private int compareTo(Comparable o1, Comparable o2) {

        if (o1 != null && o2 != null) {
            return o1.compareTo(o2);

        } else if (o1 == null && o2 == null) {
            return 0;
        } else if (o2 == null) {
            return 1;
        } else {
            return -1;
        }
    }

}
