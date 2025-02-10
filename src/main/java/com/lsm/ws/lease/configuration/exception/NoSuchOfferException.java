package com.lsm.ws.lease.configuration.exception;

public class NoSuchOfferException extends ValidationException {

    public NoSuchOfferException() {
        super(ErrorCode.OFFER_DOES_NOT_EXIST);
    }
}
