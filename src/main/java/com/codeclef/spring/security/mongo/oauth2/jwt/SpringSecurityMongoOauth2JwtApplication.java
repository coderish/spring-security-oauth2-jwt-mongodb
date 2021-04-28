package com.codeclef.spring.security.mongo.oauth2.jwt;

import com.codeclef.spring.security.mongo.oauth2.jwt.model.mongo.User;
import com.codeclef.spring.security.mongo.oauth2.jwt.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

@SpringBootApplication
public class SpringSecurityMongoOauth2JwtApplication implements CommandLineRunner {

	@Bean
	CommandLineRunner init(UserServiceImpl userService) {
		return (evt) -> {
			User admin = new User("admin", "admin", AuthorityUtils.createAuthorityList("ADMIN", "USER"));
			User guest = new User("guest", "guest", AuthorityUtils.createAuthorityList("USER"));

			try {
				userService.getByUsername("admin");
				userService.getByUsername("guest");
			}
			catch (UsernameNotFoundException exception) {
				userService.createAll(Arrays.asList(admin, guest));
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityMongoOauth2JwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {}
}
