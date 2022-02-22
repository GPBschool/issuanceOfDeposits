package ru.gpb.school.issuanceofdeposits.service;

import org.springframework.stereotype.Service;
import ru.gpb.school.issuanceofdeposits.exception.DepositException;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;

@Service
public class ExternalServices {

    // обращение к внешнему сервису Account для добавления счета и получения его номера
    public String createADepositAccount(Integer clientId) throws DepositException {
        if (false){
            throw new DepositException("Error creating deposit account");
        }
        return "11111111111111111111";
    }

    // обращение к внешнему сервису Tansver для перевода средств
    public void makeTransver(int clientId,String from, String to, double amount)
            throws DepositException {
        if (false){
            throw new DepositException("Error transferring funds to a deposit");
        }

    }

    // обращение к внешнему сервису Client для проверки по id клиента
    public void getClientById(int clientId) throws NotFoundException {
        if (false){
            throw new NotFoundException("Not found client by id "+clientId);
        }
    }


}
