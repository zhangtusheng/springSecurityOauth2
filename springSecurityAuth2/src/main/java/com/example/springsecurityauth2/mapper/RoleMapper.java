package com.example.springsecurityauth2.mapper;

import java.util.List;

import com.example.springsecurityauth2.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zts
 * @date 2023/3/19 00:25
 * @Description
 */
@Mapper
public interface RoleMapper {

	@Select( "SELECT A.id,A.name FROM role A LEFT JOIN user_role B ON A.id=B.role_id WHERE B.user_id=${userId}" )
	List<Role> getRolesByUserId(@Param("userId") Long userId);

}
