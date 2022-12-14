package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não encontrado!"
) {

    fun listar(): List<TopicoView> {
        return topicos.map { topico ->
            topicoViewMapper.map(topico)
        }.toList()
    }

    fun buscarPorId(id: Long): TopicoView {
        try {
            return topicoViewMapper.map(topicos.first { it.id!! == id })
        } catch (ex: Exception) {
            throw NotFoundException(notFoundMessage)
        }
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico: Topico

        try {
            topico = topicos.first { it.id!! == form.id }
        } catch (ex: Exception) {
            throw NotFoundException(notFoundMessage)
        }

        val topicoAtualizado = Topico(
            id = form.id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            autor = topico.autor,
            curso = topico.curso,
            respostas = topico.respostas,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )

        topicos = topicos.minus(topico).plus(topicoAtualizado)
        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletar(id: Long) {
        try {
            topicos = topicos.minus(topicos.first { it.id!! == id })
        } catch (ex: Exception) {
            throw NotFoundException(notFoundMessage)
        }
    }
}