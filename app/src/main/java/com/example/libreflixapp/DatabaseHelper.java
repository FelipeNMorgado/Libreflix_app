package com.example.libreflixapp;

import static java.security.AccessController.getContext;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "libreflix.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        copyDatabase(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        copyDatabase(context);

        String CREATE_TABLE_FILMES = "CREATE TABLE filmes (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, descricao TEXT)";
        db.execSQL(CREATE_TABLE_FILMES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS filmes");
        onCreate(db);
    }

    // Método para copiar o banco de dados
    private void copyDatabase(Context context) {
        try {
            String destPath = context.getDatabasePath(DATABASE_NAME).getPath();
            File dbFile = new File(destPath);

            if (!dbFile.exists()) { // Se o banco não existe, copia-o
                InputStream input = context.getAssets().open(DATABASE_NAME);
                OutputStream output = new FileOutputStream(destPath);

                byte[] buffer = new byte[1024];
                int length;

                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }

                output.flush();
                output.close();
                input.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

