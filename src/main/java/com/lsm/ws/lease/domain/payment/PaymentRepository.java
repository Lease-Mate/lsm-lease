package com.lsm.ws.lease.domain.payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {

    Payment save(Payment payment);

    List<Payment> findByRentId(String rentId);

    Optional<Payment> getById(String paymentId);
}
