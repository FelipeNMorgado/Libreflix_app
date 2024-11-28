package com.example.libreflixapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.Libreflix.entidade.Filme;
import br.com.Libreflix.entidade.Episodio;
import br.com.Libreflix.entidade.Serie;
import br.com.Libreflix.entidade.Usuario;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "libreflix2.db";
    private static final int DATABASE_VERSION = 5;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_FILMES = "CREATE TABLE filmes (" +
                "id INTEGER PRIMARY KEY, " +
                "tags TEXT, " +
                "ano INTEGER, " +
                "classificacaoIndicativa INTEGER, " +
                "diretor TEXT, " +
                "elenco TEXT" +
                ");";
        db.execSQL(CREATE_TABLE_FILMES);

        String CREATE_TABLE_EPISODIO = "CREATE TABLE episodio (" +
                "id INTEGER PRIMARY KEY, " +
                "videoUri TEXT, " +
                "titulo TEXT NOT NULL, " +
                "descricao TEXT, " +
                "duracao INTEGER, " +
                "tipo TEXT NOT NULL" +
                ");";
        db.execSQL(CREATE_TABLE_EPISODIO);

        String CREATE_TABLE_USUARIO = "CREATE TABLE Usuario (" +
                "email TEXT PRIMARY KEY, " +
                "userName TEXT NOT NULL, " +
                "senha TEXT NOT NULL" +
                ");";
        db.execSQL(CREATE_TABLE_USUARIO);

        String CREATE_TABLE_SERIE = "CREATE TABLE Serie (" +
                "id INTEGER PRIMARY KEY, " +
                "tituloSerie TEXT NOT NULL, " +
                "descricaoSerie TEXT NOT NULL, " +
                "tags TEXT NOT NULL, " +
                "ano INTEGER NOT NULL, " +
                "classificacaoIndicativa INTEGER NOT NULL, " +
                "elenco TEXT NOT NULL, " +
                "diretor TEXT NOT NULL, " +
                "qntdTemporadas INTEGER NOT NULL" +
                ");";
        db.execSQL(CREATE_TABLE_SERIE);

        /*String CREATE_TABLE_SERIEEP = "CREATE TABLE SerieEp (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idSerie INTEGER NOT NULL, " +
                "ano INTEGER NOT NULL, " +
                "classifcacaoIndicativa INTEGER NOT NULL, " +
                "elenco TEXT NOT NULL, " +
                "direitor TEXT NOT NULL, " +
                "qntdTemporadas INTEGER NOT NULL" +
                ");";
        db.execSQL(CREATE_TABLE_SERIEEP);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS filmes");
        db.execSQL("DROP TABLE IF EXISTS episodio");
        db.execSQL("DROP TABLE IF EXISTS Usuario");
        db.execSQL("DROP TABLE IF EXISTS Serie");
        onCreate(db);
    }

    /*public void adicionarFilme(Filme filme) {
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
    }*/

    public void adicionarFilme(Filme filme) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Inserindo o filme na tabela "filmes"
        ContentValues filmeValues = new ContentValues();
        filmeValues.put("id", filme.getId());
        filmeValues.put("tags", filme.getTags());
        filmeValues.put("ano", filme.getAno());
        filmeValues.put("classificacaoIndicativa", filme.getClassificacaoIndicativa());
        filmeValues.put("diretor", filme.getDiretor());
        filmeValues.put("elenco", filme.getElenco());

        db.insert("filmes", null, filmeValues);

        // Inserindo o episódio correspondente na tabela "episodio"
        ContentValues episodioValues = new ContentValues();
        episodioValues.put("id", filme.getId());
        episodioValues.put("videoUri", filme.getUriVideo()); // Usando URI do vídeo do filme
        episodioValues.put("titulo", filme.getTitulo()); // Título do filme como título do episódio
        episodioValues.put("descricao", filme.getDescricao()); // Descrição do filme
        episodioValues.put("duracao", filme.getDuracao()); // Duração do filme
        episodioValues.put("tipo", "Filme"); // Tipo fixo como "Filme" (ou outro, conforme necessidade)

        db.insert("episodio", null, episodioValues);

        db.close();
    }

    public void adicionarUsuario(Usuario usuario){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues usuarioValues = new ContentValues();
        usuarioValues.put("email", usuario.getEmail());
        usuarioValues.put("userName", usuario.getUserName());
        usuarioValues.put("senha", usuario.getSenha());

        db.insert("usuario", null,usuarioValues);

        db.close();
    }

    public void adicionarSerie(Serie serie){
        SQLiteDatabase db = this.getWritableDatabase();

        // Inserindo o filme na tabela "filmes"
        ContentValues serieValues = new ContentValues();
        serieValues.put("id", serie.getId());
        serieValues.put("tituloSerie", serie.getTituloSerie());
        serieValues.put("descricaoSerie", serie.getDescricaoSerie());
        serieValues.put("tags", serie.getTags());
        serieValues.put("ano", serie.getAno());
        serieValues.put("classificacaoIndicativa", serie.getClassificacaoIndicativa());
        serieValues.put("elenco", serie.getElenco());
        serieValues.put("diretor", serie.getDiretor());
        serieValues.put("qntdTemporadas", serie.getQntdTemporadas());

        db.insert("Serie", null, serieValues);

        // Inserindo o episódio correspondente na tabela "episodio"
        ContentValues episodioValues = new ContentValues();
        episodioValues.put("id", serie.getId());
        episodioValues.put("videoUri", serie.getUriVideo()); // Usando URI do vídeo do filme
        episodioValues.put("titulo", serie.getTitulo()); // Título do filme como título do episódio
        episodioValues.put("descricao", serie.getDescricao()); // Descrição do filme
        episodioValues.put("duracao", serie.getDuracao()); // Duração do filme
        episodioValues.put("tipo", "Serie"); // Tipo fixo como "Filme" (ou outro, conforme necessidade)

        db.insert("episodio", null, episodioValues);

        db.close();
    }

    public String consultarEmailUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT email FROM Usuario WHERE email = ?", new String[]{usuario.getEmail()});
        if (cursor.moveToFirst()) {
            String emailEncontrado = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            cursor.close();
            return emailEncontrado;  // Email encontrado
        }
        cursor.close();
        return null;  // Email não encontrado
    }

    public String consultarSenhaUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT senha FROM Usuario WHERE email = ?", new String[]{usuario.getEmail()});
        if (cursor.moveToFirst()) {
            String senhaEncontrada = cursor.getString(cursor.getColumnIndexOrThrow("senha"));
            cursor.close();
            if (senhaEncontrada.equals(usuario.getSenha())) {
                return senhaEncontrada;  // Senha correta
            }
        }
        cursor.close();
        return null;  // Senha incorreta
    }

    public String consultarFilmePorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM filmes WHERE id = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            cursor.close();
            return "ID encontrado"; // Indicando que o ID já existe
        }
        cursor.close();
        return null; // Caso contrário, retornamos null
    }

    public String consultarSerie(String titulo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT titulo FROM episodio WHERE titulo = ?", new String[]{titulo});
        if (cursor.moveToFirst()) {
            String resultado = cursor.getString(0);
            cursor.close();
            return resultado;
        }
        cursor.close();
        return null;
    }

    public String consultarFilme(String titulo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT titulo FROM filmes WHERE titulo = ?", new String[]{titulo});
        if (cursor.moveToFirst()) {
            String resultado = cursor.getString(0);
            cursor.close();
            return resultado;
        }
        cursor.close();
        return null;
    }
}