package com.example.josmar.tarefas;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.josmar.tarefas.DAO.DAOSubtarefas;
import com.example.josmar.tarefas.Modelo.Subtarefas;

public class CadastraSubtarefasActivity extends AppCompatActivity {
    CheckBox status;
    int statusBanco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Cadastro de Subtarefas");
        setContentView(R.layout.activity_cadastra_subtarefas);
        cadastraSubtarefas = new CadastraSubtarefas(this);

    }

    public int carregaIdTarefa(){
        SharedPreferences preferences = getSharedPreferences("preferencia_tarefa", Activity.MODE_PRIVATE);
        return preferences.getInt("id_tarefa",0);

    }

    private CadastraSubtarefas cadastraSubtarefas;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_criar_subtarefa, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_Sub_ok:
                EditText campoDesc = findViewById(R.id.cadastra_subtarefa_descricao);
                status =  findViewById(R.id.status_subtarefa);
                String validaDesc = campoDesc.getText().toString();
                boolean validaStatus = status.isSelected();
                if(TextUtils.isEmpty(validaDesc)){
                    campoDesc.setError("Descrição não poder ser vazia!");
                }else if(validaStatus){
                    status.setError("Não pode cadastrar uma subtarefa como feita!!");
                }else{
                    Subtarefas subtarefas = cadastraSubtarefas.bancoTarefa();
                    DAOSubtarefas daoSubtarefas = new DAOSubtarefas(this);
                    daoSubtarefas.inserirSubtarefas(subtarefas);
                    Toast.makeText(CadastraSubtarefasActivity.this,"Subtarefa " +
                            subtarefas.getDescricao() + " Salva!", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
