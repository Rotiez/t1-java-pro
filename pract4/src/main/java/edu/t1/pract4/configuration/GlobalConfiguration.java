package edu.t1.pract4.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.t1.pract4.factory.YamlPropertySourceFactory;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
@ComponentScan(basePackages = "edu.t1")
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:application.yml")
public class GlobalConfiguration {

    private final Environment env;

    public GlobalConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        try {
            var config = new HikariConfig();
            config.setJdbcUrl(env.getProperty("spring.datasource.url"));
            config.setUsername(env.getProperty("spring.datasource.username"));
            config.setPassword(env.getProperty("spring.datasource.password"));
            config.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
            return new HikariDataSource(config);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create DataSource bean: " + e.getMessage());
        }
    }

    @Bean
    public SpringLiquibase liquibase() {
        try {
            var liquibase = new SpringLiquibase();
            liquibase.setDataSource(dataSource());
            liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.yaml");
            return liquibase;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Liquibase bean: " + e.getMessage());
        }
    }

}
