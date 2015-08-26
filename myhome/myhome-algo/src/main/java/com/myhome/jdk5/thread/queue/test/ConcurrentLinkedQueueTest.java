package com.myhome.jdk5.thread.queue.test;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentLinkedQueueTest {
    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
    private static int count = 2; // The number of threads
    //CountDownLatch, A synchronous auxiliary, prior to the completion of a set of operations are performed in other threads, it allows one or more threads waiting. 
    private static CountDownLatch latch = new CountDownLatch(count);

    public static void main(String[] args) throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(4);
        ConcurrentLinkedQueueTest.offer();
        for (int i = 0; i <count; i++) {
            es.submit(new Poll());
        }
        latch.await(); //The main thread (main) block until latch.countDown (zero) to continue
        System.out.println("cost time " + (System.currentTimeMillis() - timeStart) + "ms");
        es.shutdown();
    }
    
    /**
     * Production
     */
    public static void offer() {
        for (int i = 0; i <100000; i++) {
            queue.offer(i);
        }
    }


    /**
     * Consumption
     *  
     * @author Lin Jiqin
     * @version 1 2013-7-25 05:32:56 this afternoon
     */
    static class Poll implements Runnable {
        public void run() {
            // while (queue.size()>0) {
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }
            latch.countDown();
        }
    }
}