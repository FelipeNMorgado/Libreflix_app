package com.example.libreflixapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "libreflix.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_FILMES = "CREATE TABLE filmes (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, descricao TEXT)";
        db.execSQL(CREATE_TABLE_FILMES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS filmes");
        onCreate(db);
    }

    // Método para adicionar um filme
    public void adicionarFilme(String titulo, String descricao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", titulo);
        values.put("descricao", descricao);
        db.insert("filmes", null, values);
        db.close();
    }

    // Método para listar filmes
    public List<String> listarFilmes() {
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
    }
}