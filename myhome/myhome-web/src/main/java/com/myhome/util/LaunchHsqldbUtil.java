package com.myhome.util;

public class LaunchHsqldbUtil {
	public static void main(String[] args) {

		// jdbc:hsqldb:file:base/mydb
		//jdbc:hsqldb:file:C:\hsqldb\data\maBase
		org.hsqldb.util.DatabaseManager.main(new String[] {});
	}

}
