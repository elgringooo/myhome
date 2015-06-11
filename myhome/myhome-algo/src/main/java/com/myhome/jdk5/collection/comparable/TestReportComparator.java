/* 
 * @(#)TestReportComparator.java
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class TestReportComparator {

    public static String removeFileNameExtension(String filename) {

        if (filename != null) {
            int index = -1;
            if (!((index = filename.lastIndexOf(".")) == -1)) {
                int len = filename.length();
                filename = filename.substring(0, len - index);
            }
        }
        return filename;
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        System.out.println(removeFileNameExtension(null));

        List<ReportVO> reports = new ArrayList<ReportVO>();

        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            ReportVO vo = new ReportVO();
            vo.setName("report" + i);
            if (i % 2 == 0)
                vo.setUploadedDate(Calendar.getInstance());
            reports.add(vo);
        }

        ReportComparator comp = new ReportComparator();
        Collections.sort(reports, comp);

        for (ReportVO r : reports) {
            System.out.println(r);
        }

    }

}
