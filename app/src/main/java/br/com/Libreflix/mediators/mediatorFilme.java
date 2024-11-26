package br.com.Libreflix.mediators;

import com.example.libreflixapp.DatabaseHelper;

import br.com.Libreflix.entidade.Filme;
import br.com.Libreflix.repositorios.RepositorioFilme;

public class mediatorFilme {

    private Filme filme;
    private RepositorioFilme rf;

    public String validar(Filme filme){
        if(rf.consultar(filme.getTitulo()) == null){
            return "Filme não encontrado!";
        }else{
            return null;
        }
    }

    public String incluir(Filme filme){
        if(validar(filme) == null){
            return "Filme já existe!";
        }else{
            rf.incluir(filme);
            return null;
        }
    }

}
