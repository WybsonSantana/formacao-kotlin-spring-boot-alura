package br.com.alura.forum.model

object UsuarioTest {

    fun build() = Usuario(
        id = 1,
        nome = "Bel Trano",
        email = "beltrano@mail.com",
        password = "123456"
    )
}