package com.mrizak.payment.exposition;

public class PaymentResponse {
    public String id;
    public String memberId;
    public double amount;
    public String dateTime;
    public String type;

    public PaymentResponse(String id, String memberId, double amount, String dateTime, String type) {
        this.id = id;
        this.memberId = memberId;
        this.amount = amount;
        this.dateTime = dateTime;
        this.type = type;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", memberid='" + memberId + '\'' +
                ", amount='" + amount + '\'' +
                ", datetime='" + dateTime + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
