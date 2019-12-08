package com.javainuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class SpringBootHelloWorldApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(SpringBootHelloWorldApplication.class, args);
		DispatcherServlet disp = (DispatcherServlet)ctx.getBean("dispatcherServlet");
		disp.setThrowExceptionIfNoHandlerFound(true);
	}
}
