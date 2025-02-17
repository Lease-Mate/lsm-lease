package com.lsm.ws.lease.infrastructure.persistance.jpa;

import com.lsm.ws.lease.domain.Language;
import com.lsm.ws.lease.infrastructure.persistance.model.dictionary.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CountryJpaRepository extends JpaRepository<CountryEntity, String>,
        JpaSpecificationExecutor<CountryEntity> {

    Optional<CountryEntity> findByIdIsoCodeAndIdLang(String isoCode, Language lang);
}
