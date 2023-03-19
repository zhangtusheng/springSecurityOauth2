package com.example.springsecurityauth2.config;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * @author zts
 * @date 2023/3/19 00:51
 * @Description
 */
@Component
public class MD5PasswordEncoder implements PasswordEncoder {
	@Override
	public String encode(CharSequence charSequence) {
		return charSequence.toString();
	}

	@Override
	public boolean matches(CharSequence charSequence, String s) {
		return Objects.equals(charSequence.toString(),s);
	}
}
