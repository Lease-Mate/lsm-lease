package com.lsm.ws.lease.infrastructure.persistance.jpa;

import com.lsm.ws.lease.infrastructure.persistance.model.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RentJpaRepository extends JpaRepository<RentEntity, String>, JpaSpecificationExecutor<RentEntity> {
}
