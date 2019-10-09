package com.quartz.InsideJob;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class QuartzJobA implements Job {

	public static int count = 0;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		JobKey jobKey = context.getJobDetail().getKey();
		String jobName = jobKey.getName();
		String group = jobKey.getGroup();
		count++;
		System.out.println("==============JobA running=============\n" + new Date() + "\nJobName : " + jobName
				+ "\nGroupName : " + group + "\nCount : " + count);

		StatefulRedisConnection<String, String> connection = RedisSchedularMain.getConnection();
		RedisCommands<String, String> syncCommands = connection.sync();
		String key1 = syncCommands.get("key1");
		String key2 = syncCommands.get("key2");
		System.out.println(key1 + " " + key2);

		/*
		 * if (key1.equals("value1")) { System.out.println("success"); }
		 */

		/*
		 * RedisClient redisClient =
		 * RedisClient.create("redis://masterpassnbiot@10.63.71.35:17000/");
		 * StatefulRedisConnection<String, String> connection =
		 * redisConnection.connect();
		 * 
		 * RedisCommands<String, String> syncCommands = connection.sync();
		 * 
		 * 
		 * String key1 = syncCommands.get("key1"); String key2 =
		 * syncCommands.get("key2"); String key3 = syncCommands.get("key3"); String key4
		 * = syncCommands.get("key4"); String key5 = syncCommands.get("key5");
		 * 
		 * // System.out.println(syncCommands.get("key1")); //
		 * System.out.println(syncCommands.get("key2"));
		 * 
		 * // defining rules : if (key1.equals("value1")) { //
		 * System.out.println("success");
		 * 
		 * 
		 * }
		 * 
		 * 
		 * // close client and connection connection.close(); redisClient.shutdown();
		 */
	}

}
