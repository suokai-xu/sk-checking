package com.sk.colud.mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface BaseMapper{
	
    int insert(Map params);

    int update(Map params);

    int delete(Map params);

    HashMap queryForObject(Map params);
}
