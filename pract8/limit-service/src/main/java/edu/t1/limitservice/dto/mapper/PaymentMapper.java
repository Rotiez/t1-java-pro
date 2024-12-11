package edu.t1.limitservice.dto.mapper;

import edu.t1.limitservice.dto.PaymentRequestDto;
import edu.t1.limitservice.dto.PaymentResponseDto;
import edu.t1.limitservice.dto.client.PaymentInitRequestDto;
import edu.t1.limitservice.dto.client.PaymentInitResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public PaymentInitRequestDto toInitRequest(PaymentRequestDto dto) {
        return new PaymentInitRequestDto(
            dto.getAmount(),
            dto.getAccount(),
            dto.getProductType()
        );
    }

    public PaymentResponseDto fromInitResponse(PaymentInitResponseDto dto) {
        return new PaymentResponseDto(
            dto.getId(),
            dto.getUserId(),
            dto.getAmount(),
            dto.getCreatedAt()
        );
    }
}
