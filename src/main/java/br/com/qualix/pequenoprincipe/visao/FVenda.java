/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.pequenoprincipe.visao;

import br.com.qualix.aaaConfig.Configuracoes;
import br.com.qualix.aaaFerramentas.Pandora;
import br.com.qualix.financeiro.modelo.TipoPagamento;
import br.com.qualix.pessoa.modelo.Pessoa;
import br.com.qualix.pessoa.servico.SPessoa;
import br.com.qualix.pessoa.visao.FBoxPessoa;
import br.com.qualix.pessoa.visao.FPessoa;
import br.com.qualix.print.servico.PP2;
import br.com.qualix.proserv.modelo.Pedido;
import br.com.qualix.proserv.modelo.PedidoPagamento;
import br.com.qualix.proserv.modelo.PedidoProduto;
import br.com.qualix.proserv.modelo.Produto;
import br.com.qualix.proserv.servico.SImagemProduto;
import br.com.qualix.proserv.servico.SPedido;
import br.com.qualix.proserv.servico.SProduto;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wagner
 */
public class FVenda extends javax.swing.JFrame {

    /**
     * Creates new form FModular
     */
    
    private static String tipo_pagamento = "";
    private static boolean in_impressao = true;
    private static String cd_terminal = "";
    
    public FVenda(String inicio) {
        initComponents();
        jnmCliente1.setText("CLIENTE NÃO IDENTIFICADO");
        jqtMultiplo.setText("1");
        jqtMultiplo.grabFocus();
        jqtMultiplo.selectAll();
        jnf.setVisible(false);
        tipo_pagamento = "";
        try {
            this.setIconImage(Configuracoes.LOGO_SISTEMA);
        } catch (Exception e) {
        }
        
        try {
            cd_terminal = "T-"+SPessoa.retrievePessoa(1L).getNrTelefone2();
        } catch (Exception e) {
            cd_terminal = "";
        }
        
        
        try {
            //Busca Produtos
            ArrayList<Produto> listaProduto = new ArrayList<>();
            listaProduto = SProduto.retrieveProduto();
            
//            listaProduto.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
            
            for (Produto produto : listaProduto) {
                
                jnf.setValue(produto.getVlProduto());
                JButton button = new JButton(jnf.getText());
                boolean produtoComImagem = produto.getCdGrupo() != null && produto.getCdGrupo().equals(1L);
                Dimension tamanho = produtoComImagem ? new Dimension(140, 160) : new Dimension(290, 70);
                button.setPreferredSize(tamanho);
                button.setMinimumSize(tamanho);
                button.setMaximumSize(tamanho);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                
                
                if (produtoComImagem) {
                    try{
                        button.setFont(new Font("Arial", Font.BOLD, 16));
                        button.setIcon(SImagemProduto.retrieveImagemProduto(produto));
                    } catch (Exception q){
                        button.setFont(new Font("Arial", Font.PLAIN, 14));
                        button.setText(getTextoBotaoProduto(produto, jnf.getText()));
                    }
                } else {
                    button.setFont(new Font("Arial", Font.PLAIN, 14));
                    button.setText(getTextoBotaoProduto(produto, jnf.getText()));
                }
                
                button.addActionListener(e -> {
                    addProduto(produto);
                });
                
                jPanel2.add(button);
                
            }
        
//            for (int i = 0; i < 15; i++) {
//                JButton button = new JButton("Botão " + i);
//                Dimension tamanho = new Dimension(140, 160);
//                button.setPreferredSize(tamanho);
//                button.setMinimumSize(tamanho);
//                button.setMaximumSize(tamanho);
//                jPanel2.add(button);
//            }
        
        } catch (Exception e) {
            
        }
        
//        //Carrega última Venda
//        try {
//            ArrayList<Pedido> listaPedido = new ArrayList<>();
//            listaPedido = SPedido.retrievePedido();
//            Long maior = 0L;
//            for (Pedido pedido : listaPedido) {
//                if (pedido.getId() > maior){
//                    maior = pedido.getId();
//                }
//            }
//            jnrVendaFim.setText(maior+"");
//        } catch (Exception e) {
//            Pandora.ex(e);
//        }

        jnrVendaInicio.setText("1");
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jvlTotal = new JNumberField.JNumberField();
        jvlTroco = new JNumberField.JNumberField();
        jvlDinheiro = new JNumberField.JNumberField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabelPagamento = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jcdCliente1 = new EditFiled.JNumberLetterField();
        jnmCliente1 = new EditFiled.JNumberLetterField();
        jbrNovoPessoa1 = new javax.swing.JButton();
        jbtLimpaPessoa1 = new javax.swing.JButton();
        jbtBuscaPessoa1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jinSeparado = new javax.swing.JCheckBox();
        jLabelImpressao = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jqtMultiplo = new EditFiled.JNumberLetterField();
        jLabel11 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jnf = new JNumberField.JNumberField();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jnrVendaPrint = new EditFiled.JNumberLetterField();
        jButton14 = new javax.swing.JButton();
        jinprimirseparado = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jnrVendaInicio = new EditFiled.JNumberLetterField();
        jLabel9 = new javax.swing.JLabel();
        jnrVendaFim = new EditFiled.JNumberLetterField();
        jButton9 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePagamento = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableProdutos = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jvlTotalPagamentos = new JNumberField.JNumberField();
        jvlTotalProdutos = new JNumberField.JNumberField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableFiado = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jvlTotalFiado = new JNumberField.JNumberField();
        jButton13 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jResp1 = new EditFiled.JNumberLetterField();
        jResp2 = new EditFiled.JNumberLetterField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Venda");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTable2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jTable2.setForeground(new java.awt.Color(102, 102, 102));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(360);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        jvlTotal.setEditable(false);
        jvlTotal.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        jvlTroco.setEditable(false);

        jvlDinheiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jvlDinheiroKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Valor Total");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Troco");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Dinheiro");

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabelPagamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPagamento.setText("Tipo de Pagamento");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pgdinheiro.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pgpix.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pgdebit.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pgbrinde.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pgcrediatrio.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPagamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel6.setText("Cliente");

        jcdCliente1.setBackground(new java.awt.Color(204, 255, 255));
        jcdCliente1.setText("jNumberLetterField1");
        jcdCliente1.setNumber(true);
        jcdCliente1.setTamanhoMax(8);
        jcdCliente1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcdCliente1FocusLost(evt);
            }
        });
        jcdCliente1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcdCliente1MouseClicked(evt);
            }
        });

        jnmCliente1.setEditable(false);
        jnmCliente1.setText("jNumberLetterField2");
        jnmCliente1.setTamanhoMax(50);
        jnmCliente1.setUppercase(true);

        jbrNovoPessoa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/addPessoa16.png"))); // NOI18N
        jbrNovoPessoa1.setText(" Novo");
        jbrNovoPessoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbrNovoPessoa1ActionPerformed(evt);
            }
        });

        jbtLimpaPessoa1.setText("Limpar");
        jbtLimpaPessoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLimpaPessoa1ActionPerformed(evt);
            }
        });

        jbtBuscaPessoa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/lupa16.png"))); // NOI18N
        jbtBuscaPessoa1.setText(" Buscar");
        jbtBuscaPessoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBuscaPessoa1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcdCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbtBuscaPessoa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jbrNovoPessoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtLimpaPessoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jnmCliente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jcdCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jnmCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbrNovoPessoa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtLimpaPessoa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jbtBuscaPessoa1))
                .addContainerGap())
        );

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("PRODUTOS DO PEDIDO");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/limpar32.png"))); // NOI18N
        jButton7.setText("Limpar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/print.png"))); // NOI18N
        jButton6.setText("Salvar Venda");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jinSeparado.setSelected(true);
        jinSeparado.setText("Imprimir Separado");
        jinSeparado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jinSeparado.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabelImpressao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelImpressao.setForeground(new java.awt.Color(204, 0, 51));
        jLabelImpressao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelImpressao.setText(" ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelImpressao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jinSeparado))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jinSeparado)
                    .addComponent(jLabelImpressao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("(dois cliques para remover)");

        jqtMultiplo.setText("1");
        jqtMultiplo.setNumber(true);
        jqtMultiplo.setPreferredSize(new java.awt.Dimension(64, 16));
        jqtMultiplo.setTamanhoMax(3);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("x");

        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton15.setText("Conferir com Cliente");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jqtMultiplo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jvlDinheiro, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jvlTroco, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jvlTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))))
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5)
                    .addComponent(jqtMultiplo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jvlTroco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jvlDinheiro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jvlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton15))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Venda", jPanel1);

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Saber a Última Venda");

        jButton8.setText("Número da Última Venda");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Reimprimir Ficha");

        jLabel10.setText("Venda Número:");

        jnrVendaPrint.setText("jNumberLetterField1");
        jnrVendaPrint.setNumber(true);
        jnrVendaPrint.setTamanhoMax(8);

        jButton14.setText("Imprimir");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jinprimirseparado.setText("Imprimir Separado");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jnrVendaPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addGap(67, 67, 67))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jinprimirseparado, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jnrVendaPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jinprimirseparado)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jnf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(278, 543, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 455, Short.MAX_VALUE)
                .addComponent(jnf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Controle", jPanel3);

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel4.setText("Venda Inicial");

        jnrVendaInicio.setText("jNumberLetterField1");
        jnrVendaInicio.setNumber(true);
        jnrVendaInicio.setPreferredSize(new java.awt.Dimension(64, 23));
        jnrVendaInicio.setTamanhoMax(6);

        jLabel9.setText("Venda Final");

        jnrVendaFim.setBackground(new java.awt.Color(204, 255, 255));
        jnrVendaFim.setText("jNumberLetterField1");
        jnrVendaFim.setNumber(true);
        jnrVendaFim.setPreferredSize(new java.awt.Dimension(64, 23));
        jnrVendaFim.setTamanhoMax(6);
        jnrVendaFim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jnrVendaFimMouseClicked(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setText("Processar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTablePagamento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTablePagamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Forma", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePagamento.setRowHeight(30);
        jTablePagamento.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTablePagamento);
        if (jTablePagamento.getColumnModel().getColumnCount() > 0) {
            jTablePagamento.getColumnModel().getColumn(0).setResizable(false);
            jTablePagamento.getColumnModel().getColumn(1).setResizable(false);
        }

        jTableProdutos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Qtde - Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProdutos.setRowHeight(30);
        jTableProdutos.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTableProdutos);
        if (jTableProdutos.getColumnModel().getColumnCount() > 0) {
            jTableProdutos.getColumnModel().getColumn(0).setResizable(false);
            jTableProdutos.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/print.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/print.png"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jvlTotalPagamentos.setEditable(false);

        jvlTotalProdutos.setEditable(false);

        jTableFiado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTableFiado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Qtde - Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFiado.setRowHeight(30);
        jTableFiado.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableFiado.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTableFiado);
        if (jTableFiado.getColumnModel().getColumnCount() > 0) {
            jTableFiado.getColumnModel().getColumn(0).setResizable(false);
            jTableFiado.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/print.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jvlTotalFiado.setEditable(false);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ok24.png"))); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel13.setForeground(new java.awt.Color(255, 51, 51));
        jLabel13.setText("Para fazer o fechamento informe a primeira e última venda (à esquerda)");

        jResp1.setText("jNumberLetterField1");
        jResp1.setTamanhoMax(35);
        jResp1.setUppercase(true);
        jResp1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jResp1KeyReleased(evt);
            }
        });

        jResp2.setText("jNumberLetterField2");
        jResp2.setTamanhoMax(35);
        jResp2.setUppercase(true);
        jResp2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jResp2KeyReleased(evt);
            }
        });

        jLabel14.setText("Responsável");

        jLabel15.setText("Responsável");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jvlTotalPagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jnrVendaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jnrVendaFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jResp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jvlTotalProdutos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jResp2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jvlTotalFiado, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(21, Short.MAX_VALUE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jnrVendaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jnrVendaFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jResp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jResp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jvlTotalPagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jvlTotalProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jvlTotalFiado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Relatório de Fechamento", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jcdCliente1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcdCliente1FocusLost
        try {
            if (!jcdCliente1.getText().equals("")){
                Long idPessoa = Long.parseLong(jcdCliente1.getText());
                Pessoa pessoa = SPessoa.retrievePessoa(idPessoa);
                if (pessoa != null){
                    jcdCliente1.setText(pessoa.getId()+"");
                    jnmCliente1.setText(pessoa.getNmPessoa());
                } else {
                    throw new Exception("");
                }
            } else {
                jnmCliente1.setText("");
            }
            
        } catch (Exception e) {
            jcdCliente1.setText("");
            jnmCliente1.setText("");
            
        }
    }//GEN-LAST:event_jcdCliente1FocusLost

    private void jbrNovoPessoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbrNovoPessoa1ActionPerformed
        FPessoa.getNewInstanceAutoCadastro().setVisible(true);
        FPessoa.getInstance().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Pessoa pessoa = FPessoa.getPessoaSelecionada();
                if (pessoa != null){
                    jcdCliente1.setText(pessoa.getId()+"");
                    jnmCliente1.setText(pessoa.getNmPessoa());
                } else {
                    jcdCliente1.setText("");
                    jnmCliente1.setText("");
                    
                }
                jqtMultiplo.grabFocus();
                jqtMultiplo.selectAll();
            }
            
        });
    }//GEN-LAST:event_jbrNovoPessoa1ActionPerformed

    private void jbtLimpaPessoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLimpaPessoa1ActionPerformed
        jcdCliente1.setText("");
        jnmCliente1.setText("CLIENTE NÃO IDENTIFICADO");
    }//GEN-LAST:event_jbtLimpaPessoa1ActionPerformed

    private void jbtBuscaPessoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBuscaPessoa1ActionPerformed
        FBoxPessoa.getNewInstance().setVisible(true);
        FBoxPessoa.getInstance().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Pessoa pessoa = FBoxPessoa.getPessoaSelecionada();
                if (pessoa != null){
                    jcdCliente1.setText(pessoa.getId()+"");
                    jnmCliente1.setText(pessoa.getNmPessoa());
                    FBoxPessoa.removePessoaSelecionada();
                } 
                jqtMultiplo.grabFocus();
                jqtMultiplo.selectAll();
            }
            
        });
    }//GEN-LAST:event_jbtBuscaPessoa1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        limparTela();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if (evt.getClickCount() == 2){
            DefaultTableModel dm = (DefaultTableModel) jTable2.getModel();
            dm.removeRow(jTable2.getSelectedRow());
            atualizaContador();
            jqtMultiplo.setText("1");
            jqtMultiplo.grabFocus();
            jqtMultiplo.selectAll();
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jvlDinheiroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jvlDinheiroKeyReleased
        calculaTroco();
        tipo_pagamento = "Dinheiro";
        atualizaContador();
        
    }//GEN-LAST:event_jvlDinheiroKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tipo_pagamento = "DINHEIRO";
        atualizaContador();
        jqtMultiplo.setText("1");
        jqtMultiplo.grabFocus();
        jqtMultiplo.selectAll();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        tipo_pagamento = "PIX";
        atualizaContador();
        jqtMultiplo.setText("1");
        jqtMultiplo.grabFocus();
        jqtMultiplo.selectAll();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        tipo_pagamento = "CREDITO";
        atualizaContador();
        jqtMultiplo.setText("1");
        jqtMultiplo.grabFocus();
        jqtMultiplo.selectAll();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        tipo_pagamento = "BRINDE";
        atualizaContador();
        jqtMultiplo.setText("1");
        jqtMultiplo.grabFocus();
        jqtMultiplo.selectAll();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        tipo_pagamento = "CREDIARIO";
        atualizaContador();
        jqtMultiplo.setText("1");
        jqtMultiplo.grabFocus();
        jqtMultiplo.selectAll();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
             
            if (jTable2.getRowCount() == 0){
                throw new Exception("Selecione os produtos");
            }
            
            if (tipo_pagamento.equals("")){
                throw new Exception("Informe o Tipo de Pagamento");
            } else if (tipo_pagamento.equals("CREDIARIO") && jcdCliente1.getText().equals("")){
                throw new Exception("Informe o Cliente para Venda no Crediário");
            }
            
            //------------------------------------------------------------------
            
            
            Long id = null;
            Long cdPessoa = null;
            
            if (!jcdCliente1.getText().equals("")){
                cdPessoa = Long.parseLong(jcdCliente1.getText());
            }
            
            if (jnmCliente1.getText().equals("")){
                jnmCliente1.setText("CLIENTE NÃO IDENTIFICADO");
            }
            
            
            ArrayList<PedidoProduto> listaProduto = new ArrayList<>();
            PedidoProduto pedPro = new PedidoProduto();
            Produto produto = new Produto();
            
            
            for (int i = 0; i < jTable2.getRowCount() ; i++) {
                produto = (Produto) jTable2.getValueAt(i, 0);
                pedPro = new PedidoProduto(null, cdPessoa, produto, BigDecimal.ONE, produto.getVlProduto(), "", BigDecimal.ZERO, jvlTotal.getValue());
                listaProduto.add(pedPro);
            }
            
            ArrayList<PedidoPagamento> listaPag = new ArrayList<>();
            PedidoPagamento pedPag = new PedidoPagamento();
            
            TipoPagamento tipo = null;
            String dsTipo = null;
            if (tipo_pagamento.equals("DINHEIRO")){
                tipo = new TipoPagamento(1l, "DINHEIRO", 0);
                dsTipo = "D";
            } else if (tipo_pagamento.equals("PIX")){
                tipo = new TipoPagamento(4l, "PIX", 0);
                dsTipo = "P";
            } else if (tipo_pagamento.equals("CREDITO")){
                tipo = new TipoPagamento(2l, "CREDITO", 1);
                dsTipo = "C";
            } else if (tipo_pagamento.equals("BRINDE")){
                tipo = new TipoPagamento(11l, "BRINDE", 0);
                dsTipo = "B";
            } else if (tipo_pagamento.equals("CREDIARIO")){
                tipo = new TipoPagamento(6l, "CREDIARIO", 0);
                dsTipo = "F";
            } else {
                throw new Exception("Erro ao Selecionar o pagamento");
            }
            
            
            pedPag = new PedidoPagamento(null, tipo, jvlTotal.getValue());
            listaPag.add(pedPag);
            
            
            BigDecimal total = BigDecimal.valueOf(jvlTotal.getValue().doubleValue());
            boolean inRetirado = false;
            boolean inPago     = true;
            
            //Resolve datas
            LocalDateTime dtTransacao = LocalDateTime.now();
            
            LocalDate dtValidade = null;
            
            String dsSituacao = "EFETIVADO";
            
            //Grava Pedido
            Pedido pedidoGravado = SPedido.gravaPedido(id, jnmCliente1.getText(), "VENDA", dsSituacao, dtTransacao, dtValidade, cdPessoa, null, jvlTotal.getValue(), BigDecimal.ZERO, total, inPago, inRetirado, "", false, listaProduto, listaPag, "", false, "");
            //-----------------
            DefaultTableModel dm = (DefaultTableModel) jTable2.getModel();
        
            String codigoPedido = pedidoGravado.getId() + "";
//            
            while (codigoPedido.length() < 8){
                codigoPedido = "0" + codigoPedido;
            }
            
            
            String texto = "";
//            String codigo = "05240001"; //8dig
            boolean inBrinquedo = false;
            if (jinSeparado.isSelected()){

                for (int i = 0; i < dm.getRowCount(); i++) {
                    Produto pro = (Produto) dm.getValueAt(i, 0);
                    String valor   = (String) dm.getValueAt(i, 1);
                    if (pro.getId() != Long.parseLong(Configuracoes.versaoArquivo)){
                        texto = pro.getDsProduto() + "  " + valor;
                        if (in_impressao){
                            PP2.imprimeCaminhada(codigoPedido, texto, dsTipo, cd_terminal);
//                            PP2.imprimeComLogo(codigoPedido, texto, dsTipo, cd_terminal);
//                            PP2.imprimeSoImagemPronto(codigoPedido, texto, dsTipo, cd_terminal);
                        } else {
                            Pandora.msgInfo(texto);
                        }
                    } else {
                        texto = pro.getDsProduto();
                        inBrinquedo = true;
                    }
                    
                }

            } else {

                for (int i = 0; i < dm.getRowCount(); i++) {
                    Produto pro = (Produto) dm.getValueAt(i, 0);
                    String valor = (String) dm.getValueAt(i, 1);
                    
                    if (pro.getId() != Long.parseLong(Configuracoes.versaoArquivo)){
                        texto += "\n" + pro.getDsProduto() + "  " + valor;
                    } else {
                        texto += "\n" + pro.getDsProduto();
                        inBrinquedo = true;
                    }
                }
                if (in_impressao){
                    PP2.imprimeCaminhada(codigoPedido, texto, dsTipo, cd_terminal);
                } else {
                    Pandora.msgInfo(texto);
                }
            }
            
            
            if (inBrinquedo){
                FLembreteBrinquedo box = new FLembreteBrinquedo();
                box.setVisible(true);
            }
            
            //-----------------
            
            limparTela();
//            validaCampos(Configuracoes.STATUS_TELA_PADRAO);
            
            //------------------------------------------------------------------
            
        } catch (Exception e) {
            Pandora.msgAlerta(e.getMessage());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            ArrayList<Pedido> listaPedido = new ArrayList<>();
            listaPedido = SPedido.retrievePedido();
            Long maior = 0L;
            for (Pedido pedido : listaPedido) {
                if (pedido.getId() > maior){
                    maior = pedido.getId();
                }
            }
            Pandora.msgInfo("Última Venda: " + maior);
        } catch (Exception e) {
            Pandora.ex(e);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        
        String textoFormaEmail = "";
        String textoProdutoEmail = "";
        
        try {
            
            
            
            
            
            if (jnrVendaInicio.getText().equals("")){
                throw new Exception("Informe a Venda Inicial");
            }
            if (jnrVendaFim.getText().equals("")){
                
                try {
                    ArrayList<Pedido> listaPedido = new ArrayList<>();
                    listaPedido = SPedido.retrievePedido();
                    Long maior = 0L;
                    for (Pedido pedido : listaPedido) {
                        if (pedido.getId() > maior){
                            maior = pedido.getId();
                        }
                    }
                    jnrVendaFim.setText(maior+"");
                } catch (Exception e) {
                }
                
                
            }


            DefaultTableModel dm = (DefaultTableModel) jTablePagamento.getModel();
            dm.setNumRows(0);
            
            DefaultTableModel dmpro = (DefaultTableModel) jTableProdutos.getModel();
            dmpro.setNumRows(0);
            
            DefaultTableModel dmfiado = (DefaultTableModel) jTableFiado.getModel();
            dmfiado.setNumRows(0);

            BigDecimal vlSomaProdutos = BigDecimal.ZERO;
            BigDecimal vlSomaFiado = BigDecimal.ZERO;
            
            BigDecimal vlSomaPagamentos = BigDecimal.ZERO;
            BigDecimal vlSomaDinheiro = BigDecimal.ZERO;
            BigDecimal vlSomaPix = BigDecimal.ZERO;
            BigDecimal vlSomaCredito = BigDecimal.ZERO;
            BigDecimal vlSomaBrinde = BigDecimal.ZERO;
            BigDecimal vlSomaCrediario = BigDecimal.ZERO;
        
            Long pedIni = Long.parseLong(jnrVendaInicio.getText());
            Long pedFim = Long.parseLong(jnrVendaFim.getText());
        
            ArrayList<Pedido> listaPedido = new ArrayList<>();
            listaPedido = SPedido.retrievePedido();
            
            
            Map<String, Totalizador> totalPorProduto = new HashMap<>();
            
            Map<Pessoa, Totalizador> totalPorFiado = new HashMap<>();
            
            for (Pedido pedido : listaPedido) {
                
                if (pedido.getId() >= pedIni && pedido.getId() <= pedFim){
                    
                    //Calcula Pagamentos
                    if (pedido.getListaPagamento() != null && pedido.getListaPagamento().size() > 0){
                        String tipoPag = pedido.getListaPagamento().get(0).getPagamento().getDsTipoPagamento();
                        if (tipoPag.equals("DINHEIRO")){
                            vlSomaDinheiro = vlSomaDinheiro.add(pedido.getListaPagamento().get(0).getVlPagamento());
                            vlSomaPagamentos = vlSomaPagamentos.add(pedido.getListaPagamento().get(0).getVlPagamento());
                        } else if (tipoPag.equals("PIX")){
                            vlSomaPix = vlSomaPix.add(pedido.getListaPagamento().get(0).getVlPagamento());
                            vlSomaPagamentos = vlSomaPagamentos.add(pedido.getListaPagamento().get(0).getVlPagamento());
                        } else if (tipoPag.equals("CREDITO")){
                            vlSomaCredito = vlSomaCredito.add(pedido.getListaPagamento().get(0).getVlPagamento());
                            vlSomaPagamentos = vlSomaPagamentos.add(pedido.getListaPagamento().get(0).getVlPagamento());
                        } else if (tipoPag.equals("BRINDE")){
                            vlSomaBrinde = vlSomaBrinde.add(pedido.getListaPagamento().get(0).getVlPagamento());
                            vlSomaPagamentos = vlSomaPagamentos.add(pedido.getListaPagamento().get(0).getVlPagamento());
                        } else if (tipoPag.equals("CREDIARIO")){
                            vlSomaCrediario = vlSomaCrediario.add(pedido.getListaPagamento().get(0).getVlPagamento());
                            vlSomaPagamentos = vlSomaPagamentos.add(pedido.getListaPagamento().get(0).getVlPagamento());
                            
                            
                            //Adicionar na lista devedores
                            try {
                                Pessoa pessoa = SPessoa.retrievePessoa(pedido.getCdPessoa());
                                BigDecimal qtd = BigDecimal.ONE;
                                BigDecimal val = pedido.getListaPagamento().get(0).getVlPagamento();
                                totalPorFiado.computeIfAbsent(pessoa, k -> new Totalizador()).adicionar(qtd, val);
                                vlSomaFiado = vlSomaFiado.add(val);
                            } catch (Exception e) {
                            }
                            //Adicionar na lista devedores
                            
                        }
                    }
                    //Fim Calcula Pagamentos
                    
                    
                    //Calcula Produtos
                    for (PedidoProduto pp : pedido.getListaProduto()) {
                        String nomeProduto = pp.getProduto().getDsProduto();
                        BigDecimal qtd = pp.getQtItem();
                        BigDecimal val = pp.getVlItem().multiply(pp.getQtItem()); // ou pp.getValorUnitario() * qtd, se for o caso
                        vlSomaProdutos = vlSomaProdutos.add(val);
                        totalPorProduto.computeIfAbsent(nomeProduto, k -> new Totalizador()).adicionar(qtd, val);
                    }
                    
                    
                    
                    
                    
                }
            }
            
            //Print produtos
            for (Map.Entry<String, Totalizador> entry : totalPorProduto.entrySet()) {
                dmpro.setNumRows(dmpro.getRowCount()+1);
                dmpro.setValueAt(entry.getKey(), dmpro.getRowCount()-1, 0);
                dmpro.setValueAt(entry.getValue(), dmpro.getRowCount()-1, 1);
                textoProdutoEmail += "<br>" + entry.getKey() + " - " + entry.getValue();
//                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            
            //Print fiado
            for (Map.Entry<Pessoa, Totalizador> entry : totalPorFiado.entrySet()) {
                dmfiado.setNumRows(dmfiado.getRowCount()+1);
                dmfiado.setValueAt(entry.getKey(), dmfiado.getRowCount()-1, 0);
                dmfiado.setValueAt(entry.getValue(), dmfiado.getRowCount()-1, 1);
            }
            jvlTotalFiado.setValue(vlSomaFiado);

            jvlTotalProdutos.setValue(vlSomaProdutos);
            textoProdutoEmail += "<br>TOTAL: " + "  " + jvlTotalProdutos.getText();
            
            
            //Print Pagamentos
            dm.setNumRows(dm.getRowCount()+1);
            dm.setValueAt("Dinheiro", dm.getRowCount()-1, 0);
            jnf.setValue(vlSomaDinheiro);
            dm.setValueAt(jnf.getText(), dm.getRowCount()-1, 1);
            textoFormaEmail += "<br>Dinheiro" + " - " + jnf.getText();

            dm.setNumRows(dm.getRowCount()+1);
            dm.setValueAt("Pix", dm.getRowCount()-1, 0);
            jnf.setValue(vlSomaPix);
            dm.setValueAt(jnf.getText(), dm.getRowCount()-1, 1);
            textoFormaEmail += "<br>Pix" + " - " + jnf.getText();

            dm.setNumRows(dm.getRowCount()+1);
            dm.setValueAt("Cartão", dm.getRowCount()-1, 0);
            jnf.setValue(vlSomaCredito);
            dm.setValueAt(jnf.getText(), dm.getRowCount()-1, 1);
            textoFormaEmail += "<br>Cartão" + " - " + jnf.getText();

            dm.setNumRows(dm.getRowCount()+1);
            dm.setValueAt("Brinde", dm.getRowCount()-1, 0);
            jnf.setValue(vlSomaBrinde);
            dm.setValueAt(jnf.getText(), dm.getRowCount()-1, 1);
            textoFormaEmail += "<br>Brinde" + " - " + jnf.getText();

            dm.setNumRows(dm.getRowCount()+1);
            dm.setValueAt("Crediário", dm.getRowCount()-1, 0);
            jnf.setValue(vlSomaCrediario);
            dm.setValueAt(jnf.getText(), dm.getRowCount()-1, 1);
            textoFormaEmail += "<br>Crediário" + " - " + jnf.getText();
            
            jvlTotalPagamentos.setValue(vlSomaPagamentos);
            textoFormaEmail += "<br>TOTAL: " + "  " + jvlTotalPagamentos.getText();
        
        } catch (Exception e) {
            Pandora.ex(e);
        }
        
        String texto = textoFormaEmail + "<br>" + textoProdutoEmail;
        
        
        Thread t = new Thread() {
            public void run() {
                try {
                } catch (Exception ex) {
                }
            }
        };
        t.start();
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        
        if (jnrVendaInicio.getText().equals("") || jnrVendaFim.getText().equals("")){
            Pandora.msgAlerta("Clique em Processar antes de imprimir o relatório");
            return;
        }
        
        DefaultTableModel dm = (DefaultTableModel) jTablePagamento.getModel();
        
        if (dm.getRowCount() == 0){
            Pandora.msgAlerta("Clique em Processar antes de imprimir o relatório");
            return;
        }
        
        String texto = "          FECHAMENTO DE CAIXA "+ cd_terminal +"\n\n";
        try {
            texto += "Data: " + Pandora.getAgoraSTR() + "\n";
        } catch (Exception e) {
        }
        texto += "Vendas: " + jnrVendaInicio.getText() + " a " + jnrVendaFim.getText();
        
        if (!jResp1.getText().equals("")){
            texto += "\nResponsavel: " + jResp1.getText();
        }
        
        texto += "\n\n";
        
        for (int i = 0; i < dm.getRowCount(); i++) {
            texto += (String) dm.getValueAt(i, 0);
            texto += " = " + (String) dm.getValueAt(i, 1) + "\n";
        }
        
        texto += "\nTOTAL: " + jvlTotalPagamentos.getText() + "\n";
        
        texto += "\n\n\n";
        texto += "         ----------------------          \n";
        texto += "         ASSINATURA RESPONSAVEL          \n\n\n\n\n\n";
        
        if (in_impressao){
            PP2.imprimeTextoSimples(texto);
        } else {
            Pandora.msgInfo(texto);
        }
//        Pandora.msgAlerta(texto);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        if (jnrVendaInicio.getText().equals("") || jnrVendaFim.getText().equals("")){
            Pandora.msgAlerta("Clique em Processar antes de imprimir o relatório");
            return;
        }
        
        DefaultTableModel dm = (DefaultTableModel) jTableProdutos.getModel();

        if (dm.getRowCount() == 0){
            Pandora.msgAlerta("Clique em Processar antes de imprimir o relatório");
            return;
        }
        
        String texto = "          FECHAMENTO DE VENDAS "+ cd_terminal +"\n\n";
        try {
            texto += "Data: " + Pandora.getAgoraSTR() + "\n";
        } catch (Exception e) {
        }
        texto += "Vendas: " + jnrVendaInicio.getText() + " a " + jnrVendaFim.getText();
        
        if (!jResp2.getText().equals("")){
            texto += "\nResponsavel: " + jResp2.getText();
        }
        
        texto += "\n\n";
        
//        texto += "PRODUTO = QTDE - VALOR\n";
        for (int i = 0; i < dm.getRowCount(); i++) {
            texto += (String) dm.getValueAt(i, 0);
            Totalizador tot = (Totalizador) dm.getValueAt(i, 1);
            texto += " = " + tot.toString() + "\n";
        }
        
        texto += "\nTOTAL: " + jvlTotalProdutos.getText() + "\n";
        
        texto += "\n\n\n";
        texto += "         ----------------------          \n";
        texto += "         ASSINATURA RESPONSAVEL          \n\n\n\n\n\n";
        if(in_impressao){
            PP2.imprimeTextoSimples(texto);
        } else {
            Pandora.msgInfo(texto);
        }
//        Pandora.msgAlerta(texto);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jnrVendaFimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jnrVendaFimMouseClicked
        if (evt.getClickCount() == 2){
            try {
                ArrayList<Pedido> listaPedido = new ArrayList<>();
                listaPedido = SPedido.retrievePedido();
                Long maior = 0L;
                for (Pedido pedido : listaPedido) {
                    if (pedido.getId() > maior){
                        maior = pedido.getId();
                    }
                }
                jnrVendaFim.setText(maior+"");
            } catch (Exception e) {
                Pandora.ex(e);
            }
        }
    }//GEN-LAST:event_jnrVendaFimMouseClicked

    private void jcdCliente1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcdCliente1MouseClicked
        if (evt.getClickCount() == 2){
            FBoxPessoa.getNewInstance().setVisible(true);
            FBoxPessoa.getInstance().addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    Pessoa pessoa = FBoxPessoa.getPessoaSelecionada();
                    if (pessoa != null){
                        jcdCliente1.setText(pessoa.getId()+"");
                        jnmCliente1.setText(pessoa.getNmPessoa());
                        FBoxPessoa.removePessoaSelecionada();
                        jqtMultiplo.grabFocus();
                        jqtMultiplo.selectAll();
                    } 
                }

            });
        }
    }//GEN-LAST:event_jcdCliente1MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if (jnrVendaInicio.getText().equals("") || jnrVendaFim.getText().equals("")){
            Pandora.msgAlerta("Clique em Processar antes de imprimir o relatório");
            return;
        }
        
        DefaultTableModel dm = (DefaultTableModel) jTableFiado.getModel();

        if (dm.getRowCount() == 0){
            Pandora.msgAlerta("Clique em Processar antes de imprimir o relatório");
            return;
        }
        
        String texto = "          CONTROLE DE CREDIARIO "+ cd_terminal +"\n\n";
        try {
            texto += "Data: " + Pandora.getAgoraSTR() + "\n";
        } catch (Exception e) {
        }
        texto += "Periodo de Vendas: " + jnrVendaInicio.getText() + " a " + jnrVendaFim.getText() + "\n\n";
        
//        texto += "PESSOA = QTDE - VALOR\n";
        for (int i = 0; i < dm.getRowCount(); i++) {
            Pessoa pessoa = (Pessoa) dm.getValueAt(i, 0);
            texto += pessoa.getNmPessoa();
            
            Totalizador tot = (Totalizador) dm.getValueAt(i, 1);
            texto += " = " + tot.toString() + "\n";
        }
        
        texto += "\nTOTAL: " + jvlTotalFiado.getText() + "\n\n\n\n\n";
        if (in_impressao){
            PP2.imprimeTextoSimples(texto);
        } else {
            Pandora.msgInfo(texto);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if (jTableFiado.getSelectedRow() >= 0){
            Pessoa pessoa = (Pessoa) jTableFiado.getValueAt(jTableFiado.getSelectedRow(), 0);
            
            if (pessoa != null && pessoa.getId() != null){
                FPagaDivida box = new FPagaDivida(pessoa);
                box.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        jButton9.doClick();
                    }
                    
                });
                
                box.setVisible(true);
            }
        } else {
            Pandora.msgAlerta("Selecione a dívida a pagar");
            
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        
        try {
            if (jnrVendaPrint.getText().equals("")){
                throw new Exception("Informe o número da venda que deseja reimprimir.");
            }
            
            Pedido pedido = SPedido.retrievePedido(Long.parseLong(jnrVendaPrint.getText()));
            String texto = "";
            
            String codigoPedido = pedido.getId()+"";
            
            while (codigoPedido.length() < 8){
                codigoPedido = "0" + codigoPedido;
            }
            
            if (jinprimirseparado.isSelected()){
                for (PedidoProduto pp : pedido.getListaProduto()) {
                    jnf.setValue(pp.getProduto().getVlProduto());
                    texto = pp.getProduto().getDsProduto() + "  " + jnf.getText();
                    if (in_impressao){
                        PP2.imprimeCaminhada(codigoPedido, texto, null, cd_terminal);
                    } else {
                        Pandora.msgInfo(texto);
                    }
                }
            } else {
                for (PedidoProduto pp : pedido.getListaProduto()) {
                    jnf.setValue(pp.getProduto().getVlProduto());
                    texto += "\n" + pp.getProduto().getDsProduto() + "  " + jnf.getText();
                }
                if (in_impressao){
                    PP2.imprimeCaminhada(codigoPedido, texto, null, cd_terminal);
                } else {
                    Pandora.msgInfo(texto);
                }
            }
            
            try {
                String compl = "Agrupado";
                if (jinprimirseparado.isSelected()){
                    compl = "Separado";
                }
            } catch (Exception e) {
                
            }
            
            
        } catch (Exception e) {
            Pandora.ex(e);
        }
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

        if (jTable2.getRowCount() == 0){
            jqtMultiplo.grabFocus();
            jqtMultiplo.selectAll();
            return;
        }
        
        Map<String, Totalizador> totalPorProduto = new HashMap<>();
        DefaultTableModel dm = (DefaultTableModel) jTable2.getModel();
        String texto = "";
        for (int i = 0; i < dm.getRowCount(); i++) {
            Produto pro = (Produto) dm.getValueAt(i, 0);
            String valor = (String) dm.getValueAt(i, 1);
            //Calcula Produtos
            BigDecimal qtd = BigDecimal.ONE;
            BigDecimal val = pro.getVlProduto();
            totalPorProduto.computeIfAbsent(pro.getDsProduto(), k -> new Totalizador()).adicionar(qtd, val);
//            if (pro.getCdGrupo() != 1L){
//                texto += "\n" + pro.getDsProduto();
//            } else {
//                texto += "\n" + pro.getDsProduto() + "  " + valor;
//            }
        }
        
        
        for (Map.Entry<String, Totalizador> entry : totalPorProduto.entrySet()) {
            jnf.setValue(entry.getValue().getValor());
            texto += entry.getValue().getQuantidade() + " - " + entry.getKey() + "   (" + jnf.getText() +  ")\n";
        }
        
//        Pandora.msgInfo(texto);
        FConferencia box = new FConferencia(texto);
        box.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                jqtMultiplo.grabFocus();
                jqtMultiplo.selectAll();
            }
            
        });
        box.setVisible(true);
        
//        PP2.imprimeTextoSimples(texto);

    }//GEN-LAST:event_jButton15ActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        if (evt.getClickCount() == 3){
            in_impressao = !in_impressao;
            if (!in_impressao){
                jLabelImpressao.setText("Não imprimir");
            } else {
                jLabelImpressao.setText(" ");
                
            }
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jResp1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jResp1KeyReleased
        jResp2.setText(jResp1.getText());
    }//GEN-LAST:event_jResp1KeyReleased

    private void jResp2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jResp2KeyReleased
        jResp1.setText(jResp2.getText());
    }//GEN-LAST:event_jResp2KeyReleased

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FVenda("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelImpressao;
    private javax.swing.JLabel jLabelPagamento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private EditFiled.JNumberLetterField jResp1;
    private EditFiled.JNumberLetterField jResp2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTableFiado;
    private javax.swing.JTable jTablePagamento;
    private javax.swing.JTable jTableProdutos;
    private javax.swing.JButton jbrNovoPessoa1;
    private javax.swing.JButton jbtBuscaPessoa1;
    private javax.swing.JButton jbtLimpaPessoa1;
    private EditFiled.JNumberLetterField jcdCliente1;
    private javax.swing.JCheckBox jinSeparado;
    private javax.swing.JCheckBox jinprimirseparado;
    private JNumberField.JNumberField jnf;
    private EditFiled.JNumberLetterField jnmCliente1;
    private EditFiled.JNumberLetterField jnrVendaFim;
    private EditFiled.JNumberLetterField jnrVendaInicio;
    private EditFiled.JNumberLetterField jnrVendaPrint;
    private EditFiled.JNumberLetterField jqtMultiplo;
    private JNumberField.JNumberField jvlDinheiro;
    private JNumberField.JNumberField jvlTotal;
    private JNumberField.JNumberField jvlTotalFiado;
    private JNumberField.JNumberField jvlTotalPagamentos;
    private JNumberField.JNumberField jvlTotalProdutos;
    private JNumberField.JNumberField jvlTroco;
    // End of variables declaration//GEN-END:variables



    private String getTextoBotaoProduto(Produto produto, String valorProduto){
        return "<html><center>"
                + "<b style='font-size:13px;'>" + escapeHtml(produto.getDsProduto()) + "</b>"
                + "<br>"
                + "<span style='font-size:16px;'>" + escapeHtml(valorProduto) + "</span>"
                + "</center></html>";
    }

    private String escapeHtml(String texto){
        if (texto == null){
            return "";
        }
        return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    private void addProduto(Produto produto){
        
        if (jqtMultiplo.getText().equals("")){
            jqtMultiplo.setText("1");
        }
        int quantidade = Integer.parseInt(jqtMultiplo.getText());
        
        for (int i = 0; i < quantidade; i++) {
            DefaultTableModel dm = (DefaultTableModel) jTable2.getModel();
            dm.setRowCount(dm.getRowCount()+1);
            dm.setValueAt(produto, dm.getRowCount()-1, 0);
            jnf.setValue(produto.getVlProduto());
            dm.setValueAt(jnf.getText(), dm.getRowCount()-1, 1);
            atualizaContador();
            jqtMultiplo.setText("1");
            jqtMultiplo.grabFocus();
            jqtMultiplo.selectAll();
        }
        
    }
    
    
    private void atualizaContador(){
        DefaultTableModel dm = (DefaultTableModel) jTable2.getModel();
        
        BigDecimal somaTotal = BigDecimal.ZERO;
        for (int i = 0; i < dm.getRowCount(); i++) {
            Produto produto = (Produto) jTable2.getValueAt(i, 0);
            somaTotal = somaTotal.add(produto.getVlProduto());
        }
        
        jLabelPagamento.setText("Tipo de Pagamento");
        if (!tipo_pagamento.equals("")){
            jLabelPagamento.setText("Tipo de Pagamento: " + tipo_pagamento.toUpperCase());
        }
        
        jvlTotal.setValue(somaTotal);
        calculaTroco();
//        jqtMultiplo.setText("1");
//        jqtMultiplo.grabFocus();
//        jqtMultiplo.selectAll();
    }

    private void limparTela() {
        DefaultTableModel dm = (DefaultTableModel) jTable2.getModel();
        dm.setRowCount(0);
        jcdCliente1.setText("");
        jnmCliente1.setText("");
        jvlDinheiro.setValue(BigDecimal.ZERO);
        jvlTroco.setValue(BigDecimal.ZERO);
        tipo_pagamento = "";
        atualizaContador();
        jnmCliente1.setText("CLIENTE NÃO IDENTIFICADO");
        jqtMultiplo.setText("1");
        jqtMultiplo.grabFocus();
        jqtMultiplo.selectAll();
    }

    private void calculaTroco() {
        if (jvlTotal.getValue().doubleValue() >= jvlDinheiro.getValue().doubleValue()){
            jvlTroco.setValue(BigDecimal.ZERO);
        } else {
            jvlTroco.setValue(jvlTotal.getValue().subtract(jvlDinheiro.getValue()));
        }
    }

}
