package com.zed.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ExamApplication {

    public static void main(String[] args) {
        run();
        SpringApplication.run(ExamApplication.class, args);
    }

    public static void run(){
        String plainPassword = "secret";
        String hash = new BCryptPasswordEncoder().encode(plainPassword);
        System.out.println(hash);
    }

}
