package com.example.libreflixapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.Libreflix.entidade.Filme;

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
        String CREATE_TABLE_FILMES = "CREATE TABLE filmes (id LONG PRIMARY KEY AUTOINCREMENT, titulo TEXT, videoUri TEXT, descricao TEXT, duracao LONG, tags TEXT, ano INTEGER, classificacaoIndicativa INTEGER, diretor TEXT, elenco TEXT)";
        db.execSQL(CREATE_TABLE_FILMES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS filmes");
        onCreate(db);
    }

    public void adicionarFilme(Filme filme) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String videoUri = "android.resource://" + context.getPackageName() + "/raw/" + filme.getTitulo();

        values.put("titulo", filme.getTitulo());
        values.put("videoUri", videoUri);
        values.put("descricao", filme.getDescricao());
        values.put("duracao", filme.getDuracao());
        values.put("tags", filme.getTags());
        values.put("ano", filme.getAno());
        values.put("classificacaoIndicativa", filme.getClassificacaoIndicativa());
        values.put("diretor", filme.getDiretor());
        values.put("elenco", filme.getElenco());

        db.insert("filmes", null, values);
        db.close();
    }

    public void adicionarUsuario(){

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



}