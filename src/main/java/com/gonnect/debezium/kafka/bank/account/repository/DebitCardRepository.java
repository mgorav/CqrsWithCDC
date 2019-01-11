package com.gonnect.debezium.kafka.bank.account.repository;

import com.gonnect.debezium.kafka.bank.account.model.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebitCardRepository extends JpaRepository<DebitCard,String> {
}
