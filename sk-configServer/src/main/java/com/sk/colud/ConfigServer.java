package com.sk.colud;
/**
* 2019年5月30日 上午11:34:01
* @HXing xu
* sk-configServer
* 
**/
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import cn.hutool.core.util.NetUtil;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
@EnableEurekaClient
public class ConfigServer {
	public static void main(String[] args) {
    	int port = 8030;
		if(!NetUtil.isUsableLocalPort(port)) {
			System.err.printf("端口%d被占用了，无法启动%n", port );
    		System.exit(1);
    	}
        new SpringApplicationBuilder(ConfigServer.class).properties("server.port=" + port).run(args);
	
	}
}
