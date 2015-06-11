/* 
 * @(#)TryCatchFinallyTest.java
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
package com.myhome.jdk5.gestion_exception;

import java.text.DateFormat;
import java.text.ParseException;

public class TryCatchFinallyTest {

    public static void main(String[] args) {

     

     try {
         System.out.println("try");
        DateFormat.getDateInstance().parse("ded");
    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        
        System.out.println("catch");
        return;

    }finally{
        System.out.println("finally");
    }

        System.out.println("dededede");
    }
}
