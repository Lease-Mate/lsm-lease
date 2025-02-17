package com.lsm.ws.lease.context.offer.dto;

import com.lsm.ws.lease.domain.offer.Address;
import com.lsm.ws.lease.domain.offer.Offer;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;

@Validated
public class OfferDto {

    public String id;
    public String appUserId;
    public String title;
    public String description;
    public LocalDate availableFrom;
    public BigDecimal rent;
    public Integer rooms;
    public Integer floor;
    public Double surfaceArea;
    public Address address;
    public String thumbnailId;

    public static OfferDto from(Offer offer) {
        OfferDto dto = new OfferDto();
        dto.id = offer.getId();
        dto.appUserId = offer.getAppUserId();
        dto.title = offer.getTitle();
        dto.description = offer.getDescription();
        dto.availableFrom = offer.getAvailableFrom();
        dto.rent = offer.getRent();
        dto.rooms = offer.getRooms();
        dto.floor = offer.getFloor();
        dto.surfaceArea = offer.getSurfaceArea();
        dto.address = offer.getAddress();
        dto.thumbnailId = offer.getThumbnailId();
        return dto;
    }
}
