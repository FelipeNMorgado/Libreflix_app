package br.com.Libreflix.mediators;

import br.com.Libreflix.repositorios.RepositorioUsuario;
import br.com.Libreflix.entidade.Usuario;

public class MediatorUsuario {

    private Usuario usuario;
    private RepositorioUsuario ru = new RepositorioUsuario();

    public String validarCadastro(Usuario usuario){

        if(usuario.getNome() == null && usuario.getNome().trim().isEmpty()){
            return "Por favor informe um nome";
        }if(ru.consultarEmail(usuario.getEmail()) != null){
            return "Esse email já existe";
        }if(usuario.getUserName() == null && usuario.getUserName().trim().isEmpty()){
            return "Por favor informe seu username";
        }if(usuario.getUserName().length() > 25){
            return "Username extenso demais";
        }if(usuario.getSenha().length() < 8){
            return "Pelo menos 8 caracteres";
        }
        else{
            return null;
        }
    }

    public String validarLogIn(Usuario usuario){
        if(ru.consultarEmail(usuario.getEmail()) == null){
            return "Email inválido";
        }if(ru.consultarSenha(usuario.getSenha()) == null){
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