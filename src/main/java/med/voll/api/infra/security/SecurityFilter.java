package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.http.HttpServletRequest;
import med.voll.api.domain.usuario.UsuarioRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
            throws jakarta.servlet.ServletException, java.io.IOException {

                System.out.println("=== SecurityFilter ===");
                System.out.println("URI: " + request.getRequestURI());
                System.out.println("Method: " + request.getMethod());

                var tokenJWT = recuperarToken(request);
                System.out.println("Token recuperado: " + (tokenJWT != null ? "SIM" : "NÃO"));

                if (tokenJWT != null) {
                    try {
                        var subject = tokenService.getSubject(tokenJWT);
                        System.out.println("Subject extraído: " + subject);

                        var usuario = usuarioRepository.findByNome(subject);
                        System.out.println("Usuário encontrado: " + (usuario != null ? usuario.getNome() : "NULL"));

                        var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        System.out.println("Autenticação realizada com sucesso!");
                    } catch (RuntimeException e) {
                        System.out.println("ERRO ao validar token: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

}
