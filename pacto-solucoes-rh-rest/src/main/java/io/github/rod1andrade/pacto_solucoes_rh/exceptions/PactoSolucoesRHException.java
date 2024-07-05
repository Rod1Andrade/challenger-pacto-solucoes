package io.github.rod1andrade.pacto_solucoes_rh.exceptions;

public class PactoSolucoesRHException extends RuntimeException {
    public static final int ERRO_DESCONHECIDO = -1;
    private int codigo;

    public PactoSolucoesRHException(String mensagem) {
        super(mensagem);
        this.codigo = ERRO_DESCONHECIDO;
    }

    public PactoSolucoesRHException(String mensagem, int codigo) {
        super(mensagem);
        this.codigo = codigo;
    }
}
