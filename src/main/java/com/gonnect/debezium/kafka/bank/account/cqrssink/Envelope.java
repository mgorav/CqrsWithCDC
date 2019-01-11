package com.gonnect.debezium.kafka.bank.account.cqrssink;

public class Envelope {

    private String op;
    private String ts_ms;
    private DebitCardCdc before;
    private DebitCardCdc after;

    String getOp() {
        return op;
    }

    void setOp(String op) {
        this.op = op;
    }

    DebitCardCdc getBefore() {
        return before;
    }

    void setBefore(DebitCardCdc before) {
        this.before = before;
    }

    String getTs_ms() {
        return ts_ms;
    }

    void setTs_ms(String ts_ms) {
        this.ts_ms = ts_ms;
    }

    DebitCardCdc getAfter() {
        return after;
    }

    void setAfter(DebitCardCdc after) {
        this.after = after;
    }
}
