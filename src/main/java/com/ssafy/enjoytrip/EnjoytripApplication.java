package com.ssafy.enjoytrip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.ssafy.enjoytrip.**.model.mapper"})
@SpringBootApplication
public class EnjoytripApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnjoytripApplication.class, args);
	}

}
