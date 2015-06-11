/* 
 * @(#)BuildExample.java
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
package com.myhome.designpattern.creation.builder;

/** A given type of pizza being constructed. */
public class BuildExample {
    public static void main(String[] args) {
        Cook cook = new Cook();

        PizzaBuilder spicyPizzaBuilder = new SpicyPizzaBuilder();

        cook.setPizzaBuilder(spicyPizzaBuilder);
        cook.constructPizza();

        Pizza spicy = cook.getPizza();
    }
}
