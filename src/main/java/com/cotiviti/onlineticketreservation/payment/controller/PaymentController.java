package com.cotiviti.onlineticketreservation.payment.controller;

import com.cotiviti.onlineticketreservation.payment.dto.PaymentDto;
import com.cotiviti.onlineticketreservation.payment.dto.PaymentPageDto;
import com.cotiviti.onlineticketreservation.payment.dto.PaymentRequestDto;
import com.cotiviti.onlineticketreservation.payment.service.PaymentService;
import com.cotiviti.onlineticketreservation.util.FieldValidationService;
import com.cotiviti.onlineticketreservation.util.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.cotiviti.onlineticketreservation.util.PaginationUtil.getPageRequest;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final FieldValidationService fieldValidationService;


    public PaymentController(PaymentService paymentService, FieldValidationService fieldValidationService) {
        this.paymentService = paymentService;
        this.fieldValidationService = fieldValidationService;
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PaymentDto paymentDto, BindingResult result) {
        ResponseEntity<?> errorMap = fieldValidationService.fieldValidation(result);
        if (errorMap != null) {
            return errorMap;
        }
        return new Response().success(paymentService.save(paymentDto));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody PaymentDto paymentDto, BindingResult result) {
        ResponseEntity<?> errorMap = fieldValidationService.fieldValidation(result);
        if (errorMap != null) {
            return errorMap;
        }
        return new Response().success(paymentService.save(paymentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        paymentService.delete(id);
        return new ResponseEntity("Deleted successfully", HttpStatus.OK);
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
    public ResponseEntity<?> pay(@RequestBody PaymentRequestDto paymentDto) {
        return new Response().success(paymentService.pay(paymentDto));
    }

    @GetMapping
    public ResponseEntity<PaymentPageDto> getPayment(@RequestParam(value = "page", required = false) Integer page,
                                                     @RequestParam(value = "per-page", required = false) Integer perPage,
                                                     @RequestParam(value = "sort-by", required = false) String sortBy,
                                                     @RequestParam(value = "order", required = false) String order) {
        PageRequest pageRequest = getPageRequest(page, perPage, sortBy, order);
        return new ResponseEntity(paymentService.findAll(pageRequest), HttpStatus.OK);
    }
}
