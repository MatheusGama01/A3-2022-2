/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLLER;

import DAO.ConexaoDAO;
import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelCriarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import VIEW.TelaHome;

/**
 *
 * @author Gabriele
 */
public class ControllerTelaAdicionarTarefa {

    //Chama o método de criar tarefa em tarefaDAO.
    public void criarTarefa(String descricao, UsuarioDTO usuario) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCriarATarefaException {
        TarefaDTO tarefa = new TarefaDTO(descricao, false);
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        TarefaDAO tarefaDAO = new TarefaDAO(conexaoDAO);
        
        tarefaDAO.criarTarefa(tarefa, usuario);
        TelaHome telaHome = new TelaHome(usuario);
        telaHome.setVisible(true);
    }

}
