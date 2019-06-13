package org.fukua.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class DemoApplication {
	@Autowired
	private ApplicationContext appContext;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void postConstruct(){
		appContext.getBean(StartupDemoApplicationScenario.class).createUserForBasicAuthentication();
	}
}
