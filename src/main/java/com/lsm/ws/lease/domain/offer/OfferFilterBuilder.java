package com.lsm.ws.lease.domain.offer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OfferFilterBuilder {

    private OfferStatus status;
    private String country;
    private String city;
    private LocalDate availableTo;
    private BigDecimal rentFrom;
    private BigDecimal rentTo;
    private Double surfaceAreaFrom;
    private Double surfaceAreaTo;
    private String userId;

    public OfferFilterBuilder withStatus(OfferStatus status) {
        this.status = status;
        return this;
    }

    public OfferFilterBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public OfferFilterBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public OfferFilterBuilder withAvailableTo(LocalDate availableTo) {
        this.availableTo = availableTo;
        return this;
    }

    public OfferFilterBuilder withRentFrom(BigDecimal rentFrom) {
        this.rentFrom = rentFrom;
        return this;
    }

    public OfferFilterBuilder withRentTo(BigDecimal rentTo) {
        this.rentTo = rentTo;
        return this;
    }

    public OfferFilterBuilder withSurfaceAreaFrom(Double surfaceAreaFrom) {
        this.surfaceAreaFrom = surfaceAreaFrom;
        return this;
    }

    public OfferFilterBuilder withSurfaceAreaTo(Double surfaceAreaTo) {
        this.surfaceAreaTo = surfaceAreaTo;
        return this;
    }

    public OfferFilterBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public OfferFilter build() {
        return new OfferFilter(
                status,
                country,
                city,
                availableTo,
                rentFrom,
                rentTo,
                surfaceAreaFrom,
                surfaceAreaTo,
                userId
        );
    }
}
