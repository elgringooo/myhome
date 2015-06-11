package com.myhome.jdk5.collection;

/*
  Get Sub List of Java ArrayList Example
  This Java Example shows how to get sub list of java ArrayList using subList
  method by providing start and end index.
 */

import java.util.ArrayList;
import java.util.List;

public class GetSubListOfJavaArrayListExample {

	public static void main(String[] args) {

		// create an ArrayList object
		ArrayList arrayList = new ArrayList();

		// Add elements to Arraylist
		arrayList.add("0");
		arrayList.add("1");
		arrayList.add("2");
		arrayList.add("3");
		arrayList.add("4");

		/*
		 * To get a sub list of Java ArrayList use List subList(int startIndex,
		 * int endIndex) method. This method returns an object of type List
		 * containing elements from startIndex to endIndex - 1.
		 */

		List lst = arrayList.subList(0, 7);
		
		
		
		

		// display elements of sub list.
		System.out.println("Sub list contains : "+lst);
	 

	}
}
/*
 * Output would be Sub list contains : 2 3 2 is removed from sub list After
 * removing 2 from sub list original ArrayList contains : 1 3 4 5
 */
