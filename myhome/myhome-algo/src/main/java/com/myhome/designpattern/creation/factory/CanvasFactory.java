/* 
 * @(#)CanevasFactory.java
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

/**
 * Une fabrique de cr�ation (ou factory) est une classe qui n'a pour r�le que de construire des objets. Cette classe utilise des interfaces ou des classes abstraites pour masquer l'origine des objets.
 * Dans cet exemple, nous d�finissons deux interfaces Point et Line repr�sentant deux classes Abstraites. Ces classes Point et Line designent des objets retourn�es par la classe CanvasFactory. Cette classe masque la v�ritable nature des objets. Ici nous retournons par les m�thodes d'acc�s des objets PointImpl et LineImpl qui impl�mentent respectivement les interfaces Point et Line. Ainsi, l'application utilisera la classe CanvasFactory pour obtenir des �l�ments graphiques, lors d'une �volution l'utilisateur pourra changer facilement la nature des objets (avec d'autres classes impl�mentant les interfaces Point et Line...).

 On distingue parfois deux formes de fabrique :

 * Les fabriques abstraites comme celle que nous venons de voir reposant sur l'exploitation de classes g�n�riques (Point et Line).
 * Les fabriques concr�tes masquant toutes les m�thodes n�c�ssaires � la cr�ation et � l'initialisation de l'objet.


 */

/** Fabrique retournant des objets de types point ou ligne */
public class CanvasFactory {
    /** Retourne un Point aux coordonn�es x,y */
    public Point getPoint(int x, int y) {
        return new PointImpl(x, y);
    }

    /** Retourne une Ligne aux coordonn�es x1,y1,x2,y2 */
    public Line getLine(int x1, int y1, int x2, int y2) {
        return new LineImpl(x1, y1, x2, y2);
    }

    public class PointImpl implements Point {
        public PointImpl(int x, int y) {

        }

        public int getX() {
            // TODO Auto-generated method stub
            return 0;
        }

        public int getY() {
            // TODO Auto-generated method stub
            return 0;
        }
    }

    public class LineImpl implements Line {
        public LineImpl(int x1, int y1, int x2, int y2) {

        }

        public int getX1() {
            // TODO Auto-generated method stub
            return 0;
        }

        public int getX2() {
            // TODO Auto-generated method stub
            return 0;
        }

        public int getY1() {
            // TODO Auto-generated method stub
            return 0;
        }

        public int getY2() {
            // TODO Auto-generated method stub
            return 0;
        }

    }

}