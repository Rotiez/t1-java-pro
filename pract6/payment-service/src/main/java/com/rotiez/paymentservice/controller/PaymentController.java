package com.rotiez.paymentservice.controller;

import com.rotiez.paymentservice.dao.model.Payment;
import com.rotiez.paymentservice.dto.PaymentRequestDto;
import com.rotiez.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{userId}")
    public Payment makePayment(@PathVariable long userId, @RequestBody PaymentRequestDto paymentRequestDto) {
        return paymentService.makePayment(userId, paymentRequestDto);
    }

    @GetMapping("/{userId}")
    public List<Payment> getUserPayments(@PathVariable long userId) {
        return paymentService.getUserPayments(userId);
    }
}
