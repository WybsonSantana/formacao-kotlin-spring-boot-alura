package br.com.alura.forum.model

import java.time.LocalDateTime

data class Topico(
    val id: Long? = null,
    val titulo: String,
    val mensagem: String,
    val data: LocalDateTime = LocalDateTime.now(),
    val curso: Curso,
    val autor: Usuario,
    val respostas:List<Resposta> = ArrayList(),
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO
)