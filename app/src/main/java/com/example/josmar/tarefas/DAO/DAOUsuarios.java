package com.example.josmar.tarefas.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.josmar.tarefas.DBHelper;
import com.example.josmar.tarefas.Modelo.Usuario;

/**
 * Criado por Josmar Saiefert em 03/06/2018.
 */

public class DAOUsuarios {
    private Context context;

    public DAOUsuarios(Context context){
        this.context = context;
    }

    public void cadastra(Usuario usuario) {
        SQLiteDatabase db = DBHelper.getBancoEscrita(context);

        ContentValues dados = new ContentValues();
        dados.put("usuario", usuario.getUsuario());
        dados.put("senha", usuario.getSenha());

        db.insert("Usuarios", null, dados);
    }

    public int VerificaID(String usuario, String senha) {
        SQLiteDatabase db = DBHelper.getBancoLeitura(context);
        String[] params = {usuario, senha};
        try {
            int i = 0;
            Cursor c = null;
            c = db.rawQuery("select * from Usuarios where usuario=? and senha=?", params);
            c.moveToFirst();
            i = c.getInt(c.getColumnIndex("id"));
            c.close();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int verificaSeUsuarioExiste(String usuario) {
        SQLiteDatabase db = DBHelper.getBancoLeitura(context);
        String[] busca = new String[]{usuario};
        try {
            int i = 0;
            Cursor c = null;
            c = db.rawQuery("select * from Usuarios where usuario=?", busca);
            c.moveToFirst();
            i = c.getCount();
            c.close();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
