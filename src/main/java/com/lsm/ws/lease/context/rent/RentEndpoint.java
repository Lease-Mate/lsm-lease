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

    private final RentService rentService;

    public RentEndpoint(RentService rentService) {
        this.rentService = rentService;
    }

    @Operation(summary = "Zapytanie wynajmu", description = "Wysyła zapytanie wynajmu do właściciela, wymaga tokenu JWT")
    @PostMapping("/{offerId}/request")
    public ResponseEntity<Void> requestRent(@PathVariable String offerId) {

        rentService.request(offerId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Cofnij zapytanie wynajmu",
            description = "Cofa zapytanie wynajmu, wymaga tokenu JWT")
    @PostMapping("/{rentId}/revoke")
    public ResponseEntity<Void> revoke(@PathVariable String rentId) {

        rentService.revoke(rentId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Pobierz zapytania wynajmu",
            description = "Zwraca listę zapytań wynajmu dla danej oferty, wymaga tokenu JWT")
    @GetMapping("/{offerId}/request")
    public ResponseEntity<List<Rent>> getOfferRequests(@PathVariable String offerId,
                                                       @ParameterObject
                                                       PaginationSpecification paginationSpecification) {

        var requests = rentService.getOfferRequests(offerId, paginationSpecification);
        return ResponseEntity.ok(requests);
    }

    @Operation(summary = "Zaakceptuj żądanie wynajmu",
            description = "Akceptuje żądanie wynajmu, rozpoczyna proces płatności, wymaga tokenu JWT")
    @PutMapping("/{offerId}/request/{rentId}/accept")
    public ResponseEntity<Void> acceptRent(@PathVariable String offerId, @PathVariable String rentId) {

        rentService.accept(offerId, rentId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Odrzuć żądanie", description = "Odrzuca zapytanie wynajmu, wymaga tokenu JWT")
    @PutMapping("/{offerId}/request/{rentId}/reject")
    public ResponseEntity<Void> rejectRent(@PathVariable String offerId, @PathVariable String rentId) {

        rentService.reject(offerId, rentId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Pobierz wynajmy", description = "Zwraca listę wynajmów, wymaga tokenu JWT")
    @GetMapping("/owner")
    public ResponseEntity<List<Rent>> getOwnerRents() {
        var rents = rentService.getOwnerRents();
        return ResponseEntity.ok(rents);
    }

    @Operation(summary = "Pobierz najmy", description = "Zwraca listę najmów, wymaga tokenu JWT")
    @GetMapping()
    public ResponseEntity<List<Rent>> getUserRents() {
        var rents = rentService.getUserRents();
        return ResponseEntity.ok(rents);
    }

    @Operation(summary = "Pobierz żądania najmu", description = "Zwraca listę żądań najmów, wymaga tokenu JWT")
    @GetMapping("/request")
    public ResponseEntity<List<Rent>> getUserRequests() {

        var requests = rentService.getUserRequests();
        return ResponseEntity.ok(requests);
    }

    @Operation(summary = "Pobierz żądania wynajmu",
            description = "Zwraca listę żądań najmów od strony właściciela, wymaga tokenu JWT")
    @GetMapping("/request/owner")
    public ResponseEntity<List<Rent>> getOwnerRequests() {

        var requests = rentService.getOwnerRequests();
        return ResponseEntity.ok(requests);
    }

    @Operation(summary = "Pobierz informacje o najmie",
            description = "Zwraca informacje o najmie, wymaga tokenu JWT")
    @GetMapping("/{rentId}")
    public ResponseEntity<Rent> getRent(@PathVariable String rentId) {
        var rent = rentService.getRent(rentId);
        return ResponseEntity.ok(rent);
    }
}
