package com.lsm.ws.lease.context.payment;

import com.lsm.ws.lease.configuration.exception.NoSuchPaymentException;
import com.lsm.ws.lease.configuration.exception.NoSuchRentRequestException;
import com.lsm.ws.lease.configuration.exception.PaymentAlreadyPaidException;
import com.lsm.ws.lease.context.rent.UserAccessValidator;
import com.lsm.ws.lease.domain.payment.Payment;
import com.lsm.ws.lease.domain.payment.PaymentRepository;
import com.lsm.ws.lease.domain.payment.PaymentStatus;
import com.lsm.ws.lease.domain.rent.Rent;
import com.lsm.ws.lease.domain.rent.RentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RentRepository rentRepository;
    private final UserAccessValidator userAccessValidator;

    public PaymentService(PaymentRepository paymentRepository, RentRepository rentRepository,
                          UserAccessValidator userAccessValidator) {
        this.paymentRepository = paymentRepository;
        this.rentRepository = rentRepository;
        this.userAccessValidator = userAccessValidator;
    }

    public void createPayment(Rent rent) {
        var payment = new Payment(UUID.randomUUID().toString(),
                                  rent.getId(),
                                  rent.getOwnerId(),
                                  rent.getOfferId(),
                                  rent.getUserId(),
                                  PaymentStatus.UNPAID,
                                  LocalDate.now().plusDays(10));
        paymentRepository.save(payment);
    }

    public List<Payment> getAllPaymentsForRent(String rentId) {
        var rent = rentRepository.findById(rentId)
                                 .orElseThrow(NoSuchRentRequestException::new);

        userAccessValidator.validateAccess(rent);

        return paymentRepository.findByRentId(rentId);
    }

    public Payment payRent(String paymentId) {
        var payment = paymentRepository.getById(paymentId)
                                       .orElseThrow(NoSuchPaymentException::new);
        if (payment.getStatus() == PaymentStatus.PAID) {
            throw new PaymentAlreadyPaidException();
        }
        payment.setStatus(PaymentStatus.PAID);
        payment.setPaymentDate(LocalDateTime.now());
        payment = paymentRepository.save(payment);

        var newPayment = new Payment(UUID.randomUUID().toString(),
                                     payment.getRentId(),
                                     payment.getOwnerId(),
                                     payment.getOfferId(),
                                     payment.getUserId(),
                                     PaymentStatus.UNPAID,
                                     payment.getDueDate().plusMonths(1));
        paymentRepository.save(newPayment);
        return payment;
    }
}
