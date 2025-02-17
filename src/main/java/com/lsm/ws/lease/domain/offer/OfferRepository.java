package com.lsm.ws.lease.domain.offer;

import java.util.Optional;

public interface OfferRepository {

    Optional<Offer> findById(String id);
}
