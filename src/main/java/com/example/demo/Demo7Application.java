package com.example.demo;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.StorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo7Application {

    StorageService storageService;

    private UserRepository userRepository;


    public Demo7Application(StorageService storageService) {
        this.storageService = storageService;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {

        SpringApplication.run(Demo7Application.class, args);
    }



}
