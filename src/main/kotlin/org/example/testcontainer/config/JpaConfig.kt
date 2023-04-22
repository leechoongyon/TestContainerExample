package org.example.testcontainer.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource

@Configuration
@EntityScan(basePackages = ["org.example.testcontainer.domain"])
@EnableJpaRepositories(basePackages = ["org.example.testcontainer.infrastructure"])
class JpaConfig {
    @Bean
    fun hibernateJpaVendorAdapter(): HibernateJpaVendorAdapter {
        val adapter = HibernateJpaVendorAdapter()
        adapter.setShowSql(true)
        adapter.setGenerateDdl(true)
        adapter.setDatabase(Database.MYSQL)
        return adapter
    }

    @Bean
    fun entityManagerFactory(
        dataSource: DataSource,
        jpaVendorAdapter: JpaVendorAdapter
    ): LocalContainerEntityManagerFactoryBean {
        val emf = LocalContainerEntityManagerFactoryBean()
        emf.dataSource = dataSource
        emf.jpaVendorAdapter = jpaVendorAdapter
        emf.setPackagesToScan("org.example.testcontainer.domain")
        return emf
    }

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): JpaTransactionManager {
        val txManager = JpaTransactionManager()
        txManager.entityManagerFactory = entityManagerFactory
        return txManager
    }
}
