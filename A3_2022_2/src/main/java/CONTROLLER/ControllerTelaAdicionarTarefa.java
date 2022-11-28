/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLLER;

import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelCriarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;

/**
 *
 * @author Gabriele
 */
public class ControllerTelaAdicionarTarefa {

    private final TarefaDAO tarefaDAO;

    public ControllerTelaAdicionarTarefa(TarefaDAO tarefaDAO) {
        this.tarefaDAO = tarefaDAO;
    }

    //Chama o método de criar tarefa em tarefaDAO.
    public Boolean criarTarefa(String descricao, UsuarioDTO usuario) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCriarATarefaException {
        TarefaDTO tarefa = new TarefaDTO(descricao, false);

        Boolean tarefaCriada = tarefaDAO.criarTarefa(tarefa, usuario);

        return tarefaCriada;
    }

}
