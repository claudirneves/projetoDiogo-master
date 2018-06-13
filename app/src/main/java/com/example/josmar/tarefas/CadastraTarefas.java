package com.example.josmar.tarefas;

import android.widget.EditText;

import com.example.josmar.tarefas.Modelo.Tarefas;

public class CadastraTarefas {
    private final EditText campoDescricao;
    private final EditText campoVencimento;

    CadastraTarefasActivity lista;

    public CadastraTarefas(CadastraTarefasActivity activity) {
        lista = activity;
        campoDescricao = activity.findViewById(R.id.cadastra_tarefas_descricao);
        campoVencimento = activity.findViewById(R.id.cadastra_tarefas_vencimento);
    }

    public Tarefas bancoUsuario() {
        Tarefas tarefas = new Tarefas();
        tarefas.setIdUsuario(lista.carregaIdUsuario());
        tarefas.setDescricao(campoDescricao.getText().toString());
        tarefas.setDataVencimento(campoVencimento.getText().toString());
        return tarefas;
    }
}
