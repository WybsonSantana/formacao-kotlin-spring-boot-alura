package br.com.alura.forum.dto

import java.io.Serializable

data class TopicoPorCategoriaDTO(
    val categoria: String,
    val quantidade: Long
) : Serializable