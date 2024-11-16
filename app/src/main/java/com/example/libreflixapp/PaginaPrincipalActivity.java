package com.example.libreflixapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaginaPrincipalActivity extends AppCompatActivity {

    private VideoView videoView;
    private Button playButton, pauseButton, forwardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagina_principal2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        videoView = findViewById(R.id.videoView);
        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        forwardButton = findViewById(R.id.forwardButton);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video1;
        videoView.setVideoURI(Uri.parse(videoPath));

        // Iniciar o vídeo
        videoView.requestFocus();

        // Play button
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();  // Iniciar o vídeo
            }
        });

        // Pause button
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.pause();  // Pausar o vídeo
            }
        });

        // Forward button
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Avançar 10 segundos
                int currentPosition = videoView.getCurrentPosition();
                videoView.seekTo(currentPosition + 10000);
            }
        });




    }
}