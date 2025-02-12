package com.lsm.ws.lease.configuration.exception;

public class PaymentAlreadyPaidException extends ValidationException {

    public PaymentAlreadyPaidException() {
        super(ErrorCode.ALREADY_PAID);
    }
}
