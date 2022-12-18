package br.com.alura.forum.controller

import br.com.alura.forum.model.Resposta
import br.com.alura.forum.service.RespostaService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/respostas")
@SecurityRequirement(name = "Bearer Auth")
class RespostaController(
    private val service: RespostaService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    fun salvar(@RequestBody @Valid resposta: Resposta) {
        service.salvar(resposta)
    }
}