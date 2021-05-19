package com.team09.airbnb;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class AirbnbApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirbnbApplication.class, args);
    }

}
