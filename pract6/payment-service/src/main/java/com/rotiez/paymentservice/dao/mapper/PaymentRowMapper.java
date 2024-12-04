package com.rotiez.paymentservice.dao.mapper;

import com.rotiez.paymentservice.dao.model.Payment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PaymentRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Payment.builder()
            .id(rs.getLong("id"))
            .userId(rs.getLong("user_id"))
            .amount(rs.getBigDecimal("amount"))
            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
        .build();
    }
}
