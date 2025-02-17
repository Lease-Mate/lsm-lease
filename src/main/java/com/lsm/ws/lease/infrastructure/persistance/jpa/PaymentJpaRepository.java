package com.lsm.ws.lease.infrastructure.persistance.jpa;

import com.lsm.ws.lease.infrastructure.persistance.model.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, String>,
        JpaSpecificationExecutor<PaymentEntity> {
}
