package com.rotiez.paymentservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "properties.product-service")
public class ProductServiceProperties {
    private String baseUrl;
}
