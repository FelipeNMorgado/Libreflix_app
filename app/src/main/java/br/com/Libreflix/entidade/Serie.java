package br.com.Libreflix.entidade;

public class Serie extends Episodio{
    private String tags;
    private int ano;
    private int classificacaoIndicativa;
    private String diretor;
    private String elenco;
    private int qntdTemporadas;
    private int qntdEpisodiosTotais;

    public Serie(long id, String uriVidio, String tags, String tituloSerie, String descricaoSerie, int ano, int classificacaoIndicativa, String diretor, String elenco, int qntdTemporadas, int qntdEpisodiosTotais, String tituloEpisodio, String descicaoEpisodio, long duracaoEpisodio) {
        super(id, uriVidio, tituloEpisodio, descicaoEpisodio, duracaoEpisodio);
        this.tags = tags;
        this.ano = ano;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.diretor = diretor;
        this.elenco = elenco;
        this.qntdTemporadas = qntdTemporadas;
        this.qntdEpisodiosTotais = qntdEpisodiosTotais;
    }


    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
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

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getElenco() {
        return elenco;
    }

    public void setElenco(String elenco) {
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