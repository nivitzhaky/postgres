package com.handson.postgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;


@SpringBootApplication
@EntityScan( basePackages = {"com.handson.postgres"} )
@EnableSwagger2
public class PostgresApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostgresApplication.class, args);
	}
}
