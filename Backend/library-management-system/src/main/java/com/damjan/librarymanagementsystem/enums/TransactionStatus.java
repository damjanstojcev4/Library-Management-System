package com.damjan.librarymanagementsystem.enums;

public enum TransactionStatus {
    CHECKED_OUT("Checked Out"),
    RETURNED("Returned"),
    OVERDUE("Overdue"),
    RESERVED("Reserved"),
    WAITING_FOR_PICKUP("Waiting for Pickup"),
    CANCELLED("Cancelled"),
    LOST("Lost"),
    REQUESTED("Requested"),
    PROCESSING("Processing");

    private final String displayValue;

    TransactionStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
