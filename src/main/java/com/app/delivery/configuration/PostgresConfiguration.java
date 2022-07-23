package com.app.delivery.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "postgreSessionFactory",
						transactionManagerRef = "postgreHibernateTransactionManager")
public class PostgresConfiguration {
	
	@Value("${postgre.datasource.driver}")
	private String driver;

	@Value("${postgre.datasource.url}")
	private String databaseUrl;

	@Value("${postgre.datasource.username}")
	private String username;

	@Value("${postgre.datasource.password}")
	private String password;

	@Value("${postgre.jpa.hibernate.ddl-auto}")
	private String hibernate_ddlauto;
	@Value("${postgre.jpa.properties.hibernate.dialect}")
	private String hibernate_dialect;
	@Value("${postgre.jpa.show-sql}")
	private String hibernate_showsql;
	@Value("${postgre.jpa.properties.hibernate.format_sql}")
	private String hibernate_formatsql;

	@Value("${postgre.packagesToScan}")
	private String packagesToScan;
	
	@Bean(name = "postgre_datasource")
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

	@Bean
	public LocalSessionFactoryBean postgreSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setHibernateProperties(hibernateProperties());
		sessionFactory.setPackagesToScan(packagesToScan);

		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager postgreHibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(postgreSessionFactory().getObject());

		return transactionManager;
	}

}
