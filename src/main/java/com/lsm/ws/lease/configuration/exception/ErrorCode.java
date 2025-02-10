package com.lsm.ws.lease.configuration.exception;

public enum ErrorCode {

    OFFER_DOES_NOT_EXIST("001", "Ogłoszenie nie istnieje"),
    OFFER_ALREADY_RENTED("002", "Ogłoszenie nie aktualne"),
    OFFER_ALREADY_REQUESTED("003", "Wysłano już żądanie najmu"),
    RENT_ALREADY_ACCEPTED("004", "Ogłoszenie jest już w trakcie najmu"),
    NO_SUCH_RENT_REQUEST("005", "Żądanie najmu nie istnieje");

    private static final String MICROSERVICE_PREFIX = "lsm-lease-";
    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = MICROSERVICE_PREFIX + code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}
