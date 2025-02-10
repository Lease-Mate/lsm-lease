package com.lsm.ws.lease.infrastructure.rest.offer;

import com.lsm.ws.lease.domain.offer.Offer;
import com.lsm.ws.lease.domain.offer.OfferRepository;
import com.lsm.ws.lease.infrastructure.rest.offer.dto.OfferDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OfferRestRepository implements OfferRepository {

    private final OfferClient offerClient;

    public OfferRestRepository(OfferClient offerClient) {
        this.offerClient = offerClient;
    }

    @Override
    public Optional<Offer> findById(String id) {
        return offerClient.getOffer(id)
                          .map(OfferDto::toOffer);
    }
}
