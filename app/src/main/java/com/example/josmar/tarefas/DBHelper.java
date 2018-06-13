package com.example.josmar.tarefas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    static DBHelper instance;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static DBHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DBHelper(context, "Aplicativo", null,1);
        }
        return instance;
    }

    public static SQLiteDatabase getBancoLeitura(Context context){
        return getInstance(context).getReadableDatabase();
    }

    public static SQLiteDatabase getBancoEscrita(Context context){
        return getInstance(context).getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Tarefas (id INTEGER PRIMARY KEY, descricao TEXT NOT NULL, " +
                "idUsuario INTEGER , FOREIGN KEY(idUsuario) REFERENCES Usuarios(id));";
        db.execSQL(sql);
        sql = "CREATE TABLE Usuarios (id INTEGER PRIMARY KEY, usuario TEXT NOT NULL, senha TEXT NOT NULL);";
        db.execSQL(sql);
        sql = "CREATE TABLE Subtarefas (id INTEGER PRIMARY KEY, descricao TEXT NOT NULL, " +
                " status, INTEGER, idTarefa INTEGER, FOREIGN KEY(idTarefa) REFERENCES Tarefas(id));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
