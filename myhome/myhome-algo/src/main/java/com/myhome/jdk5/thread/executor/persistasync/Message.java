package com.myhome.jdk5.thread.executor.persistasync;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Message.
 */
public class Message {

    /** The name. */
    private String name;

    /** The type / object map . */
    private List<Object> objects = new ArrayList<Object>();

    /**
     * Instantiates a new message.
     * @param name the name
     * @param map the map
     */
    public Message(String name) {
        this.name = name;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void add(Object o) {
        this.objects.add(o);

    }

    public void addAll(List<Object> others) {
        this.objects.addAll(others);

    }

}
