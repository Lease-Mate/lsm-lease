package com.lsm.ws.lease.domain.rent;

import com.lsm.ws.lease.domain.Pagination;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RentRepository {

    Rent save(Rent rent);

    Optional<Rent> findById(String id);

    List<Rent> findByOfferIdAndStatus(String offerId, RentStatus status);

    List<Rent> findByOfferIdAndStatus(String offerId, RentStatus status, Pagination pagination);

    List<Rent> findByUserIdAndStatus(String userId, RentStatus status);

    List<Rent> findByUserIdAndStatuses(String userId, Set<RentStatus> statuses);

    List<Rent> findByOwnerIdAndStatus(String ownerId, RentStatus status);

    void deleteForOfferId(String offerId);

    Optional<Rent> findRequestedUserRentForOffer(String userId, String offerId);
}
