package com.xx.spring.boot.jdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xx.spring.boot.jdbc.dao.BikeData;
import com.xx.spring.boot.jdbc.repository.BikeDataRepository;
@SpringBootApplication
public class MyApplication {  
	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
    }	
}            