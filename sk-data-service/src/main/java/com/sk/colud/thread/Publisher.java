//package com.sk.colud.thread;
///**
//* 2019年5月14日 下午4:59:52
//* @HXing xu
//* sk-data-service
//* 
//**/
//
//public class Publisher extends Thread {
//	
//	 private final JedisPool jedisPool;
//
//	    public Publisher(JedisPool jedisPool) {
//	        this.jedisPool = jedisPool;
//	    }
//	    
//	    @Override
//	    public void run() {
//	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//	        Jedis jedis = jedisPool.getResource();   //连接池中取出一个连接
//	        while (true) {
//	            String line = null;
//	            try {
//	                line = reader.readLine();
//	                if (!"quit".equals(line)) {
//	                    jedis.publish("mychannel", line);   //从 mychannel 的频道上推送消息
//	                } else {
//	                    break;
//	                }
//	            } catch (IOException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	    }
//}
