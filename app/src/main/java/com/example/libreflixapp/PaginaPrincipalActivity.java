package com.example.libreflixapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import br.com.Libreflix.telas.LayoutFilmesTelaPrincipal;
import br.com.Libreflix.telas.TelaPesquisa;
import br.com.Libreflix.telas.TelaPrincipal;

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

        String episodeName1 = "A Educacao Proibida";
        String imagem1 = "android.resource://" + getPackageName() + "/drawable/aeducacaoproibida";
        String videoUri1 = "android.resource://" + getPackageName() + "/raw/" + episodeName1;
        Filme meuFilme1 = new Filme(
                1,
                imagem1,
                videoUri1,
                episodeName1,
                "Documentário que se propõe a questionar as lógicas da escolarização moderna e a forma de entender a educação, mostrando diferentes experiências educativas, não convencionais, que propõem a necessidade de um novo modelo educativo.",
                50,
                "Documentário",
                2012,
                0,
                "Germán Doin",
                "Gastón Pauls, Santiago Magariños, Jimena Del Pozo, Tulio Gómez Álzaga, Amira Adre, Ezequiel Ábila, Eugenia Saraseni, Paula González, Nicolás Valenzuela, Alejandra Figueroa"

        );

        String episodeName2 = "Quebrando o Tabu";
        String imagem2 = "android.resource://" + getPackageName() + "/drawable/quebrandootabu";
        String videoUri2 = "android.resource://" + getPackageName() + "/raw/" + episodeName2;
        Filme meuFilme2 = new Filme(
                2,
                imagem2,
                videoUri2,
                episodeName2,
                "O filme propõe um debate sério e bem informado sobre o complexo problema das drogas no Brasil e no mundo. Participações de Fernando Henrique Cardoso, Bill Clinton, Jimmy Carter, Drauzio Varella e Paulo Coelho.",
                120,
                "Documentário",
                2011,
                14,
                "Desconhecido",
                "Morgan Freeman, Bill Clinton, Fernando Henrique Cardoso, Drauzio Varella"

        );

        String episodeName3 = "Observar e Absorver";
        String imagem3 = "android.resource://" + getPackageName() + "/drawable/observareabsorver";
        String videoUri3 = "android.resource://" + getPackageName() + "/raw/" + episodeName3;
        Filme meuFilme3 = new Filme(
                3,
                imagem3,
                videoUri3,
                episodeName3,
                "Eu sou extremamente ambicioso. Eu sou ambicioso de uma forma que ninguém pode conceber. Porque dinheiro, conforto, estabilidade, luxo, pra mim é pouco, eu quero mais. Eu quero tudo que eu puder levar dessa vida",
                150,
                "Documentário",
                2016,
                12,
                "José Marques de Carvalho Jr",
                "Morgan Freeman, Bill Clinton, Fernando Henrique Cardoso, Drauzio Varella"

        );

        String episodeName4 = "Tempos Modernos";
        String imagem4 = "android.resource://" + getPackageName() + "/drawable/temposmodernos";
        String videoUri4 = "android.resource://" + getPackageName() + "/raw/" + episodeName4;
        Filme meuFilme4 = new Filme(
                4,
                imagem3,
                videoUri3,
                episodeName3,
                "Tempos Modernos é um filme de 1936 dos Estados Unidos do cineasta Charlie Chaplin em que o seu famoso personagem (O Vagabundo) tenta sobreviver em meio ao mundo moderno e industrializado.",
                150,
                "Documentário",
                1936,
                0,
                "Charlie Chaplin",
                "Charlie Chaplin"

        );

        String episodeName5 = "Em Busca da Verdade";
        String imagem5 = "android.resource://" + getPackageName() + "/drawable/embuscadaverdade";
        String videoUri5 = "android.resource://" + getPackageName() + "/raw/" + episodeName5;
        Serie minhaSerie5 = new Serie(
                5,
                imagem3,
                videoUri3,
                episodeName3,
                "Tempos Modernos",
                "Documentário apresenta as principais investigações da Comissão Nacional e das Comissões Estaduais da Verdade sobre as graves violações de direitos humanos ocorridas na ditadura de 1964.",
                2015,
                10,
                "Deraldo Goulart",
                "Guilherme Oliveira, Lorena Maria",
                1,
                2,
                "Tomada do Governo",
                "Tomando as rédias do Governo, missão difícil e árdua",
                60

        );

        // Criando uma instância da classe Serie
        /*Serie minhaSerie = new Serie(
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
        );*/
        new Handler().postDelayed(() -> {

            Intent intent = new Intent(PaginaPrincipalActivity.this, TelaPrincipal.class);
            startActivity(intent);

            finish();
        }, 500);

        mf.incluir(meuFilme1);
        mf.incluir(meuFilme2);
        mf.incluir(meuFilme3);
        mf.incluir(meuFilme4);
        ms.incluir(minhaSerie5);
    }
}