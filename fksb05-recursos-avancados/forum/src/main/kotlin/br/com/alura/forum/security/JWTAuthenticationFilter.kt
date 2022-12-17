package br.com.alura.forum.security

import br.com.alura.forum.configuration.JWTUtil
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
    private val jwtUtil: JWTUtil
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")
        val jwt = getTokenDetail(token)

        if (jwtUtil.isValid(jwt)) {
            val authentication = jwtUtil.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    private fun getTokenDetail(token: String?): String? {
        return token?.let { jwt ->
            jwt.substring(token.indexOf("eyJ"), token.length)
        }
    }
}
