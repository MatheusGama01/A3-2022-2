package DAO;

import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelApagarATarefaException;
import EXCEPTIONS.NaoFoiPossivelCriarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuarioException;
import EXCEPTIONS.NaoFoiPossivelSalvarAEdicaoDaTarefaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TarefaDAO {

    private final ConexaoDAO conexaoDAO;
    PreparedStatement pstm;
    Connection conn;

    public TarefaDAO(ConexaoDAO conexao) {
        this.conexaoDAO = conexao;
    }

    //Cria um ArrayList de tarefas a partir das tarefas salvas no banco de dados.
    public ArrayList<TarefaDTO> listarTarefas(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        try {
            ArrayList<TarefaDTO> listaDeTarefas = new ArrayList<>();

            System.out.println("Em TarefaDAO o idUsuario é: " + usuarioDTO.getId());

            //Busca no banco de dados todas as tarefas com o id do usuário logado.
            String sql = "SELECT * FROM tarefas WHERE idUsuario= " + usuarioDTO.getId();
            this.conn = this.conexaoDAO.conectaBD();
            this.pstm = this.conn.prepareStatement(sql);
            ResultSet rs = this.pstm.executeQuery();

            /**
             * Insere as tarefas retornadas pelo banco de dados no ArrayList
             * listaDeTarefas.
             */
            while (rs.next()) {
                TarefaDTO tarefa = new TarefaDTO();

                System.out.println("Status da tarefa capturada em TarefaDAO: " + rs.getString("status"));

                tarefa.setId(rs.getInt("id"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setStatus(rs.getBoolean("status"));
                tarefa.setIdUsuario(rs.getInt("idUsuario"));
                listaDeTarefas.add(tarefa);
            }

            this.pstm.close();

            return listaDeTarefas;
        } catch (SQLException ex) {
            System.out.println("Deu erro em listarTarefas" + ex);
            throw new NaoFoiPossivelListarAsTarefasDoUsuarioException();
        }
    }

    //Busca uma tarefa específica no banco de dados.
    public TarefaDTO listarTarefa(TarefaDTO tarefa) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        try {
            //Busca a tarefa no banco de dados.
            String sql = "SELECT * FROM tarefas WHERE id = " + tarefa.getId();
            this.conn = this.conexaoDAO.conectaBD();
            this.pstm = this.conn.prepareStatement(sql);
            ResultSet rs = this.pstm.executeQuery();

            TarefaDTO tarefaRetornada = new TarefaDTO();

            //Cria um instância de TarefaDTO e insere a tarefa retornada dentro.
            if (rs.next()) {
                tarefaRetornada.setId(rs.getInt("id"));
                tarefaRetornada.setDescricao(rs.getString("descricao"));
                tarefaRetornada.setStatus(rs.getBoolean("status"));
                tarefaRetornada.setIdUsuario(rs.getInt("idUsuario"));
            }

            this.pstm.close();

            return tarefaRetornada;
        } catch (SQLException ex) {
            System.out.println("Deu erro em listarTarefa" + ex);
            throw new NaoFoiPossivelListarAsTarefasDoUsuarioException();
        }
    }

    //Insere uma tarefa no banco de dados.
    public Boolean criarTarefa(TarefaDTO tarefaDTO, UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCriarATarefaException {
        try {
            this.conn = this.conexaoDAO.conectaBD();
            this.pstm = this.conn.prepareStatement("INSERT INTO tarefas(descricao, status, idUsuario) VALUES(?, ?, ?)");
            this.pstm.setString(1, tarefaDTO.getDescricao());
            this.pstm.setBoolean(2, false);
            this.pstm.setInt(3, usuarioDTO.getId());
            this.pstm.execute();

            this.pstm.close();

            return true;
        } catch (SQLException ex) {
            System.out.println("Deu erro em criarTarefa" + ex);
            throw new NaoFoiPossivelCriarATarefaException();
        }
    }

    //Atualiza uma tarefa salva no banco de dados.
    public Boolean atualizarTarefa(TarefaDTO tarefaDTO) throws NaoFoiPossivelSalvarAEdicaoDaTarefaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {
        try {
            this.conn = this.conexaoDAO.conectaBD();
            this.pstm = this.conn.prepareStatement("UPDATE tarefas SET descricao = ? , status = ? WHERE  id = ?");
            this.pstm.setString(1, tarefaDTO.getDescricao());
            this.pstm.setBoolean(2, tarefaDTO.getStatus());
            this.pstm.setInt(3, tarefaDTO.getId());
            this.pstm.executeUpdate();

            this.pstm.close();

            return true;
        } catch (SQLException ex) {
            System.out.println("Deu erro em atualizarTarefa" + ex);
            throw new NaoFoiPossivelSalvarAEdicaoDaTarefaException();
        }
    }

    //Apaga uma tarefa salva no banco de dados.
    public Boolean apagarTarefa(TarefaDTO tarefaDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarATarefaException {
        try {
            this.conn = this.conexaoDAO.conectaBD();
            this.pstm = conn.prepareStatement("DELETE FROM tarefas WHERE id = ?");
            this.pstm.setInt(1, tarefaDTO.getId());
            this.pstm.executeUpdate();

            this.pstm.close();

            return true;
        } catch (SQLException ex) {
            System.out.println("Deu erro em apagarTarefa" + ex);
            throw new NaoFoiPossivelApagarATarefaException();
        }
    }
}
