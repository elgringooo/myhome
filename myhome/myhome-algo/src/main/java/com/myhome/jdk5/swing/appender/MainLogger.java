package com.myhome.jdk5.swing.appender;
import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class MainLogger {

	public static Logger log = Logger.getLogger("Logger");
	
	private static boolean init = false;
	
	private MainLogger(){}
	
	public static void init(Appender appender,Level l){
		if (init != true){
			log.addAppender(appender);
			log.setLevel(l);
		}
		init = true;
	}
	
	
	public static void logWarn(final String s){		
		log.warn(s);
	}
	
	public static void logError(final String s){
		log.error(s);		
	}
	
	public static void logError(final Throwable ex){
		log.fatal(new String(),ex);
	}
	
	public static void logTrace(final String s){
		log.trace(s);
	}
	
}
