/* 
 * @(#)CircleImplPointAdapter.java
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

import com.myhome.designpattern.creation.factory.Point;

/** Adapteur pour transformer le circle en un point */
public class CircleImplPointAdapter implements Point {
  private Circle c;
  public CircleImplPointAdapter( Circle c ) {
   this.c = c;
  } 
  public int getX() { return c.getX(); }
  public int getY() { return c.getY(); }
}