package com.lsm.ws.lease.context.offer;

import com.lsm.ws.lease.context.dto.IdWrapperDto;
import com.lsm.ws.lease.context.offer.dto.OfferDetailsDto;
import com.lsm.ws.lease.context.offer.dto.OfferDto;
import com.lsm.ws.lease.context.offer.dto.UpdateOfferRequest;
import com.lsm.ws.lease.domain.offer.OfferFilter;
import com.lsm.ws.lease.domain.offer.OfferStatus;
import com.lsm.ws.lease.infrastructure.rest.PaginationSpecification;
import com.lsm.ws.lease.infrastructure.rest.context.RequestContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "Oferty")
@RestController
@RequestMapping("/v1/api/lease/offer")
public class OfferInfoEndpoint {

    private final OfferService offerService;
    private final RequestContext requestContext;

    public OfferInfoEndpoint(OfferService offerService, RequestContext requestContext) {
        this.offerService = offerService;
        this.requestContext = requestContext;
    }

    @Operation(summary = "Utwórz ofertę", description = "Tworzy ofertę, wymaga tokenu JWT")
    @PostMapping("/create")
    public ResponseEntity<IdWrapperDto> createOffer() {
        var offer = offerService.createOffer();
        return ResponseEntity.ok(IdWrapperDto.from(offer));
    }

    @Operation(summary = "Zaktualizuj ofertę", description = "Aktualizuje ofertę, wymaga tokenu JWT")
    @PutMapping("/{offerId}/update")
    public ResponseEntity<OfferDto> addOffer(@PathVariable String offerId,
                                             @Valid @RequestBody UpdateOfferRequest request) {
        // TODO: 23/10/2024 map request to domain object e.g. AddOfferCommand
        var offer = offerService.updateOffer(offerId, request);
        return ResponseEntity.ok(OfferDto.from(offer));
    }

    @Operation(summary = "Informacje o ofercie", description = "Zwraca informacje o ofercie, nie wymaga JWT")
    @GetMapping("/{offerId}")
    public ResponseEntity<OfferDto> getById(@PathVariable String offerId) {
        var offer = offerService.getOffer(offerId);
        return ResponseEntity.ok(OfferDto.from(offer));
    }

    @Operation(summary = "Wyszukaj dostępne oferty", description = "Zwraca listę dostępnych ofert")
    @GetMapping("/available/search")
    public ResponseEntity<List<OfferDto>> searchOffers(@RequestParam(required = false) String country,
                                                       @RequestParam(required = false) String city,
                                                       @RequestParam(required = false) LocalDate availableTo,
                                                       @RequestParam(required = false) BigDecimal rentFrom,
                                                       @RequestParam(required = false) BigDecimal rentTo,
                                                       @RequestParam(required = false) Double surfaceAreaFrom,
                                                       @RequestParam(required = false) Double surfaceAreaTo,
                                                       @ParameterObject
                                                       PaginationSpecification paginationSpecification) {
        var filter = OfferFilter.builder()
                                .withStatus(OfferStatus.PUBLISHED)
                                .withCountry(country)
                                .withCity(city)
                                .withAvailableTo(availableTo)
                                .withRentFrom(rentFrom)
                                .withRentTo(rentTo)
                                .withSurfaceAreaFrom(surfaceAreaFrom)
                                .withSurfaceAreaTo(surfaceAreaTo)
                                .build();

        var response = offerService.search(filter, paginationSpecification)
                                   .stream()
                                   .map(OfferDto::from)
                                   .toList();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Usuń ofertę", description = "Usuwa ofertę")
    @DeleteMapping("/{offerId}")
    public ResponseEntity<Void> deleteOffer(@PathVariable String offerId) {
        offerService.delete(offerId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Wyszukaj oferty użytkownika",
            description = "Zwraca listę ofert dodanych przez użytkownika, wymaga tokenu JWT")
    @GetMapping("/user/search")
    public ResponseEntity<List<OfferDetailsDto>> userOffersSearch(@ParameterObject
                                                                  PaginationSpecification paginationSpecification) {
        var filter = OfferFilter.builder()
                                .withUserId(requestContext.userId())
                                .build();

        var response = offerService.search(filter, paginationSpecification)
                                   .stream()
                                   .map(OfferDetailsDto::from)
                                   .toList();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Opublikuj ofertę", description = "Publikuje ofertę, wymaga tokenu JWT")
    @PostMapping("/{offerId}/publish")
    public ResponseEntity<OfferDetailsDto> publishOffer(@PathVariable String offerId) {
        var response = OfferDetailsDto.from(offerService.publish(offerId));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Ukryj ofertę", description = "Cofa publikacje oferty, wymaga tokenu JWT")
    @PostMapping("/{offerId}/unpublish")
    public ResponseEntity<OfferDetailsDto> unPublishOffer(@PathVariable String offerId) {
        var response = OfferDetailsDto.from(offerService.unpublish(offerId));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Opłać ofertę", description = "Opłaca ofertę, wymaga tokenu JWT")
    @PostMapping("/{offerId}/pay")
    public ResponseEntity<OfferDetailsDto> payOffer(@PathVariable String offerId) {
        var response = OfferDetailsDto.from(offerService.pay(offerId));
        return ResponseEntity.ok(response);
    }
}
