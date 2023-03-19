package com.example.springsecurityauth2.mapper;

import com.example.springsecurityauth2.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zts
 * @date 2023/3/19 00:24
 * @Description
 */
@Mapper
public interface UserMapper {

	@Select( "select id , username , password from user where username = #{username}" )
	User loadUserByUsername(@Param("username") String username);

}
