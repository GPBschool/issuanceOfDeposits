package ru.gpb.school.issuanceofdeposits.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CalculatePercentsService {

    @Scheduled(cron = "0 0/1 * * * *")
    public void calculatePercents() {
        System.out.println("Расчет процентов " + LocalDateTime.now());
    }
}
