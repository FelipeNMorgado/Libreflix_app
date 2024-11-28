package br.com.Libreflix.repositorios;

import android.content.Context;
import br.com.Libreflix.entidade.Filme;
import com.example.libreflixapp.DatabaseHelper;

public class RepositorioFilme {

    private final DatabaseHelper dBH;

    public RepositorioFilme(Context context) {
        this.dBH = new DatabaseHelper(context);
    }

    public void incluir(Filme filme) {
        dBH.adicionarFilme(filme);
    }

    public String consultar(int id) {
        return dBH.consultarFilmePorId(id);
    }

    public String consultar(String titulo) {
        return dBH.consultarFilme(titulo);
    }
}