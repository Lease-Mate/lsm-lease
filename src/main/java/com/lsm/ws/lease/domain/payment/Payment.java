package com.lsm.ws.lease.domain.payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Payment {

    private final String id;
    private final String rentId;
    private final String ownerId;
    private final String offerId;
    private final String userId;
    private final LocalDate dueDate;
    private PaymentStatus status;
    private LocalDateTime paymentDate;

    public Payment(String id, String rentId, String ownerId, String offerId, String userId, PaymentStatus status,
                   LocalDate dueDate) {
        this.id = id;
        this.rentId = rentId;
        this.ownerId = ownerId;
        this.offerId = offerId;
        this.userId = userId;
        this.status = status;
        this.dueDate = dueDate;
    }

    public Payment(String id, String rentId, String ownerId, String offerId, String userId, PaymentStatus status,
                   LocalDate dueDate, LocalDateTime paymentDate) {
        this.id = id;
        this.rentId = rentId;
        this.ownerId = ownerId;
        this.offerId = offerId;
        this.userId = userId;
        this.status = status;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public String getId() {
        return id;
    }

    public String getRentId() {
        return rentId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getOfferId() {
        return offerId;
    }

    public String getUserId() {
        return userId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
