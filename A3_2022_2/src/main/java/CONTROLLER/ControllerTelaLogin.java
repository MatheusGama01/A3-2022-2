package CONTROLLER;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import VIEW.TelaHome;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerTelaLogin {

    /**
     * Realiza a verificação dos dados digitados pelo usúario e, caso estejam
     * corretos, navega para telaHome.
     */
    public void logar(String email, String senha) {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setEmail(email);
            usuarioDTO.setSenha(senha);

            UsuarioDAO usuarioDAO = new UsuarioDAO();

            ResultSet rs = usuarioDAO.autenticaUsuario(usuarioDTO);

            if (rs.next()) {
                System.out.println("Parabéns! você conseguiu acessar");
                UsuarioDTO usuario = new UsuarioDTO(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"), rs.getString("email"));
                System.out.println(usuario.getId() + " " + usuario.getNome() + " " + usuario.getEmail() + " " + usuario.getSenha());
                TelaHome telaHome = new TelaHome(usuario);
                telaHome.setVisible(true);
                telaHome.lblUsuario.setText(usuario.getNome());
            } else {
                System.out.println("Não foi possível conectar");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao tentar autenticar");
        }
    }
}
