package io.github.rod1andrade.pacto_solucoes_rh.controllers;

import io.github.rod1andrade.pacto_solucoes_rh.services.UsuarioLogadoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final HttpServletRequest request;
    private final UsuarioLogadoService usuarioLogadoService;

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> info() {
        var infos = new HashMap<String, Object>();

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        infos.put("nome", authentication.getName());
        infos.put("papeis", usuarioLogadoService.getPapeis(authentication));

        log.info("Obtendo informacoes usuario autenticado ::: {}, Session ID: {}", infos.get("nome"), request.getSession().getId());
        log.info("SecurityContext Authentication: {}", authentication);

        return ResponseEntity.ok(infos);
    }
}
