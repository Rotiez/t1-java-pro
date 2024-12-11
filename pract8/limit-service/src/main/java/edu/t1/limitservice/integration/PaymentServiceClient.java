package edu.t1.limitservice.integration;

import edu.t1.limitservice.configuration.PaymentServiceProperties;
import edu.t1.limitservice.dto.client.PaymentInitRequestDto;
import edu.t1.limitservice.dto.client.PaymentInitResponseDto;
import edu.t1.limitservice.exception.PaymentServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class PaymentServiceClient {

    private final RestClient restClient;

    public PaymentServiceClient(PaymentServiceProperties properties) {
        this.restClient = RestClient.builder().baseUrl(properties.getBaseUrl()).build();
    }

    public PaymentInitResponseDto initiatePayment(long userId, PaymentInitRequestDto paymentDto) {
        return restClient.post()
            .uri("/payment/{userId}", userId)
            .body(paymentDto)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatusCode::isError, (request, response) -> {
                throw new PaymentServiceException(
                    "Payment service error %s. Message: %s".formatted(
                            response.getStatusText(), new String(response.getBody().readAllBytes())),
                    response.getStatusCode()
                );
            })
            .body(new ParameterizedTypeReference<>() {});
    }
}
