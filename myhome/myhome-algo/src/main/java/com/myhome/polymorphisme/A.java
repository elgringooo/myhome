package com.myhome.polymorphisme;

public class A {
    public static final String CONSTANTE = "CONSTANTE A";

    public void display() {
        System.out.println("GO " + CONSTANTE+ " class"+this.getClass().getSimpleName());
    }

    private static void test(A a) {
        a.display();
    }

    public static void main(String[] args) {

        A a = new A();
        B b = new B();

        test(b);

    }

}
