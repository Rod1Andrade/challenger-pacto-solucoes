package io.github.rod1andrade.pacto_solucoes_rh.services;

import io.github.rod1andrade.pacto_solucoes_rh.entities.Usuario;
import io.github.rod1andrade.pacto_solucoes_rh.entities.Vaga;
import io.github.rod1andrade.pacto_solucoes_rh.enums.PapeisEnum;
import io.github.rod1andrade.pacto_solucoes_rh.exceptions.PactoSolucoesRHException;
import io.github.rod1andrade.pacto_solucoes_rh.mappers.VagaRequestRequestMapper;
import io.github.rod1andrade.pacto_solucoes_rh.mappers.VagaResponseMapper;
import io.github.rod1andrade.pacto_solucoes_rh.repositories.VagaRepository;
import io.github.rod1andrade.pacto_solucoes_rh.requests.VagaRequest;
import io.github.rod1andrade.pacto_solucoes_rh.responses.VagaResponse;
import io.github.rod1andrade.pacto_solucoes_rh.validators.VagaValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaValidator vagaValidator;
    private final VagaRepository vagaRepository;
    private final VagaRequestRequestMapper vagaRequestMapper;
    private final VagaResponseMapper vagaResponseMapper;

    private final UsuarioLogadoService usuarioLogadoService;

    @Transactional
    public Long anunciar(VagaRequest vagaRequest) {
        Usuario usuario = usuarioLogadoService.getUsuarioLogado();

        vagaValidator.validarAnuncioVaga(usuario);
        try {
            var vaga = vagaRequestMapper.toEntity(vagaRequest);
            vaga.setCriadoPor(usuario);
            vagaRepository.saveAndFlush(vaga);

            return vaga.getId();
        } catch (Exception e) {
            throw new PactoSolucoesRHException("Não foi possível anunciar a vaga", 2);
        }
    }

    public Page<VagaResponse> consultar(
            int pagina,
            int quantidade,
            String filtro
    ) {
        log.info("Quantidade: {} Pagina: {}", quantidade, pagina);
        boolean permissaoEditar = temPermissaoEditar();

        Pageable pageable = PageRequest.of(pagina - 1, quantidade);
        return vagaRepository
                .findByTituloContainingIgnoreCase(filtro.toLowerCase(), pageable)
                .map(vaga -> {
                    var vagaResponse = vagaResponseMapper.toDto(vaga);
                    vagaResponse.setPermissaoEditar(permissaoEditar);
                    return vagaResponse;
                });
    }

    public VagaResponse obter(Long id) {
        var vagaResponse = vagaResponseMapper.toDto(vagaRepository.findById(id).orElse(null));
        if (Objects.nonNull(vagaResponse)) {
            vagaResponse.setPermissaoEditar(temPermissaoEditar());
        }
        return vagaResponse;
    }

    public void inscrever(Long id) {
        Usuario usuario = usuarioLogadoService.getUsuarioLogado();

        if (!isUsuarioComum(usuario)) {
            throw new PactoSolucoesRHException("Somente usuarios comuns podem se inscrever para as vagas", 188221);
        }

        Optional<Vaga> optionalVaga = vagaRepository.findById(id);

        if (optionalVaga.isPresent()) {
            Vaga vaga = optionalVaga.get();

            // Verificar se o usuário já está associado à vaga para evitar duplicação
            if (vaga.getUsuarios().contains(usuario)) {
                throw new PactoSolucoesRHException("Usuário já inscrito nesta vaga", 5);
            }

            vaga.getUsuarios().add(usuario);
            vagaRepository.save(vaga);
        } else {
            throw new PactoSolucoesRHException("Vaga não encontrada", 1);
        }
    }

    public boolean verificaInscricao(Long id) {
        try {
            log.info("Verificando Inscricao ::: ID VAGA {}", id);
            Usuario usuario = usuarioLogadoService.getUsuarioLogado();
            return vagaRepository.isUsuarioInscrito(id, usuario.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Long editar(VagaRequest vagaRequest, Long id) {
        if (!temPermissaoEditar()) throw new PactoSolucoesRHException("Nao tem permissao para editar", 32388);
        Vaga vaga = vagaRepository.findById(id).orElseThrow(() ->
                new PactoSolucoesRHException("Vaga não encontrada", 404));

        vaga.setTitulo(vagaRequest.getTitulo());
        vaga.setSubtitulo(vagaRequest.getSubtitulo());
        vaga.setDescricao(vagaRequest.getDescricao());
        vaga.setTexto(vagaRequest.getTexto());

        vagaRepository.saveAndFlush(vaga);

        return vaga.getId();
    }

    private boolean temPermissaoEditar() {
        return usuarioLogadoService
                .getUsuarioLogado()
                .getPapeis()
                .stream()
                .filter(papel -> PapeisEnum.ADMIN.name().equals(papel.getNome()))
                .count() == 1;
    }

    private boolean isUsuarioComum(Usuario usuario) {
        return usuario
                .getPapeis()
                .stream()
                .filter(papel -> PapeisEnum.USUARIO_COMUM.name().equals(papel.getNome()))
                .count() == 1;
    }
}
