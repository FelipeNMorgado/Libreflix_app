package br.com.Libreflix.entidade;

public class Episodio {
    private int id;
    private String imagem;
    private String titulo;
    private String uriVideo;
    private String descricao;
    private long duracao;

    Episodio (int id,String imagem, String uriVideo, String titulo, String descricao, long duracao){
        this.id = id;
        this.imagem = imagem;
        this.uriVideo = uriVideo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
    }

    public String getImagem(){
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Episodio(String uriVideo){
        this.uriVideo = uriVideo;
    }

    public String getUriVideo(){
        return uriVideo;
    }

    public void setUriVideo(String uriVideo){
        this.uriVideo = uriVideo;
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