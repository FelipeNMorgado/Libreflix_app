public class Filme extends Episodio{
	
	private int ano;
	private int classificacaoIndicativa;
	private boolean tituloVisto;
	private boolean favorito;
	private String diretor;
	private String[] elenco;
	private Tags[] tags;
	
	public Filme(String titulo, String descricao, boolean verificarVisualizacao, long duracao, long tempoVisto, int ano,
			int classificacaoIndicativa, boolean tituloVisto, boolean favorito, String diretor, String[] elenco, Tags[] tags) {
		super(titulo, descricao, verificarVisualizacao, duracao, tempoVisto);
		this.ano = ano;
		this.classificacaoIndicativa = classificacaoIndicativa;
		this.tituloVisto = tituloVisto;
		this.favorito = favorito;
		this.diretor = diretor;
		this.elenco = elenco;
		this.tags = tags;
	}

	public int getAno() {
		return ano;
	}

	public int getClassificacaoIndicativa() {
		return classificacaoIndicativa;
	}

	public boolean isTituloVisto() {
		return tituloVisto;
	}

	public boolean isFavorito() {
		return favorito;
	}

	public String getDiretor() {
		return diretor;
	}

	public String[] getElenco() {
		return elenco;
	}
	
	public Tags[] getTags() {
		return tags;
	}
}
