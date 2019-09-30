package com.ocbibliov2.ocbibliobatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.ocbibliov2.ocbibliobatch")
public class OcbiblioBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(OcbiblioBatchApplication.class, args);
    }

}
