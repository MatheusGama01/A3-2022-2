package DAO;

import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TarefaDAO {

    PreparedStatement pstm;
    Connection conn;

    //Cria um ArrayList de tarefas a partir das tarefas salvas no banco de dados.
    public ArrayList<TarefaDTO> listarTarefas(UsuarioDTO usuarioDTO) {
        try {
            ArrayList<TarefaDTO> listaDeTarefas = new ArrayList<>();

            System.out.println("Em TarefaDAO o idUsuario é: " + usuarioDTO.getId());

            //Busca, no banco de dados, todas as tarefas da tabela tarefas.
            String sql = "SELECT * FROM tarefas WHERE idUsuario= " + usuarioDTO.getId();
            conn = new ConexaoDAO().conectaBD();
            pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            /**
             * Inseri a descrição das tarefas retornadas pelo banco de dados no
             * ArrayList listaDeTarefas.
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

            pstm.close();
            return listaDeTarefas;
        } catch (SQLException ex) {
            System.out.println("Deu erro em listarTarefas" + ex);
            return null;
        }
    }

    //Inseri uma tarefa no banco de dados.
    public void criarTarefa(TarefaDTO tarefaDTO, UsuarioDTO usuarioDTO) {
        try {
            conn = new ConexaoDAO().conectaBD();
            pstm = conn.prepareStatement("INSERT INTO tarefas(descricao,status,idUsuario) values(?,?,?)");
            pstm.setString(1, tarefaDTO.getDescricao());
            pstm.setBoolean(2, false);
            pstm.setInt(3, usuarioDTO.getId());
            pstm.execute();
        } catch (SQLException ex) {
            System.out.println("Deu erro em criarTarefa" + ex);
        }
    }

    //Atualiza uma tarefa salva no banco de dados.
    public void atualizarTarefa(TarefaDTO tarefaDTO) {
        try {
            conn = new ConexaoDAO().conectaBD();
            pstm = conn.prepareStatement("UPDATE tarefas SET descricao = ? , status = ? where  id = ?");
            pstm.setString(1, tarefaDTO.getDescricao());
            pstm.setBoolean(2, tarefaDTO.getStatus());
            pstm.setInt(3, tarefaDTO.getId());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Deu erro em atualizarTarefa" + ex);
        }
    }

    //Apaga uma tarefa salva no banco de dados.
    public Boolean apagarTarefa(TarefaDTO tarefaDTO) {
        try {
            conn = new ConexaoDAO().conectaBD();
            pstm = conn.prepareStatement("DELETE FROM tarefas WHERE id = ?");
            pstm.setInt(1, tarefaDTO.getId());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println("Deu erro em apagarTarefa" + ex);

            return false;
        }
    }
}
