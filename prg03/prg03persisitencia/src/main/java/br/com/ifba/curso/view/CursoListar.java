/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.curso.view;

import br.com.ifba.curso.entity.Curso; // Importa a Entidade (o "molde" dos dados).
import java.util.List; // Usado para receber a lista de cursos do banco.
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel; // Classe que gerencia os dados (linhas/colunas) da JTable.
import javax.swing.table.TableRowSorter; // CLASSE-CHAVE: Usada para filtrar/ordenar os dados da tabela.
import javax.swing.RowFilter; // Usado em conjunto com o TableRowSorter para definir a regra do filtro.
import javax.swing.event.DocumentListener; // "Ouvinte" que monitora mudanças em um campo de texto.
import javax.swing.event.DocumentEvent; // O "evento" que o DocumentListener escuta (ex: digitação).
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import br.com.ifba.curso.controller.CursoIController;
import jakarta.annotation.PostConstruct; // Importante para o Spring/Swing
import org.springframework.beans.factory.annotation.Autowired; // Essencial para a Injeção
import org.springframework.stereotype.Component; // A anotação chave

/**
 * CLASSE VIEW (TELA PRINCIPAL) * Função: Mostrar dados, capturar eventos de
 * botão e repassar para o Controller. * Analogia: O Dashboard ou a Vitrine. É a
 * única camada que o usuário toca.
 */
@Component // <--- ETIQUETA DO SPRING
// Diz ao Spring: "Isso é um componente. Crie ele e gerencie suas dependências".
// Isso permite que o Prg03Application.main peça essa tela ao Spring (context.getBean).
public class CursoListar extends javax.swing.JFrame {

    // @Autowired: INJEÇÃO DE DEPENDÊNCIA
    // O Spring lê isso e diz: "Essa tela precisa de um Garçom (Controller)? Toma, aqui está ele pronto."
    // NUNCA fazemos 'new CursoController()' aqui!
    @Autowired
    private CursoIController cursoController;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CursoListar.class.getName());

    private TableRowSorter<DefaultTableModel> sorter;

    /**
     * Construtor da classe. ATENÇÃO: Neste momento, o 'cursoController' PODE
     * AINDA ESTAR NULO. Por isso, só fazemos configurações visuais ou de
     * listeners.
     */
    public CursoListar() {
        initComponents(); // Inicializa os componentes do NetBeans

        // Configurações da Tabela e Filtros (código Swing normal)
        DefaultTableModel model = (DefaultTableModel) tblCurso.getModel();
        sorter = new TableRowSorter<>(model);
        tblCurso.setRowSorter(sorter);

        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);

        adicionarListenerSelecaoTabela();
        adicionarFiltroListener();

        // O carregamento inicial da tabela (preencherTabela()) foi MOVIDO para o @PostConstruct
    }

    /**
     * MÉTODO DE INICIALIZAÇÃO SEGURA
     *
     * @PostConstruct: "Rode este método SOMENTE depois que todos os objetos
     * (incluindo o 'cursoController') forem injetados e estiverem prontos."
     * Analogia: É a última checagem antes de abrir a loja.
     */
    @PostConstruct
    public void aposInicializar() {
        preencherTabela(); // É seguro chamar o Controller agora
        this.setVisible(true);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtPesquisar = new javax.swing.JTextPane();
        btnAdicionar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCurso = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(20, 51, 72));

        txtPesquisar.setToolTipText("Pesquisar Curso");
        jScrollPane1.setViewportView(txtPesquisar);

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        tblCurso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome", "Codigo do Curso"
            }
        ));
        jScrollPane2.setViewportView(tblCurso);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdicionar)
                        .addComponent(btnEditar)
                        .addComponent(btnExcluir))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * NOVO MÉTODO: Adiciona um "ouvinte" (listener) à seleção da tabela. Este
     * método será chamado toda vez que o usuário clicar em uma linha.
     */
    private void adicionarListenerSelecaoTabela() {
        // Pega o "modelo de seleção" da tabela e adiciona um "ouvinte" a ele
        tblCurso.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // 'e.getValueIsAdjusting()' evita disparos duplicados (ex: ao arrastar o mouse)
                // Nós só queremos o evento "final" (quando o usuário solta o mouse).
                if (!e.getValueIsAdjusting()) {

                    // Verifica se alguma linha está realmente selecionada
                    // 'getSelectedRow()' retorna -1 se nada estiver selecionado.
                    if (tblCurso.getSelectedRow() != -1) {
                        // Se sim, HABILITA os botões
                        btnEditar.setEnabled(true);
                        btnExcluir.setEnabled(true);
                    } else {
                        // Se não (ex: clicou fora), DESABILITA os botões
                        btnEditar.setEnabled(false);
                        btnExcluir.setEnabled(false);
                    }
                }
            }
        });
    }

    /**
     * Método responsável por buscar os dados no banco e atualizar a JTable. Ele
     * é chamado na inicialização da tela e após adicionar um novo curso.
     */
    private void preencherTabela() {
        DefaultTableModel model = (DefaultTableModel) tblCurso.getModel();
        model.setRowCount(0); // Limpa a tabela para evitar duplicatas

        // O Controller injetado é usado para buscar os dados.
        List<Curso> cursos = this.cursoController.findAll(); // <- CHAMA O MUNDO SPRING

        if (cursos != null) {
            for (Curso curso : cursos) {
                // Adiciona a linha na tabela
                model.addRow(new Object[]{
                    curso.getNome(),
                    curso.getCodigoCurso(),});
            }
        }
    }

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // 1. Cria uma nova instância da tela de adicionar (CursoAdicionar).
        //    Passamos 'this' (a tela CursoListar) como "pai"
        //    Passamos 'true' para dizer que ela é "MODAL" (ela trava a tela 'mãe'
        //    enquanto estiver aberta).
        CursoAdicionar telaAdicionar = new CursoAdicionar(this, true, this.cursoController);

        // 2. Mostra a tela de adicionar. 
        //    IMPORTANTE: Como ela é 'modal', o CÓDIGO AQUI PAUSA 
        //    e só continua quando a 'telaAdicionar' for fechada.
        telaAdicionar.setVisible(true);

        // 3. PONTO-CHAVE: Este é o nosso "refresh"!
        //    Este código só é executado DEPOIS que o usuário fechou a 
        //    tela 'telaAdicionar', garantindo que o novo item salvo
        //    apareça na tabela.
        preencherTabela();

    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // 1. Pega a linha selecionada na TELA
        int selectedRow = tblCurso.getSelectedRow();

        // 2. (Verificação de segurança) Se nada estiver selecionado, não faz nada.
        //    (O botão deve estar desabilitado, mas é bom verificar).
        if (selectedRow == -1) {
            return;
        }

        // 3. Converte o índice da TELA (view) para o índice do MODELO (dados reais)
        //    (IMPORTANTE caso a tabela esteja filtrada ou ordenada)
        int modelRow = tblCurso.convertRowIndexToModel(selectedRow);

        // 4. Pega o CÓDIGO (que é a Chave Primária) da coluna 1 do modelo.
        String codigoCurso = (String) tblCurso.getModel().getValueAt(modelRow, 1);

        // 5. USA O DAO para buscar o objeto 'Curso' COMPLETO no banco
        Curso cursoParaEditar = this.cursoController.findByCodigoCurso(codigoCurso);

        // 6. Verifica se o curso foi encontrado
        if (cursoParaEditar != null) {

            // 7. Cria a tela de Edição.
            //    (ASSUMINDO QUE VOCÊ VAI MUDAR CursoEditar para ser um JDialog,
            //    assim como o CursoAdicionar)
//                                                1. Pai  2. Modal  3. O CURSO       4. O CONTROLLER
            CursoEditar telaEditar = new CursoEditar(this, true, cursoParaEditar, this.cursoController);

            // 8. Mostra a tela de edição. O código PAUSA AQUI.
            telaEditar.setVisible(true);

            // 9. REFRESH: Este código só roda DEPOIS que a 'telaEditar' fechar.
            //    Atualiza a tabela para mostrar os novos dados.
            preencherTabela();

        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro: Não foi possível encontrar o curso para editar.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // 1. Pega a linha que o usuário selecionou na TELA (na "view")
        int selectedRow = tblCurso.getSelectedRow();

        // 2. Verifica se realmente há uma linha selecionada 
        //    (embora o botão devesse estar desabilitado se não houvesse)
        if (selectedRow == -1) {
            return; // Sai do método se nada estiver selecionado
        }

        // 3. Converte o índice da linha da TELA (view) para o índice do MODELO.
        //    ISSO É CRUCIAL! Se o usuário filtrar a tabela ("Ana"), a linha
        //    na tela pode ser a 0, mas no modelo original ela pode ser a 5.
        //    Sempre pegue dados usando o índice do MODELO.
        int modelRow = tblCurso.convertRowIndexToModel(selectedRow);

        // 4. Pega o valor da coluna "Codigo do Curso" (que está no índice 1)
        String codigoCurso = (String) tblCurso.getModel().getValueAt(modelRow, 1);
        String nomeCurso = (String) tblCurso.getModel().getValueAt(modelRow, 0);

        // 5. Mostra o JOptionPane de confirmação (Sim/Não)
        int confirm = JOptionPane.showConfirmDialog(
                this, // Componente "pai" (esta tela)
                "Tem certeza que deseja excluir o curso: \n" + nomeCurso + " (" + codigoCurso + ")?", // Mensagem
                "Confirmar Exclusão", // Título da janela
                JOptionPane.YES_NO_OPTION, // Tipo de botões (Sim/Não)
                JOptionPane.WARNING_MESSAGE // Ícone (Aviso)
        );

        // 6. Verifica se o usuário clicou em "SIM" (YES_OPTION)
        if (confirm == JOptionPane.YES_OPTION) {

            CursoIController cursoController = this.cursoController;
            try {
                // 7. Busca o objeto 'Curso' COMPLETO usando o código
                Curso cursoParaExcluir = cursoController.findByCodigoCurso(codigoCurso);

                if (cursoParaExcluir != null) {
                    // 8. Chama o método de excluir do DAO
                    cursoController.delete(cursoParaExcluir);

                    // 9. Mostra mensagem de sucesso
                    JOptionPane.showMessageDialog(this, "Curso excluído com sucesso!");

                    // 10. ATUALIZA A TABELA! (O "refresh")
                    preencherTabela();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Erro: Não foi possível encontrar o curso para excluir.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                // 11. Mostra uma mensagem de erro se o DAO falhar
                JOptionPane.showMessageDialog(this,
                        "Falha ao excluir o curso: " + e.getMessage(),
                        "Erro de Banco de Dados",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    /**
     * Método que configura o "ouvinte" (listener) para o campo de pesquisa.
     */
    private void adicionarFiltroListener() {
        // Pega o 'documento' (o conteúdo) do campo de texto e adiciona um "ouvinte" a ele.
        txtPesquisar.getDocument().addDocumentListener(new DocumentListener() {

            // Disparado sempre que o usuário digita ou cola texto.
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrar(); // Chama nosso método de filtro
            }

            // Disparado sempre que o usuário apaga texto (backspace ou delete).
            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrar(); // Chama nosso método de filtro
            }

            // Não usado para campos de texto simples.
            @Override
            public void changedUpdate(DocumentEvent e) {
                // (Geralmente usado para mudanças de estilo)
            }
        });
    }

    private void filtrar() {
        // Pega o texto atual que está no campo de pesquisa.
        String texto = txtPesquisar.getText();

        // Verifica se o campo está vazio (ignorando espaços em branco com 'trim()').
        if (texto.trim().length() == 0) {
            // Se estiver vazio, remove qualquer filtro (passando 'null'), 
            // fazendo todas as linhas aparecerem.
            sorter.setRowFilter(null);
        } else {
            // Se houver texto...
            try {
                // Aplica um filtro (RowFilter) ao 'sorter'.
                // Usamos 'regexFilter' para criar um filtro de Expressão Regular (Regex):
                //
                // "(?i)" : É um "flag" de Regex que torna a busca CASE-INSENSITIVE 
                //          (ignora maiúsculas/minúsculas). "Ana" acha "ana".
                //
                // texto   : O texto que o usuário digitou.
                //
                // 0, 1    : Os índices das COLUNAS onde o filtro deve ser aplicado.
                //           (Coluna 0 = "Nome", Coluna 1 = "Codigo do Curso").
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 0, 1));

            } catch (java.util.regex.PatternSyntaxException e) {
                // Bloco de segurança para evitar que a aplicação quebre 
                // se o usuário digitar um caractere inválido de Regex (como '[' ou '*').
                // Simplesmente não fazemos nada.
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new CursoListar().setVisible(true));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCurso;
    private javax.swing.JTextPane txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
