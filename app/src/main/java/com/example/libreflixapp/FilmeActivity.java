package com.example.libreflixapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;

import br.com.Libreflix.entidade.Filme;

public class FilmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

        LinearLayout tagsLayout = findViewById(R.id.TagsLayout);

        // Recebe o caminho da imagem da intent
        String imagemRecebida = getIntent().getStringExtra("imagemRecebida");

        // Inicializa o banco de dados
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Busca o filme no banco de dados usando a imagem recebida
        Filme filme = dbHelper.buscarFilmePorImagem(imagemRecebida);

        if (filme != null) {
            // Atualiza a interface com os dados do filme
            atualizarInterfaceComFilme(filme, tagsLayout);
        } else {
            // Exibe mensagem de erro se nenhum filme for encontrado
            exibirMensagemDeErro();
        }

        // Fecha o banco de dados
        db.close();
    }

    private void atualizarInterfaceComFilme(Filme filme, LinearLayout tagsLayout) {
        // Encontrando os elementos no layout
        ImageView imageView = findViewById(R.id.imageView3);
        TextView titleTextView = findViewById(R.id.text_title);
        TextView yearDurationTextView = findViewById(R.id.text_year_duration);
        TextView descriptionTextView = findViewById(R.id.text_description);

        // Configura a imagem no ImageView
        configurarImagem(imageView, filme.getImagem());

        // Define as informações nos TextViews
        titleTextView.setText(filme.getTitulo());
        yearDurationTextView.setText(String.format("%s • %s min", filme.getAno(), filme.getDuracao()));
        descriptionTextView.setText(filme.getDescricao());

        // Adiciona botões para as tags
        adicionarTagsDinamicamente(tagsLayout, filme.getTags());
    }

    private void configurarImagem(ImageView imageView, String imagePath) {
        // Verifica e carrega a imagem usando o caminho
        if (imagePath != null && imagePath.startsWith("android.resource://")) {
            try {
                String resourceName = imagePath.substring(imagePath.lastIndexOf("/") + 1);
                int imageResId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
                if (imageResId != 0) {
                    imageView.setImageResource(imageResId);
                }
            } catch (Exception e) {
                e.printStackTrace();
                 // Imagem de fallback em caso de erro
            }
        }
    }

    private void adicionarTagsDinamicamente(LinearLayout tagsLayout, String tags) {
        // Limpa as tags existentes
        tagsLayout.removeAllViews();

        if (tags != null && !tags.isEmpty()) {
            for (String tag : tags.split(",")) {
                Button botaoTag = new Button(this);
                botaoTag.setText(tag.trim());

                // Configura o estilo do botão
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

    private void exibirMensagemDeErro() {
        TextView titleTextView = findViewById(R.id.text_title);
        titleTextView.setText("Filme não encontrado");
        TextView yearDurationTextView = findViewById(R.id.text_year_duration);
        yearDurationTextView.setText("");
        TextView descriptionTextView = findViewById(R.id.text_description);
        descriptionTextView.setText("Verifique os dados e tente novamente.");
    }
}
