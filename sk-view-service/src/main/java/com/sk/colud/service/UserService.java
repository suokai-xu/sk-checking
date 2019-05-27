package com.sk.colud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.colud.client.UserClientFeign;
import com.sk.colud.entity.User;

/**
* 2019年5月25日 下午6:31:09
* @HXing xu
* sk-view-service
* 
**/

@Service
public class UserService{
	
	
	@Autowired UserClientFeign userClientFeign;
    public List<User> listUsers(){
        return userClientFeign.listUsers();
 
    }
}
