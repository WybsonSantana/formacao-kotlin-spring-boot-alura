package br.com.alura.forum.controller

import br.com.alura.forum.dto.TopicoPorCategoriaDTO
import br.com.alura.forum.service.TopicoService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/relatorios")
@SecurityRequirement(name = "Bearer Auth")
class RelatorioController(
    private val service: TopicoService
) {

    @GetMapping
    fun gerarRelatorio(model: Model): String {
        model.addAttribute("topicosPorCategoria", service.gerarRelatorio())
        return "relatorio"
    }
}