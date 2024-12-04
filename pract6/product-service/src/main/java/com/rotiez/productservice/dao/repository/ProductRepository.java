package com.rotiez.productservice.dao.repository;

import com.rotiez.productservice.annotation.Table;
import com.rotiez.productservice.dao.model.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ProductRepository implements BaseRepository<Product, Long> {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Product> rowMapper;
    private final String tableName;

    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate, RowMapper<Product> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        Class<Product> aClass = Product.class;
        if (!aClass.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Table " + aClass.getSimpleName() + " is not annotated with @Table");
        }
        this.tableName = aClass.getAnnotation(Table.class).name();
    }

    @Override
    public Optional<Product> findById(Long id) {
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
    public void upsert(Product product) {
        Objects.requireNonNull(product);
        String sql = """
                    insert into %s (id, user_id, account, balance, product_type)
                    values (:id, :userId, :account, :balance, :productType)
                    on conflict (id) do update set
                        user_id = excluded.user_id,
                        account = excluded.account,
                        balance = excluded.balance,
                        product_type = excluded.product_type
                """
                .formatted(tableName);

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", product.getId())
                .addValue("userId", product.getUserId())
                .addValue("account", product.getAccount())
                .addValue("balance", product.getBalance())
                .addValue("productType", product.getProductType().name());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteById(Long id) {
        Objects.requireNonNull(id);
        String sql = "delete from %s where id = :id".formatted(tableName);

        MapSqlParameterSource params = new MapSqlParameterSource("id", id);

        jdbcTemplate.update(sql, params);
    }

    @Override
    public List<Product> findAll() {
        String sql = "select * from %s".formatted(tableName);

        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Product> findAllByUserId(long userId) {
        String sql = "select * from %s where user_id = :userId".formatted(tableName);

        MapSqlParameterSource params = new MapSqlParameterSource("userId", userId);

        return jdbcTemplate.query(sql, params, rowMapper);
    }
}
