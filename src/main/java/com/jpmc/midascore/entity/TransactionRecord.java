package com.jpmc.midascore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TransactionRecord {

    @Id
    @GeneratedValue()
    private long transactionRecordId;
    private long senderId;
    private long recipientId;
    private float senderBalance;
    private float transactionAmount;

    public TransactionRecord(){

    }

    public long getTransactionRecordId() {
        return transactionRecordId;
    }

    public void setTransactionRecordId(long transactionRecordId) {
        this.transactionRecordId = transactionRecordId;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(long recipientId) {
        this.recipientId = recipientId;
    }

    public float getSenderBalance() {
        return senderBalance;
    }

    public void setSenderBalance(float senderBalance) {
        this.senderBalance = senderBalance;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Override
    public String toString(){
        return "Transaction Record{TransactionId= " + transactionRecordId + "{senderId= }" + senderId + "{recipientId= }" + recipientId + "{transactionAmount= }" + transactionAmount + "}";
    }
}

