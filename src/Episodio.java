public class Episodio {
	
	private String titulo;
	private String descricao;
	private boolean verificarVisualizacao;
	private long duracao;
	private long tempoVisto;
	
	public Episodio(String titulo, String descricao, boolean verificarVisualizacao, long duracao,
			long tempoVisto) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.verificarVisualizacao = verificarVisualizacao;
		this.duracao = duracao;
		this.tempoVisto = tempoVisto;
	}

	public boolean isVerificadorVisualizacao() {
		return verificarVisualizacao;
	}

	public void setVerificarVisualizacao(boolean verificarVisualizacao) {
		this.verificarVisualizacao = verificarVisualizacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public long getDuracao() {
		return duracao;
	}
	
	public long getTempoVisto() {
		return tempoVisto;
	}
}
