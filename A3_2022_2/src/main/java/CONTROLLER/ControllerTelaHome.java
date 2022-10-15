package CONTROLLER;

import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import DAO.TarefaDAO;
import VIEW.AdicionarTarefa;
import VIEW.TelaUsuario;
import java.util.ArrayList;

public class ControllerTelaHome {

    //Instancia tela AdicionarTarefa e a torna visível    
    public void navegarParaTelaDeAdicionarTarefa(UsuarioDTO usuario) {
        AdicionarTarefa telaAdicionarTarefa = new AdicionarTarefa(usuario);
        telaAdicionarTarefa.setVisible(true);
    }

    //Instancia TelaUsuario e a torna visível
    public void navegarParaTelaDeUsuario(UsuarioDTO usuario) {
        TelaUsuario telaUsuario = new TelaUsuario(usuario);
        telaUsuario.setVisible(true);
    }

    //Cria um ArrayList apenas com as tarefas à fazer
    public ArrayList listarTarefasAFazer() {
        TarefaDAO tarefaDAO = new TarefaDAO();
        ArrayList<TarefaDTO> listaDeTarefasAFazer = new ArrayList<>();

        //Verifica cada tarefa salva no banco e inseri na listaDeTarefasAFazer apenas as que estão com status: À fazer
        for (TarefaDTO tarefa : tarefaDAO.listarTarefas()) {
            if (tarefa.getStatus() == "À fazer") {
                listaDeTarefasAFazer.add(tarefa);
            }
        }

        return listaDeTarefasAFazer;
    }

    //Cria um ArrayList apenas com as tarefas feitas    
    public ArrayList listarTarefasFeitas() {
        TarefaDAO tarefaDAO = new TarefaDAO();
        ArrayList<TarefaDTO> listaDeTarefasFeitas = new ArrayList<>();

        //Verifica cada tarefa salva no banco e inseri na listaDeTarefasFeitas apenas as que estão com status: feitas
        for (TarefaDTO tarefa : tarefaDAO.listarTarefas()) {
            if (tarefa.getStatus() == "Feitas") {
                listaDeTarefasFeitas.add(tarefa);
            }
        }

        return listaDeTarefasFeitas;
    }
}
