package com.example.demo.datasources;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

@Configuration
public class DataSourceConfiguration {

	@Bean("jdbcTemplate")
	@Primary
	public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource datasource) {

		return new JdbcTemplate(datasource);
	}

	@Bean("dataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariDataSource hikariDataSource(@Qualifier("dataSourceProperties") DataSourceProperties dataSourceProperties) {

		HikariDataSource dataSource = createDataSource(dataSourceProperties, HikariDataSource.class);

		if (StringUtils.hasText(dataSourceProperties.getName())) {
			dataSource.setPoolName(dataSourceProperties.getName());
		}

		return dataSource;
	}

	@Bean("dataSourceProperties")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSourceProperties dataSourceProperties() {

		return new DataSourceProperties();
	}



	@Bean("customJdbcTemplate")
	public JdbcTemplate customJdbcTemplate(@Qualifier("customDataSource") DataSource datasource) {

		return new JdbcTemplate(datasource);
	}

	@Bean("customDataSource")
	@ConfigurationProperties(prefix = "custom.datasource.hikari")
	public HikariDataSource customHikariDataSource(@Qualifier("customDataSourceProperties") DataSourceProperties dataSourceProperties) {

		HikariDataSource dataSource = createDataSource(dataSourceProperties, HikariDataSource.class);

		if (StringUtils.hasText(dataSourceProperties.getName())) {
			dataSource.setPoolName(dataSourceProperties.getName());
		}

		return dataSource;
	}

	@Bean("customDataSourceProperties")
	@ConfigurationProperties(prefix = "custom.datasource")
	public DataSourceProperties customDataSourceProperties() {

		return new DataSourceProperties();
	}

	protected static <T> T createDataSource(DataSourceProperties properties, Class<? extends DataSource> type) {
		return (T) properties.initializeDataSourceBuilder().type(type).build();
	}

}
