package br.com.dexcodifica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Aplicacao {

	@Bean
	public Logger log(InjectionPoint injection) {
		Class<?> injectionClass = injection.getMember().getDeclaringClass();
		return LoggerFactory.getLogger(injectionClass);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/*").allowedOrigins("http://localhost:8081");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Aplicacao.class, args);
	}
}
