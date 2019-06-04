package com.sk.colud;
/**
* 2019年6月4日 下午2:02:42
* @HXing xu
* sk-ServiceTurbine
* 
**/

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
 
import cn.hutool.core.util.NetUtil;
 
@SpringBootApplication
@EnableTurbine
public class ServiceTurbineApplication {
    public static void main(String[] args) {
        int port = 8021;
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动%n", port );
            System.exit(1);
        }
        new SpringApplicationBuilder(ServiceTurbineApplication.class).properties("server.port=" + port).run(args);
 
    }
 
}
