package br.com.alura.forum.service

import br.com.alura.forum.model.Resposta
import br.com.alura.forum.repository.RespostaRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val repository: RespostaRepository,
    private val service: EmailService
) {

    @CacheEvict(value = ["listarTopicos", "buscarTopicoPorId", "gerarRelatorio"], allEntries = true)
    fun salvar(resposta: Resposta) {
        repository.save(resposta)
        val emailAutor = resposta.topico.autor.email
        val nomeAutor = resposta.topico.autor.nome

        service.notificar(nomeAutor, emailAutor)
    }
}