package br.com.alura.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

    fun notificar(nomeAutor: String, emailAutor: String) {
        val message = SimpleMailMessage()

        message.setFrom("forumalura@mail.com")
        message.setTo(emailAutor)
        message.setSubject("[Forum alura] Mensagem recebida!")
        message.setText("Olá, $nomeAutor!\n\nO seu tópico foi respondido. Acesse o seu post para conferir a resposta!\n\nEquipe Forum alura.")

        javaMailSender.send(message)
    }
}