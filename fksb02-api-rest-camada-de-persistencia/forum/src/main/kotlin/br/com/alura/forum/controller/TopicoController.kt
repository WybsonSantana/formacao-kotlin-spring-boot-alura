package br.com.alura.forum.controller

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoPorCategoriaDTO
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.service.TopicoService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(
    private val service: TopicoService
) {

    @GetMapping
    @Cacheable("listarTopicos")
    fun listar(
        @RequestParam(required = false) nomeCurso: String?,
        @PageableDefault(size = 5, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable
    ): Page<TopicoView> {
        return service.listar(nomeCurso, paginacao)
    }

    @GetMapping("/{id}")
    @Cacheable("buscarTopicoPorId")
    @Transactional
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["listarTopicos", "buscarTopicoPorId", "gerarRelatorio"], allEntries = true)
    fun cadastrar(
        @RequestBody @Valid form: NovoTopicoForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topicoView = service.cadastrar(form)
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["listarTopicos", "buscarTopicoPorId", "gerarRelatorio"], allEntries = true)
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): ResponseEntity<TopicoView> {
        return ResponseEntity.ok(service.atualizar(form))
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = ["listarTopicos", "buscarTopicoPorId", "gerarRelatorio"], allEntries = true)
    fun deletar(@PathVariable id: Long): ResponseEntity<Unit> {
        service.deletar(id)
        return ResponseEntity<Unit>(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/relatorio")
    @Cacheable("gerarRelatorio")
    fun gerarRelatorio(
        @PageableDefault(size = 5) paginacao: Pageable
    ): Page<TopicoPorCategoriaDTO> {
        return service.gerarRelatorio(paginacao)
    }
}