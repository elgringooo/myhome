package com.myhome.algo.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Implementation Tri a bulle (tri pas super efficace mais facile a aprehender)
 * 
 * http://fr.wikipedia.org/wiki/Tri_%C3%A0_bulles
 * http://www.algolist.net/Algorithms/Sorting/Bubble_sort
 * 
 * @author Thales
 *
 */
public class BubbleSort {

	public BubbleSort() {

	}

	public static void main(String[] args) {
		// Simple test
		int[] tab = new int[] { 5, 1, 2, 39, 2, 3, 5 };
		System.out.println("Before " + Arrays.toString(tab));
		BubbleSort.sort(tab);
		System.out.println("After " + Arrays.toString(tab));

		//Comparaison entre le Bulle Sort et le QUick Sort de java
		Random rand = new Random();
		int size = 100000;
		int[] mytab = new int[size];
		for (int i = 0; i < size; i++) {
			mytab[i] = rand.nextInt();
		}

		int[] mytab2 = mytab.clone();
		long start = System.currentTimeMillis();
		Arrays.sort(mytab);
		System.out.println((System.currentTimeMillis() - start) + "ms");

		start = System.currentTimeMillis();
		sort(mytab2);
		System.out.println((System.currentTimeMillis() - start) + "ms");

	}

	public static void sort(int tab[]) {
		// TODO Auto-generated method stub
		int j = 0;
		boolean permut = true;
		while (permut) {

			int tmp = 0;
			permut = false;
			j++;
			for (int i = 0; i < tab.length - j; i++) {
				if (tab[i] > tab[i + 1]) {
					// On permute
					tmp = tab[i + 1];
					tab[i + 1] = tab[i];
					tab[i] = tmp;
					permut = true;
				}
			}

		}

	}
}
