package com.wancs.howmuchspend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:/env/common.yml", encoding = "UTF-8")
@PropertySource(value = "classpath:/env/${spring.profiles.active}.yml", encoding = "UTF-8")
@SpringBootApplication
public class HowMuchSpendApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(HowMuchSpendApplication.class, args);
	}

}
