package com.myhome.jdk5.thread.executor.persistasync;

import java.util.Random;

import com.myhome.jdk5.thread.executor.persistasync.entity.Event;
import com.myhome.jdk5.thread.executor.persistasync.entity.WebServiceInOut;

public class PaylineAPIProducer implements Runnable {

    private MessageManager messageManager;

    public PaylineAPIProducer() {
        messageManager = MessageManager.getInstance();
    }

    public void run() {
        Random r = new Random();
        int bound = r.nextInt(100)+1;
        int done = 0;
        while (done < bound) {
            doSomething();
            done++;
        }

        System.out.printf("Producer %s send message\n", Thread.currentThread().getName());
        messageManager.send();

    }

    public void doSomething() {

        WebServiceInOut inOut = new WebServiceInOut();
        inOut.setId(String.valueOf(Math.abs(new Random().nextInt())));
        inOut.setData("DATA");
        inOut.setDirection("IN");

        Event event = new Event();
        event.setEventId(String.valueOf(Math.abs(new Random().nextInt())));
        event.setMessage("MESSAGE");
        event.setDirection("IN");

        messageManager.put(inOut);
        messageManager.put(event);

    }
}
