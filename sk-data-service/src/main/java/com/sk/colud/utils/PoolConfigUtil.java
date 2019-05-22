package com.sk.colud.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;
 

/**
* 2019年5月22日 下午3:15:52
* @HXing xu
* sk-data-service
* 
**/
@Service
public class PoolConfigUtil {
	
	public static JedisPool getSetKey() {
		
		GenericObjectPoolConfig config = new GenericObjectPoolConfig(); //JedisPool 是单例的，需要 new
//		 一个 GenericObjectPoolConfig
//		 对象
//		JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1",  6379, 10000,"123456"); // 建立连接池的连接

//		JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(8);
        config.setMaxIdle(8);
        config.setMinIdle(0);
        config.setMaxWaitMillis(-1);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        config.setTestWhileIdle(true);
        String host = "127.0.0.1";
        int port = 6379;
        int timeOut = 5000;
        String password = "123456";
        JedisPool jedisPool = new JedisPool(config, host, port, timeOut,password);

		
		return jedisPool;
	}

}
