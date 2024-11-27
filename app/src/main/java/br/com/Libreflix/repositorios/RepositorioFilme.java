package br.com.Libreflix.repositorios;

import com.example.libreflixapp.DatabaseHelper;
import br.com.Libreflix.entidade.Filme;

public class RepositorioFilme {

    private Filme filme;
    private DatabaseHelper dBH;

    public void incluir(Filme filme){

        dBH.adicionarFilme(filme);
    }

    public String consultar(long id){

        return dBH.consultarFilme(id);
    }

    public String consultar(String titulo){

        return dBH.consultarFilme(titulo);
    }

}