package com.example.libreflixapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.Libreflix.entidade.Episodio;
import br.com.Libreflix.entidade.Filme;
import br.com.Libreflix.entidade.Serie;
import br.com.Libreflix.entidade.Usuario;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "libreflix.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_FILMES = "CREATE TABLE filmes (id LONG PRIMARY KEY, tags TEXT, ano INTEGER, classificacaoIndicativa INTEGER, diretor TEXT, elenco TEXT,FOREIGN KEY (id) REFERENCES Video (id));";
        db.execSQL(CREATE_TABLE_FILMES);
    }
    public void onCreateEpisodio(SQLiteDatabase db){
        String CREATE_TABLE_EPISODIO = "CREATE TABLE IF NOT EXISTS episodio( id LONG PRIMARY KEY AUTOINCREMENT,videoUri TEXT, titulo TEXT NOT NULL, descricao TEXT, duracao LONG, tipo TEXT NOT NULL CHECK(tipo IN (Filme, Serie)))";
        db.execSQL(CREATE_TABLE_EPISODIO);
    }

    public void onCreateSerie(SQLiteDatabase db){
        String CREATE_TABLE_Serie = "CREATE TABLE IF NOT EXISTS Serie (\n" +
                "                    id LONG PRIMARY KEY,\n" +
                "                    tags TEXT NOT NULL,\n" +
                "                    ano INTEGER,\n" +
                "                    classificacaoIndicativa INTEGER,\n" +
                "                    diretor TEXT,\n" +
                "                    elenco TEXT,\n" +
                "                    temporadas INTEGER NOT NULL,\n" +
                "                    episodios INTEGER NOT NULL,\n" +
                "                    FOREIGN KEY (id) REFERENCES Video (id))";
        db.execSQL(CREATE_TABLE_Serie);
    }

    public void onCreateUsuario(SQLiteDatabase db){
        String CREATE_TABLE_Usuario = "CREATE TABLE IF NOT EXISTS Usuario (\n" +
                "                    email TEXT PRIMARY KEY,\n" +
                "                    userName TEXT NOT NULL,\n" +
                "                    senha TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE_Usuario);
    }
    public void onCreateVistos(SQLiteDatabase db){
        String CREATE_TABLE_Vistos = "CREATE TABLE IF NOT EXISTS Vistos (\n" +
                "            id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "            usuario_email TEXT NOT NULL,\n" +
                "            video_id LONG NOT NULL,\n" +
                "            data_visto DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "            tempo_assistido INTEGER NOT NULL CHECK(tempo_assistido >= 0),\n" +
                "            FOREIGN KEY (usuario_email) REFERENCES Usuario (email),\n" +
                "            FOREIGN KEY (video_id) REFERENCES Video (id)\n" +
                "        );";
        db.execSQL(CREATE_TABLE_Vistos);
    }

    public void onCreateFavoritos(SQLiteDatabase db){
        String CREATE_TABLE_FAVORITOS = "CREATE TABLE IF NOT EXISTS Favoritos (\n" +
                "            id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "            usuario_email TEXT NOT NULL,\n" +
                "            video_id INTEGER NOT NULL,\n" +
                "            data_adicionado DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "            FOREIGN KEY (usuario_email) REFERENCES Usuario (email),\n" +
                "            FOREIGN KEY (video_id) REFERENCES Video (id)\n" +
                "        );";
        db.execSQL(CREATE_TABLE_FAVORITOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS filmes");
        onCreate(db);
    }

    public void adicionarFilme( Filme filme) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", filme.getId());
        values.put("tags", filme.getTags());
        values.put("ano", filme.getAno());
        values.put("classificacaoIndicativa", filme.getClassificacaoIndicativa());
        values.put("diretor", filme.getDiretor());
        values.put("elenco", filme.getElenco());

        db.insert("filmes", null, values);
        db.close();
    }

    public void adicionarEpisodio(Episodio episodio, String tipo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String videoUri = "android.resource://" + context.getPackageName() + "/raw/" + episodio.getTitulo();
        values.put("titulo", episodio.getTitulo());
        values.put("descricao", episodio.getDescricao());
        values.put("duracao", episodio.getDuracao());
        values.put("tipo", tipo);

        db.insert("episodio", null, values);
        db.close();
    }

    public void adicionarSerie( Serie serie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", serie.getId());
        values.put("tags", serie.getTags());
        values.put("ano", serie.getAno());
        values.put("classificacaoIndicativa", serie.getClassificacaoIndicativa());
        values.put("diretor", serie.getDiretor());
        values.put("elenco", serie.getElenco());
        values.put("temporadas", serie.getQntdTemporadas());
        values.put("episodios", serie.getQntdEpisodiosTotais());

        db.insert("Serie", null, values);
        db.close();
    }

    public void adicionarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", usuario.getEmail());
        values.put("userName", usuario.getUserName());
        values.put("senha", usuario.getSenha());

        db.insert("Usuario", null, values);
        db.close();
    }

    public void adicionarVisto(Usuario usuario, Episodio episodio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario_email", usuario.getEmail());
        values.put("video_id", episodio.getId());

        db.insert("Vistos", null, values);
        db.close();
    }

    public void insertFavorito(Usuario usuario, Episodio episodio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario_email", usuario.getEmail());
        values.put("video_id", episodio.getId());

        db.insert("Favoritos", null, values);
        db.close();
    }






    // Método para listar filmes
    /*public List<String> listarFilmes() {
        List<String> filmes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("filmes", new String[]{"titulo", "descricao"}, null, null, null, null, null);

        int tituloIndex = cursor.getColumnIndex("titulo");
        int descricaoIndex = cursor.getColumnIndex("descricao");

        while (cursor.moveToNext()) {
            // Verifica se os índices são válidos
            if (tituloIndex != -1 && descricaoIndex != -1) {
                String titulo = cursor.getString(tituloIndex);
                String descricao = cursor.getString(descricaoIndex);
                filmes.add("Título: " + titulo + "\nDescrição: " + descricao);
            }
        }

        cursor.close();
        db.close();
        return filmes;
    }*/

    public String consultarSerie(String titulo){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT titulo FROM series WHERE titulo = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(titulo)});

        if (cursor.moveToFirst()) {
            String titulo1 = cursor.getString(0);
            cursor.close();
            return titulo1;
        }
        cursor.close();
        return null;
    }

    public String consultarSenhaUsuario(String senha){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT senha FROM usuarios WHERE senha = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(senha)});

        if (cursor.moveToFirst()) {
            String senha1 = cursor.getString(0);
            cursor.close();
            return senha1;
        }
        cursor.close();
        return null;
    }

    public String consultarEmailUsuario(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT email FROM usuarios WHERE email = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(email)});

        if (cursor.moveToFirst()) {
            String email1 = cursor.getString(0);
            cursor.close();
            return email1;
        }
        cursor.close();
        return null;
    }

    public String consultarFilme(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT titulo FROM filmes WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            String titulo = cursor.getString(0);
            cursor.close();
            return titulo;
        }
        cursor.close();
        return null;
    }

    public String consultarFilme(String titulo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT titulo FROM filmes WHERE titulo = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(titulo)});

        if (cursor.moveToFirst()) {
            String titulo1 = cursor.getString(0);
            cursor.close();
            return titulo1;
        }
        cursor.close();
        return null;
    }
    public List<Object> consultaFavoritos(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT V.id, V.titulo, V.descricao, V.duracao, V.tipo,\n" +
                "               F.tags AS filme_tags, F.ano AS filme_ano, F.classificacaoIndicativa AS filme_classificacao,\n" +
                "               F.diretor AS filme_diretor, F.elenco AS filme_elenco,\n" +
                "               S.tags AS serie_tags, S.ano AS serie_ano, S.classificacaoIndicativa AS serie_classificacao,\n" +
                "               S.diretor AS serie_diretor, S.elenco AS serie_elenco,\n" +
                "               S.temporadas AS serie_temporadas, S.episodios AS serie_episodios\n" +
                "        FROM Favoritos FA\n" +
                "        JOIN Video V ON FA.video_id = V.id\n" +
                "        LEFT JOIN Filme F ON V.id = F.id\n" +
                "        LEFT JOIN Serie S ON V.id = S.id\n" +
                "        WHERE FA.usuario_email = ?";

        Cursor cursor = null;
        List<Object> favoritos = new ArrayList<>();

        try {
            cursor = db.rawQuery(query, new String[]{email});

            if (cursor.moveToFirst()) {
                do {
                    String tipo = cursor.getString(cursor.getColumnIndexOrThrow("tipo"));

                    if ("Filme".equalsIgnoreCase(tipo)) {
                        // Criar objeto Filme
                        long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
                        String uriVidio = cursor.getString(cursor.getColumnIndexOrThrow("vidioUri"));
                        String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                        String descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"));
                        long duracao = cursor.getLong(cursor.getColumnIndexOrThrow("duracao"));
                        String tags = cursor.getString(cursor.getColumnIndexOrThrow("filme_tags"));
                        int ano = cursor.getInt(cursor.getColumnIndexOrThrow("filme_ano"));
                        int classificacao = cursor.getInt(cursor.getColumnIndexOrThrow("filme_classificacao"));
                        String diretor = cursor.getString(cursor.getColumnIndexOrThrow("filme_diretor"));
                        String elenco = cursor.getString(cursor.getColumnIndexOrThrow("filme_elenco"));

                        favoritos.add(new Filme(id, uriVidio, titulo, descricao, duracao, tags, ano, classificacao, diretor, elenco));
                    } else if ("Serie".equalsIgnoreCase(tipo)) {
                        // Criar objeto Serie
                        long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
                        String uriVidio = cursor.getString(cursor.getColumnIndexOrThrow("vidioUri"));
                        String tituloEpisodio = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                        String descricaoEpisodio = cursor.getString(cursor.getColumnIndexOrThrow("descricao"));
                        long duracaoEpisodio = cursor.getLong(cursor.getColumnIndexOrThrow("duracao"));
                        String tags = cursor.getString(cursor.getColumnIndexOrThrow("serie_tags"));
                        int ano = cursor.getInt(cursor.getColumnIndexOrThrow("serie_ano"));
                        int classificacao = cursor.getInt(cursor.getColumnIndexOrThrow("serie_classificacao"));
                        String diretor = cursor.getString(cursor.getColumnIndexOrThrow("serie_diretor"));
                        String elenco = cursor.getString(cursor.getColumnIndexOrThrow("serie_elenco"));
                        int temporadas = cursor.getInt(cursor.getColumnIndexOrThrow("serie_temporadas"));
                        int episodios = cursor.getInt(cursor.getColumnIndexOrThrow("serie_episodios"));

                        favoritos.add(new Serie(id, uriVidio, tags, tituloEpisodio, descricaoEpisodio, ano, classificacao, diretor, elenco, temporadas, episodios, tituloEpisodio, descricaoEpisodio, duracaoEpisodio));
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            System.err.println("Erro ao consultar favoritos: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return favoritos;
    }

}