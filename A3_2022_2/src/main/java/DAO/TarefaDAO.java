package DAO;

import DTO.TarefaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TarefaDAO {

    PreparedStatement pstm;
    Connection conn;

    //Cria um ArrayList de tarefas a partir das tarefas salvas no banco de dados.
    public ArrayList<TarefaDTO> listarTarefas() {
        try {
            ArrayList<TarefaDTO> listaDeTarefas = new ArrayList<>();

            //Busca, no banco de dados, todas as tarefas da tabela tarefas.
            String sql = "SELECT * FROM tarefa";
            conn = new ConexaoDAO().conectaBD();
            pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            //Inseri a descrição das tarefas retornadas pelo banco de dados no ArrayList listaDeTarefas.
            while (rs.next()) {
                TarefaDTO tarefa = new TarefaDTO();
                tarefa.setDescricao(rs.getString("descricao"));
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
    public void criarTarefa(TarefaDTO tarefaDTO) {
        try {
            conn = new ConexaoDAO().conectaBD();
            pstm = conn.prepareStatement("INSERT INTO tarefa(descricao,status) values(?,?)");
            pstm.setString(1, tarefaDTO.getDescricao());
            pstm.setBoolean(2, false);
            pstm.execute();
        } catch (SQLException ex) {
            System.out.println("Deu erro em criarTarefa" + ex);
        }
    }

    //Atualiza uma tarefa salva no banco de dados.
    public void atualizarTarefa(TarefaDTO tarefaDTO) {
        try {
            conn = new ConexaoDAO().conectaBD();
            pstm = conn.prepareStatement("UPDATE tarefa SET descricao = ? , status = ? where  id = ?");
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
            pstm = conn.prepareStatement("DELETE FROM tarefa WHERE id = ?");
            pstm.setInt(1, tarefaDTO.getId());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println("Deu erro em apagarTarefa" + ex);

            return false;
        }
    }
}
