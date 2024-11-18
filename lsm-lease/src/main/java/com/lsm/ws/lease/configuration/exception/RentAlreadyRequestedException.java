package com.lsm.ws.lease.configuration.exception;

public class RentAlreadyRequestedException extends ValidationException {

    public RentAlreadyRequestedException() {
        super(ErrorCode.OFFER_ALREADY_REQUESTED);
    }
}
