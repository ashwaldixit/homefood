package com.homefood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.homefood.webservice.DemoData;

@SpringBootApplication
@EnableJpaAuditing
public class HomefoodApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(HomefoodApplication.class, args);
		context.getBean(DemoData.class).generateDemoData();
	}
}
