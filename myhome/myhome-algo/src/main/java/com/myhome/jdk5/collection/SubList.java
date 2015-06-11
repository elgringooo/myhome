package com.myhome.jdk5.collection;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class SubList {
	int BATCH_SIZE = 3;
	List<String> transactions = new ArrayList<String>();

	public SubList() {

		for (int i = 0; i < 400000; i++) {
			transactions.add("tttt" + i);
		}

	}

	public static void main(String[] args) {

		SubList sub = new SubList();
		long t2 = sub.test2();
		long t1 = sub.test1();
		

		System.out.println(t1 + " vs " + t2);
	}

	public long test1() {
		long d1 = System.currentTimeMillis();
		int endIndex = 0;
		int startIndex = 0;
		int size = transactions.size();
		while (endIndex != size) {
			endIndex = startIndex + BATCH_SIZE;
			if (endIndex > size) {
				endIndex = size;
			}
			List<String> subList = transactions.subList(startIndex, endIndex);
			// System.out.println("Processing Transaction from "
			// + (startIndex + 1) + " to " + endIndex);
			System.out.println(subList);
			startIndex = endIndex;

		}
		return (System.currentTimeMillis() - d1) / 1000;

	}

	public long test2() {
		System.out.println("----------------");
		long d1 = System.currentTimeMillis();
		List<List<String>> s = Lists.partition(transactions, BATCH_SIZE);
		for (List<String> sub : s) {
			System.out.println(sub);
		}
		return (System.currentTimeMillis() - d1) / 1000;
	}
}
