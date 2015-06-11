/* 
 * @(#)AbstractPizzaBuilder.java
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

/**
 * "Abstract Builder"
 * 
 * 
 * Le Builder ou Monteur est une classe offrant des moyens de construction d'un
 * objet. Par exemple, pour construire un dessin il faut ajouter des points, des
 * lignes, des cercles.... Il ne doit pas �tre confondu avec la Fabrique.
 * 
 * Le probl�me d'une Fabrique de cr�ation, c'est qu'elle ne permet de d�finir
 * comment un objet va �tre construit, certes, il est toujours possible de
 * passer x param�tres dans la m�thode de cr�ation d'une fabrique mais cela
 * s'av�re souvent tr�s r�ducteurs voire d�licat pour la maintenance.
 * 
 * 
 * http://en.wikipedia.org/wiki/Builder_pattern
 * 
 * */
abstract class PizzaBuilder {
    protected Pizza pizza;

    public Pizza getPizza() {
        return pizza;
    }

    public void createNewPizzaProduct() {
        pizza = new Pizza();
    }

    public abstract void buildDough();

    public abstract void buildSauce();

    public abstract void buildTopping();
}
