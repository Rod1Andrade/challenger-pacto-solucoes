package io.github.rod1andrade.pacto_solucoes_rh.enums;

public enum PapeisEnum {
    ADMIN("ADMIN"), USUARIO_COMUM("USUARIO_COMUM");

    private final String nome;

    public String getNome() {
        return this.nome;
    }

    PapeisEnum(String nome) {
        this.nome = nome;
    }
}
