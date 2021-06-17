import java.io.ObjectInputStream;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Seguro;

public class Frm_cliente extends javax.swing.JFrame {
    private String serverName = "192.168.100.138";
    private int serverPort = 8081;
    private Socket socket = null;
    
    public Frm_cliente() throws RemoteException, NotBoundException {
        initComponents();
        vaciarTabla();      
        conectarServidorSocket();
        vaciarTabla2();
        conectarServidorRMI();
    }
    
    public void vaciarTabla() {
        DefaultTableModel Modelo = (DefaultTableModel) jTable1.getModel();
        String titulos[] = {"Nombre del Seguro","Cobertura", "Beneficio", "Requisito"};
        Modelo = new DefaultTableModel(null, titulos);
        jTable1.setModel(Modelo);
    }
    public void vaciarTabla2() {
        DefaultTableModel Modelo = (DefaultTableModel) jTable2.getModel();
        String titulos[] = {"Seguro", "Cobertura", "Beneficio", "Requisito"};
        Modelo = new DefaultTableModel(null, titulos);
        jTable2.setModel(Modelo);
    }

    public void conectarServidorSocket() throws RemoteException, NotBoundException {
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Conectado al servidor Socket  " + socket.getRemoteSocketAddress());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object object = objectInputStream.readObject();
            Seguro s = (Seguro) object;
            DefaultTableModel miModelo = (DefaultTableModel) jTable1.getModel();
            String dts[] = new String[4];
            dts[0] = s.getNombre();
            dts[1] = s.getCobertura();
            dts[2] = s.getBeneficio();
            dts[3] = s.getRequisito();
            miModelo.addRow(dts);
            
            objectInputStream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
       
    }
    public void conectarServidorRMI() throws RemoteException, NotBoundException{
         Registry reg = LocateRegistry.getRegistry("localhost", 9999);
        InterfaceSeguro ad = (InterfaceSeguro) reg.lookup("hi_server");
        Seguro s = new Seguro();
        DefaultTableModel miModelo = (DefaultTableModel) jTable2.getModel();
         String coordenadas = ad.getSeguro();
         String[] coordenadasSeparadas = coordenadas.split(",");
         String nombre = coordenadasSeparadas[0];
         String cobertura = coordenadasSeparadas[1];
         String beneficio = coordenadasSeparadas[2];
         String requisito = coordenadasSeparadas[3];
         String dts[] = new String[4];
            dts[0] = nombre;
            dts[1] = cobertura;
            dts[2] = beneficio;
            dts[3] = requisito;
            miModelo.addRow(dts);
            
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cmbSeguros = new javax.swing.JComboBox<>();
        btnConsultarSeguros = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("ASEGURADORAS DE VEHICULOS");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        cmbSeguros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seguro Condor", "Seguro Autonomi", " " }));

        btnConsultarSeguros.setText("Consultar");
        btnConsultarSeguros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarSegurosActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(368, 368, 368)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(602, 602, 602)
                                .addComponent(cmbSeguros, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(btnConsultarSeguros, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 251, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSeguros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultarSeguros))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarSegurosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarSegurosActionPerformed
        
        try {
            if (cmbSeguros.getSelectedItem().toString().equals("Seguro Condor")) {
                vaciarTabla();
                conectarServidorRMI();
            } else {
                if (cmbSeguros.getSelectedItem().toString().equals("Seguro Autonomi")) {
                    vaciarTabla();
                    conectarServidorSocket();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un seguro");
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(Frm_cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Frm_cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultarSegurosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws RemoteException, NotBoundException {
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
            java.util.logging.Logger.getLogger(Frm_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Frm_cliente().setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(Frm_cliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotBoundException ex) {
                    Logger.getLogger(Frm_cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
   
    }
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultarSeguros;
    private javax.swing.JComboBox<String> cmbSeguros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
