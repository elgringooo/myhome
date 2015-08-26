package com.myhome.jdk5.thread.queue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FixedThreadPoolExample {
    public static void main(String[] args) {
        // Create a fixed thread pool containing one thread
        ExecutorService fixedPool = Executors.newFixedThreadPool(1);

        // Create a Callable object of anonymous class
        Callable<String> aCallable = new Callable<String>() {
            String result = "Callable done !";

            @Override
            public String call() throws Exception {
                while (true) {
                    // Print a value
                    System.out.println("Callable at work !");
                    // Sleep for 5 sec
                    Thread.sleep(5 * 1000);
                }
                 
            }
        };

        // Create a Runnable object of anonymous class
        Runnable aRunnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {

                        // Print a value
                        System.out.println("Runnable at work !");
                        // Sleep for 5 sec
                        Thread.sleep(5 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        // Submit two tasks for running
        Future<String> callableFuture = fixedPool.submit(aCallable);
        Future<?> runnableFuture = fixedPool.submit(aRunnable);

        fixedPool.shutdown(); // shut down

    }
}
