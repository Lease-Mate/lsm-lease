package com.lsm.ws.lease.configuration.exception;

public class RentAlreadyAcceptedException extends ValidationException {

    public RentAlreadyAcceptedException() {
        super(ErrorCode.RENT_ALREADY_ACCEPTED);
    }
}
