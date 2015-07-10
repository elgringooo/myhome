package com.myhome.bytecodeinjection;

import java.awt.Point;

public class MyClassInjected {
    public void ma_methode() {
       // On regarde le temps au d�part 
       long time = System. currentTimeMillis() ;

       // le corps de ma m�thode
       int res=0;
       for (int i=0; i < 30000; i++) { Point p = new Point(i,i); res+=p.x+p.y; }

       // R�cup�ration du r�sultat
       System.out.println("Temps pass� dans ma_methode :"+( System. currentTimeMillis()-time)) ;
    }
    public static void main(String[] args) {
        new MyClassInjected().ma_methode();
    }
 }