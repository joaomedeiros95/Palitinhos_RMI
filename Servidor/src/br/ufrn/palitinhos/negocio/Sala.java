package br.ufrn.palitinhos.negocio;
import br.ufrn.palitinhos.dominio.Aposta;
import br.ufrn.palitinhos.dominio.Jogador;
import br.ufrn.palitinhos.dominio.Jogadores;
import br.ufrn.palitinhos.dominio.Resposta;
import br.ufrn.palitinhos.excecao.InvalidJogadorException;
import br.ufrn.palitinhos.utils.SalaThread;


public class Sala implements SalaInterface {
	private Jogadores jogadores;
	private int proximoJogador = 1;
    private Aposta aposta;
	private RodadaInterface rodada;
	private boolean jogoIniciou;
    private SalaThread salaThread;

	public Sala() {
		super();
		this.rodada = new Rodada();
        this.jogadores = new Jogadores();
        salaThread = new SalaThread(this);
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
                .append("Tenho " + jogador.getQuantPalitos() + " palitos.\n");
        if(ultimoJogador != null) {
            retorno.append("Último jogador foi: " + ultimoJogador.getNome() + ", ele apostou " + aposta.getQuantPalitos() + "\n");
        }
        retorno.append("Jogando jogando: " + jogadores.buscarJogadorObj(proximoJogador).getNome());
        retorno.append("\n==================");

        return retorno.toString();
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

        if(jogadores.buscarJogadorObj(id).isJaJogou()) {
            return new Resposta(Resposta.AGUARDANDO_FINALIZAR_RODADA, true);
        }

		if(id == proximoJogador(false).getId()) {
            return new Resposta(Resposta.AGUARDANDO_JOGADA, false);
		} else {
            return new Resposta(Resposta.AGUARDANDO_JOGADA, true);
		}
	}

    @Override
    public void realizarJogada(Aposta aposta, int quantPalitosJogados) {
        this.aposta = aposta;

        rodada.realizarJogada(aposta, quantPalitosJogados);
        proximoJogador();
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
