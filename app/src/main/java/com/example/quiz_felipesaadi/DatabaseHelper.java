package com.example.quiz_felipesaadi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PLAYERS = "players";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLAYERS_TABLE = "CREATE TABLE " + TABLE_PLAYERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT" + ")";
        db.execSQL(CREATE_PLAYERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        onCreate(db);
    }

    // --- MÉTODOS DO CRUD ---

    // CREATE (Criar)
    public void addPlayer(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        db.insert(TABLE_PLAYERS, null, values);
        db.close();
    }

    // READ (Ler/Listar)
    public Cursor getAllPlayers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PLAYERS, null);
    }

    // UPDATE (Atualizar)
    public void updatePlayer(int id, String newName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, newName);
        db.update(TABLE_PLAYERS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // DELETE (Deletar com reset de ID se a lista ficar vazia)
    public void deletePlayer(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // 1. Deleta o jogador
        db.delete(TABLE_PLAYERS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});

        // 2. Verifica quantos jogadores sobraram
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_PLAYERS, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();

            // 3. Se não sobrou ninguém (tabela vazia), zera o contador do AutoIncrement
            if (count == 0) {
                db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_PLAYERS + "'");
            }
        }

        db.close();
    }
}