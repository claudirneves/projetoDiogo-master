package com.example.josmar.tarefas;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.josmar.tarefas.DAO.DAOTarefas;
import com.example.josmar.tarefas.Modelo.Tarefas;


public class CadastraTarefasActivity extends AppCompatActivity {

    public int carregaIdUsuario() {
        SharedPreferences preferencias = getSharedPreferences("preferencias_usuario", Activity.MODE_PRIVATE);
        return preferencias.getInt("id_usuario", 0);
    }

    private CadastraTarefas cadastraTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_tarefas);
        getSupportActionBar().setTitle("Cadastro de Tarefas");
        cadastraTarefas = new CadastraTarefas(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_criar_tarefa, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_ok:
                EditText campoDesc = findViewById(R.id.cadastra_tarefas_descricao);
                String validaDesc = campoDesc.getText().toString();

                if (TextUtils.isEmpty(validaDesc)) {
                    campoDesc.setError("Descrição não pode ser vazia!");
                } else {
                    Tarefas tarefas = cadastraTarefas.bancoUsuario();
                    DAOTarefas dao = new DAOTarefas(this);
                    dao.insereTarefa(tarefas);

                    Toast.makeText(CadastraTarefasActivity.this, "Tarefa " + tarefas.getDescricao() + " Salva!", Toast.LENGTH_SHORT).show();

                    finish();
                    break;
                }
        }

        return super.onOptionsItemSelected(item);
    }
}
