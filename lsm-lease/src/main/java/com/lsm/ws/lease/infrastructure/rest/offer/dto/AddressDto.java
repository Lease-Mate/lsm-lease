package com.lsm.ws.lease.infrastructure.rest.offer.dto;

import com.lsm.ws.lease.domain.offer.Address;

public class AddressDto {

    public String longitude;
    public String latitude;
    public String country;
    public String region;
    public String city;
    public String street;
    public String zipCode;
    public String buildingNumber;
    public String apartmentNumber;

    public Address toAddress() {
        return new Address(
                longitude,
                latitude,
                country,
                region,
                city,
                street,
                zipCode,
                buildingNumber,
                apartmentNumber
        );
    }
}
