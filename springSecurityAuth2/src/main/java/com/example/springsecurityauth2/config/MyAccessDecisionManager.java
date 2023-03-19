package com.example.springsecurityauth2.config;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * @author zts
 * @date 2023/3/19 00:30
 * @Description
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

	private final static Logger logger = LoggerFactory.getLogger(MyAccessDecisionManager.class);

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (null == configAttributes || configAttributes.size() <= 0) {
			return;
		} else {
			String needRole;
			for(Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext(); ) {
				needRole = iter.next().getAttribute();

				for(GrantedAuthority ga : authentication.getAuthorities()) {
					if(needRole.trim().equals(ga.getAuthority().trim())) {
						return;
					}
				}
			}
			throw new AccessDeniedException("当前访问没有权限");
		}
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
