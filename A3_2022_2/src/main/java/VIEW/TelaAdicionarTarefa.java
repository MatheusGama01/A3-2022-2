package VIEW;

import CONTROLLER.ControllerTelaAdicionarTarefa;
import DAO.ConexaoDAO;
import DAO.TarefaDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelCriarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import javax.swing.JOptionPane;

public class TelaAdicionarTarefa extends javax.swing.JFrame {

    private final ControllerTelaAdicionarTarefa controller;
    UsuarioDTO usuarioLogado = new UsuarioDTO();

    /**
     * Creates new form AdicionarTarefa
     */
    public TelaAdicionarTarefa(UsuarioDTO usuario) {
        initComponents();
        usuarioLogado = usuario;
        
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        TarefaDAO tarefaDAO = new TarefaDAO(conexaoDAO);
        controller = new ControllerTelaAdicionarTarefa(tarefaDAO);
        
        inicializarTela(usuario);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BackgroundHeader = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaDescricao = new javax.swing.JTextArea();
        btnSalvarTarefa = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(185, 245, 216));

        BackgroundHeader.setBackground(new java.awt.Color(107, 143, 113));

        lblLogo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("Notepad");
        lblLogo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoMouseClicked(evt);
            }
        });

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Usuário");
        lblUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsuarioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BackgroundHeaderLayout = new javax.swing.GroupLayout(BackgroundHeader);
        BackgroundHeader.setLayout(BackgroundHeaderLayout);
        BackgroundHeaderLayout.setHorizontalGroup(
            BackgroundHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundHeaderLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 560, Short.MAX_VALUE)
                .addComponent(lblUsuario)
                .addGap(25, 25, 25))
        );
        BackgroundHeaderLayout.setVerticalGroup(
            BackgroundHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BackgroundHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLogo)
                    .addComponent(lblUsuario))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel1.setText("Adicione uma descrição à tarefa:");

        textAreaDescricao.setColumns(20);
        textAreaDescricao.setRows(5);
        jScrollPane1.setViewportView(textAreaDescricao);

        btnSalvarTarefa.setForeground(new java.awt.Color(107, 143, 113));
        btnSalvarTarefa.setText("Salvar tarefa");
        btnSalvarTarefa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarTarefaActionPerformed(evt);
            }
        });

        btnCancelar.setForeground(new java.awt.Color(255, 0, 0));
        btnCancelar.setText("Cancelar");
        btnCancelar.setMaximumSize(new java.awt.Dimension(95, 23));
        btnCancelar.setMinimumSize(new java.awt.Dimension(95, 23));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnSalvarTarefa)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(BackgroundHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarTarefa)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Chama o método de salvar tarefa.
    private void btnSalvarTarefaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarTarefaActionPerformed
        String descricao = textAreaDescricao.getText();

        try {
            controller.criarTarefa(descricao, usuarioLogado);
            this.dispose();
        } catch (NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException | NaoFoiPossivelCriarATarefaException e) {
            ErroInesperado(e);
        }
    }//GEN-LAST:event_btnSalvarTarefaActionPerformed

    //Instancia TelaHome e a torna visível.
    private void lblLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoMouseClicked
        TelaHome telaHome = new TelaHome(usuarioLogado);
        telaHome.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_lblLogoMouseClicked

    //Instancia TelaUsuario e a torna visível.
    private void lblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsuarioMouseClicked
        TelaUsuario telaUsuario = new TelaUsuario(usuarioLogado);
        telaUsuario.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_lblUsuarioMouseClicked

    //Navega para telaHome sem salvar a tarefa.
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        TelaHome telaHome = new TelaHome(usuarioLogado);
        telaHome.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    //Inicializa a telaAdicinarTarefa com o nome do usuário logado.
    private void inicializarTela(UsuarioDTO usuario) {
        lblUsuario.setText(usuario.getNome());
    }

    //Mostra uma mensagem referente ao erro ocorrido.
    private void ErroInesperado(Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackgroundHeader;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvarTarefa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextArea textAreaDescricao;
    // End of variables declaration//GEN-END:variables
}
