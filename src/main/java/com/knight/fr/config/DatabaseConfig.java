package com.knight.fr.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
public class DatabaseConfig {

	@Bean
	@Primary
	public DataSource oracleDatasource() throws SQLException {
		// DriverManagerDataSource ds = new DriverManagerDataSource();

		OracleDataSource ds = new OracleDataSource();
		ds.setUser("jarvis");
		ds.setPassword("oracle");
		ds.setURL("jdbc:oracle:thin:@localhost:1521/xe");
		return ds;
	}

	
	 @Bean
	 public DataSource hsqlDataSource() {
	 EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	 EmbeddedDatabase db = builder
	 .setType(EmbeddedDatabaseType.HSQL) //.H2 or .DERBY
	 .build();
	 return db;
	 }
}
