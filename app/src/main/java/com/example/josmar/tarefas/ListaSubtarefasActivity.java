package com.example.josmar.tarefas;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.josmar.tarefas.DAO.DAOSubtarefas;
import com.example.josmar.tarefas.Modelo.Subtarefas;

import java.util.List;

public class ListaSubtarefasActivity extends AppCompatActivity {
    ListView listaSubtarefas;

    public int carregaIdtarefa(){
        SharedPreferences preferences = getSharedPreferences("preferencias_tarefas", Activity.MODE_PRIVATE);
        return preferences.getInt("id_tarefa",0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_subtarefas);
        getSupportActionBar().setTitle("Lista de Subtarefas");
        carregaLista();

    }



    private void carregaLista(){
        DAOSubtarefas daoSubtarefas = new DAOSubtarefas(this);
        List<Subtarefas> subtarefas = daoSubtarefas.buscaSubtarefas(Integer.toString(carregaIdtarefa()));

        listaSubtarefas = findViewById(R.id.lista_subtarefas);
        ArrayAdapter<Subtarefas> adapter = new ArrayAdapter<Subtarefas>(this,android.R.layout.simple_list_item_1, subtarefas);
        listaSubtarefas.setAdapter(adapter);
    }

    public void criarSubtarefa(View view) {
       Intent vaiParaCadastro = new Intent(ListaSubtarefasActivity.this, CadastraSubtarefasActivity.class);
       startActivity(vaiParaCadastro);
    }

    @Override
    protected void onResume(){
        super.onResume();
        carregaLista();
    }

}
