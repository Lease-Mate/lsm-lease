package com.lsm.ws.lease.infrastructure.rest.offer.dto;

import com.lsm.ws.lease.domain.offer.Offer;
import com.lsm.ws.lease.domain.offer.OfferStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OfferDto {

    public String id;
    public String appUserId;
    public String title;
    public String description;
    public OfferStatus status;
    public LocalDate availableFrom;
    public BigDecimal rent;
    public Integer rooms;
    public Integer floor;
    public Double surfaceArea;
    public AddressDto address;
    public String thumbnailId;

    public Offer toOffer() {
        return new Offer(
                id,
                appUserId,
                title,
                description,
                status,
                availableFrom,
                rent,
                rooms,
                floor,
                surfaceArea,
                address.toAddress(),
                thumbnailId
        );
    }

}
