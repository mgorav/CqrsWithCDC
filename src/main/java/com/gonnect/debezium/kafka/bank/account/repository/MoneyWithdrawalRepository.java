package com.gonnect.debezium.kafka.bank.account.repository;

import com.gonnect.debezium.kafka.bank.account.model.MoneyWithdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoneyWithdrawalRepository extends JpaRepository<MoneyWithdrawal,String> {


    List<MoneyWithdrawal> findByDebitCardId(String debitCardId);
}
