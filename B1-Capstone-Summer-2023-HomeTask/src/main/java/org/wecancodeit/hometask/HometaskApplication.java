package org.wecancodeit.hometask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class HometaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HometaskApplication.class, args);

		
	}

}
