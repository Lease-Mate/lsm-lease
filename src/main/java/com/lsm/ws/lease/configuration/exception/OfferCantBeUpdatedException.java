package com.lsm.ws.lease.configuration.exception;

public class OfferCantBeUpdatedException extends ValidationException {

    public OfferCantBeUpdatedException() {
        super(ErrorCode.OFFER_CANT_BE_UPDATED);
    }
}
