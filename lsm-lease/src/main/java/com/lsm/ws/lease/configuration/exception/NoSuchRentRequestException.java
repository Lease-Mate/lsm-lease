package com.lsm.ws.lease.configuration.exception;

public class NoSuchRentRequestException extends ValidationException {

    public NoSuchRentRequestException() {
        super(ErrorCode.NO_SUCH_RENT_REQUEST);
    }
}
