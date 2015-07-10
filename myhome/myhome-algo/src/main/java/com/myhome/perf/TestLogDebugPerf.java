package com.myhome.perf;

import org.apache.log4j.Logger;

public class TestLogDebugPerf {

    private static Logger logger = Logger.getLogger(TestLogDebugPerf.class);

    public static void main(String[] args) {

        final long n = 100000L;
        long start = System.currentTimeMillis();
        for (long i = 0; i < n; i++) {
            if (logger.isDebugEnabled()) {
                logger.debug("je veux debuger le resultat " + methodeQuiPrendDuTemps());
            }
        }

        // Lorsque la ligne logger.debug est �valu�e, le param�tre est construit avant l'appel de la m�thode. Ce qui fait que m�me si le niveau de log est en
        // info, la
        // methodeQuiPrendDuTemps() va �tre appel�e alors que la log ne sera pas visible. Pour �viter ce probl�me nous avions l'habitude en java de rajouter
        // isDebug
        // devant le logger :
        long t1 = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        for (long i = 0; i < n; i++) {
            logger.debug("je veux debuger le resultat " + methodeQuiPrendDuTemps());
        }

        long t2 = System.currentTimeMillis() - start;
        System.out.println("Avec isDebugEnabled"+t1 + " ---  Sans isDebugEnabled" + t2);

    }

    private static String methodeQuiPrendDuTemps() {
        return "";

    }
}
