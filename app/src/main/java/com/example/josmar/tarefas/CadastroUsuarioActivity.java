package com.example.josmar.tarefas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.josmar.tarefas.DAO.DAOUsuarios;
import com.example.josmar.tarefas.Modelo.Usuario;

/**
 * Criado por Josmar Saiefert em 03/06/2018.
 */

public class CadastroUsuarioActivity extends AppCompatActivity {

    private CadastroUsuario cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        getSupportActionBar().setTitle("Cadastro de Usuários");
        cadastro = new CadastroUsuario(this);
    }

    public void cadastre(View view) {

        EditText campoUsr = findViewById(R.id.cadastro_usuario);
        EditText senha = findViewById(R.id.cadastro_senha);
        EditText confirmaSenha = findViewById(R.id.cadastro_confirma_senha);

        String validaUsuario = campoUsr.getText().toString();
        String validaSenha = senha.getText().toString();
        String validaSenha2 = confirmaSenha.getText().toString();

        DAOUsuarios daoUsuarios = new DAOUsuarios(this);
        int verifica = daoUsuarios.verificaSeUsuarioExiste(validaUsuario);

        if (verifica != 0) {
            campoUsr.setError("Usuário já existe!");
            return;
        } else if (TextUtils.isEmpty(validaUsuario)) {
            campoUsr.setError("Usuário não pode ser vazio!");
            return;
        } else if (TextUtils.isEmpty(validaSenha)) {
            senha.setError("Senha não pode ser vazia!");
            return;
        } else if (TextUtils.isEmpty(validaSenha2)) {
            confirmaSenha.setError("Senha não pode ser vazia!");
            return;
        } else if (TextUtils.isEmpty(validaSenha) && TextUtils.isEmpty(validaSenha2)) {
            senha.setError("Senha não pode ser vazia!");
            confirmaSenha.setError("Senha não pode ser vazia!");
            return;
        } else if (validaSenha.equals(validaSenha2)) {
            Usuario usuario = cadastro.bancoUsuario();
            DAOUsuarios dao = new DAOUsuarios(this);
            dao.cadastra(usuario);
            Toast.makeText(this, "Usuário Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "ISHE!", Toast.LENGTH_SHORT).show();
            senha.setError("Senhas não coincidem!");
            confirmaSenha.setError("Senhas não coincidem!");
            return;
        }
    }
}
