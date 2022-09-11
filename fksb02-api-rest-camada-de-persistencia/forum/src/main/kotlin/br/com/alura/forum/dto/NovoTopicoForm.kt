package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NovoTopicoForm(
    @field: NotEmpty
    @field: Size(min = 5, max = 100, message = "O título deve ter entre 5 e 100 caracteres")
    val titulo: String,

    @field: NotEmpty
    @field: Size(min = 10, max = 500, message = "A mensagem deve ter entre 10 e 500 caracteres")
    val mensagem: String,

    @field: NotNull
    val idCurso: Long,

    @field: NotNull
    val idAutor: Long
)