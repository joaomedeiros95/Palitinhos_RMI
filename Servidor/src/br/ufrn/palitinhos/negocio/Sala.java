package br.ufrn.palitinhos.negocio;
import br.ufrn.palitinhos.dominio.Aposta;
import br.ufrn.palitinhos.dominio.Jogador;
import br.ufrn.palitinhos.dominio.Jogadores;
import br.ufrn.palitinhos.dominio.Resposta;
import br.ufrn.palitinhos.excecao.InvalidApostaException;
import br.ufrn.palitinhos.excecao.InvalidJogadorException;
import br.ufrn.palitinhos.utils.SalaThread;


public class Sala implements SalaInterface {
	private Jogadores jogadores;
	private int proximoJogador = 1;
    private Aposta aposta;
	private RodadaInterface rodada;
	private boolean jogoIniciou;
    private SalaThread salaThread;
    private Integer rodadaAtual;
    private boolean jogoTerminou;

	public Sala() {
		super();
		this.rodada = new Rodada();
        this.jogadores = new Jogadores();
        salaThread = new SalaThread(this);
        this.rodadaAtual = 1;
        this.jogoTerminou = false;
	}

	public RodadaInterface getRodada() {
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}

    public void inscreverJogador(Jogador jogador) {
		if(jogoIniciou) {
			throw new InvalidJogadorException("O jogo já iniciou!");
		}

        jogadores.insertJogador(jogador);

        if(!jogoIniciou && !salaThread.isAlive()) {
            salaThread.start();
        }
    }

    @Override
    public String divulgarResultado(int idJogador) {
        StringBuilder retorno = new StringBuilder();
        Jogador jogador = jogadores.buscarJogadorObj(idJogador);
        Jogador ultimoJogador = null;

        if(aposta != null) {
            ultimoJogador = jogadores.buscarJogadorObj(aposta.getId());
        }

        retorno.append("\n==================\n")
                .append("Rodada " + rodadaAtual + "\n")
                .append("Tenho " + jogador.getQuantPalitos() + " palitos.\n");
        if(ultimoJogador != null) {
            retorno.append("Último jogador foi: " + ultimoJogador.getNome() + ", ele apostou " + aposta.getQuantPalitos() + "\n");
        }
        retorno.append("Jogador jogando: " + jogadores.buscarJogadorObj(proximoJogador).getNome());
        retorno.append(pegarEstatisticas());
        retorno.append("\n==================");

        return retorno.toString();
    }

    private String pegarEstatisticas() {
        String retorno = "\nPalitos: ";

        for(Jogador jogador : jogadores.getJogadores()) {
            retorno += jogador.getNome() + " (" + jogador.getQuantPalitos() + "), ";
        }

        retorno = retorno.substring(0, retorno.length() - 2);
        return retorno;
    }

    @Override
    public Jogador proximoJogador() {
        return proximoJogador(true);
    }

	public Jogador proximoJogador(boolean incrementar){
		Jogador retorno = jogadores.buscarJogadorObj(proximoJogador);

        if(incrementar) {
            do {
                if(proximoJogador >= jogadores.getJogadores().get(jogadores.getJogadores().size() - 1).getId()){
                    proximoJogador = 1;
                    verificarGanhador(rodada.divulgarResultado());
                    rodada = new Rodada();
                    rodadaAtual++;
                } else {
                    proximoJogador++;
                }
            } while (!jogadores.buscarJogadorObj(proximoJogador).isEstaJogando());
        }

		return retorno;
	}

    private void verificarGanhador(int idJogador) {
        if(idJogador != -1) {
            jogadores.buscarJogadorObj(idJogador).decrementarQuantPalitos();
        }
    }

    @Override
	public Resposta esperar(int id) {
		if(!jogoIniciou) {
			return new Resposta(Resposta.AGUARDANDO_INICIAR, true);
		}

        jogoTerminou();

        if(jogoTerminou) {
            return new Resposta(Resposta.JOGO_TERMINOU, false);
        }

        if(!jogadores.buscarJogadorObj(id).isEstaJogando()) {
            return new Resposta(Resposta.TERMINOU_DE_JOGAR, false);
        }

		if(id == proximoJogador(false).getId()) {
            return new Resposta(Resposta.AGUARDANDO_JOGADA, false);
		} else {
            return new Resposta(Resposta.AGUARDANDO_JOGADA, true);
		}
	}

    private void jogoTerminou() {
        int jogadoresJogando = 0;

        for(Jogador jogador : jogadores.getJogadores()) {
            if(jogador.isEstaJogando()) {
                jogadoresJogando++;
            }
        }

        if(jogadoresJogando == 1) {
            jogoTerminou = true;
        }
    }

    @Override
    public void realizarJogada(Aposta aposta, int quantPalitosJogados) {
        verificarPalitosJogados(aposta.getId(), quantPalitosJogados);
        rodada.realizarJogada(aposta, quantPalitosJogados);

        this.aposta = aposta;

        proximoJogador();
    }

    private void verificarPalitosJogados(int id, int quantPalitosJogados) {
        Jogador jogador = jogadores.buscarJogadorObj(id);

        if(rodadaAtual == 1 && quantPalitosJogados == 0) {
            throw new InvalidApostaException("Na primeira rodada não pode jogar 0 palitos");
        }

        if(jogador.getQuantPalitos() < quantPalitosJogados) {
            throw new InvalidApostaException("Você jogou(" + quantPalitosJogados + ") mais palitos do que tem ("
                    + jogador.getQuantPalitos() + ").");
        }
    }

    public Jogadores getJogadores() {
        return jogadores;
    }

    public boolean isJogoIniciou() {
        return jogoIniciou;
    }

    public void setJogoIniciou(boolean jogoIniciou) {
        this.jogoIniciou = jogoIniciou;
    }

    private void removerPalito(int id){
		jogadores.buscarJogadorObj(id).decrementarQuantPalitos();
	}
	

}