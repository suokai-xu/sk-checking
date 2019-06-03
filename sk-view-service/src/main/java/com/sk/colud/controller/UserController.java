package com.sk.colud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sk.colud.entity.User;
import com.sk.colud.service.UserService;

/**
* 2019年5月25日 下午6:37:30
* @HXing xu
* sk-view-service
* 
**/
@Controller
@RefreshScope
public class UserController {
	
	@Value("${version}")
	String version;
	
	@Autowired 
	private UserService userService;
    
    @RequestMapping("/users")
    public Object userAll(Model m) {
        List<User> us = userService.listUsers();
        m.addAttribute("version", version); 
        m.addAttribute("us", us);
        return "users";
    }
}
