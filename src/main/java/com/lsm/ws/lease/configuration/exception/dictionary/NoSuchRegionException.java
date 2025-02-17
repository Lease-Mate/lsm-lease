package com.lsm.ws.lease.configuration.exception.dictionary;

import com.lsm.ws.lease.configuration.exception.ErrorCode;
import com.lsm.ws.lease.configuration.exception.ValidationException;

public class NoSuchRegionException extends ValidationException {

    public NoSuchRegionException() {
        super(ErrorCode.REGION_DOES_NOT_EXIST);
    }
}
