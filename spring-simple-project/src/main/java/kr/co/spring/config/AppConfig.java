package kr.co.spring.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
@MapperScan("kr.co.spring.mybatis.mapper")
public class AppConfig {
	@Bean
	public Gson gson() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson;
	}
}
