package com.purushotham.serviceregistrory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistroryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistroryApplication.class, args);
	}

}
