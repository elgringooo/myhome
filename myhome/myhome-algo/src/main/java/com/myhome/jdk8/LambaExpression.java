package com.myhome.jdk8;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Lamba Expression
 * @author glevi
 */
public class LambaExpression {
    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        List<String> tes = new ArrayList<String>();
        tes.add("levi");
        tes.add("gege");
        tes.stream().forEach(s -> System.out.println(s));
    }
}
