package com.myhome.jdk5.thread.queueproducerconsumer;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The Class MessageManager is a singleton allowing to send & received message. Message is kept in the context of the current thread thanks to ThreadLocal
 */
public class MessageQueueManager {

    /**
     * Singleton instance.
     */
    private static final MessageQueueManager instance = new MessageQueueManager();

    private static final ThreadLocal<Message> threadLocal = new ThreadLocal<Message>();

    /** The queue. */
    private final ConcurrentLinkedQueue<Message> queue;

    /**
     * Private constructor.
     */
    private MessageQueueManager() {
        queue = new ConcurrentLinkedQueue<Message>();
    }

    /**
     * Get singleton instance.
     * @return single instance of MessageQueueSingleton
     */
    public static MessageQueueManager getInstance() {
        return instance;
    }

    public void put(Object object) {
        Message msg = threadLocal.get();
        if (msg == null) {
            msg = new Message(Thread.currentThread().getName());
            threadLocal.set(msg);
        }
        msg.add(object);
    }

    /**
     * Put data to queue.
     * @param msg the msg
     * @throws InterruptedException the interrupted exception
     */
    public void send() {
        Message msg = threadLocal.get();
        if (msg != null) {
            queue.add(msg);
            threadLocal.set(null);
        }

    }

    /**
     * Received.
     * @return the message
     * @throws InterruptedException the interrupted exception
     */
    public Message received() {
        return queue.poll();
    }

}
