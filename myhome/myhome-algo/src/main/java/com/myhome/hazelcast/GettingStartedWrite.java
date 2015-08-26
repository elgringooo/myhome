package com.myhome.hazelcast;
import com.hazelcast.core.*;
import com.hazelcast.config.*;
 
import java.util.Map;
import java.util.Queue;
 
/**
 * The Class GettingStarted. La Data Grid Java en mémoire de Hazelcast 3.0
 */
public class GettingStartedWrite {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        Config cfg = new Config();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        Map<Integer, String> mapCustomers = instance.getMap("customers");
        mapCustomers.put(1, "Joe");
        mapCustomers.put(2, "Ali");
        mapCustomers.put(3, "Avi");
 
        System.out.println("Customer with key 1: "+ mapCustomers.get(1));
        System.out.println("Map Size:" + mapCustomers.size());
 
        Queue<String> queueCustomers = instance.getQueue("customers");
        queueCustomers.offer("Tom");
        queueCustomers.offer("Mary");
        queueCustomers.offer("Jane");
        System.out.println("customers " + queueCustomers.toArray());
        System.out.println("Queue size: " + queueCustomers.size());
    }
}