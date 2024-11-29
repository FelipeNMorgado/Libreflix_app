package br.com.Libreflix.telas;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.libreflixapp.DatabaseHelper;
import com.example.libreflixapp.FilmeActivity;
import com.example.libreflixapp.R;

public class TelaPrincipal extends AppCompatActivity {

    private final int[] imagens1 = {
            R.drawable.aeducacaoproibida,
            R.drawable.observareabsorver,
    };

    private final int[] imagens2 = {
            R.drawable.quebrandootabu,
            R.drawable.temposmodernos,
    };

    private final int[] imagens3 = {
            R.drawable.embuscadaverdade,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ativa Edge-to-Edge e define o layout
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagina_principal);

        // Configura o padding para evitar sobreposição com barras de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configura os botões de navegação
        configurarBotoes();

        // Adicionar imagens dinamicamente
        configurarImagens();
    }

    private void configurarBotoes() {
        // Botão para navegar para a tela de pesquisa
        Button btnNavegar1 = findViewById(R.id.button3);
        btnNavegar1.setOnClickListener(view -> {
            Intent intent = new Intent(TelaPrincipal.this, TelaPesquisa.class);
            startActivity(intent);
        });

        // Botão para navegar para a tela de cadastro/login
        Button btnNavegar2 = findViewById(R.id.button5);
        btnNavegar2.setOnClickListener(view -> {
            Intent intent = new Intent(TelaPrincipal.this, TelaCadastroLogin.class);
            startActivity(intent);
        });
    }

    private void configurarImagens() {
        adicionarImagensDinamicamente(R.id.linearLayoutImagens1, imagens1);
        adicionarImagensDinamicamente(R.id.linearLayoutImagens2, imagens2);
        adicionarImagensDinamicamente(R.id.linearLayoutImagens3, imagens3);
    }

    private void adicionarImagensDinamicamente(int layoutId, int[] imagens) {
        LinearLayout linearLayout = findViewById(layoutId);

        for (int imagem : imagens) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imagem);

            // Configurar tamanho e margens
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300, 300);
            params.setMargins(35, 20, 35, 20); // Margens entre as imagens
            imageView.setLayoutParams(params);

            imageView.setOnClickListener(view -> {
                Intent intent = new Intent(TelaPrincipal.this, FilmeActivity.class);

                // Converter o recurso de imagem para o formato esperado no banco
                String imagemResId = "android.resource://" + getPackageName() + "/drawable/" +
                        getResources().getResourceEntryName(imagem);
                intent.putExtra("imagemRecebida", imagemResId);

                startActivity(intent);
            });

            // Adicionar o ImageView ao layout
            linearLayout.addView(imageView);
        }
    }
}
