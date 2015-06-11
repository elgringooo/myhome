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
 * lignes, des cercles.... Il ne doit pas être confondu avec la Fabrique.
 * 
 * Le problème d'une Fabrique de création, c'est qu'elle ne permet de définir
 * comment un objet va être construit, certes, il est toujours possible de
 * passer x paramètres dans la méthode de création d'une fabrique mais cela
 * s'avère souvent très réducteurs voire délicat pour la maintenance.
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
