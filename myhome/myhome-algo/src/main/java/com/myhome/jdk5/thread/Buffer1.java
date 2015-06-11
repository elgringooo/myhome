/*
 * @(#)Buffer1.java
 
 */
package com.myhome.jdk5.thread;

public class Buffer1 {
    private int taille = 5; // taille de la queue
    private int premier=0;  // position pour ecrire
    private int dernier=0;  // position pour lire
    private int nombre =0;  // nombre d'elements dans l'objet
    private int[] queue = new int [taille];
    
    synchronized public int get() throws Exception {
       int valeur;
       while (nombre == 0)
          wait();
       valeur = queue[premier];
       System.out.println("         *** Got : " + valeur + " par " +
               Thread.currentThread());
       premier = (premier + 1) % taille;
       nombre--;
       notify();
       return valeur;
    }

     public void set(int valeur) throws Exception {
       while (nombre == taille)
          wait();
       queue[dernier] = valeur;
       dernier = (dernier + 1) % taille;
       nombre ++;
       System.out.println("    *** Put : " + valeur + " par " +
               Thread.currentThread());
       notify();
    }
}
