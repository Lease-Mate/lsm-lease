package com.lsm.ws.lease.context.rent;

import com.lsm.ws.lease.configuration.exception.CantRejectRentRequestException;
import com.lsm.ws.lease.configuration.exception.InactiveOfferException;
import com.lsm.ws.lease.configuration.exception.NoSuchOfferException;
import com.lsm.ws.lease.configuration.exception.NoSuchRentRequestException;
import com.lsm.ws.lease.configuration.exception.RentAlreadyAcceptedException;
import com.lsm.ws.lease.configuration.exception.RentAlreadyRequestedException;
import com.lsm.ws.lease.context.payment.PaymentService;
import com.lsm.ws.lease.domain.Pagination;
import com.lsm.ws.lease.domain.offer.Offer;
import com.lsm.ws.lease.domain.offer.OfferRepository;
import com.lsm.ws.lease.domain.rent.Rent;
import com.lsm.ws.lease.domain.rent.RentRepository;
import com.lsm.ws.lease.domain.rent.RentStatus;
import com.lsm.ws.lease.infrastructure.rest.context.RequestContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class RentService {

    private final OfferRepository offerRepository;
    private final RentRepository rentRepository;
    private final RequestContext requestContext;
    private final UserAccessValidator userAccessValidator;
    private final PaymentService paymentService;

    public RentService(OfferRepository offerRepository, RentRepository rentRepository, RequestContext requestContext, UserAccessValidator userAccessValidator, PaymentService paymentService) {
        this.offerRepository = offerRepository;
        this.rentRepository = rentRepository;
        this.requestContext = requestContext;
        this.userAccessValidator = userAccessValidator;
        this.paymentService = paymentService;
    }

    public void request(String offerId) {
        var offer = offerRepository.findById(offerId)
                                   .filter(Offer::isPublished)
                                   .orElseThrow(NoSuchOfferException::new);

        rentRepository.findByOfferIdAndStatus(offerId, RentStatus.ACTIVE)
                      .stream().findAny().ifPresent((rent) -> {
                          throw new InactiveOfferException();
                      });
        rentRepository.findByUserIdAndStatus(requestContext.userId(), RentStatus.REQUESTED)
                      .stream().findAny().ifPresent((rent) -> {
                          throw new RentAlreadyRequestedException();
                      });

        var rent = new Rent(
                UUID.randomUUID().toString(),
                offer.getAppUserId(),
                offerId,
                requestContext.userId(),
                RentStatus.REQUESTED,
                LocalDateTime.now()
        );

        rentRepository.save(rent);
    }

    public List<Rent> getOfferRequests(String offerId, Pagination pagination) {
        var offer = offerRepository.findById(offerId)
                                   .orElseThrow(NoSuchOfferException::new);

        userAccessValidator.validateOwner(offer);

        return rentRepository.findByOfferIdAndStatus(offerId, RentStatus.REQUESTED, pagination);
    }

    @Transactional
    public void accept(String offerId, String rentId) {
        var offer = offerRepository.findById(offerId)
                                   .orElseThrow(NoSuchOfferException::new);

        userAccessValidator.validateOwner(offer);
        rentRepository.findByOfferIdAndStatus(offerId, RentStatus.ACTIVE)
                      .stream().findAny().ifPresent((rent) -> {
                          throw new RentAlreadyAcceptedException();
                      });

        var rent = rentRepository.findById(rentId)
                                 .filter(Rent::isRequested)
                                 .orElseThrow(NoSuchRentRequestException::new);

        paymentService.createPayment(rent);
        rent.setStatus(RentStatus.ACTIVE);
        rentRepository.save(rent);
    }

    public List<Rent> getOwnerRents() {
        return rentRepository.findByOwnerIdAndStatus(requestContext.userId(), RentStatus.ACTIVE);
    }

    public List<Rent> getUserRents() {
        return rentRepository.findByUserIdAndStatus(requestContext.userId(), RentStatus.ACTIVE);
    }

    public List<Rent> getUserRequests() {
        return rentRepository.findByUserIdAndStatuses(requestContext.userId(),
                Set.of(RentStatus.REQUESTED, RentStatus.REJECTED_REQUEST));
    }

    public List<Rent> getOwnerRequests() {
        return rentRepository.findByOwnerIdAndStatus(requestContext.userId(), RentStatus.REQUESTED);
    }

    public void reject(String offerId, String rentId) {
        var offer = offerRepository.findById(offerId)
                                   .orElseThrow(NoSuchOfferException::new);

        userAccessValidator.validateOwner(offer);

        var rent = rentRepository.findById(rentId)
                                 .orElseThrow(NoSuchRentRequestException::new);

        if (!rent.isRequested()) {
            throw new CantRejectRentRequestException();
        }

        rent.setStatus(RentStatus.REJECTED_REQUEST);
        rentRepository.save(rent);
    }

    public void revoke(String rentId) {
        var rent = rentRepository.findById(rentId)
                                 .orElseThrow(NoSuchRentRequestException::new);

        userAccessValidator.validateOwner(rent);

        if (!rent.isRequested()) {
            throw new CantRejectRentRequestException();
        }

        rent.setStatus(RentStatus.REVOKED_REQUEST);
        rentRepository.save(rent);
    }
}
