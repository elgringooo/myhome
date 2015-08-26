package com.myhome.jdk5.thread.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// TODO: Auto-generated Javadoc
/**
 * The Class FutureDemo.
 */
public class FutureDemo {

    /** The job1. */
    private Callable<Integer> job1 = new Callable<Integer>() {
        public Integer call() throws Exception {
            System.out.println("Je suis dans le thread " + Thread.currentThread().getName());
            Thread.sleep(7500);
            return 79;
        }
    };

    /** The job2. */
    private Callable<Integer> job2 = new Callable<Integer>() {
        public Integer call() throws Exception {
            System.out.println("Je suis dans le thread " + Thread.currentThread().getName());
            Thread.sleep(3000);
            return 21;
        }
    };

    /**
     * The main method.
     * @param args the arguments
     */
    public static void main(String[] args) {
        new FutureDemo().go();
    }

    /**
     * Go.
     */
    void go() {
        simpleThread();
        multiThread();
        multiThreadCompletionService();

    }

    /**
     * Simple thread. Un seul thread est utilisé pour les deux jobs
     */
    public void simpleThread() {
        System.out.println("=======> Simple Thread");
        long timeBefore = System.currentTimeMillis();
        try {
            Integer result1 = job1.call();
            System.out.printf("Job1 result after %dms : %d %n", System.currentTimeMillis() - timeBefore, result1);
            Integer result2 = job2.call();
            System.out.printf("Job2 result after %dms : %d %n", System.currentTimeMillis() - timeBefore, result2);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.printf("Result after %dms\n", System.currentTimeMillis() - timeBefore);

    }

    /**
     * Multi thread. Grâce au pool de threads, les deux jobs ont été menés en parallèle. Le temps d'exécution global a été réduit, et deux processeurs ont été
     * utilisés. Bemol l'ordre de disponibilité des results est aleatoires. Si Job2 finit avant Job1 , Job2 attend Job1
     */
    public void multiThread() {
        System.out.println("=======> Multi Thread");
        long timeBefore = System.currentTimeMillis();

        // Creation pool de thread
        ExecutorService pool = Executors.newFixedThreadPool(4);
        Future<Integer> future1 = pool.submit(job1); // Should take 7.5s
        Future<Integer> future2 = pool.submit(job2); // Should take 3s
        pool.shutdown();

        try {
            Integer result1 = future1.get();
            System.out.printf("Job1 result after %dms : %d %n", System.currentTimeMillis() - timeBefore, result1);
            Integer result2 = future2.get();
            System.out.printf("Job2 result after %dms : %d %n", System.currentTimeMillis() - timeBefore, result2);

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.printf("Result after %dms\n", System.currentTimeMillis() - timeBefore);

    }

    /**
     * Multi thread avec Completion service . C' est un wrapper qui se branche sur un ExecutorService, et qui se charge de surveiller l'état d'avancement des
     * différents traitements qui lui ont été soumis. Sa méthode take() (bloquante) renvoie les résultats au fur et à mesure de leur disponibilité.
     */
    public void multiThreadCompletionService() {
        System.out.println("=======> Multi Thread with Completion service");
        long timeBefore = System.currentTimeMillis();

        // Creation pool de thread
        ExecutorService pool = Executors.newFixedThreadPool(4);
        CompletionService<Integer> completion = new ExecutorCompletionService<Integer>(pool);
        completion.submit(job1); // Should take 7.5s
        completion.submit(job2); // Should take 3s
        pool.shutdown();

        try {
            Future<Integer> answer1 = completion.take();
            Integer result1 = answer1.get();
            System.out.printf("Job1 result after %dms : %d %n", System.currentTimeMillis() - timeBefore, result1);
            Future<Integer> answer2 = completion.take();
            Integer result2 = answer2.get();
            System.out.printf("Job2 result after %dms : %d %n", System.currentTimeMillis() - timeBefore, result2);

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.printf("Result after %dms\n", System.currentTimeMillis() - timeBefore);
    }
    
    
    

}
