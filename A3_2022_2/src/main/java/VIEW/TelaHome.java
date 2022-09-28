/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

/**
 *
 * @author Fabiano
 */
public class TelaHome extends javax.swing.JPanel {

    /**
     * Creates new form TelaHome
     */
    public TelaHome() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BackgroundHeader = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblMinhasTarefas = new javax.swing.JLabel();
        lblNovaTarefa = new javax.swing.JLabel();
        ComboBoxStatusDaTarefa = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTarefas = new javax.swing.JTable();

        setBackground(new java.awt.Color(107, 143, 113));
        setPreferredSize(new java.awt.Dimension(700, 400));

        BackgroundHeader.setBackground(new java.awt.Color(185, 245, 216));

        lblLogo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("SIDE DISH");

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Usuário");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblMinhasTarefas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblMinhasTarefas.setForeground(new java.awt.Color(255, 255, 255));
        lblMinhasTarefas.setText("Minhas tarefas:");

        lblNovaTarefa.setText("Nova Tarefa +");

        ComboBoxStatusDaTarefa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "À fazer", "Feitas" }));
        ComboBoxStatusDaTarefa.setFocusable(false);

        tblTarefas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tarefas"
            }
        ));
        jScrollPane1.setViewportView(tblTarefas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMinhasTarefas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxStatusDaTarefa, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 427, Short.MAX_VALUE)
                        .addComponent(lblNovaTarefa)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(BackgroundHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMinhasTarefas)
                    .addComponent(lblNovaTarefa)
                    .addComponent(ComboBoxStatusDaTarefa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackgroundHeader;
    private javax.swing.JComboBox<String> ComboBoxStatusDaTarefa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMinhasTarefas;
    private javax.swing.JLabel lblNovaTarefa;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblTarefas;
    // End of variables declaration//GEN-END:variables
}
