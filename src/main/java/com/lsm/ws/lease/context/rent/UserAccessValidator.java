package com.lsm.ws.lease.context.rent;

import com.lsm.ws.lease.configuration.exception.forbidden.ForbiddenException;
import com.lsm.ws.lease.domain.offer.Offer;
import com.lsm.ws.lease.domain.rent.Rent;
import com.lsm.ws.lease.infrastructure.rest.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class UserAccessValidator {

    private final RequestContext requestContext;

    public UserAccessValidator(RequestContext requestContext) {
        this.requestContext = requestContext;
    }

    public void validateOwner(Offer offer) {
        if (!offer.getAppUserId().equals(requestContext.userId())) {
            throw new ForbiddenException("Nie masz dostępu do tej oferty");
        }
    }

    public void validateOwner(Rent rent) {
        if (!rent.getOwnerId().equals(requestContext.userId())) {
            throw new ForbiddenException("Nie masz dostępu do tej oferty");
        }
    }
}
