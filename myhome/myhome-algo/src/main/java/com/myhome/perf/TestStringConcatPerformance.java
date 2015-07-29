package com.myhome.perf;

public class TestStringConcatPerformance {
    public static final String BLAH = "Blah ";
    public static final String BLAH2 = " Blah";
    public static final String BLAH3 = "Blah %d Blah";

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        int i = 0;
        long prev_time = System.currentTimeMillis();
        long time;
        int numLoops = 1000000;

        for (i = 0; i < numLoops; i++) {
            String s = BLAH + i + BLAH2;
        }
        time = System.currentTimeMillis() - prev_time;

        System.out.println("Time after for loop " + time);

        prev_time = System.currentTimeMillis();
        for (i = 0; i < numLoops; i++) {
            String s = String.format(BLAH3, i);
        }
        time = System.currentTimeMillis() - prev_time;
        System.out.println("Time after for loop " + time);

        prev_time = System.currentTimeMillis();
        for (i = 0; i < numLoops; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(BLAH);
            sb.append(i);
            sb.append(BLAH2);
            String s = sb.toString();
        }
        time = System.currentTimeMillis() - prev_time;
        System.out.println("Time after for loop " + time);
    }
}
