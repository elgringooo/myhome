 
package com.myhome.jdk5.collection.treesortedmapcomparator;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Comparator;

public class CollectionSortedMap {
    static final Comparator ORDRE_REALISATEUR = new CompRealisateur();
    static final Comparator ORDRE_ANNEE = new CompAnnee();

    public static void main(String[] args) {
        TreeMap<Video, String> associations = new TreeMap<Video, String>();
        associations
                .put(
                        new Video("Le jour le plus long", "Kene Annakin",
                                1962),
                        "Le débarquement en Normandie par les troupes alliées "
                                + "le 6 juin 1944. Un casting prestigieux pour un film "
                                + "de guerre d'anthologie.");

        associations
                .put(
                        new Video("Un pont trop loin",
                                "Richard Attenborough", 1977),
                        "La plus grande opération aéroportée sur les ponts entre "
                                + "la France et l'Allemagne, doit précipiter la chute de "
                                + "l'Allemagne nazie.");

        associations
                .put(
                        new Video("Le jour le plus long", "Kenna Annakin",
                                1962),
                        "Le débarquement en Normandie par les troupes alliées "
                                + "le 6 juin 1944. Un casting prestigieux pour un film "
                                + "de guerre d'anthologie.");

        associations
                .put(
                        new Video("Platoon", "Oliver Stone", 1986),
                        "Ce film choc sur la guerre du Vietnam, met en "
                                + "scène un peloton du 25eme régiment d'infanterie "
                                + "gangréné par des luttes intestinales.");
        ;

        System.out.println("Tri par titre :");
        afficherEntrees(associations);

        TreeMap<Video,String> associationsR = new TreeMap<Video,String>(ORDRE_REALISATEUR);
        associationsR.putAll(associations);
        System.out.println("\nTri par réalisateur :");
        afficherEntrees(associationsR);

        TreeMap associationsA = new TreeMap(ORDRE_ANNEE);
        associationsA.putAll(associations);
        System.out.println("\nTri par année :");
        afficherEntrees(associationsA);

        Video article = new Video("Platoon", "Oliver Stone", 1986);
        if (associations.containsKey(article))
            System.out.println("La vidéo Platoon a été trouvée !");

        System.out
                .println("\nRécupération des vidéos strictement inférieures :");
        SortedMap selectInf = associations.headMap(article);
        afficherEntrees(selectInf);
        System.out
                .println("\nRécupération des vidéos supérieures ou égales :");
        SortedMap selectSup = associations.tailMap(article);
        afficherEntrees(selectSup);
    }

    public static void afficherEntrees(SortedMap assoc) {
        Set ensemble = assoc.keySet();
        int taille = assoc.size();
        Iterator iterateur = ensemble.iterator();
        while (iterateur.hasNext()) {
            Object o = iterateur.next();
            System.out.println(o.toString() + " : " + assoc.get(o));
        }
    }
}
