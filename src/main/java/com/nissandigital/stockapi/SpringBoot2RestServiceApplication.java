package com.nissandigital.stockapi;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBoot2RestServiceApplication {

	public static void main(String[] args) {
		
		
		
		
		ApplicationContext ctx  = SpringApplication.run(SpringBoot2RestServiceApplication.class, args);
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
//		    System.out.println(beanName);
		}
	}
}
