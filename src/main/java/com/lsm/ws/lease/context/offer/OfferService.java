package com.lsm.ws.lease.context.offer;

import com.lsm.ws.lease.configuration.exception.NoSuchOfferException;
import com.lsm.ws.lease.configuration.exception.OfferAlreadyPaidException;
import com.lsm.ws.lease.configuration.exception.OfferCantBeUpdatedException;
import com.lsm.ws.lease.configuration.exception.OfferUnpaidException;
import com.lsm.ws.lease.context.offer.dto.UpdateOfferRequest;
import com.lsm.ws.lease.domain.Pagination;
import com.lsm.ws.lease.domain.offer.Offer;
import com.lsm.ws.lease.domain.offer.OfferFilter;
import com.lsm.ws.lease.domain.offer.OfferRepository;
import com.lsm.ws.lease.domain.offer.OfferStatus;
import com.lsm.ws.lease.domain.rent.RentRepository;
import com.lsm.ws.lease.infrastructure.rest.context.RequestContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final RentRepository rentRepository;
    private final RequestContext requestContext;

    public OfferService(OfferRepository offerRepository, RentRepository rentRepository, RequestContext requestContext) {
        this.offerRepository = offerRepository;
        this.rentRepository = rentRepository;
        this.requestContext = requestContext;
    }

    public Offer createOffer() {
        var offer = new Offer(UUID.randomUUID().toString(), requestContext.userId());
        return offerRepository.save(offer);
    }

    public Offer updateOffer(String offerId, UpdateOfferRequest request) {
        var offer = offerRepository.findById(offerId)
                                   .filter(o -> requestContext.userId().equals(o.getAppUserId()))
                                   .orElseThrow(NoSuchOfferException::new);

        if (!offer.canBeUpdated()){
            throw new OfferCantBeUpdatedException();
        }

        return offerRepository.save(request.toOffer(offer));
    }

    public List<Offer> search(OfferFilter filter, Pagination pagination) {
        return offerRepository.search(filter, pagination);
    }

    public void delete(String offerId) {
        var offer = offerRepository.findById(offerId)
                                   .filter(o -> requestContext.userId().equals(o.getAppUserId()))
                                   .orElseThrow(NoSuchOfferException::new);

        rentRepository.deleteForOfferId(offer.getId());
        offerRepository.delete(offer);
    }

    public Offer publish(String offerId) {
        var offer = offerRepository.findById(offerId)
                                   .filter(o -> requestContext.userId().equals(o.getAppUserId()))
                                   .orElseThrow(NoSuchOfferException::new);
        if (offer.getStatus() == OfferStatus.UNPAID) {
            throw new OfferUnpaidException();
        }

        offer.setStatus(OfferStatus.PUBLISHED);
        return offerRepository.save(offer);
    }

    public Offer pay(String offerId) {
        var offer = offerRepository.findById(offerId)
                                   .filter(o -> requestContext.userId().equals(o.getAppUserId()))
                                   .orElseThrow(NoSuchOfferException::new);
        if (offer.getStatus() == OfferStatus.PAID || offer.getStatus() == OfferStatus.PUBLISHED) {
            throw new OfferAlreadyPaidException();
        }

        offer.setStatus(OfferStatus.PAID);
        return offerRepository.save(offer);
    }

    public Offer unpublish(String offerId) {
        var offer = offerRepository.findById(offerId)
                                   .filter(o -> requestContext.userId().equals(o.getAppUserId()))
                                   .orElseThrow(NoSuchOfferException::new);
        if (offer.getStatus() == OfferStatus.UNPAID) {
            throw new OfferUnpaidException();
        }

        offer.setStatus(OfferStatus.PAID);
        return offerRepository.save(offer);

    }
}
