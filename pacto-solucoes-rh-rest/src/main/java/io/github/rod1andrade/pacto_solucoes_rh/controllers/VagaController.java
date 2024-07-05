package io.github.rod1andrade.pacto_solucoes_rh.controllers;

import io.github.rod1andrade.pacto_solucoes_rh.exceptions.PactoSolucoesRHException;
import io.github.rod1andrade.pacto_solucoes_rh.requests.VagaRequest;
import io.github.rod1andrade.pacto_solucoes_rh.responses.VagaResponse;
import io.github.rod1andrade.pacto_solucoes_rh.services.VagaService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/vaga")
@RequiredArgsConstructor
public class VagaController {

    private final VagaService vagaService;

    @PostMapping
    public ResponseEntity<Long> anunciar(@RequestBody @Valid VagaRequest vagaRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(vagaService.anunciar(vagaRequest));
        } catch (PactoSolucoesRHException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }

    @PutMapping("/{vagaId}")
    public ResponseEntity<Long> anunciar(@RequestBody @Valid VagaRequest vagaRequest, @PathVariable(name = "vagaId", required = true) Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(vagaService.editar(vagaRequest, id));
        } catch (PactoSolucoesRHException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }


    @GetMapping
    public ResponseEntity<Page<VagaResponse>> consultar(
            @RequestParam(name = "pagina", defaultValue = "1") int pagina,
            @RequestParam(name = "quantidade", defaultValue = "10") int quantidade,
            @RequestParam(name = "filtro") String filtro
    ) {
        return ResponseEntity.ok(vagaService.consultar(pagina, quantidade, filtro));
    }

    @GetMapping("/obter/{id}")
    public ResponseEntity<VagaResponse> obter(@PathVariable(name = "id", required = true) Long id) {
        return ResponseEntity.ok(vagaService.obter(id));
    }

    @PostMapping("/inscrever-se/{idVaga}")
    public ResponseEntity<Void> insrever(@PathVariable(value = "idVaga", required = true) Long id) {
        vagaService.inscrever(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verifica-inscricao/{idVaga}")
    public ResponseEntity<Map<String, Object>> verificaInscricao(@PathVariable(value = "idVaga", required = true) Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("jaInscrito", vagaService.verificaInscricao(id));
        return ResponseEntity.ok(map);
    }
}
