package com.lsm.ws.lease.infrastructure.persistance.facade;

import com.lsm.ws.lease.domain.payment.Payment;
import com.lsm.ws.lease.domain.payment.PaymentRepository;
import com.lsm.ws.lease.infrastructure.persistance.jpa.PaymentJpaRepository;
import com.lsm.ws.lease.infrastructure.persistance.model.PaymentEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PaymentFacadeRepository implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    public PaymentFacadeRepository(PaymentJpaRepository paymentJpaRepository) {
        this.paymentJpaRepository = paymentJpaRepository;
    }

    @Override
    public Payment save(Payment payment) {
        var entity = new PaymentEntity();
        entity.from(payment);
        return paymentJpaRepository.save(entity).toPayment();
    }

    @Override
    public List<Payment> findByRentId(String rentId) {
        var spec = Specification.where(hasRentId(rentId));

        return paymentJpaRepository.findAll(spec)
                                   .stream().map(PaymentEntity::toPayment)
                                   .toList();
    }

    @Override
    public Optional<Payment> getById(String paymentId) {
        return paymentJpaRepository.findById(paymentId)
                                   .map(PaymentEntity::toPayment);
    }

    private Specification<PaymentEntity> hasRentId(String rentId) {
        return ((root, query, cb) -> cb.equal(root.get("rentId"), rentId));
    }

}
