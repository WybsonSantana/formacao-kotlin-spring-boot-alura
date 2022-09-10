package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NovoTopicoForm(
    @field: NotEmpty(message = "O título tem que ter entre 5 e 100 caracteres")
    @field: Size(min = 5, max = 100)
    val titulo: String,

    @field: NotEmpty(message = "A mensagem tem que ter entre 10 e 500 caracteres")
    @field: Size(min = 10, max = 500)
    val mensagem: String,

    @field: NotNull
    val idCurso: Long,

    @field: NotNull
    val idAutor: Long
)