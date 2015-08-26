package com.myhome.hazelcast;
import com.hazelcast.core.*;
import com.hazelcast.config.*;
 
import java.util.Map;
import java.util.Queue;
 
/**
 * The Class GettingStarted. La Data Grid Java en mémoire de Hazelcast 3.0
 */
public class GettingStartedRead {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        Config cfg = new Config();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
    
 
        Queue<String> queueCustomers = instance.getQueue("customers");
      
        System.out.println("First customer: " + queueCustomers.poll());
        System.out.println("Second customer: "+ queueCustomers.peek());
        System.out.println("Queue size: " + queueCustomers.size());
    }
}