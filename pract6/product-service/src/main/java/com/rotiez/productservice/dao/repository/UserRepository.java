package com.rotiez.productservice.dao.repository;

import com.rotiez.productservice.annotation.Table;
import com.rotiez.productservice.dao.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepository implements BaseRepository<User, Long> {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<User> rowMapper;
    private final String tableName;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate, RowMapper<User> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        Class<User> aClass = User.class;
        if (!aClass.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Table " + aClass.getSimpleName() + " is not annotated with @Table");
        }
        this.tableName = aClass.getAnnotation(Table.class).name();
    }

    @Override
    public Optional<User> findById(Long id) {
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
    public void upsert(User user) {
        Objects.requireNonNull(user);
        String sql = ("""
            insert into %s (id, first_name, last_name, middle_name) 
            values (:id, :firstName, :lastName, :middleName)
            on conflict (id) do update set
                first_name = excluded.first_name,
                last_name = excluded.last_name,
                middle_name = excluded.middle_name
        """).formatted(tableName);

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("middleName", user.getMiddleName());

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
    public List<User> findAll() {
        String sql = "select * from %s".formatted(tableName);

        return jdbcTemplate.query(sql, rowMapper);
    }
}
