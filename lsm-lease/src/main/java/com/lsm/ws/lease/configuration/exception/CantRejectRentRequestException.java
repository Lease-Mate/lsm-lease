package com.lsm.ws.lease.configuration.exception;

public class CantRejectRentRequestException extends ValidationException {

    public CantRejectRentRequestException() {
        super(ErrorCode.RENT_REQUEST_CANT_BE_REJECTED);
    }
}
