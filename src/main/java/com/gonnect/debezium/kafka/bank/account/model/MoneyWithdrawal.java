package com.gonnect.debezium.kafka.bank.account.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class MoneyWithdrawal {

    @Id
    private String id;
    private @Getter BigDecimal amount;
    private String debitCardId;

    public MoneyWithdrawal(BigDecimal amount, String debitCardId) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.debitCardId = debitCardId;
    }
}
