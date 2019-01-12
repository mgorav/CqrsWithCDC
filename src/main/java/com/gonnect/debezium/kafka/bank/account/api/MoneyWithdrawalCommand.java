package com.gonnect.debezium.kafka.bank.account.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneyWithdrawalCommand {

    private String debitCard;
    private BigDecimal amount;
}
