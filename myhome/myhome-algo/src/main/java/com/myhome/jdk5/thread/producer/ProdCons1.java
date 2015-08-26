package com.myhome.jdk5.thread.producer;

import java.util.Random;

class Producer1 implements Runnable {
    Buffer1 q;
    Random ran = new Random();
    int duree;

    public Producer1(Buffer1 q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    public void run() {
        int i = 0;
        while (true) {
            try {
                duree = ran.nextInt() % 2000;
                if (duree < 0)
                    duree = -duree;
                Thread.sleep(duree);
                q.set(i++);
            } catch (Exception e) {
                System.out.println("interrupted!");
            }
        }
    }
}

class Consumer1 implements Runnable {
    Buffer1 q;
    Random ran = new Random();
    int duree;
    int n;

    public Consumer1(Buffer1 q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        while (true) {
            try {
                duree = ran.nextInt() % 3000;
                if (duree < 0)
                    duree = -duree;
                Thread.sleep(duree);
                n = q.get();
            } catch (Exception e) {
                System.out.println("interrupted!");
            }
        }
    }
}

class ProdCons1 {
    public static void main(String[] args) {
        Buffer1 queue = new Buffer1();
        new Producer1(queue);
        new Producer1(queue);
        new Consumer1(queue);
        new Consumer1(queue);
    }
}
