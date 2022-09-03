package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: List<Topico>
) {

    init {
        val topico1 = Topico(
            id = 1,
            titulo = "Dúvida sobre Kotlin 1",
            mensagem = "Tipos de variáveis no Kotlin 1",
            curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programação"
            ),
            autor = Usuario(
                id = 1,
                nome = "Fulano de Tal",
                email = "fulanodetal@mail.com"
            )
        )

        val topico2 = Topico(
            id = 2,
            titulo = "Dúvida sobre Kotlin 2",
            mensagem = "Tipos de variáveis no Kotlin 2",
            curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programação"
            ),
            autor = Usuario(
                id = 1,
                nome = "Fulano de Tal",
                email = "fulanodetal@mail.com"
            )
        )

        val topico3 = Topico(
            id = 3,
            titulo = "Dúvida sobre Kotlin 3",
            mensagem = "Tipos de variáveis no Kotlin 3",
            curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programação"
            ),
            autor = Usuario(
                id = 1,
                nome = "Fulano de Tal",
                email = "fulanodetal@mail.com"
            )
        )

        topicos = listOf(topico1, topico2, topico3)
    }

    fun listarTodos(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico  {
return topicos.first { it.id!! == id }
    }
}