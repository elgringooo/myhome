/* 
 * @(#)Line.java
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
package com.myhome.designpattern.creation.factory;

/** Interface de description d'une ligne */
public interface Line {
    /** Retourne les coordonnées du mier point */
    public int getX1();

    public int getY1();

    /** Retourne les coordonnées du deuxième point */
    public int getX2();

    public int getY2();
}