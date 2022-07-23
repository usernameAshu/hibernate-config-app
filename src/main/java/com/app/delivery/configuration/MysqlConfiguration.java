package com.app.delivery.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MysqlConfiguration {

	@Value("${app.datasource.driver}")
	private String driver;

	@Value("${app.datasource.url}")
	private String databaseUrl;

	@Value("${app.datasource.username}")
	private String username;

	@Value("${app.datasource.password}")
	private String password;

	@Value("${app.jpa.hibernate.ddl-auto}")
	private String hibernate_ddlauto;
	@Value("${app.jpa.properties.hibernate.dialect}")
	private String hibernate_dialect;
	@Value("${app.jpa.show-sql}")
	private String hibernate_showsql;
	@Value("${app.jpa.properties.hibernate.format_sql}")
	private String hibernate_formatsql;

	@Value("${app.packagesToScan}")
	private String packagesToScan;

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", hibernate_dialect);
		properties.put("hibernate.show_sql", hibernate_showsql);
		properties.put("hibernate.format_sql", hibernate_formatsql);
		properties.put("hibernate.hbm2ddl.auto", hibernate_ddlauto);

		return properties;
	}

	@Bean(name = "entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setHibernateProperties(hibernateProperties());
		sessionFactory.setPackagesToScan(packagesToScan);

		return sessionFactory;
	}

	@Bean(name = "transactionManager")
	public HibernateTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());

		return transactionManager;
	}
}
