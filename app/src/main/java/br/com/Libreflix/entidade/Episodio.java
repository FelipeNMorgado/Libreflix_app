package br.com.Libreflix.entidade;

public class Episodio {
    private String titulo;
    private String descricao;
    private long duracao;

    Episodio (String titulo, String descricao, long duracao){
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
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

    public long getDuracao() {
        return duracao;
    }

    public void setDuracao(long duracao) {
        this.duracao = duracao;
    }

}
