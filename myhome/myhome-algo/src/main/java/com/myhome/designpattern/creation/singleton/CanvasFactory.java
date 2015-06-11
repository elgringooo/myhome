/* 
 * @(#)Singleton.java
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
package com.myhome.designpattern.creation.singleton;

public class CanvasFactory {
    /** Donnée de classe contenant l'instance courante */
    private static CanvasFactory instance = new CanvasFactory();

    /** Constructeur privé interdisant l'instanciation en dehors de cette classe */
    private CanvasFactory() {
    }

    /** Singleton de la classe courante */
    public static CanvasFactory getInstance() {
        return instance;
    }

}