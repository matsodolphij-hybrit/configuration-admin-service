package org.hybrit.carbonite.configurationadminservice;

import org.hybrit.carbonite.mongo.Mongo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		int count = Mongo.INSTANCE.getCountInCollection("assets");
		System.out.println("the count from the main " + count);
}
}
