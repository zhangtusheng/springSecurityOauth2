package com.example.springsecurityauth2.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author zts
 * @date 2023/3/19 00:20
 * @Description
 */
public class Role implements GrantedAuthority {

	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name;
	}

}
