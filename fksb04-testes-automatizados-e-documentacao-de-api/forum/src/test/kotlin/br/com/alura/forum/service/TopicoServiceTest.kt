package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.TopicoTest
import br.com.alura.forum.model.TopicoViewTest
import br.com.alura.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicoServiceTest {

    private val topico = TopicoTest.build()
    private val topicoView = TopicoViewTest.build()
    private val pages = PageImpl(listOf(topico))

    private val pageable: Pageable = mockk()
    private val repository: TopicoRepository = mockk {
        every { findAll(pageable) } returns pages
        every { findByCursoNome(any(), any()) } returns pages
    }
    private val topicoViewMapper: TopicoViewMapper = mockk()
    private val topicoFormMapper: TopicoFormMapper = mockk()

    private val topicoService = TopicoService(
        repository = repository,
        topicoViewMapper = topicoViewMapper,
        topicoFormMapper = topicoFormMapper,
        notFoundMessage = "Tópico não encontrado!"
    )

    @Test
    fun `deveria listar topico pelo nome do curso`() {
        val slot = slot<Topico>()

        every { topicoViewMapper.map(capture(slot)) } returns topicoView

        topicoService.listar("Kotlin", pageable)

        verify(exactly = 0) { repository.findAll(pageable) }
        verify(exactly = 1) { repository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }

        assertThat(slot.captured.titulo).isEqualTo(topico.titulo)
        assertThat(slot.captured.mensagem).isEqualTo(topico.mensagem)
        assertThat(slot.captured.status).isEqualByComparingTo(topico.status)
    }

    @Test
    fun `Deveria listar todos os topicos quando o nome do curso nao for informado`() {
        val slot = slot<Topico>()

        every { topicoViewMapper.map(capture(slot)) } returns topicoView

        topicoService.listar(null, pageable)

        verify(exactly = 1) { repository.findAll(pageable) }
        verify(exactly = 0) { repository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }

        assertThat(slot.captured.titulo).isEqualTo(topico.titulo)
        assertThat(slot.captured.mensagem).isEqualTo(topico.mensagem)
        assertThat(slot.captured.status).isEqualByComparingTo(topico.status)
    }

    @Test
    fun `Deveria lancar uma excecao se o id do topico nao for encontrado`() {
        every { repository.findById(any()) } returns Optional.empty()

        val actual = assertThrows<NotFoundException> {
topicoService.buscarPorId(2)
        }

        assertThat(actual.message).isEqualTo("Tópico não encontrado!")
    }
}