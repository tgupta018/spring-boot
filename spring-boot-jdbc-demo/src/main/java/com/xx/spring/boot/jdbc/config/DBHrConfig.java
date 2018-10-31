package com.xx.spring.boot.jdbc.config;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = {"com.xx.spring.boot.jdbc"},
        entityManagerFactoryRef = "dbHREntityManager",
        transactionManagerRef = "dbHRTransactionManager")
public class DBHrConfig {
    @Autowired
    private Environment env;
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean dbHREntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dbHRDatasource());
        em.setPackagesToScan(new String[]{"com.xx.spring.boot.jdbc.config"});
        em.setPersistenceUnitName("dbHREntityManager");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Map<String,String> properties = new HashMap<>();
        properties.put("hibernate.dialect",
                env.getProperty("spring.jpa.database-platform"));
        properties.put("hibernate.show-sql",
                env.getProperty("spring.jpa.show-sql"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean
    public DataSource dbHRDatasource() {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("spring.datasource.driver-class-name"));
//        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUrl(env.getProperty("dbHR.datasource.url"));
        dataSource.setUsername(env.getProperty("dbHR.datasource.username"));
        dataSource.setPassword(env.getProperty("dbHR.datasource.password"));

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager dbHRTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                dbHREntityManager().getObject());
        return transactionManager;
    }
}