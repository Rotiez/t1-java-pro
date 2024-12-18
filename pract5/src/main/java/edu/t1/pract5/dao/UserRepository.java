package edu.t1.pract5.dao;

import edu.t1.pract5.annotation.Table;
import edu.t1.pract5.dao.model.User;
import edu.t1.pract5.exception.DataAccessException;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepository implements BaseRepository<User, Long> {

    private final DataSource dataSource;
    private final String tableName;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        Class<User> aClass = User.class;
        if (!aClass.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Table " + aClass.getSimpleName() + " is not annotated with @Table");
        }
        this.tableName = aClass.getAnnotation(Table.class).name();
    }

    @Override
    public Optional<User> findById(Long id) {
        Objects.requireNonNull(id);
        String sql = "select * from %s where id = ?".formatted(tableName);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(getUserFromRS(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void upsert(User user) {
        Objects.requireNonNull(user);
        String sql = ("""
            insert into %s (id, first_name, last_name, middle_name) values (?, ?, ?, ?)
            on conflict (id) do update set
                first_name = excluded.first_name,
                last_name = excluded.last_name,
                middle_name = excluded.middle_name
        """).formatted(tableName);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, user.getId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(3, user.getMiddleName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        Objects.requireNonNull(id);
        String sql = "delete from %s where id = ?".formatted(tableName);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from %s".formatted(tableName);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);

             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(getUserFromRS(resultSet));
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return users;
    }

    private static User getUserFromRS(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("middle_name")
        );
    }
}
