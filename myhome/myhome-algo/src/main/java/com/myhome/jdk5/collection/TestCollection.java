 
package com.myhome.jdk5.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class testCollection.
 * <p>
 * TODO Insert a description here.
 * </p>
 * 

 */
public class TestCollection {

    /**
     * @param args
     */
    public static void main(String[] args) {
        /***********************************************************************
         * Parcours d'une map
         */

        Map<String, String> updateMap;
        List<String> listToRemove;

        updateMap = new HashMap<String, String>();
        listToRemove = new ArrayList<String>();

        updateMap.put("023", "11");
        updateMap.put("013", "12");

        for (Map.Entry<String, String> entry : updateMap.entrySet()) {
            entry.getKey();
            entry.getValue();
            System.out.println("aa" + entry.getKey() + " " + entry.getValue());
            listToRemove.add(entry.getKey());

        }
        
        for (String key : listToRemove) {
            updateMap.remove(key);

        }
        

  
        for (Map.Entry<String, String> entry : updateMap.entrySet()) {
            entry.getKey();
            entry.getValue();
            System.out.println("aa" + entry.getKey() + " " + entry.getValue());
        

        }
    }

}
