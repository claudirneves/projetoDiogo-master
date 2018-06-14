package com.example.josmar.tarefas;

import android.widget.CheckBox;
import android.widget.EditText;

import com.example.josmar.tarefas.Modelo.Subtarefas;

/**
 * Created by Claudir on 09/06/2018.
 */

public class CadastraSubtarefas {
    private  EditText campoDescricao;
    private CheckBox campoStatus;

    CadastraSubtarefasActivity lista;

    public CadastraSubtarefas(CadastraSubtarefasActivity activity){
        lista = activity;
        campoDescricao = activity.findViewById(R.id.cadastra_subtarefa_descricao);

    }

    public Subtarefas bancoTarefa(){
        Subtarefas subtarefas = new Subtarefas();
        subtarefas.setIdTarefa(lista.carregaIdTarefa());
        subtarefas.setDescricao(campoDescricao.getText().toString());
        return subtarefas;
    }

}
