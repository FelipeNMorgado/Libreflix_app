package br.com.Libreflix.mediators;

import android.content.Context;
import br.com.Libreflix.repositorios.RepositorioUsuario;
import br.com.Libreflix.entidade.Usuario;

public class MediatorUsuario {

    private Usuario usuario;
    private RepositorioUsuario ru;

    public MediatorUsuario(Context context) {
        this.ru = new RepositorioUsuario(context);
    }

    public String validarCadastro(Usuario usuario) {

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            return "Por favor, informe um nome";
        }

        if (ru.consultarEmail(usuario) != null) {
            return "Esse email já existe";
        }

        if (usuario.getUserName() == null || usuario.getUserName().trim().isEmpty()) {
            return "Por favor, informe seu username";
        }

        if (usuario.getUserName().length() > 25) {
            return "Username extenso demais";
        }

        if (usuario.getSenha() == null || usuario.getSenha().length() < 8) {
            return "A senha deve ter pelo menos 8 caracteres";
        }

        return null;  // Se todas as validações passarem
    }

    public String validarLogIn(Usuario usuario){
        if(ru.consultarEmail(usuario) == null){
            return "Email inválido";
        }if(ru.consultarSenha(usuario) == null){
            return "Senha inválida";
        }
        else{
            return null;
        }
    }

    public String incluir(Usuario usuario){
        if(validarCadastro(usuario) != null){
            return validarCadastro(usuario);
        }else{
            ru.incluir(usuario);
            return null;
        }
    }
}