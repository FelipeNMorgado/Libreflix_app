package br.com.Libreflix.telas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log; // Importação para logs
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libreflixapp.R;

import br.com.Libreflix.entidade.Filme;
import br.com.Libreflix.entidade.Serie;
import br.com.Libreflix.mediators.MediatorFilme;
import br.com.Libreflix.mediators.MediatorSerie;
import com.example.libreflixapp.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class TelaPesquisa extends AppCompatActivity {

    private static final String TAG = "TelaPesquisa"; // Identificador para os logs
    private EditText busca;
    private Button btnBuscar;
    private MediatorFilme mediatorFilme;
    private MediatorSerie mediatorSerie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            // Ativa Edge-to-Edge e define o layout
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_tela_pesquisa);

            // Configura o padding para evitar sobreposição com barras de sistema
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            btnBuscar = findViewById(R.id.button8);
            busca = findViewById(R.id.search_bar);

            btnBuscar.setOnClickListener(view -> {
                try {
                    realizarBusca();
                } catch (Exception e) {
                    Log.e(TAG, "Erro ao realizar busca", e);
                    Toast.makeText(this, "Erro ao realizar busca: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            // Configura os botões de navegação
            Button btnNavegar1 = findViewById(R.id.button5);
            btnNavegar1.setOnClickListener(view -> navegarPara(TelaCadastroLogin.class));

            Button btnNavegar2 = findViewById(R.id.button2);
            btnNavegar2.setOnClickListener(view -> navegarPara(TelaPrincipal.class));


        } catch (Exception e) {
            Log.e(TAG, "Erro ao inicializar a tela", e);
            Toast.makeText(this, "Erro ao inicializar a tela: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void realizarBusca() {
        String termoBusca = busca.getText().toString().trim();
        DatabaseHelper dbh = new DatabaseHelper(this);

        if (termoBusca.isEmpty()) {
            Toast.makeText(this, "Por favor, insira um termo de busca", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            List<Filme> filmes = dbh.getAllFilmes();
            List<Serie> series = dbh.consultarSeries(termoBusca);

            if (filmes.isEmpty() && series.isEmpty()) {
                Toast.makeText(this, "Nenhum filme ou série encontrado", Toast.LENGTH_SHORT).show();
                return;
            }

            List<Object> resultados = new ArrayList<>();
            resultados.addAll(filmes);
            resultados.addAll(series);

            RecyclerView recyclerView = findViewById(R.id.recyclerViewResultados);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ResultadoAdapter adapter = new ResultadoAdapter(resultados);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            Log.e(TAG, "Erro ao buscar filmes ou séries", e);
            Toast.makeText(this, "Erro ao buscar filmes ou séries: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

   private void navegarPara(Class<?> destino) {
        try {
            Intent intent = new Intent(TelaPesquisa.this, destino);
            startActivity(intent);
            finish(); // Finaliza a tela atual
        } catch (Exception e) {
            Log.e(TAG, "Erro ao navegar para " + destino.getSimpleName(), e);
            Toast.makeText(this, "Erro ao navegar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}