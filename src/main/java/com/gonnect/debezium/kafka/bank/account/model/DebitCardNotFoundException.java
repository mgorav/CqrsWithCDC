package com.gonnect.debezium.kafka.bank.account.model;

import java.math.BigDecimal;

public class DebitCardNotFoundException extends RuntimeException {

    public DebitCardNotFoundException(String message) {
        super(message);
    }


}
