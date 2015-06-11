 
package com.myhome.jdk5.collection.treesortedmapcomparator;

//Classe comparatrice CompAnnee
import java.util.Comparator;

public class CompAnnee implements Comparator {
    public int compare(Object o1, Object o2) {
        if (!(o1 instanceof Video))
            throw new ClassCastException();
        return (new Integer(((Video) o1).obtenirAnnee()))
                .compareTo(new Integer(((Video) o2).obtenirAnnee()));
    }
}