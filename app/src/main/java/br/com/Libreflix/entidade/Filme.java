package br.com.Libreflix.entidade;

public class Filme extends Episodio {
    private long id;
    private String tags;
    private int ano;
    private int classificacaoIndicativa;
    private String diretor;
    private String elenco;

    public Filme(long id, String titulo, String descricao, long duracao, long tempoVisto, String tags, int ano, int classificacaoIndicativa, String diretor, String elenco) {
        super(titulo, descricao, duracao);
        this.id = id;
        this.tags = tags;
        this.ano = ano;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.diretor = diretor;
        this.elenco = elenco;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
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
}


