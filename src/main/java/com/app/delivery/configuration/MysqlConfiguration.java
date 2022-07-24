package com.app.delivery.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlSessionFactory", 
						transactionManagerRef = "mysqlHibernateTransactionManager")
public class MysqlConfiguration {

	@Value("${mysql.datasource.driver}")
	private String driver;

	@Value("${mysql.datasource.url}")
	private String databaseUrl;

	@Value("${mysql.datasource.username}")
	private String username;

	@Value("${mysql.datasource.password}")
	private String password;

	@Value("${mysql.jpa.hibernate.ddl-auto}")
	private String hibernate_ddlauto;
	@Value("${mysql.jpa.properties.hibernate.dialect}")
	private String hibernate_dialect;
	@Value("${mysql.jpa.show-sql}")
	private String hibernate_showsql;
	@Value("${mysql.jpa.properties.hibernate.format_sql}")
	private String hibernate_formatsql;

	@Value("${mysql.packagesToScan}")
	private String packagesToScan;

	@Bean(name = "mysql_datasource")
	@Primary
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
	@Primary
	public LocalSessionFactoryBean mysqlSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setHibernateProperties(hibernateProperties());
		sessionFactory.setPackagesToScan(packagesToScan);

		return sessionFactory;
	}

	@Bean
	@Primary
	public HibernateTransactionManager mysqlHibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(mysqlSessionFactory().getObject());

		return transactionManager;
	}
}
