package com.example.springsecurityauth2.config;

import com.example.springsecurityauth2.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * @author zts
 * @date 2023/3/19 00:19
 * @Description
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService userDetailsService;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.requestMatchers()
				.antMatchers("/oauth/**","/login","/login-error")
				.and()
				.authorizeRequests()
				.antMatchers("/oauth/**").authenticated()
				.and()
				.formLogin().loginPage( "/login" ).failureUrl( "/login-error" );
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
			@Override
			public String encode(CharSequence charSequence) {
				System.out.println(charSequence.toString());
				return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
			}
			//对密码进行判断匹配
			@Override
			public boolean matches(CharSequence charSequence, String s) {
				String encode = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
				boolean res = s.equals( encode );
				return res;
			}
		});
	}
}
