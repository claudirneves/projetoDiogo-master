package com.example.josmar.tarefas.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.josmar.tarefas.DBHelper;
import com.example.josmar.tarefas.Modelo.Subtarefas;

import java.util.ArrayList;
import java.util.List;

public class DAOSubtarefas {
    private Context context;

    public DAOSubtarefas(Context context){
        this.context = context;
    }

    public void inserirSubtarefas(Subtarefas subtarefas){
        SQLiteDatabase db = DBHelper.getBancoEscrita(context);
        ContentValues dados = new ContentValues();
        dados.put("descricao", subtarefas.getDescricao());
        dados.put("idTarefa", subtarefas.getIdTarefa());
        dados.put("status", subtarefas.getStatus());

        db.insert("Subtarefas", null, dados);
    }

    public List<Subtarefas> buscaSubtarefas(String idTarefa){
        String[] parametro = {idTarefa};
        String sql = ("SELECT * FROM Subtarefas where idTarefa=?");
        SQLiteDatabase db = DBHelper.getBancoLeitura(context);
        Cursor c = db.rawQuery(sql, parametro);
        List<Subtarefas> subtarefas = new ArrayList<Subtarefas>();

        while (c.moveToNext()){
            Subtarefas subtarefa = new Subtarefas();
            subtarefa.setId(c.getInt(c.getColumnIndex("id")));
            subtarefa.setDescricao(c.getString(c.getColumnIndex("descricao")));
            subtarefa.setIdTarefa(c.getColumnIndex("idTarefa"));
            subtarefa.setStatus(c.getInt(c.getColumnIndex("status")));
            subtarefas.add(subtarefa);
        }
        c.close();
        return subtarefas;
    }
}
