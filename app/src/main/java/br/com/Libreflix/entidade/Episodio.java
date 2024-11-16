package br.com.Libreflix.entidade;

public class Episodio {
    private String titulo;
    private String descricao;
    private boolean verificarVisualizacao;
    private long duracao;
    private long tempoVisto;

    Episodio (String titulo, String descricao, boolean verificarVisualizacao, long duracao, long tempoVisto){
        this.titulo = titulo;
        this.descricao = descricao;
        this.verificarVisualizacao = verificarVisualizacao;
        this.duracao = duracao;
        this.tempoVisto = tempoVisto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getVerificarVisualizacao() {
        return verificarVisualizacao;
    }

    public void setVerificarVisualizacao(boolean verificarVisualizacao) {
        this.verificarVisualizacao = verificarVisualizacao;
    }

    public long getDuracao() {
        return duracao;
    }

    public void setDuracao(long duracao) {
        this.duracao = duracao;
    }

    public long getTempoVisto() {
        return tempoVisto;
    }

    public void setTempoVisto(long tempoVisto) {
        this.tempoVisto = tempoVisto;
    }
}
