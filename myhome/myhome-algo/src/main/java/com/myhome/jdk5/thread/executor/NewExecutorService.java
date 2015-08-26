package com.myhome.jdk5.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewExecutorService {
    public static void main(String... args) {

        Runnable job = new Runnable() {
            public void run() {
                while(true){
                    System.out.println("Je suis dans le thread : " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                

            }
        };

        // Pool avec 4 threads
        ExecutorService pool = Executors.newFixedThreadPool(5);
        pool.submit(job);
        pool.submit(job);
        pool.submit(job);
        pool.submit(job);
        pool.submit(job);
        pool.shutdown();

        System.out.println("Je suis dans le thread : " + Thread.currentThread().getName());

    }
}
