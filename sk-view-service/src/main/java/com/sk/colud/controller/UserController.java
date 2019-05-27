package com.sk.colud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {
	@Autowired 
	private UserService userService;
    
    @RequestMapping("/users")
    public Object products(Model m) {
        List<User> us = userService.listUsers();
        m.addAttribute("us", us);
        return "users";
    }
}
