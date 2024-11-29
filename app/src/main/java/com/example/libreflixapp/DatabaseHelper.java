package com.example.libreflixapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.Libreflix.entidade.Filme;
import br.com.Libreflix.entidade.Episodio;
import br.com.Libreflix.entidade.Serie;
import br.com.Libreflix.entidade.Usuario;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "libreflix2.db";
    private static final int DATABASE_VERSION = 7;

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

        String CREATE_TABLE_FAVORITO = "CREATE TABLE Favorito (" +
                "emailUsuario TEXT NOT NULL, " +
                "userName TEXT NOT NULL, " +
                "idSerieFilme TEXT NOT NULL, " +
                "nomeSerieFilme TEXT NOT NULL " +
                ");";
        db.execSQL(CREATE_TABLE_FAVORITO);

        String CREATE_TABLE_ASSISTIR = "CREATE TABLE Assistir (" +
                "emailUsuario TEXT NOT NULL, " +
                "userName TEXT NOT NULL, " +
                "idSerieFilme TEXT NOT NULL, " +
                "nomeSerieFilme TEXT NOT NULL " +
                ");";
        db.execSQL(CREATE_TABLE_ASSISTIR);

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
        db.execSQL("DROP TABLE IF EXISTS Favorito");
        db.execSQL("DROP TABLE IF EXISTS Assistir");
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

    public void assistir(Usuario usuario, Filme filme){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues assistirValues = new ContentValues();
        assistirValues.put("emailUsuario", usuario.getEmail());
        assistirValues.put("userName", usuario.getUserName());
        assistirValues.put("idSerieFilme", filme.getId());
        assistirValues.put("nomeSerieFilme", filme.getTitulo());

        db.insert("Assistir", null, assistirValues);
        db.close();
    }

    public void assistir(Usuario usuario, Serie serie){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues assistirValues = new ContentValues();
        assistirValues.put("emailUsuario", usuario.getEmail());
        assistirValues.put("userName", usuario.getUserName());
        assistirValues.put("idSerieFilme", serie.getId());
        assistirValues.put("nomeSerieFilme", serie.getTituloSerie());

        db.insert("Assistir", null, assistirValues);
        db.close();
    }

    public void favoritar(Usuario usuario, Filme filme){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues favoritarValues = new ContentValues();
        favoritarValues.put("emailUsuario", usuario.getEmail());
        favoritarValues.put("userName", usuario.getUserName());
        favoritarValues.put("idSerieFilme", filme.getId());
        favoritarValues.put("nomeSerieFilme", filme.getTitulo());

        db.insert("Favorito", null, favoritarValues);
        db.close();
    }

    public void favoritar(Usuario usuario, Serie serie){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues favoritarValues = new ContentValues();
        favoritarValues.put("emailUsuario", usuario.getEmail());
        favoritarValues.put("userName", usuario.getUserName());
        favoritarValues.put("idSerieFilme", serie.getId());
        favoritarValues.put("nomeSerieFilme", serie.getTituloSerie());

        db.insert("Favorito", null, favoritarValues);
        db.close();
    }

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
    public List<Filme> getAllFilmes() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Filme> filmes = new ArrayList<>();

        String query = "SELECT id, tags, ano, classificacaoIndicativa, diretor, elenco FROM filmes";

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    String tags = cursor.getString(cursor.getColumnIndexOrThrow("tags"));
                    int ano = cursor.getInt(cursor.getColumnIndexOrThrow("ano"));
                    int classificacaoIndicativa = cursor.getInt(cursor.getColumnIndexOrThrow("classificacaoIndicativa"));
                    String diretor = cursor.getString(cursor.getColumnIndexOrThrow("diretor"));
                    String elenco = cursor.getString(cursor.getColumnIndexOrThrow("elenco"));

                    // Buscar informações adicionais do filme na tabela episodio
                    Cursor episodioCursor = db.rawQuery("SELECT titulo, descricao, duracao, videoUri FROM episodio WHERE id = ?", new String[]{String.valueOf(id)});
                    String titulo = null;
                    String descricao = null;
                    long duracao = 0L;
                    String videoUri = null;

                    if (episodioCursor != null && episodioCursor.moveToFirst()) {
                        titulo = episodioCursor.getString(episodioCursor.getColumnIndexOrThrow("titulo"));
                        descricao = episodioCursor.getString(episodioCursor.getColumnIndexOrThrow("descricao"));
                        duracao = episodioCursor.getLong(episodioCursor.getColumnIndexOrThrow("duracao"));
                        videoUri = episodioCursor.getString(episodioCursor.getColumnIndexOrThrow("videoUri"));
                        episodioCursor.close();
                    }

                    // Criar o objeto Filme com os dados recuperados
                    Filme filme = new Filme(
                            id,
                            videoUri, // URI do vídeo do filme
                            titulo, // Título do filme
                            descricao, // Descrição do filme
                            duracao, // Duração do filme
                            tags,
                            ano,
                            classificacaoIndicativa,
                            diretor,
                            elenco
                    );

                    filmes.add(filme);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace(); // Registrar exceção
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return filmes;
    }

    public List<Filme> getFilmesByTitulo(String tituloBusca) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Filme> filmes = new ArrayList<>();

        // Consulta SQL com filtro pelo título
        String query = "SELECT f.id, f.tags, f.ano, f.classificacaoIndicativa, f.diretor, f.elenco, e.titulo, e.descricao, e.duracao, e.videoUri " +
                "FROM filmes f " +
                "LEFT JOIN episodio e ON f.id = e.id " +
                "WHERE e.titulo LIKE ?";

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, new String[]{"%" + tituloBusca + "%"});

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    String tags = cursor.getString(cursor.getColumnIndexOrThrow("tags"));
                    int ano = cursor.getInt(cursor.getColumnIndexOrThrow("ano"));
                    int classificacaoIndicativa = cursor.getInt(cursor.getColumnIndexOrThrow("classificacaoIndicativa"));
                    String diretor = cursor.getString(cursor.getColumnIndexOrThrow("diretor"));
                    String elenco = cursor.getString(cursor.getColumnIndexOrThrow("elenco"));

                    String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                    String descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"));
                    long duracao = cursor.getLong(cursor.getColumnIndexOrThrow("duracao"));
                    String videoUri = cursor.getString(cursor.getColumnIndexOrThrow("videoUri"));

                    // Criar o objeto Filme com os dados recuperados
                    Filme filme = new Filme(
                            id,
                            videoUri,
                            titulo,
                            descricao,
                            duracao,
                            tags,
                            ano,
                            classificacaoIndicativa,
                            diretor,
                            elenco
                    );

                    filmes.add(filme);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace(); // Registrar exceção para depuração
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return filmes;
    }

    // Consultar filmes no banco de dados
    public List<Filme> consultarFilmes(String termoBusca) {
        List<Filme> filmes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("filmes", null, "titulo LIKE ?", new String[]{"%" + termoBusca + "%"}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Verifique se a coluna realmente existe antes de acessar
                int idIndex = cursor.getColumnIndex("id");
                int tagsIndex = cursor.getColumnIndex("tags");
                int tituloIndex = cursor.getColumnIndex("titulo");
                int descricaoIndex = cursor.getColumnIndex("descricao");
                int duracaoIndex = cursor.getColumnIndex("duracao");
                int generoIndex = cursor.getColumnIndex("genero");
                int anoIndex = cursor.getColumnIndex("ano");
                int faixaEtariaIndex = cursor.getColumnIndex("faixa_etaria");
                int usuarioInclusaoIndex = cursor.getColumnIndex("usuario_inclusao");
                int usuarioUltimaAlteracaoIndex = cursor.getColumnIndex("usuario_ultima_alteracao");

                if (idIndex != -1 && tagsIndex != -1 && tituloIndex != -1) {
                    Filme filme = new Filme(
                            cursor.getInt(idIndex),
                            cursor.getString(tagsIndex),
                            cursor.getString(tituloIndex),
                            cursor.getString(descricaoIndex),
                            cursor.getInt(duracaoIndex),
                            cursor.getString(generoIndex),
                            cursor.getInt(anoIndex),
                            cursor.getInt(faixaEtariaIndex),
                            cursor.getString(usuarioInclusaoIndex),
                            cursor.getString(usuarioUltimaAlteracaoIndex)
                    );
                    filmes.add(filme);
                }
            } while (cursor.moveToNext());
        }
        if (cursor != null) cursor.close();
        return filmes;
    }


    // Consultar séries no banco de dados
    public List<Serie> consultarSeries(String termoBusca) {
        List<Serie> series = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Consulta na tabela "Serie" para obter as séries que correspondem ao termo de busca
        Cursor cursor = db.query("Serie", null, "tituloSerie LIKE ?", new String[]{"%" + termoBusca + "%"}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Verifica os índices das colunas no Cursor
                int idIndex = cursor.getColumnIndex("id");
                int uriVideoIndex = cursor.getColumnIndex("videoUri");  // URI do vídeo
                int tagsIndex = cursor.getColumnIndex("tags");
                int tituloSerieIndex = cursor.getColumnIndex("tituloSerie");
                int descricaoSerieIndex = cursor.getColumnIndex("descricaoSerie");
                int anoIndex = cursor.getColumnIndex("ano");
                int classificacaoIndicativaIndex = cursor.getColumnIndex("classificacaoIndicativa");
                int diretorIndex = cursor.getColumnIndex("diretor");
                int elencoIndex = cursor.getColumnIndex("elenco");
                int qntdTemporadasIndex = cursor.getColumnIndex("qntdTemporadas");
                int qntdEpisodiosTotaisIndex = cursor.getColumnIndex("qntdEpisodiosTotais"); // Total de episódios
                int tituloEpisodioIndex = cursor.getColumnIndex("titulo"); // Título do episódio
                int descricaoEpisodioIndex = cursor.getColumnIndex("descricao"); // Descrição do episódio
                int duracaoEpisodioIndex = cursor.getColumnIndex("duracao"); // Duração do episódio

                // Verifica se todas as colunas necessárias existem
                if (idIndex != -1 && uriVideoIndex != -1 && tituloSerieIndex != -1 && descricaoSerieIndex != -1) {
                    // Cria um novo objeto Serie com os valores obtidos do Cursor
                    Serie serie = new Serie(
                            cursor.getInt(idIndex),  // id
                            cursor.getString(uriVideoIndex),  // uriVidio
                            cursor.getString(tagsIndex),  // tags
                            cursor.getString(tituloSerieIndex),  // tituloSerie
                            cursor.getString(descricaoSerieIndex),  // descricaoSerie
                            cursor.getInt(anoIndex),  // ano
                            cursor.getInt(classificacaoIndicativaIndex),  // classificacaoIndicativa
                            cursor.getString(diretorIndex),  // diretor
                            cursor.getString(elencoIndex),  // elenco
                            cursor.getInt(qntdTemporadasIndex),  // qntdTemporadas
                            cursor.getInt(qntdEpisodiosTotaisIndex), // qntdEpisodiosTotais
                            cursor.getString(tituloEpisodioIndex),  // tituloEpisodio
                            cursor.getString(descricaoEpisodioIndex),  // descricaoEpisodio
                            cursor.getLong(duracaoEpisodioIndex)  // duracaoEpisodio
                    );
                    // Adiciona a série à lista
                    series.add(serie);
                }
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();  // Fecha o cursor
        }

        return series;
    }


}