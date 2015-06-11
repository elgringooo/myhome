package com.myhome.helper;

/**
 * @author 
 *
 */
public class Timer {

	/** long timer */
	private long timer;

	/** String trace */
	private String trace;

	/**
	 * Constructor
	 */
	public Timer() {
	}

	/**
	 * @param String comment
	 * @return Timer this
	 */
	public Timer start(String comment) {
		timer = System.currentTimeMillis();
		trace = comment;
		return this;
	}

	/**
	 */
	public String stop() {
		return trace + " " + (System.currentTimeMillis() - timer) + " ms";
	}
}
