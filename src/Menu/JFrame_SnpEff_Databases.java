package Menu;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Objeto de características que modificarão o comando do SNPEff sobre o arquivo VCF selecionado
 * @author eliseumedeiros
 */
public class JFrame_SnpEff_Databases extends javax.swing.JFrame {

    /**
     * Creates new form JFrame_SnpEff_Databases1
     */
    public JFrame_SnpEff_Databases() {
        initComponents();
        verificarSistema();
        //setIconImage(new ImageIcon(getClass().getResource("/Icons/icon_gene.png").getPath()).getImage());
        this.jPanelLoadingDownload.setVisible(false);
        this.caminho = "";
        this.dbSelected = "";
        this.memory = " -Xmx4G";
        this.jRadioBSavedDb.setSelected(true);
        listRadioBEffDataBases = new ArrayList<>();
        listRadioBSiftDataBases = new ArrayList<>();
        listAllRadioButtonEFFDatabases();
        listAllRadioButtonSiftDatabases();
        this.buttonGroup3.add(this.jRadioBOtherDb);
        this.buttonGroup3.add(this.jRadioBSavedDb);
        
    }
    private void verificarSistema() {
        sistemaLocal = System.getProperties().getProperty("sun.desktop");
        sep =(String) System.getProperties().get("file.separator");
        if(sistemaLocal.equals("windows")){
            comandoConsole = "cmd.exe /c ";
            mover = "move /Y ";
            deletar = "del /Q";
            removerDir= "rd /S /Q";
        }else{
            comandoConsole = "";
            mover = "mv ";
            deletar = "rm";
            removerDir = "rm -r";
        }
    }
    public String getComandoConsole(){
        return this.comandoConsole;
    }
     public String getMover(){
        return this.mover;
    }
      public String getDeletar(){
        return this.deletar;
    }
       public String getRemoverDir(){
        return this.removerDir;
    }
    public void listAllRadioButtonEFFDatabases(){
        File file = new File(System.getProperty("user.dir")+sep+"snpEff"+sep+"data");
        if(!file.exists()){
            file.mkdirs();
            return;
        }  
        String[] directories = file.list(new FilenameFilter() {
          @Override
          public boolean accept(File current, String name) {
            return new File(current, name).isDirectory();
          }
        });
        jPanelEffDatabases.removeAll();
            this.listRadioBEffDataBases.removeAll(listRadioBEffDataBases);
            this.buttonGroup1 = new javax.swing.ButtonGroup();
        for(String a: directories){
            final javax.swing.JRadioButton aux = new javax.swing.JRadioButton();
            aux.setBackground(new java.awt.Color(237,255,237));
            aux.setText(a);
            aux.setName(a);
            aux.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jRadioButtonActionPerformed(evt, aux.getText());
                }
            });
            jPanelEffDatabases.add(aux);
            this.listRadioBEffDataBases.add(aux);
            this.buttonGroup1.add(aux);
        }
        if(!this.listRadioBEffDataBases.isEmpty()){
            this.listRadioBEffDataBases.get(0).setSelected(true);
            this.caminho = this.listRadioBEffDataBases.get(0).getText();
        }
        this.jPanelEffDatabases.setVisible(false);
        this.jPanelEffDatabases.setVisible(true);
    }
    public void listAllRadioButtonSiftDatabases(){
        File file = new File(System.getProperty("user.dir")+sep+"snpEff"+sep+"data");
        if(!file.exists()){
            file.mkdirs();
            return;
        }
        String[] directories = file.list(new FilenameFilter() {
          @Override
          public boolean accept(File current, String name) {
              if(name.substring(name.length()-2).equals("gz") && new File(current, name).isFile())
                  return true;
            return false;
          }
        });
        for(String a: directories){
            final javax.swing.JRadioButton aux = new javax.swing.JRadioButton();
            aux.setBackground(new java.awt.Color(237,255,237));
            aux.setText(a);
            aux.setName(a);
            aux.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jRadioButtonSiftActionPerformed(evt, aux.getText());
                }
            });
            jPanelSiftDatabases.add(aux);
            this.listRadioBSiftDataBases.add(aux);
            this.buttonGroup2.add(aux);
        }  
        if(!this.listRadioBSiftDataBases.isEmpty()){
            this.listRadioBSiftDataBases.get(0).setSelected(true);
            this.dbSelected = this.listRadioBSiftDataBases.get(0).getText();
        }
        else{
            this.jRadioBOtherDb.setSelected(true);
            if(this.jTextFSiftPath.getText().isEmpty()){
                this.dbSelected = "";
            }
            else{
                this.dbSelected = this.jTextFSiftPath.getText();
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanelAllComponents = new javax.swing.JPanel();
        jLabelTitleFrame = new javax.swing.JLabel();
        jButtonOk = new javax.swing.JButton();
        jPanelMainComponents = new javax.swing.JPanel();
        jPanelLoadingDownload = new javax.swing.JPanel();
        jLabelLoadingMsg = new javax.swing.JLabel();
        jLabelLoading1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanelEff = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jTextFDownloadDb = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelEffDatabases = new javax.swing.JPanel();
        jButtonDbEff = new javax.swing.JButton();
        jPanelSift = new javax.swing.JPanel();
        jPanelLocation = new javax.swing.JPanel();
        jBSelectSiftPath = new javax.swing.JButton();
        jTextFSiftPath = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButtonMoveDbSift = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanelSiftDatabases = new javax.swing.JPanel();
        jRadioBSavedDb = new javax.swing.JRadioButton();
        jRadioBOtherDb = new javax.swing.JRadioButton();
        jButtonRemoveDbSift = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabelURL = new javax.swing.JLabel();
        jPanelMemory = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jComboBMemory = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BioInfo - Project");
        setIconImage(new ImageIcon(getClass().getResource("/Icons/icon_gene.png").getPath()).getImage());
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        jPanelAllComponents.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAllComponents.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 1, true));
        jPanelAllComponents.setName(""); // NOI18N

        jLabelTitleFrame.setFont(new java.awt.Font("Segoe UI Light", 1, 28)); // NOI18N
        jLabelTitleFrame.setForeground(new java.awt.Color(47, 77, 77));
        jLabelTitleFrame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitleFrame.setText("Annotators databases");

        jButtonOk.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonOk.setForeground(new java.awt.Color(47, 77, 77));
        jButtonOk.setText("Ok");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jPanelMainComponents.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMainComponents.setLayout(new javax.swing.OverlayLayout(jPanelMainComponents));

        jPanelLoadingDownload.setBackground(new java.awt.Color(255, 255, 255));
        jPanelLoadingDownload.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelLoadingMsg.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLoadingMsg.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabelLoadingMsg.setForeground(new java.awt.Color(47, 77, 77));
        jLabelLoadingMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLoadingMsg.setText("Loading... Please wait to download");

        jLabelLoading1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabelLoading1.setForeground(new java.awt.Color(47, 77, 77));
        jLabelLoading1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLoading1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/loading-gene.gif"))); // NOI18N

        javax.swing.GroupLayout jPanelLoadingDownloadLayout = new javax.swing.GroupLayout(jPanelLoadingDownload);
        jPanelLoadingDownload.setLayout(jPanelLoadingDownloadLayout);
        jPanelLoadingDownloadLayout.setHorizontalGroup(
            jPanelLoadingDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelLoadingMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
            .addGroup(jPanelLoadingDownloadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelLoading1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelLoadingDownloadLayout.setVerticalGroup(
            jPanelLoadingDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoadingDownloadLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabelLoadingMsg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelLoading1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        jPanelMainComponents.add(jPanelLoadingDownload);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanelEff.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEff.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SnpEff", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 1, 14), new java.awt.Color(31, 145, 113))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 221));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Download", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(31, 145, 113))); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(47, 77, 77));
        jButton2.setText("Download");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(47, 77, 77));
        jLabel2.setText("Database Name:");

        jButton3.setForeground(new java.awt.Color(47, 77, 77));
        jButton3.setText("View all databases");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFDownloadDb, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFDownloadDb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jScrollPane1.setBorder(null);

        jPanelEffDatabases.setBackground(new java.awt.Color(234, 255, 234));
        jPanelEffDatabases.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Databases", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(31, 145, 113))); // NOI18N
        jPanelEffDatabases.setLayout(new javax.swing.BoxLayout(jPanelEffDatabases, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(jPanelEffDatabases);

        jButtonDbEff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/remover.png"))); // NOI18N
        jButtonDbEff.setName("Remove selected database"); // NOI18N
        jButtonDbEff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDbEffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelEffLayout = new javax.swing.GroupLayout(jPanelEff);
        jPanelEff.setLayout(jPanelEffLayout);
        jPanelEffLayout.setHorizontalGroup(
            jPanelEffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEffLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDbEff, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelEffLayout.setVerticalGroup(
            jPanelEffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelEffLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDbEff, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.add(jPanelEff, java.awt.BorderLayout.PAGE_START);

        jPanelSift.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSift.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SnpSift", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 1, 14), new java.awt.Color(31, 145, 113))); // NOI18N

        jPanelLocation.setBackground(new java.awt.Color(234, 234, 255));

        jBSelectSiftPath.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBSelectSiftPath.setForeground(new java.awt.Color(47, 77, 77));
        jBSelectSiftPath.setText("Select");
        jBSelectSiftPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSelectSiftPathActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(47, 77, 77));
        jLabel3.setText("\"\".txt.gz file location:");

        jButtonMoveDbSift.setForeground(new java.awt.Color(47, 77, 77));
        jButtonMoveDbSift.setText("Move to my Saved Databases");
        jButtonMoveDbSift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMoveDbSiftActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/atencao.png"))); // NOI18N
        jLabel4.setText("The tabix index file must be in the same folder as the database.");

        javax.swing.GroupLayout jPanelLocationLayout = new javax.swing.GroupLayout(jPanelLocation);
        jPanelLocation.setLayout(jPanelLocationLayout);
        jPanelLocationLayout.setHorizontalGroup(
            jPanelLocationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLocationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLocationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLocationLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonMoveDbSift)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBSelectSiftPath))
                    .addComponent(jTextFSiftPath)
                    .addGroup(jPanelLocationLayout.createSequentialGroup()
                        .addGroup(jPanelLocationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(8, 8, 8))
        );
        jPanelLocationLayout.setVerticalGroup(
            jPanelLocationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLocationLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFSiftPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLocationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBSelectSiftPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonMoveDbSift))
                .addGap(0, 0, 0)
                .addComponent(jLabel4)
                .addGap(2, 2, 2))
        );

        jScrollPane2.setBorder(null);

        jPanelSiftDatabases.setBackground(new java.awt.Color(234, 255, 234));
        jPanelSiftDatabases.setLayout(new javax.swing.BoxLayout(jPanelSiftDatabases, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane2.setViewportView(jPanelSiftDatabases);

        jRadioBSavedDb.setBackground(new java.awt.Color(204, 255, 204));
        jRadioBSavedDb.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jRadioBSavedDb.setForeground(new java.awt.Color(47, 77, 77));
        jRadioBSavedDb.setSelected(true);
        jRadioBSavedDb.setText("Saved Databases");
        jRadioBSavedDb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioBSavedDbActionPerformed(evt);
            }
        });

        jRadioBOtherDb.setBackground(new java.awt.Color(204, 204, 255));
        jRadioBOtherDb.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jRadioBOtherDb.setForeground(new java.awt.Color(47, 77, 77));
        jRadioBOtherDb.setText("Other Database");
        jRadioBOtherDb.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jRadioBOtherDb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioBOtherDbActionPerformed(evt);
            }
        });

        jButtonRemoveDbSift.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/remover.png"))); // NOI18N
        jButtonRemoveDbSift.setName("Remove selected database"); // NOI18N
        jButtonRemoveDbSift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveDbSiftActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("*You can download dbNSFP from the SnpSift website:");

        jLabelURL.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabelURL.setForeground(new java.awt.Color(106, 163, 163));
        jLabelURL.setText("http://snpeff.sourceforge.net/SnpSift.html#dbNSFP");
        jLabelURL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelURLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelURLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelURLMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelSiftLayout = new javax.swing.GroupLayout(jPanelSift);
        jPanelSift.setLayout(jPanelSiftLayout);
        jPanelSiftLayout.setHorizontalGroup(
            jPanelSiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSiftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSiftLayout.createSequentialGroup()
                        .addGroup(jPanelSiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioBSavedDb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelSiftLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonRemoveDbSift, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jRadioBOtherDb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelLocation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelSiftLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelURL)
                        .addGap(0, 87, Short.MAX_VALUE))))
        );
        jPanelSiftLayout.setVerticalGroup(
            jPanelSiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSiftLayout.createSequentialGroup()
                .addGroup(jPanelSiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jRadioBSavedDb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioBOtherDb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSiftLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemoveDbSift))
                    .addComponent(jPanelLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelSiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabelURL)))
        );

        jPanel3.add(jPanelSift, java.awt.BorderLayout.CENTER);

        jPanelMemory.setBackground(new java.awt.Color(242, 242, 242));
        jPanelMemory.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "System Memory ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 1, 14), new java.awt.Color(31, 145, 113))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(47, 77, 77));
        jLabel7.setText("RAM to be used when running SNP: ");

        jComboBMemory.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jComboBMemory.setForeground(new java.awt.Color(47, 77, 77));
        jComboBMemory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "not specify", "1 GB", "2 GB", "3 GB", "4 GB", "5 GB", "6 GB", "7 GB", "8 GB" }));
        jComboBMemory.setSelectedIndex(4);
        jComboBMemory.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBMemoryItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelMemoryLayout = new javax.swing.GroupLayout(jPanelMemory);
        jPanelMemory.setLayout(jPanelMemoryLayout);
        jPanelMemoryLayout.setHorizontalGroup(
            jPanelMemoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMemoryLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBMemory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(275, Short.MAX_VALUE))
        );
        jPanelMemoryLayout.setVerticalGroup(
            jPanelMemoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMemoryLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelMemoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBMemory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)))
        );

        jPanel3.add(jPanelMemory, java.awt.BorderLayout.PAGE_END);

        jPanelMainComponents.add(jPanel3);

        javax.swing.GroupLayout jPanelAllComponentsLayout = new javax.swing.GroupLayout(jPanelAllComponents);
        jPanelAllComponents.setLayout(jPanelAllComponentsLayout);
        jPanelAllComponentsLayout.setHorizontalGroup(
            jPanelAllComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAllComponentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAllComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitleFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelMainComponents, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAllComponentsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelAllComponentsLayout.setVerticalGroup(
            jPanelAllComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAllComponentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitleFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMainComponents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanelAllComponents);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jBSelectSiftPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSelectSiftPathActionPerformed
        // TODO add your handling code here:
        jRadioBOtherDb.setSelected(true);
        JFileChooser chooser = new JFileChooser();
        //Se caso ocorer algum erro, não faz nada além de retornar
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "SNPSift will run before opening your file. This may take several minutes.");
            return;
        }
        this.dbSelected = chooser.getSelectedFile().toString();
        this.caminho = chooser.getSelectedFile().toString();
        this.jTextFSiftPath.setText(caminho);       
    }//GEN-LAST:event_jBSelectSiftPathActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        final String textoAtual = this.jTextFDownloadDb.getText().replaceAll(" ", ""); 
        if(textoAtual.isEmpty()){
            JOptionPane.showMessageDialog(null, "You need to enter the name of the database.");
            return;
        }
        final String dbName = this.jTextFDownloadDb.getText();
        this.jTextFDownloadDb.setText("");
        this.jLabelLoadingMsg.setText("Loading ... Please wait to download");
        new Thread(){
            @Override
            public void run(){
                 try {
                    iniciarLoading();
                    Process tr = Runtime.getRuntime().exec( comandoConsole + "java" + memory +" -jar "+System.getProperty("user.dir")+sep+"snpEff"+sep+"snpEff.jar download " + textoAtual);
                    BufferedReader stdOut=new BufferedReader(new InputStreamReader(tr.getInputStream()));
                    String s;
                    while((s=stdOut.readLine())!=null){
                           System.out.println(s);
                    }
//                    Process p = Runtime.getRuntime().exec(comandoConsole + "java" + memory +" -jar "+System.getProperty("user.dir")+sep+"snpEff"+sep+"snpEff.jar download " + textoAtual);
//                    p.waitFor();
                    System.out.println("Executado com sucesso");
                    finalizarLoading();
                    listAllRadioButtonEFFDatabases();

                    } catch (IOException ex) {
                    }
            }
            
        }.start();
        
    }//GEN-LAST:event_jButton2ActionPerformed
    public void setAllEnabled(boolean enabled){
        this.jButtonOk.setEnabled(enabled);
        this.jComboBMemory.setEnabled(enabled);
        this.jComboBMemory.setVisible(enabled);
        this.jButton2.setEnabled(enabled);
        this.jButton3.setVisible(enabled);
        this.jBSelectSiftPath.setEnabled(enabled);
        this.jButtonMoveDbSift.setVisible(enabled);
        this.jButtonRemoveDbSift.setVisible(enabled);
        this.jButtonDbEff.setEnabled(enabled);
        this.jButtonDbEff.setVisible(enabled);
        this.jRadioBOtherDb.setVisible(enabled);
        this.jRadioBSavedDb.setVisible(enabled);
        this.jTextFSiftPath.setEnabled(enabled);
        this.jTextFDownloadDb.setVisible(enabled);
        this.jPanel2.setVisible(enabled);
        this.jPanelEff.setEnabled(enabled);
        this.jPanelSift.setEnabled(enabled);
        this.jPanelEffDatabases.setVisible(enabled);
        this.jPanelLocation.setVisible(enabled);
        this.jPanelSiftDatabases.setVisible(enabled);
        this.jLabelURL.setVisible(enabled);
        for(int i = 0; i<this.listRadioBEffDataBases.size();i++){
            listRadioBEffDataBases.get(i).setEnabled(enabled);
        }
        for(int i = 0; i<this.listRadioBSiftDataBases.size();i++){
            listRadioBSiftDataBases.get(i).setEnabled(enabled);
        }
        
        
    }
    public void iniciarLoading(){
        setAllEnabled(false);
        this.jPanelLoadingDownload.setEnabled(false);
        this.jPanelLoadingDownload.setVisible(true);
    }
    public void finalizarLoading(){
        this.jPanelLoadingDownload.setVisible(false);
        setAllEnabled(true);
}
    private void jRadioBOtherDbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioBOtherDbActionPerformed
        // TODO add your handling code here:
        if(jRadioBOtherDb.isSelected() && !this.jTextFSiftPath.getText().isEmpty()){
            this.caminho = this.jTextFSiftPath.getText();
        }
    }//GEN-LAST:event_jRadioBOtherDbActionPerformed

    private void jRadioBSavedDbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioBSavedDbActionPerformed
        // TODO add your handling code here:
         if(jRadioBSavedDb.isSelected() && !this.listRadioBSiftDataBases.isEmpty()){
            for(javax.swing.JRadioButton rbutton : this.listRadioBSiftDataBases){
                if(rbutton.isSelected()){
                    System.out.println(rbutton.getText());
                }
            }
           
         }
    }//GEN-LAST:event_jRadioBSavedDbActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        final File file = new File(System.getProperty("user.dir")+sep+"snpEff"+sep+"allDatabasesToDownload.txt");
        System.out.println(System.getProperty("user.dir")+sep+"snpEff"+sep+"allDatabasesToDownload.txt");
        if(!file.exists()){
            this.jLabelLoadingMsg.setText("Loading ... wait for the databases names to be downloaded");
            new Thread(){
            @Override
            public void run(){
                 try {
                    iniciarLoading();
                    String comando = comandoConsole + "java"+ getMemory()+" -jar snpEff"+sep+"snpEff.jar databases > "+System.getProperty("user.dir")+sep+"snpEff"+sep+"allDatabasesToDownload.txt";
                    Process p = Runtime.getRuntime().exec(comando);
                    p.waitFor();
                    System.out.println(comando + "\nExecutado com sucesso");
                    //System.out.println
                    finalizarLoading();
                    } catch (IOException | InterruptedException ex) {
                        JOptionPane.showMessageDialog(null, "Sorry, could not create the file");
                    }
                  Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(file);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Sorry, could not open the file");
                }
            }
            
        }.start();
        
        }
        else{
            if(!Desktop.isDesktopSupported()){
                System.out.println("Desktop is not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(file);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Sorry, could not open the file");
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonDbEffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDbEffActionPerformed
        // TODO add your handling code here:
        for(final javax.swing.JRadioButton rButton : listRadioBEffDataBases){
            if(rButton.isSelected()){
                new Thread(){
                        @Override
                        public void run(){
                            try {
                                String comando = comandoConsole + removerDir+" snpEff"+sep+"data"+sep + rButton.getName();
                                Process p = Runtime.getRuntime().exec(comando);
                                p.waitFor();
                                System.out.println("Executado com sucesso");
                                listAllRadioButtonEFFDatabases();
                                } catch (IOException | InterruptedException ex) {
                                    JOptionPane.showMessageDialog(null, "Sorry, could not remove "+ rButton.getName() + " file");
                                }
                        }
                }.start();
                this.buttonGroup1.remove(rButton);
                this.jPanelEffDatabases.remove(rButton);
                if(!this.listRadioBEffDataBases.isEmpty()){
                    this.listRadioBEffDataBases.get(0).setSelected(true);
                }
                this.jPanelEffDatabases.setVisible(false);
                this.jPanelEffDatabases.setVisible(true);
                
            }
        }
    }//GEN-LAST:event_jButtonDbEffActionPerformed

    private void jButtonRemoveDbSiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveDbSiftActionPerformed
        // TODO add your handling code here:
        for(final javax.swing.JRadioButton rButton : listRadioBSiftDataBases){
            if(rButton.isSelected()){
                new Thread(){
                        @Override
                        public void run(){
                            try {
                                String comando = comandoConsole + deletar+" snpEff"+sep+"data"+sep + rButton.getName();
                                System.out.println("Comando: " + comando);
                                Process p;
                                p = Runtime.getRuntime().exec(comando);
                                p.waitFor();
                                System.out.print(p.getOutputStream());
                                System.out.println("Executado com sucesso");
                                listAllRadioButtonSiftDatabases();
                            } catch (IOException | InterruptedException ex) {
                                JOptionPane.showMessageDialog(null, "Sorry, could not remove "+ rButton.getName() + " file");
                            }
                        }
                }.start();
                this.buttonGroup2.remove(rButton);
                this.jPanelSiftDatabases.remove(rButton);
                if(!this.listRadioBSiftDataBases.isEmpty()){
                    this.listRadioBSiftDataBases.get(0).setSelected(true);
                }
                this.jPanelSiftDatabases.setVisible(false);
                this.jPanelSiftDatabases.setVisible(true);
                
            }
        }
    }//GEN-LAST:event_jButtonRemoveDbSiftActionPerformed

    private void jButtonMoveDbSiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveDbSiftActionPerformed
        if(jTextFSiftPath.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "You need to select a file");
                return;
        }

        new Thread(){
                @Override
                public void run(){
                    try {
                        String comando = comandoConsole + mover +jTextFSiftPath.getText() + " "+System.getProperty("user.dir")+sep+"snpEff"+sep+"data";
                        String comando2 = comandoConsole + mover + jTextFSiftPath.getText() + ".tbi" + " "+System.getProperty("user.dir")+sep+"snpEff"+sep+"data";
                        Process p;
                        p = Runtime.getRuntime().exec(comando);
                        p.waitFor();
                        System.out.println(comando + "\nExecutado com sucesso");
                        p = Runtime.getRuntime().exec(comando2);
                        p.waitFor();
                        listAllRadioButtonSiftDatabases();
                        jPanelSiftDatabases.setVisible(false);
                        jPanelSiftDatabases.setVisible(true);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Sorry, could not move file");
                    } catch (InterruptedException ex) {
                        JOptionPane.showMessageDialog(null, "Sorry, could not remove move file");
                    }
                }
        }.start();

                this.jTextFSiftPath.setText("");
                this.jRadioBSavedDb.setSelected(true);

    }//GEN-LAST:event_jButtonMoveDbSiftActionPerformed

    private void jLabelURLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelURLMouseClicked
        // TODO add your handling code here:
        try {
          java.awt.Desktop.getDesktop().browse( new java.net.URI( "http://snpeff.sourceforge.net/SnpSift.html#dbNSFP"));
        } catch (URISyntaxException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Sorry, could not open URL address");
        } 
    }//GEN-LAST:event_jLabelURLMouseClicked

    private void jLabelURLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelURLMouseEntered
        // TODO add your handling code here:
        this.jLabelURL.setForeground(new java.awt.Color(0,153,153));
    }//GEN-LAST:event_jLabelURLMouseEntered

    private void jLabelURLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelURLMouseExited
        // TODO add your handling code here:
        this.jLabelURL.setForeground(new java.awt.Color(106,163,163));
    }//GEN-LAST:event_jLabelURLMouseExited

    private void jComboBMemoryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBMemoryItemStateChanged
        // TODO add your handling code here:
        //not specify, 1 Gb, 2 Gb, 3 Gb, 4 Gb
        switch(this.jComboBMemory.getSelectedItem().toString()){
            case "not specify": 
                this.memory = "";
                break;
            case "1 GB":
                this.memory = " -Xmx1G";
                break;
            case "2 GB":
                this.memory = " -Xmx2G";
                break;
            case "3 GB":
                this.memory = " -Xmx3G";
                break;
            case "4 GB":
                this.memory = " -Xmx4G";
                break;
            case "5 GB":
                this.memory = " -Xmx5G";
                break;
            case "6 GB":
                this.memory = " -Xmx6G";
                break;
            case "7 GB":
                this.memory = " -Xmx7G";
                break;
            case "8 GB":
                this.memory = " -Xmx8G";
                break;
            default:
                this.memory = "";
        }
        
    }//GEN-LAST:event_jComboBMemoryItemStateChanged
    private void jRadioButtonActionPerformed(java.awt.event.ActionEvent evt, String name) { 
        this.caminho = name;
    }
    private void jRadioButtonSiftActionPerformed(java.awt.event.ActionEvent evt, String name) { 
        this.dbSelected = name;
    }    
    public void setCaminho(String caminho){
        this.caminho = caminho;
    }
    public String getCaminho(){
        return this.caminho;
    }
    public void setdbSelected(String dbcaminho){
        this.dbSelected = dbcaminho;
    }
    public String getdbSelected(){
        return this.dbSelected;
    }
    public String getMemory(){
        return this.memory;
    }
   
    private ArrayList<javax.swing.JRadioButton> listRadioBEffDataBases;
    private ArrayList<javax.swing.JRadioButton> listRadioBSiftDataBases;
    private String dbSelected;
    private String caminho;
    private String memory;
    private String sistemaLocal;
    private String comandoConsole;
    private String sep;
    private String mover;
    private String deletar;
    private String removerDir;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jBSelectSiftPath;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonDbEff;
    private javax.swing.JButton jButtonMoveDbSift;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JButton jButtonRemoveDbSift;
    private javax.swing.JComboBox<String> jComboBMemory;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelLoading1;
    private javax.swing.JLabel jLabelLoadingMsg;
    private javax.swing.JLabel jLabelTitleFrame;
    private javax.swing.JLabel jLabelURL;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelAllComponents;
    private javax.swing.JPanel jPanelEff;
    private javax.swing.JPanel jPanelEffDatabases;
    private javax.swing.JPanel jPanelLoadingDownload;
    private javax.swing.JPanel jPanelLocation;
    private javax.swing.JPanel jPanelMainComponents;
    private javax.swing.JPanel jPanelMemory;
    private javax.swing.JPanel jPanelSift;
    private javax.swing.JPanel jPanelSiftDatabases;
    private javax.swing.JRadioButton jRadioBOtherDb;
    private javax.swing.JRadioButton jRadioBSavedDb;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFDownloadDb;
    private javax.swing.JTextField jTextFSiftPath;
    // End of variables declaration//GEN-END:variables


}
