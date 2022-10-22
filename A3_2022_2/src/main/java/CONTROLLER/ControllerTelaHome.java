package CONTROLLER;

import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import DAO.TarefaDAO;
import VIEW.AdicionarTarefa;
import VIEW.TelaTarefa;
import VIEW.TelaUsuario;
import java.util.ArrayList;

public class ControllerTelaHome {

    //Instancia tela AdicionarTarefa e a torna visível.
    public void navegarParaTelaDeAdicionarTarefa(UsuarioDTO usuario) {
        AdicionarTarefa telaAdicionarTarefa = new AdicionarTarefa(usuario);
        telaAdicionarTarefa.setVisible(true);
    }

    //Instancia TelaUsuario e a torna visível.
    public void navegarParaTelaDeUsuario(UsuarioDTO usuario) {
        TelaUsuario telaUsuario = new TelaUsuario(usuario);
        telaUsuario.setVisible(true);
    }

    public void navegarParaTelaTarefa(int idTarefa, String descricao, String statusRecebido, UsuarioDTO usuario) {
        Boolean status;

        if (statusRecebido == "Feita") {
            status = true;
        } else {
            status = false;
        }

        TarefaDTO tarefa = new TarefaDTO(idTarefa, descricao, status);

        TelaTarefa telaTarefa = new TelaTarefa(tarefa, usuario);
        telaTarefa.setVisible(true);
    }

    public ArrayList listarTarefas(UsuarioDTO usuario) {
        TarefaDAO tarefaDAO = new TarefaDAO();
        ArrayList<TarefaDTO> listaDeTarefas = new ArrayList<>();

        for (TarefaDTO tarefa : tarefaDAO.listarTarefas(usuario)) {
            listaDeTarefas.add(tarefa);
        }

        return listaDeTarefas;
    }

    //Cria um ArrayList apenas com as tarefas à fazer.
    public ArrayList listarTarefasAFazer(UsuarioDTO usuario) {
        TarefaDAO tarefaDAO = new TarefaDAO();
        ArrayList<TarefaDTO> listaDeTarefasAFazer = new ArrayList<>();

        /**
         * Verifica cada tarefa salva no banco e inseri na listaDeTarefasAFazer
         * apenas as que estão com status: false (à fazer).
         */
        for (TarefaDTO tarefa : tarefaDAO.listarTarefas(usuario)) {
            if (tarefa.getStatus() == false) {
                listaDeTarefasAFazer.add(tarefa);
            }
        }

        return listaDeTarefasAFazer;
    }

    //Cria um ArrayList apenas com as tarefas feitas.
    public ArrayList listarTarefasFeitas(UsuarioDTO usuario) {
        TarefaDAO tarefaDAO = new TarefaDAO();
        ArrayList<TarefaDTO> listaDeTarefasFeitas = new ArrayList<>();

        /**
         * Verifica cada tarefa salva no banco e inseri na listaDeTarefasFeitas
         * apenas as que estão com status: true (feitas).
         */
        for (TarefaDTO tarefa : tarefaDAO.listarTarefas(usuario)) {
            if (tarefa.getStatus() == true) {
                listaDeTarefasFeitas.add(tarefa);
            }
        }

        return listaDeTarefasFeitas;
    }
}
