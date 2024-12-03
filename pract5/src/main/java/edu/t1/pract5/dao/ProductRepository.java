package edu.t1.pract5.dao;

import edu.t1.pract5.annotation.Table;
import edu.t1.pract5.dao.model.Product;
import edu.t1.pract5.dao.model.ProductType;
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
public class ProductRepository implements BaseRepository<Product, Long> {

    private final DataSource dataSource;
    private final String tableName;

    public ProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        Class<Product> aClass = Product.class;
        if (!aClass.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Table " + aClass.getSimpleName() + " is not annotated with @Table");
        }
        this.tableName = aClass.getAnnotation(Table.class).name();
    }

    @Override
    public Optional<Product> findById(Long id) {
        Objects.requireNonNull(id);
        String sql = "select * from %s where id = ?".formatted(tableName);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(getProductFromRS(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void upsert(Product product) {
        Objects.requireNonNull(product);
        String sql = """
                    insert into %s (id, user_id, account, balance, product_type)
                    values (?, ?, ?, ?, ?)
                    on conflict (id) do update set
                        user_id = excluded.user_id,
                        account = excluded.account,
                        balance = excluded.balance,
                        product_type = excluded.product_type
                """
                .formatted(tableName);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            int i = 1;
            statement.setLong(i++, product.getId());
            statement.setLong(i++, product.getUserId());
            statement.setString(i++, product.getAccount());
            statement.setBigDecimal(i++, product.getBalance());
            statement.setString(i++, product.getProductType().getName());
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
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "select * from %s".formatted(tableName);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(getProductFromRS(resultSet));
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return products;
    }

    public List<Product> findAllByUserId(long userId) {
        List<Product> products = new ArrayList<>();
        String sql = "select * from %s where user_id = ?".formatted(tableName);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(getProductFromRS(resultSet));
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return products;
    }


    private static Product getProductFromRS(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getLong("id"),
                resultSet.getString("account"),
                resultSet.getBigDecimal("balance"),
                ProductType.valueOf(resultSet.getString("product_type"))
        );
    }
}
