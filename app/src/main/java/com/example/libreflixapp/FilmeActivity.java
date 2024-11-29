package com.example.libreflixapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import br.com.Libreflix.entidade.Filme;

public class FilmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);  // Nome do seu layout XML

        // Criando uma instância de Filme (simulando um filme que você recebeu de outra parte do código)
        String imagem = "android.resource://" + getPackageName() + "/drawable/tela"; // Caminho da imagem no recurso
        String videoUri = "android.resource://" + getPackageName() + "/raw/" + "episodeName";

        Filme meuFilme = new Filme(
                150,
                imagem,
                videoUri,
                "Nome do Episódio",
                "Hotel Laide foi um dos mais importantes hotéis sociais...",
                120L,
                "Ação, Aventura",
                1990,
                12,
                "Nome do Diretor",
                "Ator A, Atriz B, Ator C"
        );

        // Encontrando os elementos no layout
        ImageView imageView = findViewById(R.id.imageView3);
        TextView titleTextView = findViewById(R.id.text_title);
        TextView yearDurationTextView = findViewById(R.id.text_year_duration);
        TextView descriptionTextView = findViewById(R.id.text_description);

        // Carregando a imagem (se for uma imagem local)
        String imagePath = meuFilme.getImagem(); // Caminho da imagem
        if (imagePath.startsWith("android.resource://")) {
            try {
                String packageName = getPackageName();
                int imageResId = getResources().getIdentifier(imagePath.substring(imagePath.lastIndexOf("/") + 1), "drawable", packageName);
                imageView.setImageResource(imageResId); // Carrega a imagem do recurso drawable
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Setando as informações no layout
        titleTextView.setText(meuFilme.getTitulo()); // Título do episódio
        yearDurationTextView.setText(meuFilme.getAno() + " • " + meuFilme.getDuracao() + " min"); // Ano e duração
        descriptionTextView.setText(meuFilme.getDescricao()); // Descrição

        // Se você quiser configurar os botões, pode fazer da seguinte forma:
        Button watchButton = findViewById(R.id.button_watch);
        Button favoriteButton = findViewById(R.id.button_favorite);
        Button rateButton = findViewById(R.id.button_rate);

        watchButton.setOnClickListener(v -> {
            // Ação para assistir
        });

        favoriteButton.setOnClickListener(v -> {
            // Ação para favoritar
        });

        rateButton.setOnClickListener(v -> {
            // Ação para rotular
        });
    }
}