package com.lsm.ws.lease.configuration.exception.dictionary;

import com.lsm.ws.lease.configuration.exception.ErrorCode;
import com.lsm.ws.lease.configuration.exception.ValidationException;

public class NoSuchCountryException extends ValidationException {

    public NoSuchCountryException() {
        super(ErrorCode.COUNTRY_DOES_NOT_EXIST);
    }
}
