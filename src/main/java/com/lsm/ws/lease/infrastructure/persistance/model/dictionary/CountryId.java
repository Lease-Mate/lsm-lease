package com.lsm.ws.lease.infrastructure.persistance.model.dictionary;

import com.lsm.ws.lease.domain.Language;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class CountryId {

    @Column(name = "iso_code")
    private String isoCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "lang")
    private Language lang;

    public String getIsoCode() {
        return isoCode;
    }
}
