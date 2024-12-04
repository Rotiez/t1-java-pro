package com.rotiez.paymentservice.dao.repository;

import com.rotiez.paymentservice.annotation.Table;
import com.rotiez.paymentservice.dao.model.Payment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PaymentRepository implements BaseRepository<Payment, Long> {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Payment> rowMapper;
    private final String tableName;

    public PaymentRepository(NamedParameterJdbcTemplate jdbcTemplate, RowMapper<Payment> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        Class<Payment> aClass = Payment.class;
        if (!aClass.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Table " + aClass.getSimpleName() + " is not annotated with @Table");
        }
        this.tableName = aClass.getAnnotation(Table.class).name();
    }

    @Override
    public Optional<Payment> findById(Long id) {
        Objects.requireNonNull(id);
        String sql = "select * from %s where id = :id".formatted(tableName);

        MapSqlParameterSource params = new MapSqlParameterSource("id", id);

        try {
            return Optional.ofNullable(
                jdbcTemplate.queryForObject(sql, params, rowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
             return Optional.empty();
        }

    }

    @Override
    public Payment save(Payment payment) {
        Objects.requireNonNull(payment);
        String sql = """
                    insert into %s (user_id, amount)
                    values (:userId, :amount)
                    returning *
                """
                .formatted(tableName);

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", payment.getId())
                .addValue("userId", payment.getUserId())
                .addValue("amount", payment.getAmount())
                .addValue("createdAt", payment.getCreatedAt());

        return jdbcTemplate.queryForObject(sql, params, rowMapper);
    }

    @Override
    public void deleteById(Long id) {
        Objects.requireNonNull(id);
        String sql = "delete from %s where id = :id".formatted(tableName);

        MapSqlParameterSource params = new MapSqlParameterSource("id", id);

        jdbcTemplate.update(sql, params);
    }

    @Override
    public List<Payment> findAll() {
        String sql = "select * from %s".formatted(tableName);

        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Payment> findAllByUserId(long userId) {
        String sql = "select * from %s where user_id = :userId".formatted(tableName);

        MapSqlParameterSource params = new MapSqlParameterSource("userId", userId);

        return jdbcTemplate.query(sql, params, rowMapper);
    }
}