package org.example.testcontainer.infrastructure

import org.example.testcontainer.config.DataSourceConfig
import org.example.testcontainer.config.TestMySQLContainer
import org.example.testcontainer.domain.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(TestMySQLContainer::class, DataSourceConfig::class)
class UserRepositoryTest {
    // lateinit 이 변수가 나중에 초기화될 것이며, 선언할 때 즉시 초기화할 필요가 없다는 것을 의미
    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    fun `given User when findById then return User`() {
        // given
        val user = User(name = "HelloWorld...")

        userRepository.save(user)

        // when
        val result = userRepository.findById(user.id!!)

        // then
        assertEquals(user, result.get())
    }
}
