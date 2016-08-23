package com.myhome.util;

public class LaunchHsqldbUtil {
    public static void main(String[] args) {
        // <!-- T>java -cp d:\hsqldb-2.3.2.jar org.hsqldb.util.DatabaseManager -->
        // <!-- jdbc:hsqldb:file:C:/hsqldb/data/myHome -->
        org.hsqldb.util.DatabaseManager.main(new String[] {});
    }

}
