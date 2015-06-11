package com.myhome.jdk5.file.inputstream;

import java.lang.reflect.Field;

public class Person {
	
	
    public final static String NOM = "nom";
    
    public final static String PRENOM = "prenom";
	private String lastName;
	
	private String firstName;
	
	public static void main(String[] args) {
		
		
		Field[]fields = Person.class.getDeclaredFields();
		
		for(Field f: fields){
			

			System.out.println(f.getName());
		}
		
	}

}
