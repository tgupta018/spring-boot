package com.xx.spring.boot.jdbc.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableJpaRepositories(basePackages = {"com.xx.spring.boot.jdbc"},
        entityManagerFactoryRef = "dbADFEntityManager",
        transactionManagerRef = "dbADFTransactionManager")
public class DBSummitADFConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean dbADFEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dbADFDatasource());
        em.setPackagesToScan(
                new String[]{"com.xx.spring.boot.jdbc"});
        em.setPersistenceUnitName("dbADFEntityManager");
        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Map<String,String> properties = new HashMap<>();
        properties.put("hibernate.dialect",
                env.getProperty("spring.jpa.database-platform"));
        properties.put("hibernate.show-sql",
                env.getProperty("spring.jpa.show-sql"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public DataSource dbADFDatasource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("dbADF.datasource.url"));
        dataSource.setUsername(env.getProperty("dbADF.datasource.username"));
        dataSource.setPassword(env.getProperty("dbADF.datasource.password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager dbADFTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                dbADFEntityManager().getObject());
        return transactionManager;
    }
}