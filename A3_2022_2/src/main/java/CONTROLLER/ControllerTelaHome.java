package CONTROLLER;

import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import DAO.TarefaDAO;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuario;
import VIEW.TelaAdicionarTarefa;
import VIEW.TelaTarefa;
import VIEW.TelaUsuario;
import java.util.ArrayList;

public class ControllerTelaHome {

    //Instancia tela AdicionarTarefa e a torna visível.
    public void navegarParaTelaDeAdicionarTarefa(UsuarioDTO usuario) {
        TelaAdicionarTarefa telaAdicionarTarefa = new TelaAdicionarTarefa(usuario);
        telaAdicionarTarefa.setVisible(true);
    }

    //Instancia TelaUsuario e a torna visível.
    public void navegarParaTelaDeUsuario(UsuarioDTO usuario) {
        TelaUsuario telaUsuario = new TelaUsuario(usuario);
        telaUsuario.setVisible(true);
    }

    /**
     * Instancia TelaTarefa e a torna visível com os dados dados da tarefa que
     * foi seleciando.
     */
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

    //Cria um ArrayList com todas as tarefas do usuário.
    public ArrayList<TarefaDTO> listarTarefas(UsuarioDTO usuario) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario {
        System.out.println("Em ControllerTelaHome o idUsuario é: " + usuario.getId());
        TarefaDAO tarefaDAO = new TarefaDAO();
        ArrayList<TarefaDTO> listaDeTarefas = new ArrayList<>();

        //Inseri os dados retornados pelo banco em um ArrayList.
        for (TarefaDTO tarefa : tarefaDAO.listarTarefas(usuario)) {
            System.out.println("ControllerTelaHome.listarTarefas - Status da tarefa: " + tarefa.getStatus());
            listaDeTarefas.add(tarefa);
        }

        return listaDeTarefas;
    }

    //Cria um ArrayList apenas com as tarefas à fazer.
    public ArrayList listarTarefasAFazer(UsuarioDTO usuario) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario {
        System.out.println("Em ControllerTelaHome o idUsuario é: " + usuario.getId());
        TarefaDAO tarefaDAO = new TarefaDAO();
        ArrayList<TarefaDTO> listaDeTarefasAFazer = new ArrayList<>();

        /**
         * Verifica cada tarefa salva no banco e inseri na listaDeTarefasAFazer
         * apenas as que estão com status: false (à fazer).
         */
        for (TarefaDTO tarefa : tarefaDAO.listarTarefas(usuario)) {
            System.out.println("ControllerTelaHome.listarTarefas - Status da tarefa: " + tarefa.getStatus());
            if (tarefa.getStatus() == false) {
                listaDeTarefasAFazer.add(tarefa);
            }
        }

        return listaDeTarefasAFazer;
    }

    //Cria um ArrayList apenas com as tarefas feitas.
    public ArrayList listarTarefasFeitas(UsuarioDTO usuario) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario {
        System.out.println("Em ControllerTelaHome o idUsuario é: " + usuario.getId());
        TarefaDAO tarefaDAO = new TarefaDAO();
        ArrayList<TarefaDTO> listaDeTarefasFeitas = new ArrayList<>();

        /**
         * Verifica cada tarefa salva no banco e inseri na listaDeTarefasFeitas
         * apenas as que estão com status: true (feitas).
         */
        for (TarefaDTO tarefa : tarefaDAO.listarTarefas(usuario)) {
            System.out.println("ControllerTelaHome.listarTarefas - Status da tarefa: " + tarefa.getStatus());
            if (tarefa.getStatus() == true) {
                listaDeTarefasFeitas.add(tarefa);
            }
        }

        return listaDeTarefasFeitas;
    }
}
