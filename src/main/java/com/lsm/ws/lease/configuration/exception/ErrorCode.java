package com.lsm.ws.lease.configuration.exception;

public enum ErrorCode {

    OFFER_DOES_NOT_EXIST("001", "Ogłoszenie nie istnieje"),
    OFFER_ALREADY_RENTED("002", "Ogłoszenie nie aktualne"),
    OFFER_ALREADY_REQUESTED("003", "Wysłano już żądanie najmu"),
    RENT_ALREADY_ACCEPTED("004", "Ogłoszenie jest już w trakcie najmu"),
    NO_SUCH_RENT_REQUEST("005", "Żądanie najmu nie istnieje"),
    RENT_REQUEST_CANT_BE_REJECTED("006", "Nie można odrzucić tej prośby"),
    NO_SUCH_PAYMENT("007", "Płatność nie istnieje"),
    ALREADY_PAID("007", "Płatność już opłacona"),
    OFFER_ALREADY_PAID("008", "Oferta została już opłacona"),
    OFFER_UNPAID("009", "Oferta nie została opłacona"),
    COUNTRY_DOES_NOT_EXIST("010", "Kraj nie istnieje"),
    REGION_DOES_NOT_EXIST("011", "Region nie istnieje"),
    OFFER_CANT_BE_UPDATED("012", "Oferta nie może zostać aktualizowana");

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
