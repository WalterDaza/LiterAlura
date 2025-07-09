package com.alura.challenge.literatura;

import com.alura.challenge.literatura.principal.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class LiteraturaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LiteraturaApplication.class, args);

		Menu menu = context.getBean(Menu.class);
		menu.correrMenu();

	}

}
