package com.sk.colud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sk.colud.entity.User;
import com.sk.colud.utils.MyMapper;

@Mapper
public interface UserMapper extends MyMapper<User> {
	
	@Select("select * from m_user where id=#{id} ")
	User getUserById(Integer id);
	
	@Select("delete from m_user where id=#{id} ")
	Long delUserById(Integer id);
	
	@Select("select * from m_user where nick=#{name} ")
	User getUserByName(String name);
	
	@Update("UPDATE m_user set name=#{name} , salt=#{salt} where id=#{id} ")
	void updateId(@Param("name") String name,@Param("salt") String salt,@Param("id") Integer id);
	
	@Select("select * from m_user")
	List<User> listUser();


}
