package com.lsm.ws.lease.domain.offer;

public class Address {

    private String longitude;
    private String latitude;
    private String country;
    private String region;
    private String city;
    private String street;
    private String zipCode;
    private String buildingNumber;
    private String apartmentNumber;

    public Address(String longitude, String latitude, String country, String region, String city, String street,
                   String zipCode, String buildingNumber, String apartmentNumber) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }
}
