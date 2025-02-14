package com.lsm.ws.lease.infrastructure.persistance.facade;

import com.lsm.ws.lease.domain.Pagination;
import com.lsm.ws.lease.domain.rent.Rent;
import com.lsm.ws.lease.domain.rent.RentRepository;
import com.lsm.ws.lease.domain.rent.RentStatus;
import com.lsm.ws.lease.infrastructure.persistance.jpa.RentJpaRepository;
import com.lsm.ws.lease.infrastructure.persistance.model.RentEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class RentFacadeRepository implements RentRepository {

    private final RentJpaRepository rentJpaRepository;

    public RentFacadeRepository(RentJpaRepository rentJpaRepository) {
        this.rentJpaRepository = rentJpaRepository;
    }

    @Override
    public Rent save(Rent rent) {
        var entity = new RentEntity();
        entity.from(rent);
        return rentJpaRepository.save(entity)
                                .toRent();
    }

    @Override
    public Optional<Rent> findById(String id) {
        return rentJpaRepository.findById(id)
                                .map(RentEntity::toRent);
    }

    @Override
    public List<Rent> findByOfferIdAndStatus(String offerId, RentStatus status) {
        var spec = Specification.where(hasOfferId(offerId))
                                .and(hasStatus(status));
        return rentJpaRepository.findAll(spec)
                                .stream().map(RentEntity::toRent)
                                .toList();
    }

    @Override
    public List<Rent> findByOfferIdAndStatus(String offerId, RentStatus status, Pagination pagination) {
        var spec = Specification.where(hasOfferId(offerId))
                                .and(hasStatus(status));

        var pageable = PageRequest.of(
                pagination.getPage() - 1,
                pagination.getSize(),
                Sort.by(Sort.Direction.DESC, "requestDate")
        );
        return rentJpaRepository.findAll(spec, pageable)
                                .stream().map(RentEntity::toRent)
                                .toList();
    }

    @Override
    public List<Rent> findByUserIdAndStatus(String userId, RentStatus status) {
        var spec = Specification.where(hasUserId(userId))
                                .and(hasStatus(status));
        return rentJpaRepository.findAll(spec)
                                .stream().map(RentEntity::toRent)
                                .toList();
    }

    @Override
    public List<Rent> findByUserIdAndStatuses(String userId, Set<RentStatus> statuses) {
        var spec = Specification.where(hasUserId(userId))
                                .and(hasStatus(statuses));
        return rentJpaRepository.findAll(spec)
                                .stream().map(RentEntity::toRent)
                                .toList();
    }

    @Override
    public List<Rent> findByOwnerIdAndStatus(String ownerId, RentStatus status) {
        var spec = Specification.where(hasOwnerId(ownerId))
                                .and(hasStatus(status));
        return rentJpaRepository.findAll(spec)
                                .stream().map(RentEntity::toRent)
                                .toList();
    }

    @Override
    public void deleteForOfferId(String offerId) {
        var spec = Specification.where(hasOfferId(offerId));
        rentJpaRepository.delete(spec);
    }

    @Override
    public Optional<Rent> findRequestedUserRentForOffer(String userId, String offerId) {
        var spec = Specification.where(hasUserId(userId))
                                .and(hasOfferId(offerId))
                                .and(hasStatus(RentStatus.REQUESTED));
        return rentJpaRepository.findAll(spec)
                                .stream()
                                .findFirst()
                                .map(RentEntity::toRent);
    }

    private Specification<RentEntity> hasOwnerId(String ownerId) {
        return ((root, query, cb) -> cb.equal(root.get("ownerId"), ownerId));
    }

    private Specification<RentEntity> hasUserId(String userId) {
        return (root, query, cb) -> cb.equal(root.get("userId"), userId);
    }

    private Specification<RentEntity> hasStatus(RentStatus status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    private Specification<RentEntity> hasOfferId(String offerId) {
        return (root, query, cb) -> cb.equal(root.get("offerId"), offerId);
    }

    private Specification<RentEntity> hasStatus(Set<RentStatus> statuses) {
        return (root, query, cb) -> root.get("status").in(statuses);
    }
}
