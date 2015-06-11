/* 
 * @(#)Circle.java
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
package com.myhome.designpattern.structure.adapter;

/** Interface de représentation d'un cercle */
public interface Circle {
  /** Retourne l'abscisse du centre du cercle */
  public int getX();
  /** Retourne l'ordonnée du centre du cercle */
  public int getY();
  /** Retourne le rayon du cercle */
  public int getR();
}

