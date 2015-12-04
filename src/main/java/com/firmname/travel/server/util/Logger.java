package com.firmname.travel.server.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**
 *	 
 * @author CC
 * @date 2015-12-02
 */
public class Logger {

	//logger cache
	private static Map<String, org.slf4j.Logger> loggerCache = new ConcurrentHashMap<String, org.slf4j.Logger>();
	
	
	public static org.slf4j.Logger initLogger(){
		
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
	
	public static void trace(String msg) {
		initLogger().trace(msg);
	}
	
	public static boolean isTraceEnabled() {
		return initLogger().isTraceEnabled();
	}
	
	public static void trace(String format, Object arg) {
		initLogger().trace(format, arg);
	}

	
	public static void trace(String format, Object arg1, Object arg2) {
		initLogger().trace(format, arg1, arg2);
	}

	
	public static void trace(String format, Object... arguments) {
		initLogger().trace(format, arguments);
		
	}

	
	public static void trace(String msg, Throwable t) {
		initLogger().trace(msg, t);
	}

	
	public static boolean isTraceEnabled(Marker marker) {
		return initLogger().isTraceEnabled(marker);
	}

	
	public static void trace(Marker marker, String msg) {
		initLogger().trace(marker, msg);
	}

	
	public static void trace(Marker marker, String format, Object arg) {
		initLogger().trace(marker, format, arg);
	}

	
	public static void trace(Marker marker, String format, Object arg1, Object arg2) {
		initLogger().trace(marker, format, arg1, arg2);
	}

	
	public static void trace(Marker marker, String format, Object... argArray) {
		initLogger().trace(marker, format, argArray);
	}

	
	public static void trace(Marker marker, String msg, Throwable t) {
		initLogger().trace(marker, msg, t);
	}

	
	public static boolean isDebugEnabled() {
		return initLogger().isDebugEnabled();
	}

	
	public static void debug(String msg) {
		initLogger().debug(msg);
	}

	
	public static void debug(String format, Object arg) {
		initLogger().debug(format, arg);
	}

	
	public static void debug(String format, Object arg1, Object arg2) {
		initLogger().debug(format, arg1, arg2);
	}

	
	public static void debug(String format, Object... arguments) {
		initLogger().debug(format, arguments);
	}

	
	public static void debug(String msg, Throwable t) {
		initLogger().debug(msg, t);
	}

	
	public static boolean isDebugEnabled(Marker marker) {
		return initLogger().isDebugEnabled();
	}

	
	public static void debug(Marker marker, String msg) {
		initLogger().debug(marker, msg);
	}

	
	public static void debug(Marker marker, String format, Object arg) {
		initLogger().debug(marker, format, arg);
	}

	
	public static void debug(Marker marker, String format, Object arg1, Object arg2) {
		initLogger().debug(marker, format, arg1, arg2);
	}

	
	public static void debug(Marker marker, String format, Object... arguments) {
		initLogger().debug(marker, format, arguments);
	}

	
	public static void debug(Marker marker, String msg, Throwable t) {
		initLogger().debug(marker, msg, t);
	}

	
	public static boolean isInfoEnabled() {
		return initLogger().isInfoEnabled();
	}

	
	public static void info(String msg) {
		initLogger().info(msg);
	}

	
	public static void info(String format, Object arg) {
		initLogger().info(format, arg);
	}

	
	public static void info(String format, Object arg1, Object arg2) {
		initLogger().info(format, arg1, arg2);
	}

	
	public static void info(String format, Object... arguments) {
		initLogger().info(format, arguments);
	}

	
	public static void info(String msg, Throwable t) {
		initLogger().info(msg, t);
	}

	
	public static boolean isInfoEnabled(Marker marker) {
		return initLogger().isInfoEnabled(marker);
	}

	
	public static void info(Marker marker, String msg) {
		initLogger().info(marker, msg);
	}

	
	public static void info(Marker marker, String format, Object arg) {
		initLogger().info(marker, format, arg);
	}

	
	public static void info(Marker marker, String format, Object arg1, Object arg2) {
		initLogger().debug(marker, format, arg1, arg2);
	}

	
	public static void info(Marker marker, String format, Object... arguments) {
		initLogger().info(marker, format, arguments);
	}

	
	public static void info(Marker marker, String msg, Throwable t) {
		initLogger().debug(marker, msg, t);
	}

	
	public static boolean isWarnEnabled() {
		return initLogger().isWarnEnabled();
	}

	
	public static void warn(String msg) {
		initLogger().warn(msg);
	}

	
	public static void warn(String format, Object arg) {
		initLogger().warn(format, arg);
	}

	
	public static void warn(String format, Object... arguments) {
		initLogger().warn(format, arguments);
	}

	
	public static void warn(String format, Object arg1, Object arg2) {
		initLogger().warn(format, arg1, arg2);
	}

	
	public static void warn(String msg, Throwable t) {
		initLogger().warn(msg, t);
		
	}

	
	public static boolean isWarnEnabled(Marker marker) {
		return initLogger().isWarnEnabled(marker);
	}

	
	public static void warn(Marker marker, String msg) {
		initLogger().warn(marker, msg);
	}

	
	public static void warn(Marker marker, String format, Object arg) {
		initLogger().warn(marker, format, arg);
	}

	
	public static void warn(Marker marker, String format, Object arg1, Object arg2) {
		initLogger().warn(marker, format, arg1, arg2);
	}

	
	public static void warn(Marker marker, String format, Object... arguments) {
		initLogger().warn(marker, format, arguments);
	}

	
	public static void warn(Marker marker, String msg, Throwable t) {
		initLogger().warn(marker, msg, t);
	}

	
	public static boolean isErrorEnabled() {
		return initLogger().isErrorEnabled();
	}

	
	public static void error(String msg) {
		initLogger().error(msg);
	}

	
	public static void error(String format, Object arg) {
		initLogger().error(format, arg);
	}

	
	public static void error(String format, Object arg1, Object arg2) {
		initLogger().error(format, arg1, arg2);
	}

	
	public static void error(String format, Object... arguments) {
		initLogger().error(format, arguments);
	}

	
	public static void error(String msg, Throwable t) {
		initLogger().error(msg, t);
	}

	
	public static boolean isErrorEnabled(Marker marker) {
		return initLogger().isErrorEnabled(marker);
	}

	
	public static void error(Marker marker, String msg) {
		initLogger().error(marker, msg);
	}

	
	public static void error(Marker marker, String format, Object arg) {
		initLogger().error(marker, format, arg);
	}

	
	public static void error(Marker marker, String format, Object arg1, Object arg2) {
		initLogger().error(marker, format, arg1, arg2);
	}

	
	public static void error(Marker marker, String format, Object... arguments) {
		initLogger().error(marker, format, arguments);
	}

	
	public static void error(Marker marker, String msg, Throwable t) {
		initLogger().error(marker, msg, t);
	}

	
}
