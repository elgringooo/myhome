package com.myhome.jdk5.thread.queue.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The producer / consumer model of multi thread simulation
 *  
 * @author Lin Jiqin
 * @version 1 2013-7-25 05:23:11 this afternoon
 */
public class BlockingQueueTest2 {
    /**
     * 
     * The definition of Apple basket
     * 
     */
    public class Basket {
        // Basket, can accommodate 3 apples
        BlockingQueue<String> basket = new LinkedBlockingQueue<String>(3);

        // The production of apple, into the basket
        public void produce() throws InterruptedException {
            // The put method into an apple, if the basket is full, wait until the basket position
            basket.put("An apple");
        }

        // Consumption of apple, away from the basket
        public String consume() throws InterruptedException {
            // The take method took out an apple, if the basket is empty, when Apple has basket so far (access and remove the head of the queue)
            return basket.take();
        }
    }

    // The definition of Apple producers
    class Producer implements Runnable {
        private String instance;
        private Basket basket;

        public Producer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        public void run() {
            try {
                while (true) {
                    // The production of apple
                    System.out.println("The producer to the production of apple: " + instance);
                    basket.produce();
                    System.out.println("!Producers of the production of apple.: " + instance);
                    // Sleep 300ms
                    Thread.sleep(300);
                }
            } catch (InterruptedException ex) {
                System.out.println("Producer Interrupted");
            }
        }
    }

    // The definition of Apple consumers
    class Consumer implements Runnable {
        private String instance;
        private Basket basket;

        public Consumer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        public void run() {
            try {
                while (true) {
                    // Consumption of apples
                    System.out.println("Consumer to consumer apple: " + instance);
                    System.out.println(basket.consume());
                    System.out.println("!Consumer spending is apple: " + instance);
                    // Sleep 1000ms
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                System.out.println("Consumer Interrupted");
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueueTest2 test = new BlockingQueueTest2();

        // Set up a basket of apples
        Basket basket = test.new Basket();

        ExecutorService service = Executors.newCachedThreadPool();
        Producer producer = test.new Producer("Producer 001", basket);
        Producer producer2 = test.new Producer("Producer 002", basket);
        Consumer consumer = test.new Consumer("Consumer 001", basket);
        service.submit(producer);
        service.submit(producer2);
        service.submit(consumer);
        // 5S program, all the tasks to stop
//        try {
//            Thread.sleep(1000 * 5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        service.shutdownNow();
    }

}