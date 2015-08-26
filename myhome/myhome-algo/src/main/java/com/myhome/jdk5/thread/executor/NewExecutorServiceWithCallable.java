package com.myhome.jdk5.thread.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewExecutorServiceWithCallable {
    public static void main(String[] args) {
        
        Callable<Void> job = new Callable<Void>() {
            public Void call() throws Exception{
                System.out.println("Je suis dans le thread " + Thread.currentThread().getName());
                return null;
            }
        };
 
        
        
        Callable<String> job2 = new Callable<String>() {
            public String call() throws Exception{
                System.out.println("Je suis dans le thread " + Thread.currentThread().getName());
                return "done";
            }
        };
        
        
        
        ExecutorService pool = Executors.newFixedThreadPool(4);
        pool.submit(job);
        pool.submit(job2);
        pool.shutdown();
 
        System.out.println("Je suis dans le thread " + Thread.currentThread().getName());
 
    }
}
