package logica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lopez
 */
public class VistaGrafica extends javax.swing.JFrame {
    
    DefaultTableModel dtm=new DefaultTableModel();

    public VistaGrafica() {
        initComponents();
        String[] titulo=new String[]{"Fuerza","Angulo","Fuerza en x","Fuerza en Y"};
        dtm.setColumnIdentifiers(titulo);
        this.tabla_tb.setModel(dtm);
    }
    
    // atributos
    private double fuerza;
    private double angulo;
 public boolean checkNumbers(String entradaF,String entradaA){

     try {
         fuerza=Double.parseDouble(entradaF);
         angulo=Double.parseDouble(entradaA);
         return true;
     } catch (Exception e) {
         return false;
     }
 }
 public void iniciarVariables(){
 fuerza=0;
 angulo=0;
 }
  //
 public void agregar(double fx,double fy){
 dtm.addRow(new Object[]{
 fuerza_txf.getText(),angulo_txf.getText(),fx,fy
 });
 }
 //eliminar fila
 public void eliminar(){
 int fila=tabla_tb.getSelectedRow();
 dtm.removeRow(fila);
 }
 //Eliminar tabla
 public void eliminartabla(){
 int fila=tabla_tb.getRowCount();
 for(int i=0;i<fila;i++){
 dtm.removeRow(0);
 }
 }
//resultante
 public void resultante(){
 String r = "";
 String ang="";
 double aux,aux2=0;
 double fx = 0,fy=0;
 int filas=tabla_tb.getRowCount();
 
 for(int i=0;i<filas;i++){
 fx+= Double.parseDouble(tabla_tb.getValueAt(i,2).toString());
 fy+=Double.parseDouble(tabla_tb.getValueAt(i,3).toString() );   
 
 }
 r=""+Math.sqrt( (Math.pow(fx,2))+(Math.pow(fy,2)) );
/*  System.out.println("fx :"+fx);
  System.out.println("fy:"+fy);
  System.out.println("resultante "+r);
  */
fuerza_lb.setText(r);
aux=(fy/fx);

if(fx<0){
aux2=180+((Math.atan(aux))*(180/Math.PI));
}
if(fx>=0 && fy>=0){
aux2=(Math.atan(aux))*(180/Math.PI);
}
if(fx>=0 && fy<0){
aux2=360+((Math.atan(aux))*(180/Math.PI));
}

ang=""+aux2;
angulo_lb.setText(ang);






 }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo1 = new javax.swing.JPanel();
        fuerza_txf = new javax.swing.JTextField();
        angulo_txf = new javax.swing.JTextField();
        ouputerror_lb = new javax.swing.JLabel();
        enter_button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_tb = new javax.swing.JTable();
        eliminar_btt = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        fuerza_lb = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        angulo_lb = new javax.swing.JLabel();
        EliminatT_btt = new javax.swing.JButton();
        resultante_btt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Fondo1.setBackground(new java.awt.Color(255, 204, 204));

        fuerza_txf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        ouputerror_lb.setText("......");

        enter_button.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        enter_button.setText("Enter");
        enter_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_buttonActionPerformed(evt);
            }
        });

        jLabel1.setText("Fuerza");

        jLabel2.setText("Angulo");

        tabla_tb.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tabla_tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fuerza", "Angulo", "Fx", "Fy"
            }
        ));
        jScrollPane1.setViewportView(tabla_tb);

        eliminar_btt.setText("Eliminar");
        eliminar_btt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar_bttActionPerformed(evt);
            }
        });

        jLabel3.setText("Fuerza resultante : ");

        fuerza_lb.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        fuerza_lb.setText(". . .");

        jLabel5.setText("Angulo :");

        angulo_lb.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        angulo_lb.setText(". . . ");

        EliminatT_btt.setText("EliminarTabla");
        EliminatT_btt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminatT_bttActionPerformed(evt);
            }
        });

        resultante_btt.setText("Resultante");
        resultante_btt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultante_bttActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Fondo1Layout = new javax.swing.GroupLayout(Fondo1);
        Fondo1.setLayout(Fondo1Layout);
        Fondo1Layout.setHorizontalGroup(
            Fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Fondo1Layout.createSequentialGroup()
                .addGroup(Fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Fondo1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel1)
                        .addGap(36, 36, 36)
                        .addComponent(fuerza_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)
                        .addGap(31, 31, 31)
                        .addComponent(angulo_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(enter_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eliminar_btt)
                        .addGap(18, 18, 18)
                        .addComponent(EliminatT_btt))
                    .addGroup(Fondo1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(Fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Fondo1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(78, 78, 78)
                                .addComponent(angulo_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Fondo1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(fuerza_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(Fondo1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resultante_btt))
                    .addGroup(Fondo1Layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(ouputerror_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        Fondo1Layout.setVerticalGroup(
            Fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Fondo1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(ouputerror_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(angulo_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enter_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fuerza_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(eliminar_btt)
                    .addComponent(EliminatT_btt))
                .addGroup(Fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Fondo1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Fondo1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(resultante_btt)))
                .addGap(31, 31, 31)
                .addGroup(Fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fuerza_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(angulo_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void enter_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enter_buttonActionPerformed
     String fuerzaentrada;
     String anguloentrada;
    // double sumafx,sumafy,resultante,anguloresultante;
   double  fx = 0,fy = 0;
     fuerzaentrada=fuerza_txf.getText();
     anguloentrada=angulo_txf.getText();
     
     
    if(checkNumbers(fuerzaentrada,anguloentrada)!=false){
       
      fx=fuerza*Math.cos(Math.toRadians(angulo));
       // System.out.println("coseno"+Math.cos(Math.toRadians(angulo)));
      fy=fuerza*Math.sin(Math.toRadians(angulo));
     /*   System.out.println("seno "+Math.sin(Math.toRadians(angulo)));
      resultante=Math.sqrt((Math.pow(fx,2))+(Math.pow(fy,2)));
     anguloresultante=Math.tanh(fy/fx)*(180/Math.PI);
      String salidda;
     salidda="fx "+fx+"\n"+"fy"+fy+"\n"
              +"la fuerz resultante es: "+resultante+"\n"+"el angulo es: "+anguloresultante;
      */
      agregar(fx,fy);
      resultante();
      //resultante();
     // fuerza_lb.setText(resultante());
    //  angulo_lb.setText(anguloresultante());
     // salida_txA.setText(salidda);
    }else
        
        
        
    ouputerror_lb.setText("ingrese correctamente los datos");

    angulo_txf.requestFocus();
    }//GEN-LAST:event_enter_buttonActionPerformed

    private void eliminar_bttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar_bttActionPerformed
        eliminar();
    }//GEN-LAST:event_eliminar_bttActionPerformed

    private void EliminatT_bttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminatT_bttActionPerformed
eliminartabla();
    }//GEN-LAST:event_EliminatT_bttActionPerformed

    private void resultante_bttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultante_bttActionPerformed

    resultante();
    }//GEN-LAST:event_resultante_bttActionPerformed

 
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
            java.util.logging.Logger.getLogger(VistaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaGrafica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EliminatT_btt;
    private javax.swing.JPanel Fondo1;
    private javax.swing.JLabel angulo_lb;
    private javax.swing.JTextField angulo_txf;
    private javax.swing.JButton eliminar_btt;
    private javax.swing.JButton enter_button;
    private javax.swing.JLabel fuerza_lb;
    private javax.swing.JTextField fuerza_txf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel ouputerror_lb;
    private javax.swing.JButton resultante_btt;
    private javax.swing.JTable tabla_tb;
    // End of variables declaration//GEN-END:variables
}
