package com.gonnect.debezium.kafka.bank.account.model;

import java.math.BigDecimal;

class InSufficientMoneyException extends RuntimeException {

    InSufficientMoneyException(String cardNo, BigDecimal wanted, BigDecimal availableBalance) {
        super(String.format("Card %s not able to withdraw %s. Balance is %s", cardNo, wanted, availableBalance));
    }


}
