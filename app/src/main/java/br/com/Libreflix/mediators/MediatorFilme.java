package br.com.Libreflix.mediators;

import android.content.Context;
import br.com.Libreflix.entidade.Filme;
import br.com.Libreflix.repositorios.RepositorioFilme;

public class MediatorFilme {

    private final RepositorioFilme rf;

    public MediatorFilme(Context context) {
        this.rf = new RepositorioFilme(context);
    }

    public String validar(Filme filme) {
        if (rf.consultar(filme.getId()) != null) {
            return "Filme já existe!";
        }
        return null;
    }

    public String incluir(Filme filme) {
        // Validar duplicidade pelo ID
        String validacao = validar(filme);
        if (validacao != null) {
            return validacao; // Retorna "Filme já existe!" se duplicado
        }
        rf.incluir(filme); // Adiciona o filme ao repositório
        return null; // Retorno null indica sucesso
    }
}
