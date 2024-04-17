package com.company.sample;

import io.jmix.autoconfigure.data.JmixLiquibaseCreator;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import io.jmix.core.JmixModules;
import io.jmix.core.Resources;
import io.jmix.data.impl.JmixEntityManagerFactoryBean;
import io.jmix.data.impl.JmixTransactionManager;
import io.jmix.data.persistence.DbmsSpecifics;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;

@Configuration
public class ManagementStoreConfiguration {

    @Bean
    @ConfigurationProperties("management.datasource")
    DataSourceProperties managementDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "management.datasource.hikari")
    DataSource managementDataSource(@Qualifier("managementDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean managementEntityManagerFactory(
            @Qualifier("managementDataSource") DataSource dataSource,
            JpaVendorAdapter jpaVendorAdapter,
            DbmsSpecifics dbmsSpecifics,
            JmixModules jmixModules,
            Resources resources
    ) {
        return new JmixEntityManagerFactoryBean("management", dataSource, jpaVendorAdapter, dbmsSpecifics, jmixModules, resources);
    }

    @Bean
    JpaTransactionManager managementTransactionManager(@Qualifier("managementEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JmixTransactionManager("management", entityManagerFactory);
    }

    @Bean("managementLiquibaseProperties")
    @ConfigurationProperties(prefix = "management.liquibase")
    public LiquibaseProperties managementLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean("managementLiquibase")
    public SpringLiquibase managementLiquibase(@Qualifier("managementDataSource") DataSource dataSource,
                                               @Qualifier("managementLiquibaseProperties") LiquibaseProperties liquibaseProperties) {
        return JmixLiquibaseCreator.create(dataSource, liquibaseProperties);
    }
}
