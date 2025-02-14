package com.lsm.ws.lease.context.rent;

import com.lsm.ws.lease.domain.rent.Rent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Rent services")
@RestController
@RequestMapping("/v1/api/lease/internal")
public class RentInternalEndpoint {

    private final RentService rentService;

    public RentInternalEndpoint(RentService rentService) {
        this.rentService = rentService;
    }

    @Operation(hidden = true)
    @DeleteMapping("/offer/{offerId}/rent")
    public ResponseEntity<List<Rent>> deleteRents(@PathVariable String offerId) {

        rentService.deleteRentsForOffer(offerId);
        return ResponseEntity.ok().build();
    }
}
