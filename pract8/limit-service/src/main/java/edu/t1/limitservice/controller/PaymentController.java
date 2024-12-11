package edu.t1.limitservice.controller;

import edu.t1.limitservice.dto.PaymentRequestDto;
import edu.t1.limitservice.dto.PaymentResponseDto;
import edu.t1.limitservice.service.LimitService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final LimitService limitService;

    public PaymentController(LimitService limitService) {
        this.limitService = limitService;
    }

    @PostMapping("/{userId}")
    public PaymentResponseDto makePaymentWithLimit(@PathVariable Long userId, @RequestBody PaymentRequestDto request) {
        return limitService.payWithLimitCheck(userId, request);
    }
}
