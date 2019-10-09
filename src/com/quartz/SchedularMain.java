package com.quartz;

import java.util.Calendar;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class SchedularMain {

	public static void main(String[] args) throws SchedulerException {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();
	    scheduler.start();

		JobDetail job1 = JobBuilder.newJob(QuartzJobA.class).withIdentity("job1", "group1").build();

		SimpleTrigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
				.startAt(new Date(Calendar.getInstance().getTimeInMillis() + 3000))
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(20))
				.build();
		scheduler.scheduleJob(job1, trigger1);

		RedisClient redisClient = RedisClient.create("redis://masterpassnbiot@10.63.71.35:17000/");
		StatefulRedisConnection<String, String> connection = redisClient.connect();
	//	System.out.println(connection.toString());
		
		RedisCommands<String, String> syncCommands = connection.sync();
		
		syncCommands.set("key1", "value1");
		syncCommands.set("key2", "value2");
		syncCommands.set("key3", "value3");
		syncCommands.set("key4", "value4");
		syncCommands.set("key5", "value5");
	/*	System.out.println(syncCommands.get("key1"));
		System.out.println(syncCommands.get("key2"));
		System.out.println(syncCommands.get("key3"));
		System.out.println(syncCommands.get("key4"));
		System.out.println(syncCommands.get("key5"));
		
		*/
		
		
		// close client and connection
		//connection.close();
		//redisClient.shutdown();
	}
}
