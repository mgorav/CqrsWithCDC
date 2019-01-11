package com.gonnect.debezium.kafka.bank.account.service;

import com.gonnect.debezium.kafka.bank.account.model.DebitCard;
import com.gonnect.debezium.kafka.bank.account.model.DebitCardNotFoundException;
import com.gonnect.debezium.kafka.bank.account.repository.DebitCardRepository;
import com.gonnect.debezium.kafka.bank.account.repository.MoneyWithdrawalRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class MoneyWithdrawalService {

    private final DebitCardRepository debitCardRepository;
    private final MoneyWithdrawalRepository moneyWithdrawalRepository;

    MoneyWithdrawalService(DebitCardRepository debitCardRepository, MoneyWithdrawalRepository moneyWithdrawalRepository) {
        this.debitCardRepository = debitCardRepository;
        this.moneyWithdrawalRepository = moneyWithdrawalRepository;
    }


    @Transactional
    public void withdraw(String debitCardId, BigDecimal amount) {

        DebitCard debitCard = debitCardRepository.findById(debitCardId)
                .orElseThrow(() -> new DebitCardNotFoundException("Cannot find debit card with id " + debitCardId));
        debitCard.withdraw(amount);
    }
}
