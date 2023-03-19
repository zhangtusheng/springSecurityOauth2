package com.example.springsecurityauth2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class SpringSecurityAuth2ApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	public void testPassword() {
		String encode = DigestUtils.md5DigestAsHex("123456".getBytes());
		System.out.println(encode);
	}
}
