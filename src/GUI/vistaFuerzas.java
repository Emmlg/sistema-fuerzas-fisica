/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import logica.VistaGrafica;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.table.DefaultTableModel;
import logica.VistaGrafica;

/**
 *
 * @author lopez
 */
public class vistaFuerzas extends javax.swing.JFrame {
   
   String[] header1={"# Fuerza","Fuerza","Angulo"};
     
    
    public vistaFuerzas() {
        initComponents();
       
       tbEntrada.setColumnIdentifiers(header1);
        datosEntrada_tb.setModel(tbEntrada); 
         String[] header2={"# Fuerza","Fx","Fy"};
        tbsuma.setColumnIdentifiers(header2);
        vistaDato_tb.setModel(tbsuma);
        setBackground(Color.WHITE);
     
    }
//atributos
    
        DefaultTableModel tbsuma=new DefaultTableModel(){
           @Override
           public boolean isCellEditable(int row, int column) {
               if(column<3)return false;
               else
                   return true;
           }
    
    };
      DefaultTableModel tbEntrada=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int i, int colum) {
               if(colum<1){
               return false;
               }else
                   return true;
                
                //return super.isCellEditable(i, i1); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setValueAt(Object avalue, int row, int column) {
               if(isnumeric(avalue))                
                super.setValueAt(avalue, row,column); //To change body of generated methods, choose Tools | Templates.
               else return;
            if(column==1||column==2){
                double fuerza=Double.parseDouble(String.valueOf(getValueAt(row,1)));
                double angulo=Double.parseDouble(String.valueOf(getValueAt(row,2)));
                double fx=fuerza*Math.cos(Math.toRadians(angulo)),fy=fuerza*Math.sin(Math.toRadians(angulo));
               tbsuma.setValueAt(fx, row,1);
               tbsuma.setValueAt(fy, row,2);
              fuerzaResultante();///////////////////////////////
              //graficarfuerzas(fuerza, angulo);
              prueba();
              graficar();
            }
 
            }

       };



//Metodos
    
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
     
     tbEntrada.addRow(new Object[]{
     "Fuerza "+(tbEntrada.getRowCount()+1),fuerza,angulo
     });
    
    }
    
    public void agregaFxFy(double fuerza,double angulo){
    double fx=fuerza*Math.cos(Math.toRadians(angulo)),fy=fuerza*Math.sin(Math.toRadians(angulo));
  
     tbsuma.addRow(new Object[]{
     "fuerza "+(tbsuma.getRowCount()+1),fx,fy
     });
  //checar opcion  
  /*
   VistaGrafica p=new VistaGrafica(grafica_jpa);
  int fx1=(int)fx;
  int fy1=(int)fy;
  p.dibujarFuerzas(fx1, fy1);
  */
//  graficarfuerzas();

    }
    
public void graficarfuerzas(double fuerza,double angulo){
    
 double fx=fuerza*Math.cos(Math.toRadians(angulo)),fy=fuerza*Math.sin(Math.toRadians(angulo));
  
 //grafica_jpa.repaint(0,0,grafica_jpa.getHeight(),grafica_jpa.getWidth());
 
  VistaGrafica p=new VistaGrafica(grafica_jpa);
  int fx1=(int)fx;
  int fy1=(int)fy;
 // p.dibujarFuerzas(fx1, fy1);
  
}


  public void fuerzaResultante(){
      
  double fx = 0,fy = 0,resultante=0,anguloresultante = 0;
  
     for(int i=0;i<tbsuma.getRowCount();i++){
     fx+=Double.parseDouble(tbsuma.getValueAt(i,1).toString());
     fy+=Double.parseDouble(tbsuma.getValueAt(i,2).toString());
     
     }
     //resultante
     resultante=Math.sqrt( (Math.pow(fx,2))+(Math.pow(fy,2)) );
     Fuerza_lb.setText(""+resultante);
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
 angulos_lb.setText(""+anguloresultante+" °");
     
  }   
  
   public void vaciar(){
   fuerza_txf.setText("");
   angulo_txf.setText("");
   } 
   /////////////////////////////////////////
 public void graficar(){
  VistaGrafica p=new VistaGrafica(grafica_jpa);
   p.dibujarEjes();
 }
 
 public int sumatoriax(){
   double fx = 0;
           //fy = 0,resultante=0,anguloresultante = 0;
  
     for(int i=0;i<tbsuma.getRowCount();i++){
     fx+=Double.parseDouble(tbsuma.getValueAt(i,1).toString());

     }
     //resultante
    // resultante=Math.sqrt( (Math.pow(fx,2))+(Math.pow(fy,2)) );
    int fX=(int)fx;
 return  fX;
 }
 
public int sumatoriay(){
    double fy = 0;
     for(int i=0;i<tbsuma.getRowCount();i++){
     fy+=Double.parseDouble(tbsuma.getValueAt(i,2).toString());
     }
      int fY=(int)fy;
      return fY;
}

public double resultante(){
    
    double fx = 0,fy = 0,resultante=0;
  
     for(int i=0;i<tbsuma.getRowCount();i++){
     fx+=Double.parseDouble(tbsuma.getValueAt(i,1).toString());
     fy+=Double.parseDouble(tbsuma.getValueAt(i,2).toString());
     
     }
    
     resultante=Math.sqrt( (Math.pow(fx,2))+(Math.pow(fy,2)) );
return resultante;
}
/////////////////////////////////////////////
public void prueba(){

    int w=grafica_jpa.getWidth();
    int h=grafica_jpa.getHeight();
    VistaGrafica p=new VistaGrafica(grafica_jpa);
    Graphics2D g=(Graphics2D)grafica_jpa.getGraphics();
   
    g.clearRect(0, 0, w, h);
    
    for (int i = 0; i <vistaDato_tb.getRowCount(); i++) {
     double fx=Double.parseDouble(vistaDato_tb.getValueAt(i,1).toString());
     double fy=Double.parseDouble(vistaDato_tb.getValueAt(i,2).toString());  
     String fN=(datosEntrada_tb.getValueAt(i,1).toString());
     String row=""+(i+1);
     int fx1=(int)fx;
     int fy1=(int)fy;
   //  int fn=(int)fN;
     p.dibujarFuerzas(fx1,fy1,fN,row);
     
    }
    pruebaresul();

}

public void trash(){
    //reiniciar txf
vaciar();
//eliminar tabla entrada
 int fila=datosEntrada_tb.getRowCount();
 for(int i=0;i<fila;i++){
 tbEntrada.removeRow(0);
 }
 //eliminar tabla sumas
 int fila1=vistaDato_tb.getRowCount();
 for(int i=0;i<fila1;i++){
 tbsuma.removeRow(0);
 }
//eliminar lb
Fuerza_lb.setText("");
angulos_lb.setText("");
//reiniciar panel
grafica_jpa.repaint();////////////////////////////////////////////////////
}
public void pruebaresul(){

 VistaGrafica p=new VistaGrafica(grafica_jpa);
 p.dibujarEjes();
    if(vistaDato_tb.getRowCount()>1){
    int sumax=sumatoriax();
    int sumay=sumatoriay();
    String r=""+resultante();
    
    p.dibujarResultante(sumax,sumay,r);
     
}
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        datosEntrada_tb = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fuerza_txf = new javax.swing.JTextField();
        angulo_txf = new javax.swing.JTextField();
        enter_btt = new javax.swing.JButton();
        error_lb = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        vistaDato_tb = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Fuerza_lb = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        angulos_lb = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        grafica_jpa = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 600));
        jPanel1.setLayout(new java.awt.BorderLayout());

        datosEntrada_tb.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        datosEntrada_tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(datosEntrada_tb);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Fuerza");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Angulo");

        fuerza_txf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fuerza_txfKeyTyped(evt);
            }
        });

        angulo_txf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                angulo_txfKeyTyped(evt);
            }
        });

        enter_btt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/enterkey.png"))); // NOI18N
        enter_btt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_bttActionPerformed(evt);
            }
        });

        error_lb.setText(". . . ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(97, 97, 97))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(fuerza_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(angulo_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(error_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 52, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(enter_btt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fuerza_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(angulo_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(error_lb))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(enter_btt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel3.setLayout(new java.awt.BorderLayout());

        vistaDato_tb.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        vistaDato_tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(vistaDato_tb);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("Fuerza Resultante :");

        Fuerza_lb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Fuerza_lb.setText(". . .");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("Angulo Resultante :");

        angulos_lb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        angulos_lb.setText(". . . ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("N");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("°");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(Fuerza_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(angulos_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6))
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Fuerza_lb)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(angulos_lb, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        grafica_jpa.setBackground(new java.awt.Color(255, 255, 255));
        grafica_jpa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout grafica_jpaLayout = new javax.swing.GroupLayout(grafica_jpa);
        grafica_jpa.setLayout(grafica_jpaLayout);
        grafica_jpaLayout.setHorizontalGroup(
            grafica_jpaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );
        grafica_jpaLayout.setVerticalGroup(
            grafica_jpaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Hecho por:");

        jLabel8.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 18)); // NOI18N
        jLabel8.setText("Equipo comiHaks");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comihacks (3).jpeg"))); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton2.setText("Reiniciar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(grafica_jpa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(grafica_jpa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void enter_bttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enter_bttActionPerformed
String fuerzaE,anguloE;
fuerzaE=fuerza_txf.getText();
anguloE=angulo_txf.getText();
boolean fu,an;
    fu=verificadorDatos(fuerzaE);
    an=verificadorDatos(anguloE);
    double angi=Double.parseDouble(anguloE);
    //envio de datos
    if(fu && an==true && angi<360){
     error_lb.setText(". . .");
     vaciar();
     /*
    VistaGrafica p=new VistaGrafica(grafica_jpa);
    p.dibujarEjes();*/
    //p.dibujarFuerzas(WIDTH, WIDTH);
    graficar();
    
    Double fuerza=Double.parseDouble(fuerzaE),ang=Double.parseDouble(anguloE);
    agregarEntrada(fuerza,ang);
    agregaFxFy(fuerza,ang);
    
    prueba();
    fuerzaResultante();
    
    }else{
    error_lb.setText("verifique los datos");
    }


   fuerza_txf.requestFocus();
    }//GEN-LAST:event_enter_bttActionPerformed

    private void angulo_txfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_angulo_txfKeyTyped
char enter=evt.getKeyChar();
if(enter==KeyEvent.VK_ENTER){
enter_btt.doClick();
}
  
    }//GEN-LAST:event_angulo_txfKeyTyped

    private void fuerza_txfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fuerza_txfKeyTyped

char enter=evt.getKeyChar();
if(enter==KeyEvent.VK_ENTER){
enter_btt.doClick();
}
    }//GEN-LAST:event_fuerza_txfKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        trash();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(vistaFuerzas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaFuerzas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaFuerzas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaFuerzas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaFuerzas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel Fuerza_lb;
    private javax.swing.JTextField angulo_txf;
    private javax.swing.JLabel angulos_lb;
    private javax.swing.JTable datosEntrada_tb;
    private javax.swing.JButton enter_btt;
    private javax.swing.JLabel error_lb;
    private javax.swing.JTextField fuerza_txf;
    private javax.swing.JPanel grafica_jpa;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable vistaDato_tb;
    // End of variables declaration//GEN-END:variables
}
