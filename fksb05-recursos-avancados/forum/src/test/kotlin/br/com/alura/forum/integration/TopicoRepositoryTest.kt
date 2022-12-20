package br.com.alura.forum.integration

import br.com.alura.forum.model.TopicoTest
import br.com.alura.forum.repository.TopicoRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicoRepositoryTest {

            @Autowired
    private lateinit var repository: TopicoRepository

    private val paginacao = PageRequest.of(0, 5)
    private val topico = TopicoTest.build()

    companion object {
        @Container
        private val mySqlContainer = MySQLContainer<Nothing>("mysql:latest").apply {
            withDatabaseName("testedb")
            withUsername("Bel Trano")
            withPassword("123456")
        }

        @Container
        private val redisContainer = GenericContainer<Nothing>("redis:latest").apply {
            withExposedPorts(6379)
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mySqlContainer::getJdbcUrl)
            registry.add("spring.datasource.username", mySqlContainer::getUsername)
            registry.add("spring.datasource.password", mySqlContainer::getPassword)

registry.add("spring.redis.host", redisContainer::getContainerIpAddress)
            registry.add("spring.redis.port", redisContainer::getFirstMappedPort)
        }
    }

    @Test
    fun `deveria gerar um relatorio`() {
        repository.save(topico)

        val relatorio = repository.gerarRelatorio()

        assertThat(relatorio).isNotNull
    }

    @Test
    fun `deveria buscar um topico pelo nome`() {
        repository.save(topico)

        val resultado = repository.findByCursoNome("Kotlin", paginacao)

        assertThat(resultado.totalElements).isEqualTo(1)
    }
}