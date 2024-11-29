package br.com.Libreflix.telas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.Libreflix.entidade.Filme;
import br.com.Libreflix.entidade.Serie;
import com.example.libreflixapp.R;

public class ResultadoAdapter extends RecyclerView.Adapter<ResultadoAdapter.ViewHolder> {

    private final List<Object> resultados;

    public ResultadoAdapter(List<Object> resultados) {
        this.resultados = resultados;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate o layout para cada item da lista (assuma que você tem um layout específico chamado "item_resultado")
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_resultado, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Object item = resultados.get(position);

        if (item instanceof Filme) {
            Filme filme = (Filme) item;
            holder.titulo.setText(filme.getTitulo());
            holder.descricao.setText("Filme: " + filme.getDescricao()); // Exemplo de uso de outro atributo
        } else if (item instanceof Serie) {
            Serie serie = (Serie) item;
            holder.titulo.setText(serie.getTitulo());
            holder.descricao.setText("Série: " + serie.getDescricao()); // Exemplo de uso de outro atributo
        }
    }

    @Override
    public int getItemCount() {
        return resultados.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titulo;
        TextView descricao; // Novo campo para informações adicionais

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textTitulo);
            descricao = itemView.findViewById(R.id.textDescricao);
        }
    }
}