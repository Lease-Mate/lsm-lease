package com.lsm.ws.lease.context.dto;

import com.lsm.ws.lease.domain.offer.Offer;

public class IdWrapperDto {

    public String id;

    public IdWrapperDto(String id) {
        this.id = id;
    }

    public static IdWrapperDto from(Offer offer) {
        return new IdWrapperDto(offer.getId());
    }
}
