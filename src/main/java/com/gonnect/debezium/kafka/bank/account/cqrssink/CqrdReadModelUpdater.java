package com.gonnect.debezium.kafka.bank.account.cqrssink;

import com.gonnect.debezium.kafka.bank.account.model.MoneyWithdrawal;
import com.gonnect.debezium.kafka.bank.account.repository.MoneyWithdrawalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

import static org.springframework.cloud.stream.messaging.Sink.INPUT;
import static org.springframework.cloud.stream.messaging.Source.OUTPUT;

@Service
@Slf4j
public class CqrdReadModelUpdater {

    private final MoneyWithdrawalRepository moneyWithdrawalRepository;

    CqrdReadModelUpdater(MoneyWithdrawalRepository moneyWithdrawalRepository) {
        this.moneyWithdrawalRepository = moneyWithdrawalRepository;
    }

    @StreamListener(INPUT)
    @SendTo(OUTPUT)
    public MiniStatement process(DebitCardCdcMessage message) {
        MiniStatement miniStatement = new MiniStatement();
        if(message.isUpdate()) {
            MoneyWithdrawal moneyWithdrawal = saveWithdrawalFrom(message);
           miniStatement.setId(moneyWithdrawal.getId());
           miniStatement.setAmount(moneyWithdrawal.getAmount());
           miniStatement.setDebitCardId(moneyWithdrawal.getDebitCardId());
           miniStatement.setTransactionDate(new Date(System.currentTimeMillis()));

        }

        return miniStatement;
    }

    private MoneyWithdrawal saveWithdrawalFrom(DebitCardCdcMessage message) {
        String debitCardId = message.getPayload().getBefore().getId();
        BigDecimal withdrawalAmount
                = balanceAfter(message).subtract(balanceBefore(message));
        return moneyWithdrawalRepository.save(new MoneyWithdrawal(withdrawalAmount, debitCardId));
    }

    private BigDecimal balanceAfter(DebitCardCdcMessage message) {
        return message.getPayload().getAfter().getUsed_limit();
    }

    private BigDecimal balanceBefore(DebitCardCdcMessage message) {
        return message.getPayload().getBefore().getUsed_limit();
    }
}
