package br.ufrn.palitinhos.negocio;
import br.ufrn.palitinhos.dominio.Aposta;
import br.ufrn.palitinhos.dominio.Jogador;
import br.ufrn.palitinhos.dominio.Jogadores;
import br.ufrn.palitinhos.utils.SalaThread;


public class Sala implements SalaInterface {
	private Jogadores jogadores;
	private int proximoJogador = 0;
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
        jogadores.insertJogador(jogador);

        if(!jogoIniciou && !salaThread.isAlive()) {
            salaThread.run();
        }
    }

	@Override
	public Jogador proximoJogador(){
		Jogador retorno = jogadores.buscarJogadorObj(proximoJogador);
		proximoJogador++;
		if(proximoJogador == jogadores.getJogadores().size()){
			proximoJogador = 0;
			//Imprime resultado
			rodada = new Rodada();
		}
		return retorno;
	}

	@Override
	public boolean esperar(int id) {
		if(!jogoIniciou) {
			return false;
		}

		if(id == proximoJogador().getId()) {
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
