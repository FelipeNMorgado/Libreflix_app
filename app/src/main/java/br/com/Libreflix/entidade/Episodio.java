package br.com.Libreflix.entidade;

public class Episodio {
    private int id;
    private String titulo;
    private String uriVidio;
    private String descricao;
    private long duracao;

    Episodio (int id, String uriVidio, String titulo, String descricao, long duracao){
        this.id = id;
        this.uriVidio = uriVidio;
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
    }
    public Episodio(String uriVidio){
        this.uriVidio = uriVidio;
    }

    public String getUriVidio(){
        return uriVidio;
    }

    public void setUriVidio(String uriVidio){
        this.uriVidio = uriVidio;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
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