package com.example.josmar.tarefas;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.josmar.tarefas.DAO.DAOTarefas;
import com.example.josmar.tarefas.Modelo.Tarefas;

import java.util.List;

/**
 * Criado por Josmar Saiefert em 03/06/2018.
 */

public class ListaTarefasActivity extends AppCompatActivity {
    ListView listaTarefas;

    public int carregaIdUsuario() {
        SharedPreferences preferencias = getSharedPreferences("preferencias_usuario", Activity.MODE_PRIVATE);
        return preferencias.getInt("id_usuario", 0);
    }

    private void carregaLista() {
        DAOTarefas dao = new DAOTarefas(this);
        List<Tarefas> tarefas = dao.buscaTarefas(Integer.toString(carregaIdUsuario()));

        listaTarefas = findViewById(R.id.lista_tarefas);
        ArrayAdapter<Tarefas> adapter = new ArrayAdapter<Tarefas>(this, android.R.layout.simple_list_item_1, tarefas);
        listaTarefas.setAdapter(adapter);
    }

    public void chamaSubtarefa() {
        listaTarefas = findViewById(R.id.lista_tarefas);
        listaTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Tarefas tarefas = (Tarefas) listaTarefas.getItemAtPosition(posicao);
                DAOTarefas daoTarefas = new DAOTarefas(ListaTarefasActivity.this);
                int idTarefa = tarefas.getId();

                tarefas.getDescricao();

                SharedPreferences preferencias = getSharedPreferences("preferencias_tarefas", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();
                editor.clear();
                editor.putInt("id_tarefa", idTarefa);
                editor.commit();

                Intent vaiParaSubtarefa = new Intent(ListaTarefasActivity.this, ListaSubtarefasActivity.class);
                startActivity(vaiParaSubtarefa);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefas);
        getSupportActionBar().setTitle("Lista de Tarefas");
        chamaSubtarefa();
        carregaLista();
    }

    public void criarTarefa(View view) {
        Intent vaiProCadastro = new Intent(ListaTarefasActivity.this, CadastraTarefasActivity.class);
        startActivity(vaiProCadastro);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

}
