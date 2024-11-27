package com.example.libreflixapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DatabaseHelperActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText editTextTitulo;
    private EditText editTextDescricao;
    private ListView listViewFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_helper);

        databaseHelper = new DatabaseHelper(this);

        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextDescricao = findViewById(R.id.editTextDescricao);
        listViewFilmes = findViewById(R.id.listViewFilmes);
        Button buttonAdicionar = findViewById(R.id.buttonAdicionar);

        // Configura o botÃ£o para adicionar um filme ao banco de dados
        buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = editTextTitulo.getText().toString();
                String descricao = editTextDescricao.getText().toString();

                if (!titulo.isEmpty() && !descricao.isEmpty()) {
                   // databaseHelper.adicionarFilme(titulo, descricao);
                   // atualizarListaFilmes();
                    editTextTitulo.setText("");
                    editTextDescricao.setText("");
                }
            }
        });

        // Atualiza a lista ao abrir o app
        //atualizarListaFilmes();
    }

    /*private void atualizarListaFilmes() {
        List<String> filmes = databaseHelper.listarFilmes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filmes);
        listViewFilmes.setAdapter(adapter);
    }*/
}