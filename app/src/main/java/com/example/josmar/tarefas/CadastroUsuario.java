package com.example.josmar.tarefas;
import android.widget.EditText;
import com.example.josmar.tarefas.Modelo.Usuario;

/**
 * Criado por Josmar Saiefert em 03/06/2018.
 */

public class CadastroUsuario {

    private final EditText campoUsuario;
    private final EditText campoSenha;

    public CadastroUsuario(CadastroUsuarioActivity activity) {
        campoUsuario = activity.findViewById(R.id.cadastro_usuario);
        campoSenha = activity.findViewById(R.id.cadastro_senha);
    }

    public Usuario bancoUsuario() {
        Usuario usuario = new Usuario();
        usuario.setUsuario(campoUsuario.getText().toString());
        usuario.setSenha(campoSenha.getText().toString());
        return usuario;
    }
}
