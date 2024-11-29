package com.example.libreflixapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.media.MediaPlayer;
import android.widget.MediaController;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.Libreflix.entidade.Filme;

public class FilmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

        // Configuração para as tags
        LinearLayout tagsLayout = findViewById(R.id.TagsLayout);

        // Configurações do VideoView e botão
        VideoView videoView = findViewById(R.id.videoView5);
        Button playVideoButton = findViewById(R.id.button9);

        // Recebe o caminho do vídeo (supõe que o vídeo seja passado pela intent)
        String videoPath = getIntent().getStringExtra("videoPath");

        // Instância do banco de dados
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Supõe que o caminho da imagem ou filme seja passado pela intent para buscar o filme
        String imagemRecebida = getIntent().getStringExtra("imagemRecebida");

        // Busca o filme usando a imagem recebida
        Filme filme = dbHelper.buscarFilmePorImagem(imagemRecebida);

        if (filme != null) {
            // Atualiza a interface com os dados do filme
            atualizarInterfaceComFilme(filme, tagsLayout);
        } else {
            // Mostra mensagem de erro se nenhum filme foi encontrado
            TextView titleTextView = findViewById(R.id.text_title);
            titleTextView.setText("Filme não encontrado");
        }

        // Fecha o banco de dados
        db.close();

        // Configura o botão para rodar o vídeo
        playVideoButton.setOnClickListener(v -> {
            setContentView(R.layout.activity_plano_fundo_video); // Substitua com o layout do player

            VideoView videoView1 = findViewById(R.id.videoView); // Elemento do novo layout
            String videoUri = "android.resource://" + getPackageName() + "/raw/" + "video1";
            videoView1.setVideoPath(videoUri);

            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView1);
            videoView1.setMediaController(mediaController);

            videoView1.start();

            videoView1.setOnCompletionListener(mp -> {
                Toast.makeText(FilmeActivity.this, "Vídeo finalizado", Toast.LENGTH_SHORT).show();
            });
        });
    }

    private void atualizarInterfaceComFilme(Filme filme, LinearLayout tagsLayout) {
        // Encontrando os elementos no layout
        ImageView imageView = findViewById(R.id.imageView3);
        TextView titleTextView = findViewById(R.id.text_title);
        TextView yearDurationTextView = findViewById(R.id.text_year_duration);
        TextView descriptionTextView = findViewById(R.id.text_description);

        // Carrega a imagem
        String imagePath = filme.getImagem();
        if (imagePath.startsWith("android.resource://")) {
            try {
                int imageResId = getResources().getIdentifier(
                        imagePath.substring(imagePath.lastIndexOf("/") + 1),
                        "drawable",
                        getPackageName()
                );
                imageView.setImageResource(imageResId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Define as informações no layout
        titleTextView.setText(filme.getTitulo());
        yearDurationTextView.setText(filme.getAno() + " • " + filme.getDuracao() + " min");
        descriptionTextView.setText(filme.getDescricao());

        // Adiciona botões para as tags
        tagsLayout.removeAllViews(); // Limpa as tags existentes
        for (String tag : filme.getTags().split(",")) {
            Button botaoTag = new Button(this);
            botaoTag.setText(tag.trim());
            // Não é necessário definir cor nos botões conforme pedido
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 8, 8, 8);
            botaoTag.setLayoutParams(params);
            tagsLayout.addView(botaoTag);
        }
    }
}
