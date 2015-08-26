package com.myhome.jdk5.thread.executor.persistasync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 200 threads produisent 10 entrées de webserviceinout et event chacun. EntityPersistenceTask consomme et persiste ces entités.
 */
public class SampleLauncher1 {

    /** The Constant NB. */
    public static final int NB = 1000;

    /**
     * The main method.
     * @param args the arguments
     */
    public static void main(String[] args) {
        new SampleLauncher1().go();

    }

    /**
     * Go.
     */
    public void go() {
        launchEntityPersistenceThread();
        sleep(2000);
        launchPaylineAPIThreads();

    }

    /**
     * Launch payline thread.
     */
    public void launchPaylineAPIThreads() {
        ExecutorService paylinePool = Executors.newFixedThreadPool(NB);
        for (int i = 0; i < NB; i++) {
            paylinePool.execute(new PaylineAPIProducer());
        }

        paylinePool.shutdown();
    }

    /**
     * Launch consumer thread.
     */
    public void launchEntityPersistenceThread() {
        ExecutorService consumerPool = Executors.newSingleThreadExecutor();
        consumerPool.execute(new EntityPersistenceTask());
    }

    /**
     * Sleep.
     * @param s the s
     */
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
