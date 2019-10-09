package com.quartz.InsideJob;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

public class RedisSchedularMain {
	static RedisConnection redisConnection = RedisConnection.getInstance();
	static StatefulRedisConnection<String, String> connection = redisConnection.provideRedisConnection();

	public static StatefulRedisConnection<String, String> getConnection() {
		return connection;
	}

	public static void setConnection(StatefulRedisConnection<String, String> connection) {
		RedisSchedularMain.connection = connection;
	}

	public static void main(String[] args) throws SchedulerException, IOException {

		
		String string1 = "./quartzClusterjlinn.properties";
		FileInputStream f1 = new FileInputStream(string1);
		Properties properties = new Properties();
		properties.load(f1);
		
		SchedulerFactory sf = new StdSchedulerFactory(properties);
		Scheduler scheduler = sf.getScheduler();
		scheduler.start();
		
		

		JobDetail job1 = JobBuilder.newJob(QuartzJobA.class).withIdentity("job1", "group1").build();
		JobDetail job2 = JobBuilder.newJob(QuartzJobA.class).withIdentity("job2", "group1").build();
		JobDetail job3 = JobBuilder.newJob(QuartzJobA.class).withIdentity("job3", "group1").build();
		JobDetail job4 = JobBuilder.newJob(QuartzJobA.class).withIdentity("job4", "group1").build();
		JobDetail job5 = JobBuilder.newJob(QuartzJobA.class).withIdentity("job5", "group1").build();
		JobDetail job6 = JobBuilder.newJob(QuartzJobA.class).withIdentity("job6", "group1").build();
		JobDetail job7 = JobBuilder.newJob(QuartzJobA.class).withIdentity("job7", "group1").build();
		JobDetail job8 = JobBuilder.newJob(QuartzJobA.class).withIdentity("job8", "group1").build();
//		JobDetail job9 = JobBuilder.newJob(QuartzJobA.class).withIdentity("job9", "group1").build();

		SimpleTrigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(17).repeatForever())
				.build();
		SimpleTrigger trigger2 = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(17).repeatForever())
				.build();
		SimpleTrigger trigger3 = TriggerBuilder.newTrigger().withIdentity("trigger3", "group1").startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(17).repeatForever())
				.build();
		SimpleTrigger trigger4 = TriggerBuilder.newTrigger().withIdentity("trigger4", "group1").startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(17).repeatForever())
				.build();
		SimpleTrigger trigger5 = TriggerBuilder.newTrigger().withIdentity("trigger5", "group1").startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(17).repeatForever())
				.build();
		SimpleTrigger trigger6 = TriggerBuilder.newTrigger().withIdentity("trigger6", "group1").startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(17).repeatForever())
				.build();
		SimpleTrigger trigger7 = TriggerBuilder.newTrigger().withIdentity("trigger7", "group1").startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(17).repeatForever())
				.build();
		SimpleTrigger trigger8 = TriggerBuilder.newTrigger().withIdentity("trigger8", "group1").startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(17).repeatForever())
				.build();
		SimpleTrigger trigger9 = TriggerBuilder.newTrigger().withIdentity("trigger9", "group1").startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(17).repeatForever())
				.build();
		SimpleTrigger trigger10 = TriggerBuilder.newTrigger().withIdentity("trigger10", "group1").startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(17).repeatForever())
				.build();
		scheduler.scheduleJob(job1, trigger1);
		scheduler.scheduleJob(job2, trigger2);
		scheduler.scheduleJob(job3, trigger3);
		scheduler.scheduleJob(job4, trigger4);
		scheduler.scheduleJob(job5, trigger5);
		scheduler.scheduleJob(job6, trigger6);
		scheduler.scheduleJob(job7, trigger7);
		scheduler.scheduleJob(job8, trigger8);
//		scheduler.scheduleJob(job9, trigger9);
//		scheduler.scheduleJob(job10, trigger10);
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
}
