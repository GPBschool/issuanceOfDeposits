package ru.gpb.school.issuanceofdeposits.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.gpb.school.issuanceofdeposits.exception.DepositException;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CalculatePercentsService {

    private final DepositService depositService;

    // расчет процентов по депозиту каждую минуту
    @Scheduled(cron = "0 0/1 * * * *")
    public void calculatePercents() throws NotFoundException, DepositException {
        //TODO реализовать расчет процентов
        depositService.calculatePercents(LocalDateTime.now());
        //System.out.println("Расчет процентов " + LocalDateTime.now());
    }
}
