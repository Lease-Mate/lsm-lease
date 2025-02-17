package com.lsm.ws.lease.infrastructure.persistance.model.dictionary;

import com.lsm.ws.lease.domain.dictionary.Country;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "country")
public class CountryEntity {

    @EmbeddedId
    private CountryId id;

    @Column(name = "name")
    private String name;

    public Country toCountry() {
        return new Country(id.getIsoCode(), name);
    }
}
