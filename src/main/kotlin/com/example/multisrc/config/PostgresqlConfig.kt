package com.example.multisrc.config

import com.zaxxer.hikari.HikariDataSource
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
 *  @author Gunwoo Kang
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "postgresEntityManagerFactory",
    transactionManagerRef = "postgresTransactionManager",
    basePackages = ["com.example.multisrc.postgres"]
)
class PostgresqlConfig {

    @Primary
    @Bean(name = ["postgresDataSourceProperties"])
    @ConfigurationProperties("postgres-db.datasource")
    fun deviceDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }


    @Primary
    @Bean(name = ["postgresDataSource"])
    @ConfigurationProperties("postgres-db.datasource.configuration")
    fun dataSource(@Qualifier("postgresDataSourceProperties") deviceDataSourceProperties: DataSourceProperties): DataSource {
        return deviceDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource::class.java).build()
    }

    @Primary
    @Bean(name = ["postgresEntityManagerFactory"])
    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        @Qualifier("postgresDataSource") deviceDataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(deviceDataSource)
            .packages("com.example.multisrc.postgres")
            .persistenceUnit("postgres")
            .build()
    }

    @Primary
    @Bean(name = ["postgresTransactionManager"])
    fun transactionManager(
        @Qualifier("postgresEntityManagerFactory") deviceEntityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(deviceEntityManagerFactory)
    }
}