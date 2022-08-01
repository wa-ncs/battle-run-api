package com.wancs.howmuchspend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/env.yml")
public class HowMuchSpendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HowMuchSpendApplication.class, args);
	}

}
