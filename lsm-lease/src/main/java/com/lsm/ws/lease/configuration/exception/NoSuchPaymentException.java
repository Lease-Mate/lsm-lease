package com.lsm.ws.lease.configuration.exception;

public class NoSuchPaymentException extends ValidationException {

    public NoSuchPaymentException() {
        super(ErrorCode.NO_SUCH_PAYMENT);
    }
}
