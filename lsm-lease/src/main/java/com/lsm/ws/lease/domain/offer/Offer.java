package com.lsm.ws.lease.domain.offer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Offer {

    private final String id;
    private final String appUserId;
    private String title;
    private String description;
    private OfferStatus status;
    private LocalDate availableFrom;
    private BigDecimal rent;
    private Integer rooms;
    private Integer floor;
    private Double surfaceArea;
    private Address address;
    private String thumbnailId;

    public Offer(String id, String appUserId, String title, String description, OfferStatus status,
                 LocalDate availableFrom, BigDecimal rent, Integer rooms, Integer floor, Double surfaceArea,
                 Address address, String thumbnailId) {
        this.id = id;
        this.appUserId = appUserId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.availableFrom = availableFrom;
        this.rent = rent;
        this.rooms = rooms;
        this.floor = floor;
        this.surfaceArea = surfaceArea;
        this.address = address;
        this.thumbnailId = thumbnailId;
    }

    public Offer(String string, String appUserId) {
        this.id = string;
        this.appUserId = appUserId;
        this.status = OfferStatus.UNPAID;
    }

    public String getId() {
        return id;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(Double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(String thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    public boolean isPublished() {
        return status == OfferStatus.PUBLISHED;
    }
}
