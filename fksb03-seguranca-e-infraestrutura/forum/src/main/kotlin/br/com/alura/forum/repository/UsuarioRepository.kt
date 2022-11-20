package br.com.alura.forum.repository

import br.com.alura.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Repository

interface UsuarioRepository : JpaRepository<Usuario, Long> {
fun findByEmail(username: String?): Usuario?
}