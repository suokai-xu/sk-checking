package com.sk.colud.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sk.colud.common.IConstants;
import com.sk.colud.entity.JsonBean;
import com.sk.colud.entity.User;
import com.sk.colud.service.UserService;
import com.sk.colud.utils.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api("UserController--用户API")
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	/**
	 * 查询用户列表
	 * @return
	 */
	@ApiOperation(value="获取用户列表", notes="获取用户列表")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
    public Object getUserList() {
        List<User> ps = userService.listUser();
        return ps;
    }
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "addUser", notes = "管理员接口新增用户")
    @RequestMapping(method = RequestMethod.POST, value = "/adduser")
    public String addUser(@RequestParam String user) {
        JsonBean reData = new JsonBean();
        User userInfo = (User) JSONObject.parseObject(user, User.class);
        if (userService.addUserInfo(userInfo)) {
            reData.setStatus(IConstants.RESULT_INT_SUCCESS);
            reData.setMessage("新增成功");
        } else {
            reData.setStatus(IConstants.RESULT_INT_ERROR);
            reData.setMessage("新增失败");
        }
        return JSONObject.toJSONString(reData);
    }
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "addUser1", notes = "管理员接口新增用户")
    @RequestMapping(method = RequestMethod.GET, value = "/adduser1")
    public String addUser1() {
        JsonBean reData = new JsonBean();
        User userInfo = new User();
        userInfo.setName("root");
        userInfo.setPassword("root.admin");
        userInfo.setSalt("123456");
        if (userService.addUserInfo(userInfo)) {
            reData.setStatus(IConstants.RESULT_INT_SUCCESS);
            reData.setMessage("新增成功");
        } else {
            reData.setStatus(IConstants.RESULT_INT_ERROR);
            reData.setMessage("新增失败");
        }
        return JSONObject.toJSONString(reData);
    }
	
	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 */
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
	@RequestMapping(value = "getUserById/{id}", method = RequestMethod.GET)
	public  ResponseEntity<JsonResult> getUserById (@PathVariable(value = "id") Integer id) {
		log.info("根据ID获取用户信息");
		JsonResult r = null;
		try {
			User user = userService.getUserById(id);
			if(user == null) {
				r = new JsonResult("没有数据返回！！！", "ok");
			}else {
				r = new JsonResult(user, "ok");
			}
			
			
		} catch (Exception e) {
			r = new JsonResult(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	
	/**
	 * 根据ID删除用户
	 * @param id
	 * @return
	 */
	@ApiOperation(value="根据ID删除用户信息", notes="根据url的id来删除用户信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
	@RequestMapping(value = "delUserById/{id}", method = RequestMethod.GET)
	public  ResponseEntity<JsonResult> delUserById (@PathVariable(value = "id") Integer id) {
		log.info("根据ID删除用户信息！！！");
		JsonResult r = null;
		try {
			long l = userService.delUserById(id);
			r = new JsonResult(l, "ok");
			
		} catch (Exception e) {
			r = new JsonResult(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	
	@ApiOperation(value="根据ID修改用户数据", notes="根据url的id修改用户信息")
	@ApiImplicitParams(
	{
		@ApiImplicitParam(name="id",value="用户id",required=true,paramType="path"),
		@ApiImplicitParam(name="uname",value="姓名",required=true,paramType="path"),
		@ApiImplicitParam(name="phone",value="电话",required=true,paramType="path")
	})
	@RequestMapping(value = "updateId/{id}&{uname}&{phone}", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> updateId(@PathVariable(value="id") Integer id, @PathVariable(value="uname") String uname,@PathVariable(value="phone") String phone ) {

		log.info("根据ID获取需要修改的用户信息id-"+ id +"---name-"+uname +"--phone-"+phone);
		JsonResult r = null;
		try {
			// 获取到用户信息进行修改
			User user = new User();
			user.setId(id);
			user.setName(uname);
			user.setSalt(phone);
			// 修改数据库
			user = userService.updateId(user);
			
			
			r = new JsonResult(user, "ok");
			
		} catch (Exception e) {
			r = new JsonResult(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	
}
