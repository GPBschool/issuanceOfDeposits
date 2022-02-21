package ru.gpb.school.issuanceofdeposits.service;

import org.springframework.stereotype.Service;

@Service
public class ExternalServices {

    // обращение к внешнему сервису Account для добавления счета и получения его номера
    public String createADepositAccount(Integer clientId){

        return "11111111111111111111";
    }

    // обращение к внешнему сервису Tansver для перевода средств
    public boolean makeTransver(int clientId,String senderAccount, String recipientAccount, double amount){
        return true;
    }

    // обращение к внешнему сервису Client для проверки id клиента
    public boolean getClientById(int clientId){
        return true;
    }


}
