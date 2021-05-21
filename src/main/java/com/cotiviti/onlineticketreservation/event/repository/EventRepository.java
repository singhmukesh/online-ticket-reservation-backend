package com.cotiviti.onlineticketreservation.event.repository;

import com.cotiviti.onlineticketreservation.event.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findAllByTotalAvailableTicketsGreaterThanEqual(Integer ticketCount, Pageable pageable);

    @Query(value = "SELECT COUNT(id) AS total FROM reservation", nativeQuery = true)
    Map<String, Object> getTotalRevenue();
}
