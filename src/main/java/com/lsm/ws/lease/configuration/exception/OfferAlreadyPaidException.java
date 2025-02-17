package com.lsm.ws.lease.configuration.exception;

public class OfferAlreadyPaidException extends ValidationException {

    public OfferAlreadyPaidException() {
        super(ErrorCode.OFFER_ALREADY_PAID);
    }
}
