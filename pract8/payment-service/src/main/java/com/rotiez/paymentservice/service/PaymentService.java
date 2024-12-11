package com.rotiez.paymentservice.service;

import com.rotiez.paymentservice.dao.model.Payment;
import com.rotiez.paymentservice.dao.repository.PaymentRepository;
import com.rotiez.paymentservice.dto.PaymentRequestDto;
import com.rotiez.paymentservice.dto.ProductDto;
import com.rotiez.paymentservice.exception.InsufficientBalanceException;
import com.rotiez.paymentservice.exception.NotFoundException;
import com.rotiez.paymentservice.integration.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final ProductServiceClient productServiceClient;
    private final PaymentRepository paymentRepository;

    @Transactional(rollbackFor = Exception.class)
    public Payment makePayment(long userId, PaymentRequestDto request) {
        var product = productServiceClient.getUserProducts(userId).stream()
            .filter(productDto ->
                productDto.getAccount().equals(request.getAccount()) &&
                productDto.getProductType().getName().equals(request.getProductType())
            )
            .findFirst()
            .orElseThrow(() ->
                new NotFoundException("Product with account %s not found".formatted(request.getAccount()))
            );


        if (product.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        } else {
            var payment = paymentRepository.save(Payment.builder()
                .userId(userId)
                .amount(request.getAmount())
                .build()
            );

            ProductDto updatedProduct = product.clone();
            updatedProduct.setBalance(product.getBalance().subtract(payment.getAmount()));
            productServiceClient.commitPayment(updatedProduct);

            return payment;
        }
    }

    public List<Payment> getUserPayments(long userId) {
        return paymentRepository.findAllByUserId(userId);
    }
}
