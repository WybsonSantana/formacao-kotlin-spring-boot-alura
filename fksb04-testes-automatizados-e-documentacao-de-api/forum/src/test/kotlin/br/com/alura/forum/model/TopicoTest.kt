package br.com.alura.forum.model

object TopicoTest {

    fun build() = Topico(
        id = 1,
        titulo = "Kotlin",
        mensagem = "Dúvida sobre Scope Functions",
        curso = CursoTest.build(),
        autor = UsuarioTest.build()
    )
}