package com.sk.colud.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
/**
* 2019年5月16日 上午10:51:05
* @HXing xu
* sk-data-service
* 
**/
public class RedisMessageListener implements MessageListener {
 
	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
	 * @return the redisTemplate
	 */
	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}
 
	/**
	 * @param redisTemplate the redisTemplate to set
	 */
	public void setRedisTemplate(RedisTemplate  redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
 
	@Override
	public void onMessage(Message message, byte[] pattern) {
		byte[] body = message.getBody();
		String msgBody = (String) getRedisTemplate().getValueSerializer().deserialize(body);
		System.out.println(msgBody);
		byte[] channel = message.getChannel();
		String msgChannel = (String) getRedisTemplate().getValueSerializer().deserialize(channel);
		System.out.println(msgChannel);
		String msgPattern = new String(pattern);
		System.out.println(msgPattern);
	}
 
	
}