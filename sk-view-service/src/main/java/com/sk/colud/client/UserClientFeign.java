package com.sk.colud.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.sk.colud.entity.User;

/**
* 2019年5月25日 下午6:33:51
* @HXing xu
* sk-view-service
* data-service
**/
@FeignClient(value = "DATA-SERVICE")
public interface UserClientFeign {
	@GetMapping("/users")
    public List<User> listUsers();
}
