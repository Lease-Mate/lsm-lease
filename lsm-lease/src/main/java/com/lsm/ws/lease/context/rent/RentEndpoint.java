package com.lsm.ws.lease.context.rent;

import com.lsm.ws.lease.domain.rent.Rent;
import com.lsm.ws.lease.infrastructure.rest.PaginationSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Rent services")
@RestController
@RequestMapping("/v1/api/lease/rent")
public class RentEndpoint {

    private static final String REQUEST_RENT = "Request rent";
    private static final String REQUEST_RENT_DESC = "sends request to rent specified offer";
    private static final String GET_RENT_REQUESTS = "Rent requests";
    private static final String GET_RENT_REQUESTS_DESC = "returns paginated list of rent requests for specified offer";
    private static final String ACCEPT_RENT = "Accept rent";
    private static final String ACCEPT_RENT_DESC = "accepts rent request";
    private static final String OWNER_RENTS = "Owner rents";
    private static final String OWNER_RENTS_DESC = "returns rents that user owns";
    private static final String RENTS = "User rents";
    private static final String RENTS_DESC = "returns rents that user rents";

    private final RentService rentService;

    public RentEndpoint(RentService rentService) {
        this.rentService = rentService;
    }

    @Operation(summary = REQUEST_RENT, description = REQUEST_RENT_DESC)
    @PostMapping("/{offerId}/request")
    public ResponseEntity<Void> requestRent(@PathVariable String offerId) {

        rentService.request(offerId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = GET_RENT_REQUESTS, description = GET_RENT_REQUESTS_DESC)
    @GetMapping("/{offerId}/request")
    public ResponseEntity<List<Rent>> getOfferRequests(@PathVariable String offerId,
                                                       @ParameterObject
                                                       PaginationSpecification paginationSpecification) {

        var requests = rentService.getOfferRequests(offerId, paginationSpecification);
        return ResponseEntity.ok(requests);
    }

    @Operation(summary = ACCEPT_RENT, description = ACCEPT_RENT_DESC)
    @PutMapping("/{offerId}/request/{rentId}/accept")
    public ResponseEntity<Void> acceptRent(@PathVariable String offerId, @PathVariable String rentId) {

        rentService.accept(offerId, rentId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = OWNER_RENTS, description = OWNER_RENTS_DESC)
    @GetMapping("/owner")
    public ResponseEntity<List<Rent>> getOwnerRents() {
        var rents = rentService.getOwnerRents();
        return ResponseEntity.ok(rents);
    }

    @Operation(summary = RENTS, description = RENTS_DESC)
    @GetMapping()
    public ResponseEntity<List<Rent>> getUserRents() {
        var rents = rentService.getUserRents();
        return ResponseEntity.ok(rents);
    }

    @Operation(summary = "Get user requests", description = "Returns user requests to rent")
    @GetMapping("/request")
    public ResponseEntity<List<Rent>> getUserRequests() {

        var requests = rentService.getUserRequests();
        return ResponseEntity.ok(requests);
    }

    @Operation(summary = "Get owner requests", description = "Returns rent requests for owner")
    @GetMapping("/request/owner")
    public ResponseEntity<List<Rent>> getOwnerRequests() {

        var requests = rentService.getOwnerRequests();
        return ResponseEntity.ok(requests);
    }
}
