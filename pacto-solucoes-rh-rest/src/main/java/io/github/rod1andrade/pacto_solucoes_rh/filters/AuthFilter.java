package io.github.rod1andrade.pacto_solucoes_rh.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Slf4j
public class AuthFilter extends UsernamePasswordAuthenticationFilter {
    public AuthFilter(
        AuthenticationManager authenticationManager,
        SessionAuthenticationStrategy sessionAuthenticationStrategy
    ) {
        log.info("FILTER CALLED");
        super.setAuthenticationManager(authenticationManager);
        super.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
    }
}
