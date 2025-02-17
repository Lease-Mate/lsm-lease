package com.lsm.ws.lease.infrastructure.persistance.facade;

import com.lsm.ws.lease.domain.Pagination;
import com.lsm.ws.lease.domain.offer.Offer;
import com.lsm.ws.lease.domain.offer.OfferFilter;
import com.lsm.ws.lease.domain.offer.OfferRepository;
import com.lsm.ws.lease.domain.offer.OfferStatus;
import com.lsm.ws.lease.infrastructure.persistance.jpa.OfferJpaRepository;
import com.lsm.ws.lease.infrastructure.persistance.model.OfferEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class OfferFacadeRepository implements OfferRepository {

    private final OfferJpaRepository offerJpaRepository;

    public OfferFacadeRepository(OfferJpaRepository offerJpaRepository) {
        this.offerJpaRepository = offerJpaRepository;
    }

    @Override
    public Offer save(Offer offer) {
        var entity = new OfferEntity();
        entity.from(offer);
        return offerJpaRepository.save(entity)
                                 .toOffer();
    }

    @Override
    public List<Offer> search(OfferFilter offerFilter, Pagination pagination) {
        Specification<OfferEntity> specification = Specification.where(null);

        if (offerFilter.userId() != null) {
            specification = specification.and(hasUserId(offerFilter.userId()));
        }
        if (offerFilter.status() != null) {
            specification = specification.and(hasStatus(offerFilter.status()));
        }
        if (offerFilter.country() != null) {
            specification = specification.and(hasCountry(offerFilter.country()));
        }
        if (offerFilter.city() != null) {
            specification = specification.and(hasCity(offerFilter.city()));
        }
        if (offerFilter.availableTo() != null) {
            specification = specification.and(isAvailableTo(offerFilter.availableTo()));
        }
        if (offerFilter.rentFrom() != null) {
            specification = specification.and(hasRentFrom(offerFilter.rentFrom()));
        }
        if (offerFilter.rentTo() != null) {
            specification = specification.and(hasRentTo(offerFilter.rentTo()));
        }
        if (offerFilter.surfaceAreaFrom() != null) {
            specification = specification.and(hasSurfaceAreaFrom(offerFilter.surfaceAreaFrom()));
        }
        if (offerFilter.surfaceAreaTo() != null) {
            specification = specification.and(hasSurfaceAreaTo(offerFilter.surfaceAreaTo()));
        }

        var pageable = PageRequest.of(
                pagination.getPage() - 1,
                pagination.getSize(),
                Sort.by(Sort.Direction.DESC, "availableFrom")
        );
        return offerJpaRepository.findAll(specification, pageable)
                                 .stream().map(OfferEntity::toOffer)
                                 .toList();
    }

    @Override
    public Optional<Offer> findById(String offerId) {
        return offerJpaRepository.findById(offerId)
                                 .map(OfferEntity::toOffer);
    }

    @Override
    public void delete(Offer offer) {
        offerJpaRepository.deleteById(offer.getId());
    }

    private Specification<OfferEntity> hasUserId(String userId) {
        return (root, query, cb) -> cb.equal(root.get("userId"), userId);
    }

    private Specification<OfferEntity> hasStatus(OfferStatus status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    private Specification<OfferEntity> hasSurfaceAreaTo(Double surfaceAreaTo) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("surfaceArea"), surfaceAreaTo);
    }

    private Specification<OfferEntity> hasSurfaceAreaFrom(Double surfaceAreaFrom) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("surfaceArea"), surfaceAreaFrom);
    }

    private Specification<OfferEntity> hasRentTo(BigDecimal rentTo) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("rent"), rentTo);
    }

    private Specification<OfferEntity> hasRentFrom(BigDecimal rentFrom) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("rent"), rentFrom);
    }

    private Specification<OfferEntity> isAvailableTo(LocalDate availableFrom) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("availableFrom"), availableFrom);
    }

    private Specification<OfferEntity> hasCity(String city) {
        return (root, query, cb) -> cb.equal(root.get("city"), city);
    }

    private Specification<OfferEntity> hasCountry(String country) {
        return (root, query, cb) -> cb.equal(root.get("country"), country);
    }
}
