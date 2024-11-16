package br.com.Libreflix.entidade;

public class Serie {
    private Tags[] tags;
    private Temporada[] temporadas;
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

    public Serie(Tags[] tags, Temporada[] temporadas, String titulo, String descricao, int ano, int classificacaoIndicativa, boolean tituloVisto, boolean favorito, String diretor, String[] elenco, int qntdTemporadas, int qntdEpisodiosTotais) {
        this.tags = tags;
        this.temporadas = temporadas;
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
    }

    public Tags[] getTags() {
        return tags;
    }

    public void setTags(Tags[] tags) {
        this.tags = tags;
    }

    public Temporada[] getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(Temporada[] temporadas) {
        this.temporadas = temporadas;
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(int classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public boolean isTituloVisto() {
        return tituloVisto;
    }

    public void setTituloVisto(boolean tituloVisto) {
        this.tituloVisto = tituloVisto;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String[] getElenco() {
        return elenco;
    }

    public void setElenco(String[] elenco) {
        this.elenco = elenco;
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
}
