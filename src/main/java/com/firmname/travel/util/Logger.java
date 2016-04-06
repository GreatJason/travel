package com.firmname.travel.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

/**
 *	 
 * @author CC
 * @date 2015-12-02
 */
public final class Logger {

	//logger cache
	private final static Map<String, org.slf4j.Logger> loggerCache = new HashMap<String, org.slf4j.Logger>();
	private final static String loggerClassName;
	
	public static void trace(String format, Object... args) {
		if(getLogger().isTraceEnabled()){
			getLogger().trace(format, args);
		}
	}
	
	public static void trace(String msg, Throwable t) {
		if(getLogger().isTraceEnabled()){
			getLogger().trace(msg, t);
		}
	}
	
	public static void debug(String format, Object... args) {
		if(getLogger().isDebugEnabled()){
			getLogger().debug(format, args);
		}
	}
	
	public static void debug(String msg, Throwable t) {
		if(getLogger().isDebugEnabled()){
			getLogger().debug(msg, t);
		}
	}
	
	public static void info(String format, Object... args) {
		if(getLogger().isInfoEnabled()){
			getLogger().info(format, args);
		}
	}
	
	public static void info(String msg, Throwable t) {
		if(getLogger().isInfoEnabled()){
			getLogger().info(msg, t);
		}
	}
	
	public static void warn(String format, Object... args) {
		if(getLogger().isWarnEnabled()){
			getLogger().warn(format, args);
		}
	}

	public static void warn(String msg, Throwable t) {
		if(getLogger().isWarnEnabled()){
			getLogger().warn(msg, t);
		}
	}

	public static void error(String format, Object... args) {
		if(getLogger().isErrorEnabled()){
			getLogger().error(format, args);
		}
	}
	
	public static void error(String msg, Throwable t) {
		if(getLogger().isErrorEnabled()){
			getLogger().error(msg, t);
		}
	}
	
	public static void fatal(String format, Object... args) {
		if(getLogger().isErrorEnabled()){
			getLogger().error(format, args);
		}
		System.exit(1);
	}
	
	public static void fatal(String msg, Throwable t) {
		if(getLogger().isErrorEnabled()){
			getLogger().error(msg, t);
		}
		System.exit(1);
	}
	
	static {
		loggerClassName = new Object(){
			public String getClassName() 
			{
				String innerClass = this.getClass().getName();
				return innerClass.substring(0, innerClass.lastIndexOf('$'));
			}
		}.getClassName();
	}
	
	private static org.slf4j.Logger getLogger(){
		//It is acceptable that not synchronize
		String currentClass = findCallerClass();
		org.slf4j.Logger logger = loggerCache.get(currentClass);
		if(logger == null){
			logger = LoggerFactory.getLogger(currentClass);
			loggerCache.put(currentClass, logger);
		}
		return logger;
	}
	
	private static String findCallerClass(){
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		for(int i = 1; i < ste.length; ++i){
			if(!ste[i].getClassName().equals(loggerClassName)){
				return ste[i].getClassName();
			}
		}
		return loggerClassName;
	}
}
