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
import VIEW.TelaHome;
import VIEW.TelaUsuario;

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

    //Instancia TelaUsuario e a torna visível.
    public void navegarParaTelaDeUsuario(UsuarioDTO usuario) {
        TelaUsuario telaUsuario = new TelaUsuario(usuario);
        telaUsuario.setVisible(true);
    }

    //Chama o método de criar tarefa em tarefaDAO.
    public void criarTarefa(String descricao, UsuarioDTO usuario) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCriarATarefaException {
        TarefaDTO tarefa = new TarefaDTO(descricao, false);
        TarefaDAO tarefaDAO = new TarefaDAO();
        
        tarefaDAO.criarTarefa(tarefa, usuario);
        TelaHome telaHome = new TelaHome(usuario);
        telaHome.setVisible(true);
    }

}
