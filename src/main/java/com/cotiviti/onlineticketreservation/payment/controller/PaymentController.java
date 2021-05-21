package com.cotiviti.onlineticketreservation.payment.controller;

import com.cotiviti.onlineticketreservation.payment.dto.PaymentDto;
import com.cotiviti.onlineticketreservation.payment.dto.PaymentPageDto;
import com.cotiviti.onlineticketreservation.payment.dto.PaymentRequestDto;
import com.cotiviti.onlineticketreservation.user.dto.NavItem;
import com.cotiviti.onlineticketreservation.payment.service.PaymentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentDto> save(@RequestBody PaymentDto paymentDto) {
        return new ResponseEntity(paymentService.save(paymentDto), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<PaymentDto> update(@RequestBody PaymentDto paymentDto) {
        return new ResponseEntity(paymentService.save(paymentDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        paymentService.delete(id);
        return new ResponseEntity("Deleted", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getById(@PathVariable("id") Long id) {
        return new ResponseEntity(paymentService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentDto>> getAll() {
        return new ResponseEntity(paymentService.getAllPaymentMethod(), HttpStatus.OK);
    }
    @PostMapping("/pay")
    public ResponseEntity<PaymentRequestDto> pay(@RequestBody PaymentRequestDto paymentDto) {
        return new ResponseEntity(paymentService.pay(paymentDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PaymentPageDto> getPayment(@RequestParam(value = "page", required = false) Integer page,
                                                     @RequestParam(value = "per-page", required = false) Integer perPage,
                                                     @RequestParam(value = "sort-by", required = false) String sortBy,
                                                     @RequestParam(value = "order", required = false) String order) {
        PageRequest pageRequest;
        if (page != null && perPage != null && !sortBy.isBlank() && order.equals("desc")) {
            pageRequest = PageRequest.of(page, perPage, Sort.by(sortBy).descending());
        } else if (page != null && perPage != null && !sortBy.isBlank() && order.equals("asc")) {
            pageRequest = PageRequest.of(page, perPage, Sort.by(sortBy).ascending());
        } else if (page != null && perPage != null) {
            pageRequest = PageRequest.of(page, perPage, Sort.by("id").ascending());
        } else {
            pageRequest = PageRequest.of(0, 5, Sort.by("id").ascending());
        }
        return new ResponseEntity(paymentService.findAll(pageRequest), HttpStatus.OK);
    }
}
