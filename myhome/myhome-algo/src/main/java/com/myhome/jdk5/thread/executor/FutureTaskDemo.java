package com.myhome.jdk5.thread.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
    public static void main(String[] args) {

        /** The job1. */
        Callable<String> callable1 = new Callable<String>() {
            public String call() throws Exception {
                System.out.println("Je suis dans le thread " + Thread.currentThread().getName());
                Thread.sleep(7500);
                return "Hello";
            }
        };

        /** The job2. */
        Callable<String> callable2 = new Callable<String>() {
            public String call() throws Exception {
                System.out.println("Je suis dans le thread " + Thread.currentThread().getName());
                Thread.sleep(3000);
                return "world";
            }
        };

        FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
        FutureTask<String> futureTask2 = new FutureTask<String>(callable2);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(futureTask1);
        executor.execute(futureTask2);
        executor.shutdown();
        try {
            System.out.println(futureTask1.get() + futureTask2.get());
        } catch (InterruptedException | ExecutionException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }
}
