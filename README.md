# CQRS Using CDC
A bank application which demonstrates CQRS design pattern. This application performs following operations:

1. Money withdrawal using debit card

2. List all the money withdrawal (mini bank statement)

This application uses following two tables for above operations:

1. debit_card

2. money_withdrawal

A debit card withdrawl operation is stored in debit_card table. Once the transaciton is successfully committed in the debit_card table, using Debezium's & Kafka connect the CDC is moved to Kafka. Once the message arrive in Kafka topic, using Spring Cloud Stream Stream Listener, an entry in made to money_withdrawl table. This table is used to create mini statement (query)

**_NOTE_** For sake of simplicity same DB is used but as can be seen - "a command to perform debit operation" is separated from mini statemen.


Following picture shows architecture of this application:

![alt text](./CQRSWithCDC.png)


## Pre-requisite

1. MySQL

2. Apache Kafka

3. Kafka Connect

4. Debezium

5. Spring Cloud Stream


## Run Application

1. Run bank application complete infrastructure:

    docker-compose up

2. Instruct Kafka Connect to tail transaction log of MySQL DB  and start sending message to Kafka:

    curl -i -X POST -H "Accept:application/json" -H  "Content-Type:application/json" http://localhost:8083/connectors/ -d @source.json --verbose

3. Money withdrawal:

    curl http://localhost:8080/moneywithdrawals -X POST --header 'Content-Type: application/json' -d '{"debitCard":"123456789", "amount": 10.00}' --verbose

4. Verify using query model (read model)

    curl http://localhost:8080/moneywithdrawals?debitCardId=123456789 --verbose