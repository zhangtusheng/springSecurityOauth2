package com.example.springsecurityauth2.entity;

/**
 * @author zts
 * @date 2023/3/19 00:20
 * @Description
 */
public class RolePermission {

	private String url;
	private String roleName;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


}