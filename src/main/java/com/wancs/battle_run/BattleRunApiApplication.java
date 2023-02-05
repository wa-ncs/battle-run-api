package com.wancs.battle_run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@PropertySource(value = "classpath:/env/common.yml", encoding = "UTF-8")
@PropertySource(value = "classpath:/env/${spring.profiles.active}.yml", encoding = "UTF-8")
@EnableJpaAuditing
@ServletComponentScan
@SpringBootApplication
public class BattleRunApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BattleRunApiApplication.class, args);
	}
}
