package com.gonnect.debezium.kafka.bank.account.api;

import com.gonnect.debezium.kafka.bank.account.model.MoneyWithdrawal;
import com.gonnect.debezium.kafka.bank.account.repository.MoneyWithdrawalRepository;
import com.gonnect.debezium.kafka.bank.account.service.MoneyWithdrawalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController("/moneywithdrawals")
public class MoneyWithdrawalApis {

    private final MoneyWithdrawalRepository moneyWithdrawalRepository;
    private final MoneyWithdrawalService moneyWithdrawalService;

    MoneyWithdrawalApis(MoneyWithdrawalRepository moneyWithdrawalRepository, MoneyWithdrawalService moneyWithdrawalService) {
        this.moneyWithdrawalRepository = moneyWithdrawalRepository;
        this.moneyWithdrawalService = moneyWithdrawalService;
    }

    @PostMapping
    ResponseEntity withdraw(@RequestBody MoneyWithdrawalMessage moneyWithdrawalMessage) {
        moneyWithdrawalService.withdraw(moneyWithdrawalMessage.getDebitCard(), moneyWithdrawalMessage.getAmount());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<List<MoneyWithdrawal>> withdrawals(@PathParam("debitCardId") String debitCardId) {
        return ResponseEntity.ok().body(moneyWithdrawalRepository.findByDebitCardId(debitCardId));
    }
}
