package br.com.Libreflix.repositorios;

import android.content.Context;

import com.example.libreflixapp.DatabaseHelper;
import br.com.Libreflix.entidade.Serie;

public class RepositorioSerie {

    private final DatabaseHelper dBH;

    public RepositorioSerie(Context context) {
        this.dBH = new DatabaseHelper(context);
    }

    public void incluir(Serie serie){

        dBH.adicionarSerie(serie);
    }

    public String consultar(String titulo){

        return dBH.consultarSerie(titulo);
    }

}
