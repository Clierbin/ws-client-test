package com.xavier.ws.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.ws.Endpoint;

@SpringBootApplication
public class TestPublish {

	public static void main(String[] args) {
		SpringApplication.run(TestPublish.class, args);
		String address = "http://localhost:8081/test";
		Endpoint.publish(address, new TestServiceImpl());
		System.out.println("没报错！");
	}
}
