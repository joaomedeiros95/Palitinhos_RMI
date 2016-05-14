package br.ufrn.palitinhos.negocio;
import br.ufrn.palitinhos.dominio.Aposta;
import br.ufrn.palitinhos.dominio.Jogador;
import br.ufrn.palitinhos.dominio.Jogadores;
import br.ufrn.palitinhos.excecao.InvalidJogadorException;
import br.ufrn.palitinhos.utils.SalaThread;


public class Sala implements SalaInterface {
	private Jogadores jogadores;
	private int proximoJogador = 1;
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
    public Jogador proximoJogador() {
        return proximoJogador(true);
    }

	public Jogador proximoJogador(boolean incrementar){
		Jogador retorno = jogadores.buscarJogadorObj(proximoJogador);

        if(incrementar) {
            proximoJogador++;
            if(proximoJogador == jogadores.getJogadores().size()){
                proximoJogador = 0;
                //Imprime resultado
                rodada = new Rodada();
            }
        }

		return retorno;
	}

	@Override
	public boolean esperar(int id) {
		if(!jogoIniciou) {
			return true;
		}

		if(id == proximoJogador(false).getId()) {
			return false;
		} else {
			return true;
		}
	}

    @Override
    public void realizarJogada(Aposta aposta, int quantPalitosJogados) {
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
