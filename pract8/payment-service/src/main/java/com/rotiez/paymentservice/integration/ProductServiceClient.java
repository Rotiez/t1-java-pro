package com.rotiez.paymentservice.integration;

import com.rotiez.paymentservice.configuration.ProductServiceProperties;
import com.rotiez.paymentservice.dto.ProductDto;
import com.rotiez.paymentservice.exception.ProductServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Service
public class ProductServiceClient {

    private final RestClient restClient;

    public ProductServiceClient(ProductServiceProperties properties) {
        this.restClient = RestClient.builder().baseUrl(properties.getBaseUrl()).build();
    }

    public List<ProductDto> getUserProducts(long userId) {
        log.info("GET products for user %d".formatted(userId));

        return restClient.get()
            .uri("/products/{userId}", userId)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatusCode::isError, (request, response) -> {
                throw new ProductServiceException(
                        "Product service error %s. Message: %s".formatted(
                                response.getStatusText(), new String(response.getBody().readAllBytes())
                        ),
                        response.getStatusCode()
                );
            })
            .body(new ParameterizedTypeReference<>() {});
    }

    public void commitPayment(ProductDto productDto) {
        restClient.put()
            .uri("/products?product={productId}", productDto.getId())
            .body(productDto)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatusCode::isError, (request, response) -> {
                throw new ProductServiceException(
                        "Product service error %s".formatted(response.getStatusText()),
                        response.getStatusCode()
                );
            })
            .toBodilessEntity();
    }
}
