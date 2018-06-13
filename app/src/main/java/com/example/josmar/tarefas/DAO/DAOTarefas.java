package com.example.josmar.tarefas.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.josmar.tarefas.DBHelper;
import com.example.josmar.tarefas.Modelo.Tarefas;

import java.util.ArrayList;
import java.util.List;

public class DAOTarefas {
    private Context context;

    public DAOTarefas(Context context){
            this.context = context;
        }

        public void insereTarefa(Tarefas tarefas) {
        SQLiteDatabase db = DBHelper.getBancoEscrita(context);

        ContentValues dados = new ContentValues();
        dados.put("descricao", tarefas.getDescricao());
        dados.put("idUsuario", tarefas.getIdUsuario());
//      dados.put("vencimento", tarefas.getIdUsuario());

        db.insert("Tarefas", null, dados);
    }

    public int VerificaIdTarefa(String tarefa) {
        SQLiteDatabase db = DBHelper.getBancoLeitura(context);
        String[] params = {tarefa};
        try {
            int i = 0;
            Cursor c = null;
            c = db.rawQuery("select * from Tarefas where tarefa=?", params);
            c.moveToFirst();
            i = c.getInt(c.getColumnIndex("id"));
            c.close();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Tarefas> buscaTarefas(String idUsr) {
        String[] parametro = {idUsr};
        String sql = ("SELECT * FROM Tarefas WHERE idUsuario=?");
        SQLiteDatabase db = DBHelper.getBancoLeitura(context);
        Cursor c = db.rawQuery(sql,parametro);
        List<Tarefas> tarefas = new ArrayList<Tarefas>();

        while (c.moveToNext()){
            Tarefas tarefa = new Tarefas();
            tarefa.setId(c.getInt(c.getColumnIndex("id")));
            tarefa.setIdUsuario(c.getColumnIndex("idUsuario"));
            tarefa.setDescricao(c.getString(c.getColumnIndex("descricao")));

            tarefas.add(tarefa);
        }

        c.close();
        return tarefas;
    }
}

