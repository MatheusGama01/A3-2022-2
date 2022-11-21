package VIEW;

import CONTROLLER.ControllerTelaHome;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaHome extends javax.swing.JFrame {

    private final ControllerTelaHome controller;
    UsuarioDTO usuarioLogado = new UsuarioDTO();

    /**
     * Creates new form TelaHome
     */
    public TelaHome(UsuarioDTO usuario) {
        initComponents();
        controller = new ControllerTelaHome();
        usuarioLogado = usuario;
        inicializarTela();
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
        lblMinhasTarefas = new javax.swing.JLabel();
        lblNovaTarefa = new javax.swing.JLabel();
        jComboBoxStatusTarefa = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTarefas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(185, 245, 216));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 400));

        BackgroundHeader.setBackground(new java.awt.Color(107, 143, 113));

        lblLogo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("Notepad");
        lblLogo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        lblMinhasTarefas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblMinhasTarefas.setForeground(new java.awt.Color(107, 143, 113));
        lblMinhasTarefas.setText("Minhas tarefas:");

        lblNovaTarefa.setText("Nova Tarefa +");
        lblNovaTarefa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNovaTarefa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNovaTarefaMouseClicked(evt);
            }
        });

        jComboBoxStatusTarefa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "À fazer", "Feitas" }));
        jComboBoxStatusTarefa.setFocusable(false);
        jComboBoxStatusTarefa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxStatusTarefaItemStateChanged(evt);
            }
        });

        tblTarefas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descrição", "Status"
            }
        ));
        tblTarefas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTarefasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTarefas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMinhasTarefas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxStatusTarefa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 427, Short.MAX_VALUE)
                        .addComponent(lblNovaTarefa)))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(BackgroundHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxStatusTarefa)
                    .addComponent(lblMinhasTarefas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNovaTarefa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
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

    //Instancia TelaUsuario e a torna visível.
    private void lblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsuarioMouseClicked
        TelaUsuario telaUsuario = new TelaUsuario(usuarioLogado);
        telaUsuario.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_lblUsuarioMouseClicked

    //Instancia tela AdicionarTarefa e a torna visível.
    private void lblNovaTarefaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNovaTarefaMouseClicked
        TelaAdicionarTarefa telaAdicionarTarefa = new TelaAdicionarTarefa(usuarioLogado);
        telaAdicionarTarefa.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_lblNovaTarefaMouseClicked

    //Verifica se há alteração na seleção do ComboBox.
    private void jComboBoxStatusTarefaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxStatusTarefaItemStateChanged
        try {
            this.inicializarTabela();
        } catch (NaoFoiPossivelListarAsTarefasDoUsuario e) {
            ErroInesperado(e);
        }
    }//GEN-LAST:event_jComboBoxStatusTarefaItemStateChanged

    //Navega para telaTarefa.
    private void tblTarefasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTarefasMouseClicked
        int idTarefa = 0;
        String descricao = "";
        String status = "";

        if (tblTarefas.getSelectedRow() != -1) {
            idTarefa = (int) tblTarefas.getValueAt(tblTarefas.getSelectedRow(), 0);
            descricao = tblTarefas.getValueAt(tblTarefas.getSelectedRow(), 1).toString();
            status = tblTarefas.getValueAt(tblTarefas.getSelectedRow(), 2).toString();
        }

        this.controller.navegarParaTelaTarefa(idTarefa, descricao, status, usuarioLogado);
        this.dispose();
    }//GEN-LAST:event_tblTarefasMouseClicked

    //Realiza o get do item selecionado no ComboBox.
    public String statusComboBox() {
        return (String) jComboBoxStatusTarefa.getSelectedItem();
    }

    //Inicializa a telaHome.
    private void inicializarTela() {
        try {
            lblUsuario.setText(usuarioLogado.getNome());
            this.inicializarTabela();
        } catch (NaoFoiPossivelListarAsTarefasDoUsuario e) {
            ErroInesperado(e);
        }
    }

    //Inseri os dados do ArrayList tarefas na tabela.
    private void inicializarTabela() throws NaoFoiPossivelListarAsTarefasDoUsuario {
        String statusSelecionado = statusComboBox();
        ArrayList<TarefaDTO> tarefas = new ArrayList<>();

        /**
         * A partir do status selecionado na combo box da TelaHome ele chama o
         * método mais adequado para trazer as tarefas do banco de dados.
         */
        try {
            if (statusSelecionado.equals("À fazer")) {
                System.out.println("Em inicializarTabela, chamando controller.listarTarefasAFazer");
                tarefas = this.controller.listarTarefasAFazer(usuarioLogado);
            } else if (statusSelecionado.equals("Feitas")) {
                System.out.println("Em inicializarTabela, chamando controller.listarTarefasFeitas");
                tarefas = this.controller.listarTarefasFeitas(usuarioLogado);
            } else {
                System.out.println("Em inicializarTabela, chamando controller.listarTarefas");
                tarefas = this.controller.listarTarefas(usuarioLogado);
            }
        } catch (NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException e) {
            ErroInesperado(e);
        }
        
        preencherTabela(tarefas);
    }

    //Preenche a tabela com os dados do ArrayList.
    public void preencherTabela(ArrayList<TarefaDTO> tarefas) {
        DefaultTableModel tabelaTarefas = (DefaultTableModel) tblTarefas.getModel();
        tabelaTarefas.setNumRows(0);

        for (TarefaDTO tarefa : tarefas) {
            String status;

            if (tarefa.getStatus() == true) {
                status = "Feita";
            } else {
                status = "À fazer";
            }

            tabelaTarefas.addRow(new Object[]{
                tarefa.getId(),
                tarefa.getDescricao(),
                status
            });
        }
    }

    //Mostra uma mensagem referente ao erro ocorrido.
    private void ErroInesperado(Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackgroundHeader;
    private javax.swing.JComboBox<String> jComboBoxStatusTarefa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMinhasTarefas;
    private javax.swing.JLabel lblNovaTarefa;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblTarefas;
    // End of variables declaration//GEN-END:variables
}
