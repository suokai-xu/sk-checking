package com.sk.colud.service;
/**
* 2019年3月12日 上午11:16:59
* @HXing xu
* sk-redis
* 
**/

import java.util.List;

import com.sk.colud.entity.User;


public interface UserService {

	/**
	 * 通ID查询
	 * @param id
	 * @return
	 */
	User getUserById(Integer id);
	/**
	 * 通过name 查询
	 * @param name
	 * @return
	 */
	User getUserByName(String name);
	/**
	 * 修改数据
	 * @param user
	 * @return
	 */
	User updateId(User user);
	/**
	 * 通过ID删除
	 * @param id
	 * @return
	 */
	long delUserById(Integer id);

	/**
	 * 查询所有用户数据
	 * @return
	 */
	List<User> listUser();

}
