package com.myhome.jdk5.gestion_exception;



public class TestException {

	
	public static void main(java.lang.String[] args) {
		// Insert code to start the application here.
		int i = 3;
		int j = 0;
		try {
			System.out.println("résultat = " + (i / j));
		} catch (ArithmeticException e) {
	           System.out.println("getmessage");
	            System.out.println(e.getMessage());
	            System.out.println(" ");
	            System.out.println("toString");
	            System.out.println(e.toString());
	            System.out.println(" ");
	            System.out.println("printStackTrace");
	            e.printStackTrace();

		}
	}
}
