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

public class PaginaPrincipalActivity extends AppCompatActivity {

    private VideoView videoView;
    private int currentPosition = 0;
    private int currentEpisodeIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagina_principal2);


        videoView = findViewById(R.id.videoView);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        String episodeName = "video1";

        String videoUri = "android.resource://" + getPackageName() + "/raw/" + episodeName;

        /*Episodio[] episodios = new Episodio[]{
                new Episodio("android.resource://" + getPackageName() + "/raw/" + episodeName),
                new Episodio("android.resource://" + getPackageName() + "/" + R.raw.video2)
        };*/


        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        //String titulo = databaseHelper.getTituloById(3);



        /*if (titulo != null) {
            String videoPath = titulo;

            VideoView videoView = findViewById(R.id.videoView);
            videoView.setVideoURI(Uri.parse(videoPath));
            videoView.requestFocus();
            videoView.start();
        } else {
            Toast.makeText(this, "Título não encontrado!", Toast.LENGTH_SHORT).show();
        }*/


        /*Button btnPrevious = findViewById(R.id.btnPrevious);
        Button btnNext = findViewById(R.id.btnNext);

        // Listener para o botão "Anterior"
        btnPrevious.setOnClickListener(v -> {
            if (currentEpisodeIndex > 0) {
                currentEpisodeIndex--;
                setupVideo(currentEpisodeIndex);
            }
        });

        // Listener para o botão "Próximo"
        btnNext.setOnClickListener(v -> {
            if (currentEpisodeIndex < temporada1.getEpisodios().length - 1) {
                currentEpisodeIndex++;
                setupVideo(currentEpisodeIndex);
            }
        });

        setupVideo(currentEpisodeIndex);


        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("currentPosition", 0);
            currentEpisodeIndex = savedInstanceState.getInt("currentEpisodeIndex", 0);
            setupVideo(currentEpisodeIndex);
            videoView.seekTo(currentPosition);
        }
    }

    private void setupVideo(int episodeIndex) {
        String videoPath = temporada1.getEpisodios()[episodeIndex].getTitulo();
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.requestFocus();
        videoView.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Salvar a posição atual do vídeo
        outState.putInt("currentPosition", videoView.getCurrentPosition());
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Salvar a posição quando a atividade for pausada
        currentPosition = videoView.getCurrentPosition();
        videoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Restaurar a posição apenas se necessário
        if (currentPosition > 0) {
            videoView.seekTo(currentPosition);
            videoView.start();
        }*/
    }
}