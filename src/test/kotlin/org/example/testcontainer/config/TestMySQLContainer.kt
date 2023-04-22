package org.example.testcontainer.config

import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.TestConfiguration
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.containers.output.Slf4jLogConsumer
import org.testcontainers.junit.jupiter.Container

@TestConfiguration("TestMySQLContainer")
class TestMySQLContainer {
    companion object {
        val logger = LoggerFactory.getLogger(TestMySQLContainer::class.java)

        @Container
        @JvmStatic
        val container = MySQLContainer<Nothing>("mysql:8.0.23")
            .apply {
                withDatabaseName("test")
                withUsername("root")
                withPassword("1234")
            }
            .apply {
                start()
            }
            .apply {
                followOutput(Slf4jLogConsumer(logger))
            }
    }
}