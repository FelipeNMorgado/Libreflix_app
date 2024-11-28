package br.com.Libreflix.repositorios;

import android.content.Context;

import br.com.Libreflix.entidade.Usuario;
import com.example.libreflixapp.DatabaseHelper;

public class RepositorioUsuario {

    private final DatabaseHelper dBH;

    public RepositorioUsuario(Context context) {
        this.dBH = new DatabaseHelper(context);
    }

    public String consultarEmail(Usuario usuario){

        return dBH.consultarEmailUsuario(usuario);
    }

    public String consultarSenha(Usuario usuario){

        return dBH.consultarSenhaUsuario(usuario);
    }

    public void incluir(Usuario usuario){

        dBH.adicionarUsuario(usuario);
    }

}
