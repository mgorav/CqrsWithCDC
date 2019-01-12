package com.gonnect.debezium.kafka.bank.account.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
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
