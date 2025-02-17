package com.lsm.ws.lease.configuration.exception;

public class InactiveOfferException extends ValidationException {

    public InactiveOfferException() {
        super(ErrorCode.OFFER_ALREADY_RENTED);
    }
}
