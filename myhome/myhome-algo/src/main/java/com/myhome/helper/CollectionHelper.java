package com.myhome.helper;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionHelper {

	private CollectionHelper() {
	}

	/**
	 * Copy iterator in list.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param iter
	 *            the iter
	 * @return the list
	 */
	public static <T> List<T> copyIterator(Iterator<T> iter) {
		List<T> copy = new ArrayList<T>();
		while (iter.hasNext())
			copy.add(iter.next());
		return copy;
	}

}
