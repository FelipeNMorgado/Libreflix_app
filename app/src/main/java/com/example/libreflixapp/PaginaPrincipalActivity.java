package com.example.libreflixapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.Libreflix.entidade.Episodio;
import br.com.Libreflix.entidade.Filme;
import br.com.Libreflix.entidade.Usuario;
import br.com.Libreflix.mediators.MediatorFilme;
import br.com.Libreflix.mediators.MediatorUsuario;
import br.com.Libreflix.repositorios.RepositorioUsuario;

public class PaginaPrincipalActivity extends AppCompatActivity {

    private VideoView videoView;
    private int currentPosition = 0;
    private int currentEpisodeIndex = 0;
    private MediatorFilme mf = new MediatorFilme(this);
    private RepositorioUsuario ru = new RepositorioUsuario(this);
    private MediatorUsuario mu = new MediatorUsuario(this);
    private DatabaseHelper dBH = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagina_principal2);


        String episodeName = "qiuwdi";
        String videoUri = "android.resource://" + getPackageName() + "/raw/" + episodeName;
        Filme meuFilme = new Filme(
                191,
                videoUri,
                episodeName,
                "Uma descrição interessante.",
                120L,
                "Ação, Aventura",
                1990,
                12,
                "Nome do Diretor",
                "Ator A, Atriz B, Ator C"
        );
        Usuario meuUsuario = new Usuario(
                "Pluto",
                "Pe@gmail.com",
                "pepe",
                "1234192001934013"

        );
        mu.incluir(meuUsuario);
        mf.incluir(meuFilme);
    }
}