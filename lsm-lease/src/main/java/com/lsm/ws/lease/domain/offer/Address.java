package com.lsm.ws.lease.domain.offer;

public class Address {

    private String longitude;
    private String latitude;
    private String city;
    private String street;
    private String zipCode;
    private String buildingNumber;
    private String apartmentNumber;

    public Address(String longitude, String latitude, String city, String street, String zipCode,
                   String buildingNumber, String apartmentNumber) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
}
