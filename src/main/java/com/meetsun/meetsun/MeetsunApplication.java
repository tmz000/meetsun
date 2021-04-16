package com.meetsun.meetsun;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
 
@SpringBootApplication
@EnableScheduling
public class MeetsunApplication{ 
	public static void main(String[] args) { 
		SpringApplication.run(MeetsunApplication.class, args); 
	}
}

