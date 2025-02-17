package com.lsm.ws.lease.infrastructure.persistance.model;

import com.lsm.ws.lease.domain.offer.Address;
import com.lsm.ws.lease.domain.offer.Offer;
import com.lsm.ws.lease.domain.offer.OfferStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "offer")
public class OfferEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "app_user_id")
    private String userId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OfferStatus status;

    @Column(name = "available_from")
    private LocalDate availableFrom;

    @Column(name = "rent")
    private BigDecimal rent;

    @Column(name = "rooms")
    private Integer rooms;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "surface_area")
    private Double surfaceArea;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "building_number")
    private String buildingNumber;

    @Column(name = "apartment_number")
    private String apartmentNumber;

    @Column(name = "thumbnail_id")
    private String thumbnailId;

    public Offer toOffer() {
        return new Offer(
                id,
                userId,
                title,
                description,
                status,
                availableFrom,
                rent,
                rooms,
                floor,
                surfaceArea,
                new Address(
                        longitude,
                        latitude,
                        country,
                        region,
                        city,
                        street,
                        zipCode,
                        buildingNumber,
                        apartmentNumber
                ),
                thumbnailId
        );
    }

    public void from(Offer offer) {
        setId(offer.getId());
        setAppUserId(offer.getAppUserId());
        setTitle(offer.getTitle());
        setDescription(offer.getDescription());
        setStatus(offer.getStatus());
        setAvailableFrom(offer.getAvailableFrom());
        setRent(offer.getRent());
        setRooms(offer.getRooms());
        setFloor(offer.getFloor());
        setSurfaceArea(offer.getSurfaceArea());
        setLongitude(offer.getAddress() == null ? null : offer.getAddress().getLongitude());
        setLatitude(offer.getAddress() == null ? null : offer.getAddress().getLatitude());
        setCountry(offer.getAddress() == null ? null : offer.getAddress().getCountry());
        setRegion(offer.getAddress() == null ? null : offer.getAddress().getRegion());
        setCity(offer.getAddress() == null ? null : offer.getAddress().getCity());
        setStreet(offer.getAddress() == null ? null : offer.getAddress().getStreet());
        setZipCode(offer.getAddress() == null ? null : offer.getAddress().getZipCode());
        setBuildingNumber(offer.getAddress() == null ? null : offer.getAddress().getBuildingNumber());
        setApartmentNumber(offer.getAddress() == null ? null : offer.getAddress().getApartmentNumber());
        setThumbnailId(offer.getThumbnailId());
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAppUserId(String appUserId) {
        this.userId = appUserId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public void setAvailableFrom(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setSurfaceArea(Double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public void setThumbnailId(String thumbnailId) {
        this.thumbnailId = thumbnailId;
    }
}
