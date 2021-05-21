package com.cotiviti.onlineticketreservation.reservation.repository;

import com.cotiviti.onlineticketreservation.reservation.entity.Reservation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Long countReservationByUser_Email(String email);

    List<Reservation> findAllByUserId(long userId, Pageable pageable);

    @Query(value = "SELECT DATE_FORMAT(booked_date,'%M %Y') AS Month,COUNT(id) AS count FROM ticket_reservation.reservation WHERE reservation_status ='SUCCESS' GROUP BY month(booked_date)", nativeQuery = true)
    List<Map> getBookingReport();

    @Query(value = "SELECT DATE_FORMAT(booked_date,'%M %Y') AS Month,SUM(total_cost) AS total FROM ticket_reservation.reservation WHERE reservation_status ='SUCCESS' GROUP BY month(booked_date)", nativeQuery = true)
    List<Map> getMonthlyRevenueReport();

    @Query(value = "SELECT count(id) AS count FROM reservation where reservation_status = 'SUCCESS'", nativeQuery = true)
    Map<String, Object> getSuccessfulReservation();

    @Query(value = "SELECT sum(total_cost) AS revenue FROM reservation where reservation_status = 'SUCCESS'", nativeQuery = true)
    Map<String, Object> getTotalRevenue();
}
