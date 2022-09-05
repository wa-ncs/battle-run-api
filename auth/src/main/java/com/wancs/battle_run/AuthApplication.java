package com.wancs.battle_run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:/env/common.yml", encoding = "UTF-8")
@PropertySource(value = "classpath:/env/${spring.profiles.active}.yml", encoding = "UTF-8")
@SpringBootApplication
public class AuthApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
}
