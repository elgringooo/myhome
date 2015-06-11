package com.myhome.jdk5.serialisation;

import java.io.*;
import java.util.ArrayList;

public class SerializerPersonne {
   
  public static void main(String argv[]) {
    Personne personne = new Personne("Dupond","Jean",175);
    Personne personne2 = new Personne("LEVI","Gerald",175);
	
	
    try {
      FileOutputStream fichier = new FileOutputStream("personne.ser");
      ObjectOutputStream oos = new ObjectOutputStream(fichier);
      oos.writeObject(personne);
      oos.writeObject(personne2);
      
       oos.flush();
      oos.close();
    }
    catch (java.io.IOException e) {
      e.printStackTrace();
    }
  }
}   