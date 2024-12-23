package br.com.Libreflix.telas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.libreflixapp.R;

public class TelaCadastroLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ativa Edge-to-Edge e define o layout
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_cadastro_login);

        // Configura o padding para evitar sobreposição com barras de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configura o botão
        Button btnNavegar1 = findViewById(R.id.myButton1);
        btnNavegar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cria o Intent para ir para a OutraActivity
                Intent intent = new Intent(TelaCadastroLogin.this, TelaPrincipal.class);
                startActivity(intent);
            }
        });

        // Configura o botão
        Button btnNavegar2 = findViewById(R.id.myButton2);
        btnNavegar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cria o Intent para ir para a OutraActivity
                Intent intent = new Intent(TelaCadastroLogin.this, TelaLogin.class);
                startActivity(intent);
            }
        });

        // Configura o botão
        Button btnNavegar3 = findViewById(R.id.myButton3);
        btnNavegar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cria o Intent para ir para a OutraActivity
                Intent intent = new Intent(TelaCadastroLogin.this, TelaCadastro.class);
                startActivity(intent);
            }
        });

    }
}