package com.example.josmar.tarefas;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.josmar.tarefas.DAO.DAOTarefas;
import com.example.josmar.tarefas.DAO.DAOUsuarios;

/**
 * Criado por Josmar Saiefert em 03/06/2018.
 */

public class TelaLoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.tela_login);
    }

    public void botaoLogar(View view) {

        EditText usuario = findViewById(R.id.tela_login_usuario);
        EditText senha = findViewById(R.id.tela_login_senha);

        String validaUsuario = usuario.getText().toString();
        String validaSenha = senha.getText().toString();

        DAOUsuarios daoUsuarios = new DAOUsuarios(this);
        int idUsuario = daoUsuarios.VerificaID(validaUsuario, validaSenha);


        if (TextUtils.isEmpty(validaUsuario)) {
            usuario.setError("Usuário não pode ser vazio!");
        } else if (TextUtils.isEmpty(validaSenha)) {
            senha.setError("Senha não pode ser vazia!");
        } else if (idUsuario != 0){
            Intent vaiPraLista = new Intent(TelaLoginActivity.this, ListaTarefasActivity.class);
            startActivity(vaiPraLista);

            SharedPreferences preferencias = getSharedPreferences("preferencias_usuario", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.clear();
            editor.putInt("id_usuario", idUsuario);
            editor.commit();

            Toast.makeText(this, "Bem vindo " + validaUsuario, Toast.LENGTH_SHORT).show();

            finish();
        }else {
            Toast.makeText(this, "Usuário ou senha Inválidos", Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastre(View view) {
        Intent vaiProCadastro = new Intent(TelaLoginActivity.this, CadastroUsuarioActivity.class);
        startActivity(vaiProCadastro);
        Toast.makeText(this, "Cadastre!", Toast.LENGTH_SHORT).show();
    }
}
