package com.example.p3samlfetch;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties

public class P3SamlFetchApplication {

    public static void main(String[] args) {
        SpringApplication.run(P3SamlFetchApplication.class, args);
    }

}
