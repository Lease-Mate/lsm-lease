package com.lsm.ws.lease.infrastructure.persistance.model;

import com.lsm.ws.lease.domain.rent.Rent;
import com.lsm.ws.lease.domain.rent.RentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "rent")
public class RentEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "offer_id")
    private String offerId;

    @Column(name = "user_id")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RentStatus status;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    public Rent toRent() {
        return new Rent(
                id,
                ownerId,
                offerId,
                userId,
                status,
                requestDate
        );
    }

    public void from(Rent rent) {
        setId(rent.getId());
        setOwnerId(rent.getOwnerId());
        setOfferId(rent.getOfferId());
        setUserId(rent.getUserId());
        setStatus(rent.getStatus());
        setRequestDate(rent.getRequestDate());
    }

    public void setId(String id) {
        this.id = id;
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

    public void setStatus(RentStatus status) {
        this.status = status;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
}
