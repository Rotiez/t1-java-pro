package edu.t1.limitservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "app.properties.payment-service")
public class PaymentServiceProperties {
    private String baseUrl;
}
