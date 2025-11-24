/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package br.com.ifba.curso.view;

import br.com.ifba.curso.controller.CursoIController; // Precisamos da interface do Controller (o Garçom)
import br.com.ifba.curso.entity.Curso; // Precisamos da Entidade (o Molde dos dados)
import javax.swing.JOptionPane;

/**
 * CLASSE VIEW (O FORMULÁRIO POP-UP) * Função: Capturar a entrada de dados do
 * usuário e pedir ao Controller para salvar. * Analogia: É o formulário (folha
 * de pedido) que o cliente preenche.
 */
public class CursoAdicionar extends javax.swing.JDialog {

    // Variável local para guardar a referência do Controller.
    // O Spring NÃO injeta aqui porque o 'new' é chamado fora do contexto dele.
    private CursoIController cursoController;

    /**
     * CONSTRUTOR MODIFICADO * Função: Receber o Controller "pronto" de quem
     * criou esta tela. * Por que 3 parâmetros? (Pai, Se é modal, O Controller)
     * 1. parent (java.awt.Frame): Quem é a janela "mãe" (ex: CursoListar). 2.
     * modal (boolean): Se 'true', esta tela pausa a janela mãe. 3. controller
     * (CursoIController): O Controller que o Spring já injetou na tela mãe.
     */
    public CursoAdicionar(java.awt.Frame parent, boolean modal, CursoIController controller) {
        super(parent, modal);
        initComponents();
        this.cursoController = controller; // <--- Guarda a referência do Controller que recebemos.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblcriarCurso = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnCriar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblcriarCurso.setText("ADICIONANDO CURSO");

        lblNome.setText("Insira o nome do curso");

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        lblCodigo.setText("Insira o codigo do curso");

        btnCriar.setText("Criar Curso");
        btnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblcriarCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNome)
                    .addComponent(txtCodigo))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(btnCriar)
                .addContainerGap(151, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblcriarCurso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCriar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    /**
     * MÉTODO DO BOTÃO "CRIAR CURSO" * Função: Coletar os dados e acionar a
     * lógica de negócio. * Analogia: O cliente preenche o formulário e chama o
     * Garçom para levar o pedido.
     */
    private void btnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarActionPerformed

        // 1. COLETAR DADOS: Cria um objeto Curso e preenche com o que está nos campos de texto.
        Curso curso = new Curso();
        curso.setNome(txtNome.getText());
        curso.setCodigoCurso(txtCodigo.getText());
        curso.setAtivo(true); // Regra padrão: novo curso é ativo.

        // 2. CHAMAR O GERENTE (SERVICE) ATRAVÉS DO GARÇOM (CONTROLLER)
        // Usamos try-catch porque a camada Service pode lançar um erro (ex: nome vazio)
        // e nós precisamos mostrar isso ao usuário.
        try {
            // Chama o Controller que recebemos. O Controller aciona o Service, que valida
            // e manda o DAO salvar no banco.
            this.cursoController.save(curso); // <--- Usa o Controller INJETADO!

            // SUCESSO
            JOptionPane.showMessageDialog(this, "Curso criado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Fecha esta janela. O código na 'CursoListar' (que estava pausado) continua e
            // faz o 'refresh' da tabela.
            this.dispose();

        } catch (Exception e) {
            // FALHA
            // Captura o erro (ex: "O nome do curso é obrigatório!") lançado pelo Service
            // e mostra para o usuário.
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCriarActionPerformed

    /**
     * @param args the command line arguments
     */
// --- BLOCO MAIN (APENAS PARA TESTE VISUAL) ---
    /**
     * O método main só existe para você testar a aparência da tela no NetBeans.
     * Ele NUNCA é usado quando o sistema é iniciado pelo Spring Boot.
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Ao criar a tela, passamos 'null' para o controller, pois o 'main'
                // não tem o Spring ligado. O botão salvar não funcionaria neste modo de teste.
                CursoAdicionar dialog = new CursoAdicionar(new javax.swing.JFrame(), true, null);
                // ... (código para fechar a janela) ...
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriar;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblcriarCurso;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
