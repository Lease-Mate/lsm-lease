package com.lsm.ws.lease.infrastructure.persistance.model;

import com.lsm.ws.lease.domain.payment.Payment;
import com.lsm.ws.lease.domain.payment.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "rent_id")
    private String rentId;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "offer_id")
    private String offerId;

    @Column(name = "user_id")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    public Payment toPayment() {
        return new Payment(
                id,
                rentId,
                ownerId,
                offerId,
                userId,
                status,
                dueDate,
                paymentDate
        );
    }

    public void from(Payment payment) {
        setId(payment.getId());
        setRentId(payment.getRentId());
        setOwnerId(payment.getOwnerId());
        setOfferId(payment.getOfferId());
        setUserId(payment.getUserId());
        setStatus(payment.getStatus());
        setDueDate(payment.getDueDate());
        setPaymentDate(payment.getPaymentDate());
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRentId(String rentId) {
        this.rentId = rentId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
