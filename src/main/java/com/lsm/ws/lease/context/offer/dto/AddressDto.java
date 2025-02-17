package com.lsm.ws.lease.context.offer.dto;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

@Validated
public class AddressDto {

    @NotEmpty
    public String country;

    @NotEmpty
    public String region;

    @NotEmpty
    public String city;

    @NotEmpty
    public String street;

    @NotEmpty
    public String zipCode;

    @NotEmpty
    public String buildingNumber;

    public String apartmentNumber;
}
