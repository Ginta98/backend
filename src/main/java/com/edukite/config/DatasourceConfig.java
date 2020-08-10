package com.edukite.config;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Created by nydiarra on 06/05/17.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.edukite.repository")
public class DatasourceConfig {

/*
	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String dbUsername;
	
	@Value("${spring.datasource.password}")
	private String dbPass;
	*/
	@Autowired
	private DataSourceProperties properties;
	
	@Value("${spring.jpa.database-platform}")
	private String dialect;

	@Value("${spring.jpa.show-sql}")
	private String showSQL;


    @Bean
    @Primary
    public DataSource datasource() throws SQLException {
    	HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(this.properties.getDriverClassName());
		dataSource.setJdbcUrl(this.properties.getUrl());
		dataSource.setUsername(this.properties.getUsername());
		dataSource.setPassword(this.properties.getPassword());

		/**
		 * HikariCP specific properties. Remove if you move to other connection pooling
		 * library.
		 **/
		dataSource.addDataSourceProperty("cachePrepStmts", true);
		dataSource.addDataSourceProperty("prepStmtCacheSize", 25000);
		dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 20048);
		dataSource.addDataSourceProperty("useServerPrepStmts", true);
		dataSource.addDataSourceProperty("initializationFailFast", true);
		dataSource.setPoolName("KiteApi_PRIMARY_HIK_CP");
		dataSource.setMaximumPoolSize(10);
		dataSource.setIdleTimeout(30000);
		dataSource.setMaxLifetime(2000000);
		dataSource.setConnectionTimeout(30000);

		return dataSource;
    }
    
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("datasource") DataSource ds) throws PropertyVetoException{
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(ds);
        entityManagerFactory.setPackagesToScan(new String[]{"com.edukite.model"});
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        
        // JPA & Hibernate
 		HashMap<String, Object> properties = new HashMap<>();
 		properties.put("hibernate.dialect", dialect);
 		properties.put("hibernate.show-sql", showSQL);
 		entityManagerFactory.setJpaPropertyMap(properties);
 		entityManagerFactory.afterPropertiesSet();
     		
        return entityManagerFactory;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}