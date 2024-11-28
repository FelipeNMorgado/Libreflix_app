package br.com.Libreflix.telas;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.Libreflix.entidade.Usuario;
import br.com.Libreflix.mediators.MediatorUsuario;
import com.example.libreflixapp.R;

public class TelaCadastro extends AppCompatActivity {

    private MediatorUsuario mediatorUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuração do layout
        setContentView(R.layout.activity_tela_cadastro);

        // Inicializar o MediatorUsuario
        mediatorUsuario = new MediatorUsuario(this);

        // Capturar referências das views
        EditText nomeField = findViewById(R.id.editTextTextNome);
        EditText emailField = findViewById(R.id.editTextTextEmailAddress);
        EditText usernameField = findViewById(R.id.editTextTextUsername);
        EditText senhaField = findViewById(R.id.editTextTextSenha);
        Button criarContaButton = findViewById(R.id.button2);
        Button btnVoltar = findViewById(R.id.button7);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ir para a TelaCadastroLogin
                Intent intent = new Intent(TelaCadastro.this, TelaCadastroLogin.class);
                startActivity(intent);
                finish(); // Finaliza a activity atual para não ficar no backstack
            }
        });

        Button btnLogin = findViewById(R.id.button);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ir para a TelaCadastroLogin
                Intent intent = new Intent(TelaCadastro.this, TelaLogin.class);
                startActivity(intent);
                finish(); // Finaliza a activity atual para não ficar no backstack
            }
        });

        // Configurar evento de clique no botão "Criar uma conta"
        criarContaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeField.getText().toString().trim();
                String email = emailField.getText().toString().trim();
                String username = usernameField.getText().toString().trim();
                String senha = senhaField.getText().toString().trim();

                // Criar um objeto Usuario
                Usuario usuario = new Usuario(nome, email, username, senha);

                // Tentar incluir o usuário no Mediator
                String mensagemValidacao = mediatorUsuario.validarCadastro(usuario);

                if (mensagemValidacao != null) {
                    // Exibir mensagem de erro em um pop-up
                    exibirPopUpErro(mensagemValidacao);
                } else {
                    // Inclusão bem-sucedida
                    String mensagemInclusao = mediatorUsuario.incluir(usuario);
                    if (mensagemInclusao != null) {
                        exibirPopUpErro(mensagemInclusao);
                    } else {
                        exibirPopUpSucesso("Conta criada com sucesso!");
                    }
                }
            }
        });

    }

    // Método para exibir pop-up de erro
    private void exibirPopUpErro(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Erro");
        builder.setMessage(mensagem);
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    // Método para exibir pop-up de sucesso
    private void exibirPopUpSucesso(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sucesso");
        builder.setMessage(mensagem);
        builder.setPositiveButton("OK", (dialog, which) -> {
            // Iniciar a nova Activity TelaLogin
            Intent intent = new Intent(TelaCadastro.this, TelaLogin.class);
            startActivity(intent);

            // Finalizar a TelaCadastro para remover do stack
            finish();
        });
        builder.show();
    }

}