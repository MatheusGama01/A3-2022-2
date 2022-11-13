package VIEW;

import CONTROLLER.ControllerTelaTarefa;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelApagarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelSalvarAEdicaoDaTarefaException;
import EXCEPTIONS.TarefaNaoAlteradaException;
import javax.swing.JOptionPane;

public class TelaTarefa extends javax.swing.JFrame {

    private ControllerTelaTarefa controller;
    TarefaDTO tarefa = new TarefaDTO();
    UsuarioDTO usuarioLogado = new UsuarioDTO();

    /**
     * Creates new form TelaTarefa
     */
    public TelaTarefa() {
        initComponents();
    }

    public TelaTarefa(TarefaDTO tarefaSelecionada, UsuarioDTO usuario) {
        initComponents();
        controller = new ControllerTelaTarefa();
        tarefa = tarefaSelecionada;
        usuarioLogado = usuario;
        inicializarTelaTarefa(tarefaSelecionada, usuario);
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
        TextAreaDescricaoDaTarefa = new javax.swing.JTextArea();
        CheckBoxStatusDaTarefa = new javax.swing.JCheckBox();
        btnApagarTarefa = new javax.swing.JButton();
        btnSalvarEdicao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(185, 245, 216));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 400));

        BackgroundHeader.setBackground(new java.awt.Color(107, 143, 113));
        BackgroundHeader.setPreferredSize(new java.awt.Dimension(178, 51));

        lblLogo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("Notepad");
        lblLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoMouseClicked(evt);
            }
        });

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Usuário");
        lblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsuarioMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Magator");

        javax.swing.GroupLayout BackgroundHeaderLayout = new javax.swing.GroupLayout(BackgroundHeader);
        BackgroundHeader.setLayout(BackgroundHeaderLayout);
        BackgroundHeaderLayout.setHorizontalGroup(
            BackgroundHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundHeaderLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(BackgroundHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(BackgroundHeaderLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblLogo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUsuario)
                .addGap(23, 23, 23))
        );
        BackgroundHeaderLayout.setVerticalGroup(
            BackgroundHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo)
                .addGap(1, 1, 1)
                .addGroup(BackgroundHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(jLabel1))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        TextAreaDescricaoDaTarefa.setColumns(20);
        TextAreaDescricaoDaTarefa.setRows(5);
        jScrollPane1.setViewportView(TextAreaDescricaoDaTarefa);

        CheckBoxStatusDaTarefa.setText("Marcar tarefa como feita");
        CheckBoxStatusDaTarefa.setFocusable(false);

        btnApagarTarefa.setForeground(new java.awt.Color(255, 0, 0));
        btnApagarTarefa.setText("Apagar tarefa");
        btnApagarTarefa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnApagarTarefaMouseClicked(evt);
            }
        });

        btnSalvarEdicao.setForeground(new java.awt.Color(107, 143, 113));
        btnSalvarEdicao.setText("Salvar edição");
        btnSalvarEdicao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalvarEdicaoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSalvarEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(572, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CheckBoxStatusDaTarefa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnApagarTarefa)
                        .addGap(16, 16, 16))))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(BackgroundHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addComponent(CheckBoxStatusDaTarefa)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(286, 286, 286)
                        .addComponent(btnApagarTarefa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(btnSalvarEdicao)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Navega para TelaUsuario.
    private void lblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsuarioMouseClicked
        this.controller.navegarParaTelaDeUsuario(usuarioLogado);
        this.dispose();
    }//GEN-LAST:event_lblUsuarioMouseClicked

    //Navega para TelaHome.
    private void lblLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoMouseClicked
        this.controller.navegarParaTelaHome(usuarioLogado);
        this.dispose();
    }//GEN-LAST:event_lblLogoMouseClicked

    //Chama o métdo salvarEdicao de ControllerTelaTarefa.
    private void btnSalvarEdicaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarEdicaoMouseClicked
        String descricao = TextAreaDescricaoDaTarefa.getText();
        Boolean status = CheckBoxStatusDaTarefa.isSelected();
        
        try {
            controller.salvarEdicao(tarefa, descricao, status, usuarioLogado);
            this.dispose();
        } catch (TarefaNaoAlteradaException | NaoFoiPossivelSalvarAEdicaoDaTarefaException | NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException e) {
            ErroInesperado(e);
        }
    }//GEN-LAST:event_btnSalvarEdicaoMouseClicked

    //Chama  método apagarTarefa de ControllerTelaTarefa.
    private void btnApagarTarefaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnApagarTarefaMouseClicked
        //Confirma se o usuário realmente quer excluir a tarefa.
        Boolean confirmarExclusao = confirmarExclusao();
        if(confirmarExclusao == true){
            try {
                this.controller.apagarTarefa(tarefa, usuarioLogado);
                this.dispose();
                TarefaApagadaComSucesso();
            } catch (NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException | NaoFoiPossivelApagarATarefaException e) {
                ErroInesperado(e);
            }
        }
    }//GEN-LAST:event_btnApagarTarefaMouseClicked

    //Inicializa TelaTarefa com as informações da tarefa que foi passada.
    private void inicializarTelaTarefa(TarefaDTO tarefa, UsuarioDTO usuario) {        
        lblUsuario.setText(usuario.getNome());
        TextAreaDescricaoDaTarefa.setText(tarefa.getDescricao());

        /**
         * Se status == true -> a tarefa já estará marcada como feita. 
         * Se status == false -> a checkbox ficará desmarcada, pois a tarefa 
         * está à fazer.
         */
        CheckBoxStatusDaTarefa.setSelected(tarefa.getStatus());
    }

    //Confirma se o usuário realmente quer excluir a tarefa.
    private Boolean confirmarExclusao() {
        int confirmarExclusao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar a tarefa?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmarExclusao == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
    
    //Mostra uma menssagem indiccando que a tarefa foi apagada.
    private void TarefaApagadaComSucesso(){
        JOptionPane.showMessageDialog(null, "A tarefa foi apagada!");
    }
    
    //Mostra uma mensagem referente ao erro ocorrido.
    private void ErroInesperado(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackgroundHeader;
    private javax.swing.JCheckBox CheckBoxStatusDaTarefa;
    private javax.swing.JTextArea TextAreaDescricaoDaTarefa;
    private javax.swing.JButton btnApagarTarefa;
    private javax.swing.JButton btnSalvarEdicao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables
}
