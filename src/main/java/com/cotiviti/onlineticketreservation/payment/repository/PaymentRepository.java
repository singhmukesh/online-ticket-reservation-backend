package com.cotiviti.onlineticketreservation.payment.repository;

import com.cotiviti.onlineticketreservation.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
