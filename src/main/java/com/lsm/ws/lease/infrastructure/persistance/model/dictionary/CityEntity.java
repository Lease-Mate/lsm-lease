package com.lsm.ws.lease.infrastructure.persistance.model.dictionary;

import com.lsm.ws.lease.domain.Language;
import com.lsm.ws.lease.domain.dictionary.City;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "city")
public class CityEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "region_id")
    private String regionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "lang")
    private Language lang;

    @Column(name = "name")
    private String name;

    public City toCity() {
        return new City(id, name);
    }
}
