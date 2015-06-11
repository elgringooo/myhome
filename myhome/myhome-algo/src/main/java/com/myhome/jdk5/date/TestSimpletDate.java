/* 
 * @(#)TestSimpletDate.java
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

public class TestSimpletDate {

	public static void main(String[] args) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMM");
		try {
			Date date = sdf1.parse(null);
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println(sdf2.format(date));
		} catch (ParseException e) {

		}

	}
}
