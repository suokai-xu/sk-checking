package com.sk.colud;
/**
* 2019年6月3日 下午5:38:48
* @HXing xu
* sk-hystrix-dashboard
* 
**/

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
 
import cn.hutool.core.util.NetUtil;
 
@SpringBootApplication
@EnableHystrixDashboard
public class ServiceHystrixDashboardApplication {
    public static void main(String[] args) {
        int port = 8020;
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动%n", port );
            System.exit(1);
        }
        new SpringApplicationBuilder(ServiceHystrixDashboardApplication.class).properties("server.port=" + port).run(args);
 
    }
 
}
