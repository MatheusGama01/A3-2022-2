package CONTROLLER;

import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import DAO.TarefaDAO;
import VIEW.TelaUsuario;
import VIEW.TelaHome;

public class ControllerTelaTarefa {

    //Instancia TelaUsuario e a torna visível
    public void navegarParaTelaDeUsuario(UsuarioDTO usuario) {
        TelaUsuario telaUsuario = new TelaUsuario(usuario);
        telaUsuario.setVisible(true);
    }

    //Instancia TelaHome e a torna visível
    public void navegarParaTelaHome(UsuarioDTO usuario) {
        TelaHome telaHome = new TelaHome(usuario);
        telaHome.setVisible(true);
    }

    //Verifica se há alteração na tarefa, caso sim chama  método atualizarTarefa de TarefaDAO
    public void salvarEdicao(TarefaDTO tarefa, String descricao, String status, UsuarioDTO usuario) {
        if (descricao == tarefa.getDescricao() && status == tarefa.getStatus()) {
            navegarParaTelaHome(usuario);
        } else {
            TarefaDAO novaTarefa = new TarefaDAO();

            tarefa.setDescricao(descricao);
            tarefa.setStatus(status);

            novaTarefa.atualizarTarefa(tarefa);
        }
    }
}
