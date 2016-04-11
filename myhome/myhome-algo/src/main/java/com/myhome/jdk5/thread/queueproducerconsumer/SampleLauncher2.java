package com.myhome.jdk5.thread.queueproducerconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SampleLauncher2 {
    public static void main(String[] args) {

        ExecutorService consumerPool = Executors.newSingleThreadExecutor();
        consumerPool.execute(new MessageConsumerTask());
        MessageQueueManager instance = MessageQueueManager.getInstance();
        System.out.println("Sending 3 objects");
        instance.put("fdsfds");
        instance.put("221");
        instance.put("321");
        instance.send();
        System.out.println("Sending 4 objects");
        instance.put("141");
        instance.put("454");
        instance.put("54");
        instance.put("54");
        instance.send();

        System.out.println("Sending nothing");
        instance.send();
        System.out.println("Sending 1 object");
        instance.put("2xcfd");
        instance.send();

    }
}
