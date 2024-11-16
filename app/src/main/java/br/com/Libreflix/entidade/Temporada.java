package br.com.Libreflix.entidade;

public class Temporada {
    private Episodio[]episodios;
    private int qtdEpisodio;

    public Temporada(Episodio[] episodios, int qtdEpisodio) {
        this.episodios = episodios;
        this.qtdEpisodio = qtdEpisodio;
    }

    public Episodio[] getEpisodios() {
        return episodios;
    }

    public void setEpisodios(Episodio[] episodios) {
        this.episodios = episodios;
    }

    public int getQtdEpisodio() {
        return qtdEpisodio;
    }

    public void setQtdEpisodio(int qtdEpisodio) {
        this.qtdEpisodio = qtdEpisodio;
    }
}
