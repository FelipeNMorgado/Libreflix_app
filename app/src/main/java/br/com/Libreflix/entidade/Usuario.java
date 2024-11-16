package br.com.Libreflix.entidade;

public class Usuario {
    private Filme[] filmes;
    private Serie[] series;
    private String nome;
    private String email;
    private String userName;
    private String senha;

    public Usuario(Filme[] filmes, Serie[] series, String nome, String email, String userName, String senha) {
        this.filmes = filmes;
        this.series = series;
        this.nome = nome;
        this.email = email;
        this.userName = userName;
        this.senha = senha;
    }

    public Filme[] getFilmes() {
        return filmes;
    }

    public void setFilmes(Filme[] filmes) {
        this.filmes = filmes;
    }

    public Serie[] getSeries() {
        return series;
    }

    public void setSeries(Serie[] series) {
        this.series = series;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
