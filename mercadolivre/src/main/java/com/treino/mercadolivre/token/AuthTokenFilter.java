package com.treino.mercadolivre.token;

import com.treino.mercadolivre.usuario.Usuario;
import com.treino.mercadolivre.usuario.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {
    private final TokenManager tokenManager;
    private final UsuarioRepository usuarioRepository;

    public AuthTokenFilter(TokenManager tokenService, UsuarioRepository usuarioRepository) {
        this.tokenManager = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(httpServletRequest);
        boolean valido = tokenManager.isTokenValido(token);

        if(valido)
            autenticarCliente(token);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void autenticarCliente(String token) {
        Integer idUsuario = tokenManager.getIdUsuario(token);
        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private String recuperarToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer "))
            return null;

        //Tirando a palavra Bearer do retorno
        return token.substring(7);
    }
}
