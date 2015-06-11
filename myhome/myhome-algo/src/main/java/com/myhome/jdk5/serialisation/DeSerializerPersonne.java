package com.myhome.jdk5.serialisation;

import java.io.*;

public class DeSerializerPersonne {

	public static void main(String argv[]) {
		try {
			FileInputStream fichier = new FileInputStream("personne.ser");
			ObjectInputStream ois = new ObjectInputStream(fichier);

			while (fichier.available() > 0) {
				Personne personne = (Personne) ois.readObject();
				System.out.println(personne);
			}

		} catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
