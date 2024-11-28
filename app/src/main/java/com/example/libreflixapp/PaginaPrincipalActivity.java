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
import br.com.Libreflix.entidade.Serie;
import br.com.Libreflix.entidade.Filme;
import br.com.Libreflix.entidade.Usuario;
import br.com.Libreflix.mediators.MediatorFilme;
import br.com.Libreflix.mediators.MediatorSerie;
import br.com.Libreflix.mediators.MediatorUsuario;
import br.com.Libreflix.repositorios.RepositorioSerie;
import br.com.Libreflix.repositorios.RepositorioUsuario;

public class PaginaPrincipalActivity extends AppCompatActivity {

    private VideoView videoView;
    private int currentPosition = 0;
    private int currentEpisodeIndex = 0;
    private Serie minhaSerie;
    private MediatorFilme mf = new MediatorFilme(this);
    private RepositorioUsuario ru = new RepositorioUsuario(this);
    private MediatorUsuario mu = new MediatorUsuario(this);
    private DatabaseHelper dBH = new DatabaseHelper(this);
    private RepositorioSerie rs = new RepositorioSerie(this);
    private MediatorSerie ms = new MediatorSerie(this);

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
        // Criando uma instância da classe Serie
        Serie minhaSerie = new Serie(
                70, // id
                "https://exemplo.com/video.mp4", // uriVidio
                "Ação, Aventura", // tags
                "Uma Série Fantástica", // tituloSerie
                "A história de um grupo de amigos enfrentando desafios incríveis.", // descricaoSerie
                2024, // ano
                16, // classificacaoIndicativa
                "João Diretor", // diretor
                "Ana Ator, Pedro Ator, João Ator", // elenco
                3, // qntdTemporadas
                30, // qntdEpisodiosTotais
                "P", // tituloEpisodio
                "Introdução aos personagens e ao universo.", // descicaoEpisodio
                3600L // duracaoEpisodio em segundos
        );

        dBH.favoritar(meuUsuario, minhaSerie);
        dBH.assistir(meuUsuario, minhaSerie);

        //ms.incluir(minhaSerie);
        //mu.incluir(meuUsuario);
        //mf.incluir(meuFilme);
    }
}