package com.lsm.ws.lease.context.payment;

import com.lsm.ws.lease.domain.payment.Payment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Płatności")
@RestController
@RequestMapping("/v1/api/lease/payment")
public class PaymentEndpoint {

    private final PaymentService paymentService;

    public PaymentEndpoint(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "Pobierz czynsze", description = "Zwraca listę płatności czynszu, wymaga tokenu JWT")
    @PostMapping("/{rentId}/all")
    public ResponseEntity<List<Payment>> requestRent(@PathVariable String rentId) {
        var payments = paymentService.getAllPaymentsForRent(rentId);
        return ResponseEntity.ok(payments);
    }

    @Operation(summary = "Opłać czynsz", description = "Opłaca czynsz za bieżący miesiąc, wymaga tokenu JWT")
    @PostMapping("/{paymentId}/pay")
    public ResponseEntity<Payment> payRent(@PathVariable String paymentId) {
        var payment = paymentService.payRent(paymentId);
        return ResponseEntity.ok(payment);
    }
}
