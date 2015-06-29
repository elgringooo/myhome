package com.myhome.perf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestListSize {

    @org.junit.Test
    public void tes1() {

        String[] s = { "test0", "test1", "test2", "Test3" };

        List<String> test = new MyLists();
        test.addAll(Arrays.asList(s));

        System.out.println("Test 1");
        for (int i = 0; i < test.size(); i++) {
            System.out.println(test.get(i));
        }

        System.out.println("Test 2");

        for (int i = 0, size = test.size(); i < size; i++) {
            System.out.println(test.get(i));
        }

    }

}

class MyLists extends ArrayList<String> {

    /**
     * 
     */
    private static final long serialVersionUID = -8018012132908506263L;

    public MyLists() {

    }

    @Override
    public int size() {
        System.out.println("Size");
        return super.size();
    }

}
