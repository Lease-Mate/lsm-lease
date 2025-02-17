package com.lsm.ws.lease.configuration.exception;

public class OfferUnpaidException extends ValidationException {

    public OfferUnpaidException() {
        super(ErrorCode.OFFER_UNPAID);
    }
}
