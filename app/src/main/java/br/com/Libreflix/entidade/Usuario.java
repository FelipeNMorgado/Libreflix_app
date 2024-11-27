package br.com.Libreflix.entidade;

import br.com.Libreflix.mediators.MediatorUsuario;

public class Usuario {
    private String nome;
    private String email;
    private String userName;
    private String senha;

    public Usuario(String nome, String email, String userName, String senha) {
        this.nome = nome;
        this.email = email;
        this.userName = userName;
        this.senha = senha;
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
