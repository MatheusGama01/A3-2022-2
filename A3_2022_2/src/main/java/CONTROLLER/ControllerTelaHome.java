package CONTROLLER;

import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import DAO.TarefaDAO;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuarioException;
import java.util.ArrayList;

public class ControllerTelaHome {

    private final TarefaDAO tarefaDAO;

    public ControllerTelaHome(TarefaDAO tarefaDAO) {
        this.tarefaDAO = tarefaDAO;
    }

    //Retorna um ArrayList com todas as tarefas do usuário.
    public ArrayList<TarefaDTO> listarTarefas(UsuarioDTO usuario) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        System.out.println("Em ControllerTelaHome o idUsuario é: " + usuario.getId());
        ArrayList<TarefaDTO> listaDeTarefas = new ArrayList<>();

        /**
         * Insere os dados retornados pelo banco de dados no ArrayList
         * listaDeTarefas.
         */
        for (TarefaDTO tarefa : tarefaDAO.listarTarefas(usuario)) {
            System.out.println("ControllerTelaHome.listarTarefas - Status da tarefa: " + tarefa.getStatus());
            listaDeTarefas.add(tarefa);
        }

        return listaDeTarefas;
    }

    //Retorna um ArrayList apenas com as tarefas à fazer.
    public ArrayList<TarefaDTO> listarTarefasAFazer(UsuarioDTO usuario) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        System.out.println("Em ControllerTelaHome o idUsuario é: " + usuario.getId());
        ArrayList<TarefaDTO> listaDeTarefasAFazer = new ArrayList<>();

        /**
         * Verifica cada tarefa salva no banco de dados e insere na
         * listaDeTarefasAFazer apenas as que estão com status: false (à fazer).
         */
        for (TarefaDTO tarefa : tarefaDAO.listarTarefas(usuario)) {
            System.out.println("ControllerTelaHome.listarTarefas - Status da tarefa: " + tarefa.getStatus());
            if (tarefa.getStatus() == false) {
                listaDeTarefasAFazer.add(tarefa);
            }
        }

        return listaDeTarefasAFazer;
    }

    //Retorna um ArrayList apenas com as tarefas feitas.
    public ArrayList<TarefaDTO> listarTarefasFeitas(UsuarioDTO usuario) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        System.out.println("Em ControllerTelaHome o idUsuario é: " + usuario.getId());
        ArrayList<TarefaDTO> listaDeTarefasFeitas = new ArrayList<>();

        /**
         * Verifica cada tarefa salva no banco de dados e inseri na
         * listaDeTarefasFeitas apenas as que estão com status: true (feitas).
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
