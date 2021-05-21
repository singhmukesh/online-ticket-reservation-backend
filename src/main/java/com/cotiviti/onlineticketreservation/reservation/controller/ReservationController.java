package com.cotiviti.onlineticketreservation.reservation.controller;

import com.cotiviti.onlineticketreservation.reservation.dto.BookingInfoDto;
import com.cotiviti.onlineticketreservation.reservation.dto.ReservationDto;
import com.cotiviti.onlineticketreservation.reservation.dto.ReservationPageDto;
import com.cotiviti.onlineticketreservation.reservation.service.ReservationService;
import com.cotiviti.onlineticketreservation.util.FieldValidationService;
import com.cotiviti.onlineticketreservation.util.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.cotiviti.onlineticketreservation.util.PaginationUtil.getPageRequest;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final FieldValidationService fieldValidationService;

    public ReservationController(ReservationService reservationService, FieldValidationService fieldValidationService) {
        this.reservationService = reservationService;
        this.fieldValidationService = fieldValidationService;
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ReservationDto reservationDto, BindingResult result) {
        ResponseEntity<?> errorMap = fieldValidationService.fieldValidation(result);
        if (errorMap != null) {
            return errorMap;
        }
        return new Response().success(reservationService.save(reservationDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingInfoDto> getByEventId(@PathVariable("id") Long id) {
        return new ResponseEntity(reservationService.getBookingInfoById(id), HttpStatus.OK);
    }

    @GetMapping("/monthly-booking")
    public ResponseEntity<List<Map>> getMonthlyBooking() {
        return new ResponseEntity(reservationService.getMonthlyBooking(), HttpStatus.OK);
    }

    @GetMapping("/monthly-revenue")
    public ResponseEntity<List<Map>> getMonthlyRevenue() {
        return new ResponseEntity(reservationService.getMonthlyRevenue(), HttpStatus.OK);
    }

    @GetMapping("/stat")
    public ResponseEntity<Map> getReservationStatistics() {
        return new ResponseEntity(reservationService.metricsForDashboard(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ReservationPageDto> getUserBookingInfo(@RequestParam(value = "page", required = false) Integer page,
                                                                 @RequestParam(value = "per-page", required = false) Integer perPage,
                                                                 @RequestParam(value = "sort-by", required = false) String sortBy,
                                                                 @RequestParam(value = "order", required = false) String order) {
        PageRequest pageRequest = getPageRequest(page, perPage, sortBy, order);
        ReservationPageDto reservationPageDto = reservationService.getBookingInfoByUserId(pageRequest);
        return new ResponseEntity(reservationPageDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ReservationPageDto> getBookingInfoForAdmin(@RequestParam(value = "page", required = false) Integer page,
                                                                     @RequestParam(value = "per-page", required = false) Integer perPage,
                                                                     @RequestParam(value = "sort-by", required = false) String sortBy,
                                                                     @RequestParam(value = "order", required = false) String order) {
        PageRequest pageRequest = getPageRequest(page, perPage, sortBy, order);
        ReservationPageDto reservationPageDto = reservationService.findAll(pageRequest);
        return new ResponseEntity(reservationPageDto, HttpStatus.OK);
    }


}
