package br.com.Libreflix.mediators;

import android.content.Context;
import br.com.Libreflix.entidade.Serie;
import br.com.Libreflix.repositorios.RepositorioFilme;
import br.com.Libreflix.repositorios.RepositorioSerie;

public class MediatorSerie {

    private RepositorioSerie rs;

    public MediatorSerie(Context context) {
        this.rs = new RepositorioSerie(context);
    }

    public String validar(Serie serie){
        if(rs.consultar(serie.getTitulo()) != null){
            return "Serie não encontrada";
        }else{
            return null;
        }
    }

    public String incluir(Serie serie){
        if(validar(serie) != null){
            return "Filme já existe!";
        }else{
            rs.incluir(serie);
            return null;
        }
    }
}