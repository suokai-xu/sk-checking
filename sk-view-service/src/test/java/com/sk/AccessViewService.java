package com.sk;
/**
* 2019年6月3日 下午5:41:05
* @HXing xu
* sk-view-service
* 
**/

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;
 
public class AccessViewService {
	 public static void main(String[] args) {
         
	        while(true) {
	            ThreadUtil.sleep(1000);
	            access(8012);
	            access(8013);
	        }
	         
	    }
	 
	    public static void access(int port) {
	        try {
	            String html= HttpUtil.get("http://windows-aogiktg:8040/api-view/users");
	            System.out.printf("%d 地址的视图服务访问成功，返回大小是 %d%n" ,port, html.length());
	        }
	        catch(Exception e) {
	            System.err.printf("%d 地址的视图服务无法访问%n",port);
	        }
	    }
}
