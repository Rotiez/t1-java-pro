package edu.t1.limitservice.service;

import edu.t1.limitservice.dao.model.Limit;
import edu.t1.limitservice.dao.repository.LimitRepository;
import edu.t1.limitservice.dto.PaymentRequestDto;
import edu.t1.limitservice.dto.PaymentResponseDto;
import edu.t1.limitservice.dto.mapper.PaymentMapper;
import edu.t1.limitservice.exception.OverLimitException;
import edu.t1.limitservice.integration.PaymentServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimitRepository repository;
    private final PaymentServiceClient paymentClient;
    private final PaymentMapper paymentMapper;

    @Transactional(rollbackFor = Exception.class)
    public PaymentResponseDto payWithLimitCheck(Long userId, PaymentRequestDto request) {
        log.info("Incoming payment request: {}", request);

        var userLimit = repository.findById(userId).orElse(
            repository.save(Limit.builder()
                .userId(userId)
                .amountUsed(BigDecimal.valueOf(0))
                .amountLimit(BigDecimal.valueOf(10000))
                .createdAt(LocalDateTime.now())
                .build()
            )
        );

        log.info("limit used: {} limit total: {}", userLimit.getAmountUsed(), userLimit.getAmountLimit());

        if (!isLimitExceeded(userLimit, request.getAmount())) {
            userLimit.setAmountUsed(userLimit.getAmountUsed().add(request.getAmount()));
            userLimit.setModifiedAt(LocalDateTime.now());
            log.info("updated limit used: {} limit total: {}", userLimit.getAmountUsed(), userLimit.getAmountLimit());
            repository.save(userLimit);

            var response = paymentClient.initiatePayment(userId, paymentMapper.toInitRequest(request));
            return paymentMapper.fromInitResponse(response);
        } else {
            throw new OverLimitException("Превышен лимит по сумме");
        }
    }

    private boolean isLimitExceeded(Limit userLimit, BigDecimal amountToAdd) {
        BigDecimal remainingAmount = userLimit.getAmountLimit()
                .subtract(userLimit.getAmountUsed().add(amountToAdd));
        return remainingAmount.compareTo(BigDecimal.ZERO) < 0;
    }
}
