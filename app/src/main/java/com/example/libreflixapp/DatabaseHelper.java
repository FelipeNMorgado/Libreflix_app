package com.example.libreflixapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.Libreflix.entidade.Filme;
import br.com.Libreflix.entidade.Episodio;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "libreflix2.db";
    private static final int DATABASE_VERSION = 1;

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

        String CREATE_TABLE_EPISODIO = "CREATE TABLE IF NOT EXISTS episodio (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "videoUri TEXT, " +
                "titulo TEXT NOT NULL, " +
                "descricao TEXT, " +
                "duracao INTEGER, " +
                "tipo TEXT NOT NULL" +
                ");";
        db.execSQL(CREATE_TABLE_EPISODIO);

        String CREATE_TABLE_USUARIO = "CREATE TABLE IF NOT EXISTS Usuario (" +
                "email TEXT PRIMARY KEY, " +
                "userName TEXT NOT NULL, " +
                "senha TEXT NOT NULL" +
                ");";
        db.execSQL(CREATE_TABLE_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS filmes");
        db.execSQL("DROP TABLE IF EXISTS episodio");
        db.execSQL("DROP TABLE IF EXISTS Usuario");
        onCreate(db);
    }

    public void adicionarFilme(Filme filme) {
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