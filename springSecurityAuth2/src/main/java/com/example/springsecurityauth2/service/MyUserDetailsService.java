package com.example.springsecurityauth2.service;

import java.util.List;

import com.example.springsecurityauth2.entity.Role;
import com.example.springsecurityauth2.entity.User;
import com.example.springsecurityauth2.mapper.RoleMapper;
import com.example.springsecurityauth2.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author zts
 * @date 2023/3/19 00:28
 * @Description
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		//查数据库
		User user = userMapper.loadUserByUsername( userName );
		if (null != user) {
			List<Role> roles = roleMapper.getRolesByUserId( user.getId() );
			user.setAuthorities( roles );
		}

		return user;
	}


}
