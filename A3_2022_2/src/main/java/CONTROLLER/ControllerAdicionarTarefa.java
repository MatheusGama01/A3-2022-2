/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLLER;

import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;

/**
 *
 * @author Gabriele
 */
public class ControllerAdicionarTarefa {
    public void criarTarefa(String descricao, UsuarioDTO usuario) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException{
        TarefaDTO tarefa = new TarefaDTO(descricao,false);
        TarefaDAO tarefaDAO = new TarefaDAO();
        tarefaDAO.criarTarefa(tarefa, usuario);
        
        
    }
    
}
