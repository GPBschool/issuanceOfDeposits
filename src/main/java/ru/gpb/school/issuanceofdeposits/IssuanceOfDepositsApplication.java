package ru.gpb.school.issuanceofdeposits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IssuanceOfDepositsApplication {

    public static void main(String[] args) {
        SpringApplication.run(IssuanceOfDepositsApplication.class, args);
    }

}
