package com.sk.colud.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**继承自己的MyMapper
 * Created by LF on 2019/4/19.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}