//package com.sk.colud;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.sk.colud.entity.User;
//import com.sk.colud.mapper.UserMapper;
//import com.sk.colud.thread.Pub;
//
//@RunWith(SpringRunner.class)
//@ContextConfiguration(locations = "classpath:application.properties")
//public class DataApplicationTests {
//	
//	 @Autowired
//	 private UserMapper userDao;
//	 @Autowired
//	 private RedisTemplate redisTemplate;
//	
////	 @Autowired
////	    private Pub pub;
//	    
//	    @Test
//	    public void test() throws InterruptedException{
//	        User u  = new User();
//	        u.setName("wzg");
//	        u.setPassword("root.admin");
//	        u.setSalt("1123");
//	       // pub.sendMessage("dddchannel", "我发消息了");
//	       // pub.sendMessage("cccchannel", u);
//	        Thread.sleep(100);//jackson 反向序列化慢
//
//	    }
//
//}
