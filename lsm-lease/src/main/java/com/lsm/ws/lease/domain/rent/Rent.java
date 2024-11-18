package com.lsm.ws.lease.domain.rent;

import java.time.LocalDateTime;

public class Rent {

    private final String id;
    private final String ownerId;
    private final String offerId;
    private final String userId;
    private final LocalDateTime requestDate;
    private RentStatus status;

    public Rent(String id, String ownerId, String offerId, String userId, RentStatus status,
                LocalDateTime requestDate) {
        this.id = id;
        this.ownerId = ownerId;
        this.offerId = offerId;
        this.userId = userId;
        this.status = status;
        this.requestDate = requestDate;
    }

    public String getId() {
        return id;
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

    public RentStatus getStatus() {
        return status;
    }

    public void setStatus(RentStatus status) {
        this.status = status;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public boolean isEligibleToAccept() {
        return status == RentStatus.REQUESTED;
    }
}
