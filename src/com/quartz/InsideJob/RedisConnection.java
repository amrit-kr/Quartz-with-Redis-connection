package com.quartz.InsideJob;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;

/**
 * @author Amrit1.Kumar
 *
 */
public class RedisConnection {
	private static RedisConnection instance = null;

	public StatefulRedisConnection<String, String> provideRedisConnection() {
		
		RedisClient rclient = RedisClient.create("redis://masterpassnbiot@10.63.71.35:17000/");
		StatefulRedisConnection<String, String> connection = rclient.connect();
		return connection;
	}

	private RedisConnection() {

	}

	public static RedisConnection getInstance() {
		if (instance == null) {
			instance = new RedisConnection();

		}
		return instance;

	}
}
