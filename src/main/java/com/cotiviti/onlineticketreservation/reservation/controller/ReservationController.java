package com.cotiviti.onlineticketreservation.reservation.controller;

import com.cotiviti.onlineticketreservation.reservation.dto.BookingInfoDto;
import com.cotiviti.onlineticketreservation.reservation.dto.ReservationDto;
import com.cotiviti.onlineticketreservation.reservation.dto.ReservationPageDto;
import com.cotiviti.onlineticketreservation.reservation.service.ReservationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationDto> save(@RequestBody ReservationDto reservationDto) {
        return new ResponseEntity(reservationService.save(reservationDto), HttpStatus.CREATED);
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

    private PageRequest getPageRequest(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "per-page", required = false) Integer perPage, @RequestParam(value = "sort-by", required = false) String sortBy, @RequestParam(value = "order", required = false) String order) {
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
        return pageRequest;
    }
}
