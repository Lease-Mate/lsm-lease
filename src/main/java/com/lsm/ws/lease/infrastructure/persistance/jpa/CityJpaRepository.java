package com.lsm.ws.lease.infrastructure.persistance.jpa;

import com.lsm.ws.lease.domain.Language;
import com.lsm.ws.lease.infrastructure.persistance.model.dictionary.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CityJpaRepository extends JpaRepository<CityEntity, String>, JpaSpecificationExecutor<CityEntity> {

    Optional<CityEntity> findByIdAndLang(String code, Language lang);
}
