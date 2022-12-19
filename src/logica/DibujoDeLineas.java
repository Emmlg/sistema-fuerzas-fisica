package logica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lopez
 */
public class DibujoDeLineas extends javax.swing.JFrame {
//atributos
    DefaultTableModel dtm;
    DefaultTableModel tbsuma;
    public DibujoDeLineas() {
        
        initComponents();
        dtm=(DefaultTableModel) tablaEntrada_tb.getModel();
        tbsuma=(DefaultTableModel) sumafxfy_tb.getModel();
     
    }

// Metodos
   
    
    public boolean verificadorDatos(String dato){
   double data;
           try {
            data=Double.parseDouble(dato);
             return true;
        } catch (Exception e) {
            return false;
        }  
    }
    
    public boolean isnumeric(Object obj){
    if(obj==null)return false;
        try {
            Double.parseDouble( String.valueOf(obj) );
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    
    }
    
    public void agregarEntrada(double fuerza,double angulo){
     
     dtm.addRow(new Object[]{
     (dtm.getRowCount()+1),fuerza,angulo
     });
    
    }
    public void agregaFxFy(double fuerza,double angulo){
    double fx=fuerza*Math.cos(Math.toRadians(angulo)),fy=fuerza*Math.sin(Math.toRadians(angulo));
  
     tbsuma.addRow(new Object[]{
     "fuerza "+(tbsuma.getRowCount()+1),fx,fy
     });
    
    
    }
  public void fuerzaResultante(){
      
  double fx = 0,fy = 0,resultante,anguloresultante = 0;
  
     for(int i=0;i<tbsuma.getRowCount();i++){
     fx+=Double.parseDouble(tbsuma.getValueAt(i,1).toString());
     fy+=Double.parseDouble(tbsuma.getValueAt(i,2).toString());
     
     }
     //resultante
     resultante=Math.sqrt( (Math.pow(fx,2))+(Math.pow(fy,2)) );
     fuerza_lb.setText(""+resultante);
     //angulo
 double aux=(fy/fx);    
if(fx<0){
anguloresultante=180+((Math.atan(aux))*(180/Math.PI));
}
if(fx>=0 && fy>=0){
anguloresultante=(Math.atan(aux))*(180/Math.PI);
}
if(fx>=0 && fy<0){
anguloresultante=360+((Math.atan(aux))*(180/Math.PI));
} 
 angulo_lb.setText(""+anguloresultante);
     
  }  
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        VistaGrafica_lb = new javax.swing.JPanel();
        vistaDatos_jp = new javax.swing.JPanel();
        Fuerza_txf = new javax.swing.JTextField();
        angulo_txf = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEntrada_tb = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        sumafxfy_tb = new javax.swing.JTable();
        Enter_btt = new javax.swing.JButton();
        titulo1_lb = new javax.swing.JLabel();
        titulo2_lb = new javax.swing.JLabel();
        error_lb = new javax.swing.JLabel();
        titulo3 = new javax.swing.JLabel();
        fuerza_lb = new javax.swing.JLabel();
        titulo4_lb = new javax.swing.JLabel();
        angulo_lb = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        VistaGrafica_lb.setBackground(new java.awt.Color(204, 204, 255));
        VistaGrafica_lb.setPreferredSize(new java.awt.Dimension(900, 500));

        javax.swing.GroupLayout VistaGrafica_lbLayout = new javax.swing.GroupLayout(VistaGrafica_lb);
        VistaGrafica_lb.setLayout(VistaGrafica_lbLayout);
        VistaGrafica_lbLayout.setHorizontalGroup(
            VistaGrafica_lbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );
        VistaGrafica_lbLayout.setVerticalGroup(
            VistaGrafica_lbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        vistaDatos_jp.setBackground(new java.awt.Color(153, 153, 255));

        angulo_txf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                angulo_txfKeyTyped(evt);
            }
        });

        tablaEntrada_tb.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tablaEntrada_tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "# Fuerza", "Fuerza", "Angulo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEntrada_tb.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tablaEntrada_tbInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jScrollPane1.setViewportView(tablaEntrada_tb);

        sumafxfy_tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#fuerzas", "Fx", "Fy"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(sumafxfy_tb);

        Enter_btt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/enterkey.png"))); // NOI18N
        Enter_btt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Enter_bttActionPerformed(evt);
            }
        });

        titulo1_lb.setText("Angulo");

        titulo2_lb.setText("Fuerza");

        error_lb.setText(". . .");

        titulo3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo3.setText("Fuerza resultante : ");

        fuerza_lb.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        fuerza_lb.setText(". . .");

        titulo4_lb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo4_lb.setText("Angulo Resultante :");

        angulo_lb.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        angulo_lb.setText(". . .");

        javax.swing.GroupLayout vistaDatos_jpLayout = new javax.swing.GroupLayout(vistaDatos_jp);
        vistaDatos_jp.setLayout(vistaDatos_jpLayout);
        vistaDatos_jpLayout.setHorizontalGroup(
            vistaDatos_jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaDatos_jpLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(vistaDatos_jpLayout.createSequentialGroup()
                .addGroup(vistaDatos_jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(vistaDatos_jpLayout.createSequentialGroup()
                        .addGroup(vistaDatos_jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(vistaDatos_jpLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(vistaDatos_jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(vistaDatos_jpLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(titulo2_lb)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(titulo1_lb)
                                        .addGap(40, 40, 40))
                                    .addGroup(vistaDatos_jpLayout.createSequentialGroup()
                                        .addGroup(vistaDatos_jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(error_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, vistaDatos_jpLayout.createSequentialGroup()
                                                .addComponent(Fuerza_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(angulo_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addComponent(Enter_btt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(vistaDatos_jpLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(fuerza_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(vistaDatos_jpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vistaDatos_jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vistaDatos_jpLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(angulo_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(titulo4_lb)
                    .addComponent(titulo3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        vistaDatos_jpLayout.setVerticalGroup(
            vistaDatos_jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaDatos_jpLayout.createSequentialGroup()
                .addComponent(error_lb)
                .addGap(9, 9, 9)
                .addGroup(vistaDatos_jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(vistaDatos_jpLayout.createSequentialGroup()
                        .addComponent(Enter_btt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(vistaDatos_jpLayout.createSequentialGroup()
                        .addGroup(vistaDatos_jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Fuerza_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(angulo_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(vistaDatos_jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titulo2_lb)
                            .addComponent(titulo1_lb))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(titulo3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fuerza_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(titulo4_lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(angulo_lb, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(vistaDatos_jp, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(VistaGrafica_lb, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(VistaGrafica_lb, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                    .addComponent(vistaDatos_jp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Enter_bttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Enter_bttActionPerformed
String fuerzaE,anguloE;
fuerzaE=Fuerza_txf.getText();
anguloE=angulo_txf.getText();
boolean fu,an;
    fu=verificadorDatos(fuerzaE);
    an=verificadorDatos(anguloE);
    if(fu && an==true){
    Double fuerza=Double.parseDouble(fuerzaE),ang=Double.parseDouble(anguloE);
   agregarEntrada(fuerza,ang);
   agregaFxFy(fuerza,ang);
   fuerzaResultante();
    }else{
    error_lb.setText("verifique los datos");
    }
        
    }//GEN-LAST:event_Enter_bttActionPerformed

    private void angulo_txfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_angulo_txfKeyTyped
       char enter=evt.getKeyChar();
       if(enter==KeyEvent.VK_ENTER){
       Enter_btt.doClick();
       }       
    }//GEN-LAST:event_angulo_txfKeyTyped

    private void tablaEntrada_tbInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tablaEntrada_tbInputMethodTextChanged
        
        
    }//GEN-LAST:event_tablaEntrada_tbInputMethodTextChanged

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
            java.util.logging.Logger.getLogger(DibujoDeLineas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DibujoDeLineas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DibujoDeLineas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DibujoDeLineas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DibujoDeLineas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Enter_btt;
    private javax.swing.JTextField Fuerza_txf;
    private javax.swing.JPanel VistaGrafica_lb;
    private javax.swing.JLabel angulo_lb;
    private javax.swing.JTextField angulo_txf;
    private javax.swing.JLabel error_lb;
    private javax.swing.JLabel fuerza_lb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable sumafxfy_tb;
    private javax.swing.JTable tablaEntrada_tb;
    private javax.swing.JLabel titulo1_lb;
    private javax.swing.JLabel titulo2_lb;
    private javax.swing.JLabel titulo3;
    private javax.swing.JLabel titulo4_lb;
    private javax.swing.JPanel vistaDatos_jp;
    // End of variables declaration//GEN-END:variables
}
