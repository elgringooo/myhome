 
package com.myhome.jdk5.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class test.
 * <p>
 * TODO Insert a description here.
 * </p>
 * 
 */
public class Test {
	Map<String, String> updateMap = new HashMap();

	Test() {

		Timer timer = new Timer();
		TimerTask timerTask_updateCache = new TimerTask() {

			public void run() {
				synchronized (updateMap) {
					updateMap.remove(("01"));
				}

			}
		};
		timer.schedule(timerTask_updateCache, 1, 1);

		updateMap.put("01", "gege");
		updateMap.put("02", "dede");
		updateMap.put("03", "gege");
		updateMap.put("04", "ss");
		updateMap.put("05", "df");

		synchronized (updateMap) {
			while (true) {
				for (Map.Entry<String, String> entry : updateMap.entrySet()) {
					entry.getKey();
					entry.getValue();
					System.out.println(entry.getKey() + " " + entry.getValue());

				}

			}

		}
	}

	public static void main(String[] args) {
		new Test();
	}
}
