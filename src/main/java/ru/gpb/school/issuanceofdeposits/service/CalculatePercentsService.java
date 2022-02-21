package ru.gpb.school.issuanceofdeposits.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CalculatePercentsService {

    // расчет процентов по депозиту каждую минуту расчет
    @Scheduled(cron = "0 0/1 * * * *")
    public void calculatePercents() {
        //TODO реализовать расчет процентов

        System.out.println("Расчет процентов " + LocalDateTime.now());
    }
}
