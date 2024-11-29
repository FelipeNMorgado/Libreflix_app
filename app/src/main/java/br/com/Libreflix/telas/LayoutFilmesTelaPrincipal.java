package br.com.Libreflix.telas;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.libreflixapp.R;

import br.com.Libreflix.entidade.Tags;

public class LayoutFilmesTelaPrincipal extends AppCompatActivity {

    private final int[] imagens = {
            R.drawable.tela,
            R.drawable.tela,
            R.drawable.tela
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_filmes_tela_principal);

        // Obter referência ao TextView para exibir as tags
        TextView textViewTags = findViewById(R.id.categoria_do_filme);

        // Adicionar tags dinamicamente
        StringBuilder tagsStringBuilder = new StringBuilder("Categorias: ");
        for (Tags tag : Tags.values()) {
            tagsStringBuilder.append(tag.getTag()).append(", ");
        }

        // Remover a última vírgula e espaço
        if (tagsStringBuilder.length() > 0) {
            tagsStringBuilder.setLength(tagsStringBuilder.length() - 2);
        }

        // Definir o texto dinâmico no TextView
        textViewTags.setText(tagsStringBuilder.toString());

        // Obter o LinearLayout onde as imagens serão adicionadas
        LinearLayout linearLayout = findViewById(R.id.linearLayoutImagens);

        // Adicionar imagens dinamicamente
        for (int imagem : imagens) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imagem);

            // Configurar o tamanho e margens
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300, 300);
            params.setMargins(16, 16, 16, 16); // Margem entre as imagens
            imageView.setLayoutParams(params);

            // Adicionar o ImageView ao LinearLayout
            linearLayout.addView(imageView);
        }
    }
}