package com.rotiez.paymentservice.dao.repository;

import com.rotiez.paymentservice.dao.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByUserId(Long userId);
}
