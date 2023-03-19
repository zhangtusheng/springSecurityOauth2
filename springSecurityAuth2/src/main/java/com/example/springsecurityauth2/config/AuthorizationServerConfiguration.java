package com.example.springsecurityauth2.config;

import javax.sql.DataSource;

import com.example.springsecurityauth2.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * @author zts
 * @date 2023/3/19 01:02
 * @Description
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


	/**
	 * 注入权限验证控制器 来支持 password grant type
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * 注入userDetailsService，开启refresh_token需要用到
	 */
	@Autowired
	private MyUserDetailsService userDetailsService;

	/**
	 * 数据源
	 */
	@Autowired
	private DataSource dataSource;


	@Autowired
	private WebResponseExceptionTranslator webResponseExceptionTranslator;

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore( dataSource );
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
				.checkTokenAccess("permitAll()")
				.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		//开启密码授权类型
		endpoints.authenticationManager(authenticationManager);
		//配置token存储方式
		endpoints.tokenStore(tokenStore());
		//自定义登录或者鉴权失败时的返回信息
		endpoints.exceptionTranslator(webResponseExceptionTranslator);
		//要使用refresh_token的话，需要额外配置userDetailsService
		endpoints.userDetailsService( userDetailsService );

	}


}