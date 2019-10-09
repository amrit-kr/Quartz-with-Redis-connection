package com.quartz.InsideJob;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class SchedularLoopMain {
	//public static Logger log = Logger.getLogger(SchedularLoopMain.class);
	
	
	
	
	static RedisConnection redisConnection = RedisConnection.getInstance();
	static StatefulRedisConnection<String, String> connection = redisConnection.provideRedisConnection();

	public static StatefulRedisConnection<String, String> getConnection() {
		return connection;
	}

	public static void setConnection(StatefulRedisConnection<String, String> connection) {
		SchedularLoopMain.connection = connection;
	}

	public static void main(String[] args) throws SchedulerException, IOException {
		
		
		loadLog4jParams();
		String currentLogLevel= org.apache.log4j.Logger.getRootLogger().getLevel().toString();
		System.out.println("............................................Log Level is: " +currentLogLevel);
		String string1 = "./quartzClusterjlinn.properties";
		FileInputStream f1 = new FileInputStream(string1);
		Properties properties = new Properties();
		properties.load(f1);
		
		SchedulerFactory sf = new StdSchedulerFactory(properties);
		Scheduler scheduler = sf.getScheduler();
		scheduler.start();
		
		for(int i=0;i<60000;i++)
		{
			
			JobDetail job = JobBuilder.newJob(QuartzJobA.class).withIdentity("job"+i, "group"+i).build();
			
			SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger"+i, "group"+i).startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(12000).withRepeatCount(1))
					.build();
			scheduler.scheduleJob(job, trigger);
		}

		// RedisClient redisClient =
		// RedisClient.create("redis://masterpassnbiot@10.63.71.35:17000/");

		// System.out.println(connection.toString());

		RedisCommands<String, String> syncCommands = connection.sync();
		

		// syncCommands.set("jobName", "JobX");
		// syncCommands.set("jobGroup", "GroupX");

		// System.out.println(syncCommands.get("jobName"));
		// System.out.println(syncCommands.get("jobGroup"));

		// close client and connection
		// connection.close();
		// redisClient.shutdown();
	}
	
	private static void loadLog4jParams() {
		final Logger log = Logger.getLogger(SchedularLoopMain.class);
		Properties log4jProperties=new Properties();
		
		log4jProperties.setProperty("log4j.rootLogger",
				"ERROR");
		log4jProperties.setProperty(
				"log4j.category.com.jio.nbiot", "log4j.rootLogger");
//		log4jProperties.setProperty(
//				Log4jPropParameters.log4j_category_com_rancore, log4jProperties
//						.getProperty(Log4jPropParameters.log4j_rootLogger));
//		log4jProperties.setProperty(
//				Log4jPropParameters.log4j_category_org_jsmpp_session, "false");
//
//		// Set all other parameters of log4j
//		log4jProperties.setProperty("log4j.appender.FILE",
//				"com.jio.nbiot.logging.LastFileAppender");
//
//		log4jProperties.setProperty(
//				Log4jPropParameters.log4j_appender_FILE_layout,
//				Log4jPropParameters.log4j_appender_FILE_layout_Value);
//
//		log4jProperties
//				.setProperty(
//						Log4jPropParameters.log4j_appender_FILE_layout_ConversionPattern,
//						Log4jPropParameters.log4j_appender_FILE_layout_ConversionPattern_Value);
//
//		log4jProperties.setProperty("log4j.appender.CONSOLE",
//				"org.apache.log4j.ConsoleAppender");
//
//		log4jProperties.setProperty(
//				Log4jPropParameters.log4j_appender_CONSOLE_layout,
//				Log4jPropParameters.log4j_appender_CONSOLE_layout_Value);
//
//		log4jProperties
//				.setProperty(
//						Log4jPropParameters.log4j_appender_CONSOLE_layout_ConversionPattern,
//						Log4jPropParameters.log4j_appender_CONSOLE_layout_ConversionPattern_Value);
//
//		log4jProperties.setProperty(
//				Log4jPropParameters.log4j_additivity_com_rancore_saas,
//				Log4jPropParameters.log4j_additivity_com_traffix_Value);
//
//		log4jProperties.setProperty(
//				Log4jPropParameters.log4j_additivity_com_rancore,
//				Log4jPropParameters.log4j_additivity_com_rancore_Value);
//
//		log4jProperties.setProperty(
//				Log4jPropParameters.log4j_logger_TraceLogger,
//				Log4jPropParameters.log4j_logger_TraceLogger_Value);
//
//		log4jProperties.setProperty(Log4jPropParameters.log4j_appender_TRACE,
//				Log4jPropParameters.log4j_appender_TRACE_Value);
//
//		log4jProperties.setProperty(
//				Log4jPropParameters.log4j_appender_TRACE_layout,
//				Log4jPropParameters.log4j_appender_TRACE_layout_Value);
//
//		log4jProperties
//				.setProperty(
//						Log4jPropParameters.log4j_appender_TRACE_layout_ConversionPattern,
//						Log4jPropParameters.log4j_appender_TRACE_layout_ConversionPattern_Value);
//
//		log4jProperties.setProperty(
//				Log4jPropParameters.log4j_additivity_TraceLogger,
//				Log4jPropParameters.log4j_additivity_TraceLogger_Value);
//		
//		log4jProperties.setProperty(Log4jPropParameters.log4j_appender_fileLogger_datePattern_name,
//				Log4jPropParameters.log4j_appender_fileLogger_datePattern_value);
		
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date CurrentDate= new Date();
//		
//	
//		String formattedDate = formatter.format(CurrentDate);
//		filename=log4jProperties.getProperty("log4j.appender.FILE.file");
//	
//		log4jProperties.setProperty("log4j.appender.FILE.file",filename+"_"+formattedDate+".log");
	
		PropertyConfigurator.configure(log4jProperties);
	}
}
