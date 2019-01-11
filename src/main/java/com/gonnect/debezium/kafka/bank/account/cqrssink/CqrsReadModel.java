package com.gonnect.debezium.kafka.bank.account.cqrssink;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(Sink.class)
public class CqrsReadModel {
}
