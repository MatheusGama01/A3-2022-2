/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLLER;

import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import VIEW.TelaHome;

/**
 *
 * @author Gabriele
 */
public class ControllerTelaAdicionarTarefa {
    
    //Instancia TelaHome e a torna visível.
    public void navegarParaTelaHome(UsuarioDTO usuario) {
        TelaHome telaHome = new TelaHome(usuario);
        telaHome.setVisible(true);
    }
    
    public void criarTarefa(String descricao, UsuarioDTO usuario) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException{
        TarefaDTO tarefa = new TarefaDTO(descricao,false);
        TarefaDAO tarefaDAO = new TarefaDAO();
        tarefaDAO.criarTarefa(tarefa, usuario);
        
        
    }
    
}
