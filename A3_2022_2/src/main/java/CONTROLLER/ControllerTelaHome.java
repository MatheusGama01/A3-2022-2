package CONTROLLER;

import DTO.UsuarioDTO;
import VIEW.AdicionarTarefa;
import VIEW.TelaUsuario;

public class ControllerTelaHome {

    public void navegarParaTelaDeAdicionarTarefa(UsuarioDTO usuario) {
        AdicionarTarefa telaAdicionarTarefa = new AdicionarTarefa(usuario);
        telaAdicionarTarefa.setVisible(true);
    }

    public void navegarParaTelaDeUsuario(UsuarioDTO usuario) {
        TelaUsuario telaUsuario = new TelaUsuario(usuario);
        telaUsuario.setVisible(true);
    }
}
