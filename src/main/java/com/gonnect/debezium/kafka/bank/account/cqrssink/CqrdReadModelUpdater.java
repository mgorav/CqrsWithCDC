package com.gonnect.debezium.kafka.bank.account.cqrssink;

import com.gonnect.debezium.kafka.bank.account.model.MoneyWithdrawal;
import com.gonnect.debezium.kafka.bank.account.repository.MoneyWithdrawalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static org.springframework.cloud.stream.messaging.Sink.INPUT;

@Service
@Slf4j
public class CqrdReadModelUpdater {

    private final MoneyWithdrawalRepository moneyWithdrawalRepository;

    CqrdReadModelUpdater(MoneyWithdrawalRepository moneyWithdrawalRepository) {
        this.moneyWithdrawalRepository = moneyWithdrawalRepository;
    }

    @StreamListener(INPUT)
    public void handle(DebitCardCdcMessage message) {
        if(message.isUpdate()) {
            saveWithdrawalFrom(message);
        }
    }

    private void saveWithdrawalFrom(DebitCardCdcMessage message) {
        String debitCardId = message.getPayload().getBefore().getId();
        BigDecimal withdrawalAmount
                = balanceAfter(message).subtract(balanceBefore(message));
        moneyWithdrawalRepository.save(new MoneyWithdrawal(withdrawalAmount, debitCardId));
    }

    private BigDecimal balanceAfter(DebitCardCdcMessage message) {
        return message.getPayload().getAfter().getUsed_limit();
    }

    private BigDecimal balanceBefore(DebitCardCdcMessage message) {
        return message.getPayload().getBefore().getUsed_limit();
    }
}
