package br.com.dexcodifica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Aplicacao {

	
	@Bean
	public Logger log(InjectionPoint injection) {
		Class<?> injectionClass = injection.getMember().getDeclaringClass();
		return LoggerFactory.getLogger(injectionClass);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Aplicacao.class, args);
	}
}
