package com.example.springsecurityauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author zts
 * @date 2023/3/19 00:59
 * @Description
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	/**
	 * 这里设置需要token验证的url
	 * 这些url可以在WebSecurityConfigurerAdapter中排查掉，
	 * 对于相同的url，如果二者都配置了验证
	 * 则优先进入ResourceServerConfigurerAdapter,进行token验证。而不会进行
	 * WebSecurityConfigurerAdapter 的 basic auth或表单认证。
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().antMatchers("/hi")
				.and()
				.authorizeRequests()
				.antMatchers("/hi").authenticated();
	}


}
