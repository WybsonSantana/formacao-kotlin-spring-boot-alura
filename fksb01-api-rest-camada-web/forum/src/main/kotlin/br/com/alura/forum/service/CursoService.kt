package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(
    private var cursos: List<Curso>
) {

    init {
        val curso = Curso(
            id = 1,
            nome = "Kotlin",
            categoria = "Programação"
        )

        cursos = listOf(curso)
    }

    fun buscarPorId(id: Long): Curso {
        return cursos.first { it.id!! == id }
    }
}