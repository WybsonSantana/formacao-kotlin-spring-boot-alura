package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoPorCategoriaDTO
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não encontrado!",
    private val entityManager: EntityManager
) {

    fun listar(nomeCurso: String?, paginacao: Pageable): Page<TopicoView> {
        //println(entityManager)
        val topicos = if (nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map { topico ->
            topicoViewMapper.map(topico)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        try {
            return topicoViewMapper.map(repository.getReferenceById(id))
        } catch (ex: Exception) {
            throw NotFoundException(notFoundMessage)
        }
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico: Topico

        try {
            topico = repository.getReferenceById(form.id)
        } catch (ex: Exception) {
            throw NotFoundException(notFoundMessage)
        }
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        try {
            repository.deleteById(id)
        } catch (ex: Exception) {
            throw NotFoundException(notFoundMessage)
        }
    }

    fun gerarRelatorio(paginacao: Pageable): Page<TopicoPorCategoriaDTO> {
        return repository.gerarRelatorio(paginacao)
    }
}