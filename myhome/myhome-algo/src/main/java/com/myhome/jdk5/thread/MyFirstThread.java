package com.myhome.jdk5.thread;

public class MyFirstThread {

    public static void main(String... args) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Je suis dans le thread : " + Thread.currentThread().getName());
            }
        }, "Processus 1");
        t.start();
        System.out.println("Je suis dans le thread : " + Thread.currentThread().getName());

    }

}
