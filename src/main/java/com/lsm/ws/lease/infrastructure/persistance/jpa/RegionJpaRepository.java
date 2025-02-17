package com.lsm.ws.lease.infrastructure.persistance.jpa;

import com.lsm.ws.lease.domain.Language;
import com.lsm.ws.lease.infrastructure.persistance.model.dictionary.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RegionJpaRepository extends JpaRepository<RegionEntity, String>,
        JpaSpecificationExecutor<RegionEntity> {

    Optional<RegionEntity> findByIdAndLang(String code, Language lang);
}
