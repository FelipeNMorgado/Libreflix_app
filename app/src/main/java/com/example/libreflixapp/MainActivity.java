package com.example.libreflixapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.Libreflix.entidade.Filme;
import br.com.Libreflix.telas.LayoutFilmesTelaPrincipal;
import br.com.Libreflix.telas.TelaCadastro;
import br.com.Libreflix.telas.TelaCadastroLogin;
import br.com.Libreflix.telas.TelaLogin;
import br.com.Libreflix.telas.TelaPesquisa;
import br.com.Libreflix.telas.TelaPrincipal;
import br.com.Libreflix.entidade.Episodio;
import com.example.libreflixapp.DatabaseHelper;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        new Handler().postDelayed(() -> {

            Intent intent = new Intent(MainActivity.this, PaginaPrincipalActivity.class);
            startActivity(intent);

            finish();
        }, 1000);
    }


}