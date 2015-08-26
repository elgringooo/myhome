package com.myhome.jdk5.thread.executor.persistasync;

import java.util.List;

import com.myhome.jdk5.thread.executor.persistasync.entity.Event;
import com.myhome.jdk5.thread.executor.persistasync.entity.WebServiceInOut;

/**
 * The Class EntityPersistenceTask.
 */
public class EntityPersistenceTask implements Runnable {

    /** The message queue singleton. */
    private MessageManager messageQueueSingleton;

    /**
     * Instantiates a new entity persistence task.
     */
    public EntityPersistenceTask() {
        messageQueueSingleton = MessageManager.getInstance();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {
        int processed = 0;
        while (true) {
            try {

                Message msg = messageQueueSingleton.received();
                if (msg != null) {
                    System.out.printf("Consumer %s receives %s object(s) from %s\n", Thread.currentThread().getName(), msg.getObjects().size(), msg.getName());
                    List<Object> objects = msg.getObjects();
                    for (Object o : objects) {

                        if (o instanceof WebServiceInOut) {
//                            System.out
//                                .printf("Consumer %s reads object %s #%s\n", Thread.currentThread().getName(), "webservice", ((WebServiceInOut) o).getId());

                        } else if (o instanceof Event) {
//                            System.out.printf("Consumer %s reads object %s #%s\n", Thread.currentThread().getName(), "event", ((Event) o).getEventId());

                        }

                    }
                    processed++;

                    executeBatchUpdate();

                } else {
                    System.out.printf("Consumer %s: total batches executed: %s \n", Thread.currentThread().getName(), processed);
                    Thread.sleep(2000);
                }

            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Execute batch update.
     * @throws InterruptedException the interrupted exception
     */
    private void executeBatchUpdate() {
        System.out.println("Execute Batch Update");
        // Simulate commit delay
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
