package com.firmname.travel.server.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.LoggerFactory;

/**
 *	 
 * @author CC
 * @date 2015-12-02
 */
public final class Logger {

	//logger cache
	private final static Map<String, org.slf4j.Logger> loggerCache = new ConcurrentHashMap<String, org.slf4j.Logger>();
	
	public static org.slf4j.Logger getLogger(){
		//TODO: revisit for synchronized
		org.slf4j.Logger logger = null;
		String currentClass =  Thread.currentThread().getStackTrace()[2].getClassName();
		if(loggerCache.containsKey(currentClass)){
			logger = loggerCache.get(currentClass);
		}else{
			logger = LoggerFactory.getLogger(currentClass);
			loggerCache.put(currentClass, logger);
		}
		return logger;
	}
	
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

}
