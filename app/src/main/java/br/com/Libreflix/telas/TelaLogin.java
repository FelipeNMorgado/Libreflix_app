package br.com.Libreflix.telas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.Libreflix.mediators.MediatorUsuario;
import br.com.Libreflix.entidade.Usuario;
import com.example.libreflixapp.R;

public class TelaLogin extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private MediatorUsuario mediatorUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tela_login);

        // Configura o padding para evitar sobreposição com barras de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar campos e MediatorUsuario
        editEmail = findViewById(R.id.editTextTextEmailAddress);
        editSenha = findViewById(R.id.editTextTextSenha);
        mediatorUsuario = new MediatorUsuario(this);

        // Botão "Entrar"
        Button btnEntrar = findViewById(R.id.buttonEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarLogin();
            }
        });

        // Botão "Voltar"
        Button btnVoltar = findViewById(R.id.button7);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaLogin.this, TelaCadastroLogin.class);
                startActivity(intent);
                finish();
            }
        });

        // Botão "Criar Conta"
        Button btnCriarConta = findViewById(R.id.buttonCriarConta);
        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaLogin.this, TelaCadastro.class);
                startActivity(intent);
            }
        });
    }

    private void realizarLogin() {
        String email = editEmail.getText().toString().trim();
        String senha = editSenha.getText().toString().trim();

        if (email.isEmpty() || senha.isEmpty()) {
            mostrarPopUp("Erro", "Por favor, preencha todos os campos.");
            return;
        }

        Usuario usuario = new Usuario(null, email, null, senha);

        String validacao = mediatorUsuario.validarLogIn(usuario);

        if (validacao == null) {
            Intent intent = new Intent(TelaLogin.this, TelaPrincipal.class); // Substitua TelaPrincipal pela tela desejada
            startActivity(intent);
            finish();
        } else if (validacao.equals("Email inválido")) {
            mostrarPopUp("Erro de Login", "O e-mail inserido está incorreto.");
        } else if (validacao.equals("Senha inválida")) {
            mostrarPopUp("Erro de Login", "A senha inserida está incorreta.");
        } else {
            mostrarPopUp("Erro", "Ocorreu um erro inesperado.");
        }
    }

    private void mostrarPopUp(String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}