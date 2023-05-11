package com.jazztech.api.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class ClientApiApplication {

	public static void main(String[] args){
		SpringApplication.run(ClientApiApplication.class, args);
	}

}
