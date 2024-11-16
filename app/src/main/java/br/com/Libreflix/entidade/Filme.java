package br.com.Libreflix.entidade;

public class Filme extends Episodio {
    private Tags[] tags;
    private int ano;
    private int classificacaoIndicativa;
    private boolean tituloVisto;
    private boolean favorito;
    private String diretor;
    private String[] elenco;

    public Filme(String titulo, String descricao, boolean verificarVisualizacao, long duracao, long tempoVisto, Tags[] tags, int ano, int classificacaoIndicativa, boolean tituloVisto, boolean favorito, String diretor, String[] elenco) {
        super(titulo, descricao, verificarVisualizacao, duracao, tempoVisto);
        this.tags = tags;
        this.ano = ano;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.tituloVisto = tituloVisto;
        this.favorito = favorito;
        this.diretor = diretor;
        this.elenco = elenco;
    }

    public Tags[] getTags() {
        return tags;
    }

    public void setTags(Tags[] tags) {
        this.tags = tags;
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
}


