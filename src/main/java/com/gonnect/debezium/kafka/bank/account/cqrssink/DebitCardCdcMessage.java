package com.gonnect.debezium.kafka.bank.account.cqrssink;

public class DebitCardCdcMessage {

    private BankOperation payload;

    public DebitCardCdcMessage() {
    }

    public DebitCardCdcMessage(BankOperation payload) {
        this.payload = payload;
    }

    BankOperation getPayload() {
        return payload;
    }

    void setPayload(BankOperation payload) {
        this.payload = payload;
    }

    boolean isUpdate() {
        return payload.getOp().equals("u");
    }
}
