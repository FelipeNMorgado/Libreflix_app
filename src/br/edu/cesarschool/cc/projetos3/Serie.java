public class Serie {
	
	private String titulo;
	private String descricao;
	private int ano;
	private int classificacaoIndicativa;
	private boolean tituloVisto;
	private boolean favorito;
	private String diretor;
	private String[] elenco;
	private int qntdTemporadas;
	private int qntdEpisodiosTotais;
	private Tags[] tags;
	private Temporada[] temporadas;

	public Serie(String titulo, String descricao, int ano, int classificacaoIndicativa, boolean tituloVisto,
			boolean favorito, String diretor, String[] elenco, int qntdTemporadas, int qntdEpisodiosTotais, 
			Tags[] tags, Temporada[] temporadas) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.ano = ano;
		this.classificacaoIndicativa = classificacaoIndicativa;
		this.tituloVisto = tituloVisto;
		this.favorito = favorito;
		this.diretor = diretor;
		this.elenco = elenco;
		this.qntdTemporadas = qntdTemporadas;
		this.qntdEpisodiosTotais = qntdEpisodiosTotais;
		this.tags = tags;
		this.temporadas = temporadas;
	}

	public boolean isTituloVisto() {
		return tituloVisto;
	}

	public void setTituloVisto(boolean tituloVisto) {
		this.tituloVisto = tituloVisto;
	}

	public int getQntdTemporadas() {
		return qntdTemporadas;
	}

	public void setQntdTemporadas(int qntdTemporadas) {
		this.qntdTemporadas = qntdTemporadas;
	}

	public int getQntdEpisodiosTotais() {
		return qntdEpisodiosTotais;
	}

	public void setQntdEpisodiosTotais(int qntdEpisodiosTotais) {
		this.qntdEpisodiosTotais = qntdEpisodiosTotais;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getAno() {
		return ano;
	}

	public int getClassificacaoIndicativa() {
		return classificacaoIndicativa;
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
	
	public Temporada[] getTemporadas() {
		return temporadas;
	}
	
}