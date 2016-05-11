package br.ufrn.palitinhos.dominio;

/**
 * Created by joao on 10/05/16.
 */
public enum Status {

    AGUARDANDO_OUTROS("Aguardando outros jogadores"), JOGANDO("Jogando"), TERMINOU ("Terminou");

    private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
