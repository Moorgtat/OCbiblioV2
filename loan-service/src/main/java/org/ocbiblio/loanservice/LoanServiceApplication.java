package org.ocbiblio.loanservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LoanServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LoanServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}

