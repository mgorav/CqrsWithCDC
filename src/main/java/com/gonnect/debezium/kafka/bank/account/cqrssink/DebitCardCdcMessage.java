package com.gonnect.debezium.kafka.bank.account.cqrssink;

public class DebitCardCdcMessage {

    private Envelope payload;

    public DebitCardCdcMessage() {
    }

    public DebitCardCdcMessage(Envelope payload) {
        this.payload = payload;
    }

    Envelope getPayload() {
        return payload;
    }

    void setPayload(Envelope payload) {
        this.payload = payload;
    }

    boolean isUpdate() {
        return payload.getOp().equals("u");
    }
}
