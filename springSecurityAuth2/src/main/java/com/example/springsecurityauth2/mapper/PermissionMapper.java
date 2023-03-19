package com.example.springsecurityauth2.mapper;

import java.util.List;

import com.example.springsecurityauth2.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author zts
 * @date 2023/3/19 00:25
 * @Description
 */
@Mapper
public interface PermissionMapper {

	@Select( "SELECT A.NAME AS roleName,C.url FROM role AS A LEFT JOIN role_permission B ON A.id=B.role_id LEFT JOIN permission AS C ON B.permission_id=C.id" )
	List<RolePermission> getRolePermissions();

}
