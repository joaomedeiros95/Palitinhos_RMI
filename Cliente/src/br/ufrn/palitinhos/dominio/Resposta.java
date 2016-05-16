package br.ufrn.palitinhos.dominio;

import java.io.Serializable;

/**
 * Created by joao on 16/05/16.
 */
public class Resposta implements Serializable {

    public static final Integer AGUARDANDO_INICIAR = 1;
    public static final Integer AGUARDANDO_JOGADA = 2;
    public static final Integer AGUARDANDO_FINALIZAR_RODADA = 3;

    private Aposta aposta;
    private String nome;
    private Integer status;
    private boolean aguardar;

    public Resposta(Integer status, boolean aguardar) {
        this.status = status;
        this.aguardar = aguardar;
    }

    public Aposta getAposta() {
        return aposta;
    }

    public void setAposta(Aposta aposta) {
        this.aposta = aposta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isAguardar() {
        return aguardar;
    }

    public void setAguardar(boolean aguardar) {
        this.aguardar = aguardar;
    }
}
