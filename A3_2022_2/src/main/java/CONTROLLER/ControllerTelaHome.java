package CONTROLLER;

import DTO.UsuarioDTO;
import VIEW.AdicionarTarefa;
import VIEW.TelaUsuario;

public class ControllerTelaHome {

    //Instancia tela AdicionarTarefa e a trona visível
    public void navegarParaTelaDeAdicionarTarefa(UsuarioDTO usuario) {
        AdicionarTarefa telaAdicionarTarefa = new AdicionarTarefa(usuario);
        telaAdicionarTarefa.setVisible(true);
    }

    //Instancia TelaUsuario e a trona visível
    public void navegarParaTelaDeUsuario(UsuarioDTO usuario) {
        TelaUsuario telaUsuario = new TelaUsuario(usuario);
        telaUsuario.setVisible(true);
    }
}
