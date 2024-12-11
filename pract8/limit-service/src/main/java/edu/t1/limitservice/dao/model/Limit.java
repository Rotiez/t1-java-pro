package edu.t1.limitservice.dao.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "limits")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Limit {
    @Id
    private Long userId;
    private BigDecimal amountUsed;
    private BigDecimal amountLimit;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
