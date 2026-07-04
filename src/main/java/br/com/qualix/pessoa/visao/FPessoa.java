/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.pessoa.visao;

import br.com.qualix.aaaConfig.Configuracoes;
import br.com.qualix.aaaFerramentas.FLoad;
import br.com.qualix.aaaFerramentas.Pandora;
import br.com.qualix.pessoa.modelo.Pessoa;
import br.com.qualix.pessoa.servico.SPessoa;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wagner
 */


public class FPessoa extends javax.swing.JFrame {

    /**
     * Creates new form FGrupo
     */
    private static int STATUS = Configuracoes.STATUS_TELA_PADRAO;
    private static FPessoa INSTANCE;
    private static Pessoa obj_pessoa;
    private static boolean in_select = false;
    private static boolean in_empresa = false;
    private static Long temp_codigo = null;
    
    public FPessoa(String param) {
        
        initComponents();
        validaCampos(Configuracoes.STATUS_TELA_PADRAO);
        
        
        jBtConsulta1.setVisible(false);
        in_empresa = false;
        in_select = false;
        if (param != null){
            if (param.equals("EMPRESA") || param.equals("EMPRESAEDIT")){
                setTitle("Cadastro da Empresa");
                in_empresa = true;
                if (param.equals("EMPRESAEDIT")){
                    
                    
                    FLoad faguarde = new FLoad();

                    Thread t = new Thread() {
                        public void run() {
                            faguarde.setVisible(true);
                            try {

                                jBtConsulta.doClick();
                                try {
                                    Thread.sleep(800);
                                } catch (Exception e) {

                                }

                                jTable1.setRowSelectionInterval(0, 0);
                                Pessoa pessoa = (Pessoa) jTable1.getValueAt(jTable1.getSelectedRow(), 1);
                                jcdPessoa.setText(pessoa.getId()+"");
                                jdsPessoa.setText(pessoa.getNmPessoa());
                                jtpPessoa.setSelectedItem(pessoa.getTpPessoa());
                                jnrCPF.setText(pessoa.getNrCPFCNPJ());
                                jnrRG.setText(pessoa.getNrRGIE());
                                try {
                                    jdtNasc.setText(Pandora.LD_STR(pessoa.getDtNasc()));
                                } catch (Exception e) {

                                }
                                jnrTelefone1.setText(pessoa.getNrTelefone1());
                                jnrTelefone2.setText(pessoa.getNrTelefone2());
                                jdsEndereco.setText(pessoa.getDsEndereco());
                                jdsCidade.setText(pessoa.getDsCidade());
                                jdsUF.setText(pessoa.getDsUF());
                                jdsEmail.setText(pessoa.getDsEmail());
                                validaCampos(Configuracoes.STATUS_TELA_CONSULTA);
                                jTabbedPane1.setSelectedIndex(0);
                                jbtAlterar.doClick();
                                
                                

                            } catch (Exception ex) {
                                faguarde.dispose();
                                Pandora.msgInfo("Erro ao abrir Relatório\n" + ex.getMessage());
                            }

                            faguarde.dispose();
                        }
                    };

                    t.start();
                    
                    
                    
                    
                    
                    
                } else {
                    jbtNovo.doClick();
                }
            }
            if (param.equals("AUTOCADASTRO")){
                in_select = true;
                jbtNovo.doClick();
            }
        }
        
        try {
            this.setIconImage(Configuracoes.LOGO_SISTEMA);
        } catch (Exception e) {
            
        }
        
    }

    public static Pessoa getPessoaSelecionada(){
        return obj_pessoa;
    }
    
    public static FPessoa getNewInstance(){
        INSTANCE = new FPessoa(null);
        return INSTANCE;
    }
    
    public static FPessoa getNewInstanceAutoCadastro(){
        INSTANCE = new FPessoa("AUTOCADASTRO");
        return INSTANCE;
    }
    
    public static FPessoa getNewInstanceEmpresa(boolean inCadFeito){
        if (inCadFeito){
            INSTANCE = new FPessoa("EMPRESAEDIT");
        } else {
            INSTANCE = new FPessoa("EMPRESA");
        }
        return INSTANCE;
    }
    
    public static FPessoa getInstance(){
        if (INSTANCE == null){
            INSTANCE = new FPessoa(null);
        }
        return INSTANCE;
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
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jbtNovo = new javax.swing.JButton();
        jbtAlterar = new javax.swing.JButton();
        jbtExcluir = new javax.swing.JButton();
        jbtGrava = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jcdPessoa = new EditFiled.JNumberLetterField();
        jdsPessoa = new EditFiled.JNumberLetterField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtpPessoa = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jnrCPF = new EditFiled.JNumberLetterField();
        jnrRG = new EditFiled.JNumberLetterField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jdtNasc = new EditFiled.JNumberLetterField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jnrTelefone1 = new EditFiled.JNumberLetterField();
        jLabel9 = new javax.swing.JLabel();
        jnrTelefone2 = new EditFiled.JNumberLetterField();
        jLabel10 = new javax.swing.JLabel();
        jdsEndereco = new EditFiled.JNumberLetterField();
        jLabel11 = new javax.swing.JLabel();
        jdsCidade = new EditFiled.JNumberLetterField();
        jdsUF = new EditFiled.JNumberLetterField();
        jLabel12 = new javax.swing.JLabel();
        jdsEmail = new EditFiled.JNumberLetterField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jBtConsulta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBtConsulta1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Clientes");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jbtNovo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbtNovo.setText("Novo");
        jbtNovo.setPreferredSize(new java.awt.Dimension(90, 50));
        jbtNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNovoActionPerformed(evt);
            }
        });
        jPanel1.add(jbtNovo);

        jbtAlterar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbtAlterar.setText("Alterar");
        jbtAlterar.setPreferredSize(new java.awt.Dimension(90, 50));
        jbtAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAlterarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtAlterar);

        jbtExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbtExcluir.setText("Excluir");
        jbtExcluir.setPreferredSize(new java.awt.Dimension(90, 50));
        jbtExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExcluirActionPerformed(evt);
            }
        });
        jPanel1.add(jbtExcluir);

        jbtGrava.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbtGrava.setText("Gravar");
        jbtGrava.setPreferredSize(new java.awt.Dimension(90, 50));
        jbtGrava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGravaActionPerformed(evt);
            }
        });
        jPanel1.add(jbtGrava);

        jbtCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbtCancelar.setText("Cancelar");
        jbtCancelar.setPreferredSize(new java.awt.Dimension(90, 50));
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtCancelar);

        jLabel1.setText("Código");

        jcdPessoa.setText("jNumberLetterField1");
        jcdPessoa.setTamanhoMax(5);

        jdsPessoa.setText("jNumberLetterField2");
        jdsPessoa.setTamanhoMax(50);
        jdsPessoa.setUppercase(true);

        jLabel2.setText("Nome");

        jLabel4.setText("Tipo");

        jtpPessoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pessoa Física", "Pessoa Jurídica" }));

        jLabel5.setText("CPF ou CNPJ");

        jnrCPF.setText("jNumberLetterField2");
        jnrCPF.setTamanhoMax(30);
        jnrCPF.setUppercase(true);
        jnrCPF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jnrCPFFocusLost(evt);
            }
        });

        jnrRG.setText("jNumberLetterField3");
        jnrRG.setTamanhoMax(30);
        jnrRG.setUppercase(true);

        jLabel6.setText("RG ou IE");

        jLabel7.setText("Data Nasc. / Fundação");

        jdtNasc.setText("jNumberLetterField4");
        jdtNasc.setTamanhoMax(10);
        jdtNasc.setUppercase(true);
        jdtNasc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jdtNascFocusLost(evt);
            }
        });

        jLabel8.setText("Telefone Principal");

        jnrTelefone1.setText("jNumberLetterField5");
        jnrTelefone1.setTamanhoMax(20);
        jnrTelefone1.setUppercase(true);
        jnrTelefone1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jnrTelefone1FocusLost(evt);
            }
        });

        jLabel9.setText("Telefone Secundário");

        jnrTelefone2.setText("jNumberLetterField5");
        jnrTelefone2.setTamanhoMax(20);
        jnrTelefone2.setUppercase(true);
        jnrTelefone2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jnrTelefone2FocusLost(evt);
            }
        });

        jLabel10.setText("Endereço");

        jdsEndereco.setText("jNumberLetterField7");
        jdsEndereco.setTamanhoMax(50);
        jdsEndereco.setUppercase(true);

        jLabel11.setText("Cidade");

        jdsCidade.setText("jNumberLetterField8");
        jdsCidade.setTamanhoMax(50);
        jdsCidade.setUppercase(true);

        jdsUF.setText("jNumberLetterField9");
        jdsUF.setTamanhoMax(2);
        jdsUF.setUppercase(true);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("UF");

        jdsEmail.setText("jNumberLetterField5");
        jdsEmail.setTamanhoMax(100);
        jdsEmail.setUppercase(false);

        jLabel3.setText("E-mail");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtpPessoa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcdPessoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdsPessoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                    .addComponent(jnrCPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jnrRG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jdtNasc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jnrTelefone1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jnrTelefone2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)))
                            .addComponent(jdsEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jdsCidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                    .addComponent(jdsUF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jdsEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcdPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdsPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtpPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jnrCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jnrRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdtNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jnrTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jnrTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdsEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jdsEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdsCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdsUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cadastro", jPanel2);

        jBtConsulta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBtConsulta.setText("Pesquisa");
        jBtConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtConsultaActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Telefone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(30);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(126);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(126);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(420);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(180);
        }

        jBtConsulta1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBtConsulta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/print.png"))); // NOI18N
        jBtConsulta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtConsulta1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtConsulta1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jBtConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jBtConsulta1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(478, 478, 478))))
        );

        jTabbedPane1.addTab("Consulta", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtGravaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGravaActionPerformed
        try {
            Long id = null;
            
            if (!jcdPessoa.getText().equals("")){
                id = Long.parseLong(jcdPessoa.getText());
            }
            temp_codigo = id;
            
            LocalDate dtNasc = null;
            if (!jdtNasc.getText().equals("")){
                dtNasc = Pandora.STR_LD(jdtNasc.getText());
            }
            obj_pessoa = new Pessoa();
            obj_pessoa = SPessoa.gravaPessoa(id, jdsPessoa.getText(), null, jtpPessoa.getSelectedItem().toString(), jnrCPF.getText(), jnrRG.getText(), dtNasc, jnrTelefone1.getText(), jnrTelefone2.getText(), jdsEmail.getText(), jdsEndereco.getText(), jdsCidade.getText(), jdsUF.getText());
            
            if (in_empresa){
                Configuracoes.EMPRESA = SPessoa.retrievePessoa(1L);
                Thread t = new Thread() {
                    public void run() {
                        try {
                        } catch (Exception ex) {
                        }
                    }
                };
                t.start();
            }
            
            if (in_select || in_empresa){
                dispose();
            } else {
                if (id == null){
                    JOptionPane.showMessageDialog(null, Configuracoes.MSG_PADRAO_INCLUIDO);
                } else {
                    JOptionPane.showMessageDialog(null, Configuracoes.MSG_PADRAO_ALTERADO);
                }
                validaCampos(Configuracoes.STATUS_TELA_PADRAO);
                jBtConsulta.doClick();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jbtGravaActionPerformed

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
       validaCampos(Configuracoes.STATUS_TELA_PADRAO);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jbtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExcluirActionPerformed
        try {
            int opt = JOptionPane.showConfirmDialog(null, Configuracoes.MSG_PADRAO_DESEJAEXCLUIR);
            if (opt == 0){
                Long id = null;
                if (!jcdPessoa.getText().equals("")){
                    id = Long.parseLong(jcdPessoa.getText());
                }
                SPessoa.deletePessoa(id);
                JOptionPane.showMessageDialog(null, Configuracoes.MSG_PADRAO_DELETE);
                validaCampos(Configuracoes.STATUS_TELA_PADRAO);
                jBtConsulta.doClick();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jbtExcluirActionPerformed

    private void jBtConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtConsultaActionPerformed
        try {
            ArrayList<Pessoa> lista = SPessoa.retrievePessoa();
            
            //Ordenação aqui
            DefaultTableModel dm = (DefaultTableModel) jTable1.getModel();
            dm.setNumRows(0);
            
            
            boolean inMostra;
            for (Pessoa pessoa : lista) {
                inMostra = false;
                if (in_empresa && pessoa.getId() == 1){
                    inMostra = true;
                } else if (!in_empresa && pessoa.getId() != 1){
                    inMostra = true;
                }
//
                if (inMostra){
                    dm.setNumRows(dm.getRowCount()+1);
                    dm.setValueAt(pessoa.getId(), dm.getRowCount()-1, 0);
                    dm.setValueAt(pessoa, dm.getRowCount()-1, 1);
                    dm.setValueAt(pessoa.getNrTelefone1(), dm.getRowCount()-1, 2);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }//GEN-LAST:event_jBtConsultaActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2){
            if (jTable1.getSelectedRow() >= 0){
                Pessoa pessoa = (Pessoa) jTable1.getValueAt(jTable1.getSelectedRow(), 1);
                jcdPessoa.setText(pessoa.getId()+"");
                jdsPessoa.setText(pessoa.getNmPessoa());
                jtpPessoa.setSelectedItem(pessoa.getTpPessoa());
                jnrCPF.setText(pessoa.getNrCPFCNPJ());
                jnrRG.setText(pessoa.getNrRGIE());
                try {
                    jdtNasc.setText(Pandora.LD_STR(pessoa.getDtNasc()));
                } catch (Exception e) {
                    
                }
                jnrTelefone1.setText(pessoa.getNrTelefone1());
                jnrTelefone2.setText(pessoa.getNrTelefone2());
                if (pessoa.getDsEmail() != null){
                    jdsEmail.setText(pessoa.getDsEmail());
                }
                jdsEndereco.setText(pessoa.getDsEndereco());
                jdsCidade.setText(pessoa.getDsCidade());
                jdsUF.setText(pessoa.getDsUF());
                
                validaCampos(Configuracoes.STATUS_TELA_CONSULTA);
                jTabbedPane1.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jbtAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAlterarActionPerformed
        validaCampos(Configuracoes.STATUS_TELA_EDICAO);
    }//GEN-LAST:event_jbtAlterarActionPerformed

    private void jbtNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNovoActionPerformed
        validaCampos(Configuracoes.STATUS_TELA_EDICAO);
    }//GEN-LAST:event_jbtNovoActionPerformed

    private void jdtNascFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jdtNascFocusLost
        try {
            jdtNasc.setText(Pandora.validaDataNasc(jdtNasc.getText()));
        } catch (Exception e) {
            jdtNasc.setText("");
            jdtNasc.grabFocus();
            Pandora.ex(e);
        }
    }//GEN-LAST:event_jdtNascFocusLost

    private void jBtConsulta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtConsulta1ActionPerformed
        try {
            
            SPessoa.imprimirFichaPessoa(2l);
        } catch (Exception e) {
            Pandora.ex(e);
        }
    }//GEN-LAST:event_jBtConsulta1ActionPerformed

    private void jnrCPFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jnrCPFFocusLost
        try {
            String cpf = "";
            cpf = jnrCPF.getText();
            if (jtpPessoa.getSelectedIndex() == 0){
                cpf = Pandora.imprimeCPF(jnrCPF.getText());
                jnrCPF.setText(cpf);
            } else {
                cpf = Pandora.imprimeCNPJ(jnrCPF.getText());
                jnrCPF.setText(cpf);
            }
        } catch (Exception ex) {
            jnrCPF.setText("");
            jnrCPF.grabFocus();
            Pandora.msgException(ex);
        }
            
    }//GEN-LAST:event_jnrCPFFocusLost

    private void jnrTelefone1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jnrTelefone1FocusLost
        String telefone = "";
        telefone = jnrTelefone1.getText();
        try {
            telefone = Pandora.imprimeTelefone(jnrTelefone1.getText());
            jnrTelefone1.setText(telefone);
        } catch (Exception ex) {
            jnrTelefone1.setText(telefone);
            jnrTelefone1.grabFocus();
            Pandora.msgException(ex);
        }
    }//GEN-LAST:event_jnrTelefone1FocusLost

    private void jnrTelefone2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jnrTelefone2FocusLost
        String telefone = "";
        telefone = jnrTelefone2.getText();
        try {
            telefone = Pandora.imprimeTelefone(jnrTelefone2.getText());
            jnrTelefone2.setText(telefone);
        } catch (Exception ex) {
            jnrTelefone2.setText(telefone);
            jnrTelefone2.grabFocus();
            Pandora.msgException(ex);
        }
    }//GEN-LAST:event_jnrTelefone2FocusLost

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FPessoa(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtConsulta;
    private javax.swing.JButton jBtConsulta1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtAlterar;
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtExcluir;
    private javax.swing.JButton jbtGrava;
    private javax.swing.JButton jbtNovo;
    private EditFiled.JNumberLetterField jcdPessoa;
    private EditFiled.JNumberLetterField jdsCidade;
    private EditFiled.JNumberLetterField jdsEmail;
    private EditFiled.JNumberLetterField jdsEndereco;
    private EditFiled.JNumberLetterField jdsPessoa;
    private EditFiled.JNumberLetterField jdsUF;
    private EditFiled.JNumberLetterField jdtNasc;
    private EditFiled.JNumberLetterField jnrCPF;
    private EditFiled.JNumberLetterField jnrRG;
    private EditFiled.JNumberLetterField jnrTelefone1;
    private EditFiled.JNumberLetterField jnrTelefone2;
    private javax.swing.JComboBox<String> jtpPessoa;
    // End of variables declaration//GEN-END:variables


    private void validaCampos(int cdStatus){
        STATUS = cdStatus;
        if (cdStatus == Configuracoes.STATUS_TELA_PADRAO){
            limpaCampos();
            jcdPessoa.setEnabled(false);
            jdsPessoa.setEnabled(false);
            jtpPessoa.setEnabled(false);
            jnrCPF.setEnabled(false);
            jnrRG.setEnabled(false);
            jdtNasc.setEnabled(false);
            jnrTelefone1.setEnabled(false);
            jnrTelefone2.setEnabled(false);
            jdsEmail.setEnabled(false);
            jdsEndereco.setEnabled(false);
            jdsCidade.setEnabled(false);
            jdsUF.setEnabled(false);
        } else if (cdStatus == Configuracoes.STATUS_TELA_EDICAO){
            jcdPessoa.setEnabled(false);
            jdsPessoa.setEnabled(true);
            jtpPessoa.setEnabled(true);
            jnrCPF.setEnabled(true);
            jnrRG.setEnabled(true);
            jdtNasc.setEnabled(true);
            jnrTelefone1.setEnabled(true);
            jnrTelefone2.setEnabled(true);
            jdsEmail.setEnabled(true);
            jdsEndereco.setEnabled(true);
            jdsCidade.setEnabled(true);
            jdsUF.setEnabled(true);
            jdsPessoa.grabFocus();
        }
        validaBotoes(cdStatus);
    }
    
    private void validaBotoes(int cdStatus){
        if (cdStatus == Configuracoes.STATUS_TELA_PADRAO){
            jbtNovo.setEnabled(true);
            jbtAlterar.setEnabled(false);
            jbtExcluir.setEnabled(false);
            jbtGrava.setEnabled(false);
            jbtCancelar.setEnabled(false);
        } else if (cdStatus == Configuracoes.STATUS_TELA_EDICAO){
            jbtNovo.setEnabled(false);
            jbtAlterar.setEnabled(false);
            jbtExcluir.setEnabled(false);
            jbtGrava.setEnabled(true);
            jbtCancelar.setEnabled(true);
        } else if (cdStatus == Configuracoes.STATUS_TELA_CONSULTA){
            jbtNovo.setEnabled(false);
            jbtAlterar.setEnabled(true);
            jbtExcluir.setEnabled(true);
            jbtGrava.setEnabled(false);
            jbtCancelar.setEnabled(true);
        }
    }
    
    private void limpaCampos(){
        jcdPessoa.setText("");
        jdsPessoa.setText("");
        jtpPessoa.setSelectedIndex(0);
        jnrCPF.setText("");
        jnrRG.setText("");
        jdtNasc.setText("");
        jnrTelefone1.setText("");
        jnrTelefone2.setText("");
        jdsEmail.setText("");
        jdsEndereco.setText("");
        jdsCidade.setText("");
        jdsUF.setText("");
        
    }
    
}
