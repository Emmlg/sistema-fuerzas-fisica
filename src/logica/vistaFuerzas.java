package logica;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableModel;
import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 *
 * @author lopez
 */
public class vistaFuerzas extends javax.swing.JFrame {

    String[] header1 = {"# Fuerza", "Fuerza", "Angulo"};

    public vistaFuerzas() {
        initComponents();

        tbEntrada.setColumnIdentifiers(header1);
        datosEntrada_tb.setModel(tbEntrada);
        String[] header2 = {"# Fuerza", "Fx", "Fy"};
        tbsuma.setColumnIdentifiers(header2);
        vistaDato_tb.setModel(tbsuma);
        setBackground(Color.WHITE);
        // setExtendedState(MAXIMIZED_BOTH);
        SetImageLabel(jLabel7, "src/resources/expandir.png");
        setIconImage(getIconImage());
        datosEntrada_tb.getTableHeader().setDefaultRenderer(new HeaderChangeColor());
        vistaDato_tb.getTableHeader().setDefaultRenderer(new HeaderChangeColor());
    }
//atributos

    DefaultTableModel tbsuma = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            if (column < 3) {
                return false;
            } else {
                return true;
            }
        }

    };
    DefaultTableModel tbEntrada = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int i, int colum) {
            if (colum < 1) {
                return false;
            } else {
                return true;
            }

            //return super.isCellEditable(i, i1); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setValueAt(Object avalue, int row, int column) {

            if (isnumeric(avalue)) {
                super.setValueAt(avalue, row, column); //To change body of generated methods, choose Tools | Templates.
            } else {
                return;
            }
            if (column == 1 || column == 2) {
                double fuerza = Double.parseDouble(String.valueOf(getValueAt(row, 1)));
                double angulo = Double.parseDouble(String.valueOf(getValueAt(row, 2)));
                double fx = fuerza * Math.cos(Math.toRadians(angulo)), fy = fuerza * Math.sin(Math.toRadians(angulo));
                tbsuma.setValueAt(fx, row, 1);
                tbsuma.setValueAt(fy, row, 2);
                fuerzaResultante();///////////////////////////////
                //graficarfuerzas(fuerza, angulo);
                prueba();
                graficar();
            }

        }

    };

//Metodos
    public boolean verificadorDatos(String dato) {
        double data;
        try {
            data = Double.parseDouble(dato);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isnumeric(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            Double.parseDouble(String.valueOf(obj));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public void agregarEntrada(double fuerza, double angulo) {

        tbEntrada.addRow(new Object[]{
            "Fuerza " + (tbEntrada.getRowCount() + 1), fuerza, angulo
        });

    }

    public void agregaFxFy(double fuerza, double angulo) {
        double fx = fuerza * Math.cos(Math.toRadians(angulo)), fy = fuerza * Math.sin(Math.toRadians(angulo));

        tbsuma.addRow(new Object[]{
            "fuerza " + (tbsuma.getRowCount() + 1), fx, fy
        });

    }

    public void graficarfuerzas(double fuerza, double angulo) {

        double fx = fuerza * Math.cos(Math.toRadians(angulo)), fy = fuerza * Math.sin(Math.toRadians(angulo));

        //grafica_jpa.repaint(0,0,grafica_jpa.getHeight(),grafica_jpa.getWidth());
        VistaGrafica p = new VistaGrafica(grafica_jpa);
        int fx1 = (int) fx;
        int fy1 = (int) fy;
        // p.dibujarFuerzas(fx1, fy1);

    }

    public void fuerzaResultante() {

        double fx = 0, fy = 0, resultante = 0, anguloresultante = 0;

        for (int i = 0; i < tbsuma.getRowCount(); i++) {
            fx += Double.parseDouble(tbsuma.getValueAt(i, 1).toString());
            fy += Double.parseDouble(tbsuma.getValueAt(i, 2).toString());

        }
        //resultante
        resultante = Math.sqrt((Math.pow(fx, 2)) + (Math.pow(fy, 2)));
        Fuerza_lb.setText("" + resultante);
        //angulo
        double aux = (fy / fx);
      /*  
        if(fx >0 && fy > 0){
        anguloresultante=Math.tan( (fy/fx)* (180 / Math.PI) );
        }*/
        
        if (fx < 0) {
            anguloresultante = 180 + ((Math.atan(aux)) * (180 / Math.PI));
        }
        if (fx > 0 && fy > 0) {
            anguloresultante = (Math.atan(aux)) * (180 / Math.PI);
        }
        if (fx >= 0 && fy < 0) {
            anguloresultante = 360 + ((Math.atan(aux)) * (180 / Math.PI));
        }
        angulos_lb.setText("" + anguloresultante + " °");

    }

    public void vaciar() {
        fuerza_txf.setText("");
        angulo_txf.setText("");
    }
    /////////////////////////////////////////

    public void graficar() {
        VistaGrafica p = new VistaGrafica(grafica_jpa);
        p.dibujarEjes();
    }

    public int sumatoriax() {
        double fx = 0;
        //fy = 0,resultante=0,anguloresultante = 0;

        for (int i = 0; i < tbsuma.getRowCount(); i++) {
            fx += Double.parseDouble(tbsuma.getValueAt(i, 1).toString());

        }
        //resultante
        // resultante=Math.sqrt( (Math.pow(fx,2))+(Math.pow(fy,2)) );
        int fX = (int) fx;
        return fX;
    }

    public int sumatoriay() {
        double fy = 0;
        for (int i = 0; i < tbsuma.getRowCount(); i++) {
            fy += Double.parseDouble(tbsuma.getValueAt(i, 2).toString());
        }
        int fY = (int) fy;
        return fY;
    }

    public double resultante() {

        double fx = 0, fy = 0, resultante = 0;

        for (int i = 0; i < tbsuma.getRowCount(); i++) {
            fx += Double.parseDouble(tbsuma.getValueAt(i, 1).toString());
            fy += Double.parseDouble(tbsuma.getValueAt(i, 2).toString());

        }

        resultante = Math.sqrt((Math.pow(fx, 2)) + (Math.pow(fy, 2)));
        return resultante;
    }
/////////////////////////////////////////////
// checar si esta vacio las filas

    public void prueba() {

        vistaDato_tb.setShowVerticalLines(true);
        datosEntrada_tb.setShowVerticalLines(true);

        int w = grafica_jpa.getWidth();
        int h = grafica_jpa.getHeight();
        VistaGrafica p = new VistaGrafica(grafica_jpa);
        Graphics2D g = (Graphics2D) grafica_jpa.getGraphics();

        g.clearRect(0, 0, w, h);

        for (int i = 0; i < vistaDato_tb.getRowCount(); i++) {
            double fx = Double.parseDouble(vistaDato_tb.getValueAt(i, 1).toString());
            double fy = Double.parseDouble(vistaDato_tb.getValueAt(i, 2).toString());
            String fN = (datosEntrada_tb.getValueAt(i, 1).toString());
            String row = "" + (i + 1);
            int fx1 = (int) fx;
            int fy1 = (int) fy;

            p.dibujarFuerzas(fx1, fy1, fN, row);
            jLabel4.repaint();
            jLabel7.repaint();
        }

        pruebaresul();

    }

    public void trash() {
        //reiniciar txf
        vaciar();
//eliminar tabla entrada
        int fila = datosEntrada_tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            tbEntrada.removeRow(0);
        }
        //eliminar tabla sumas
        int fila1 = vistaDato_tb.getRowCount();
        for (int i = 0; i < fila1; i++) {
            tbsuma.removeRow(0);
        }

//eliminar lb
        Fuerza_lb.setText("");
        angulos_lb.setText("");
//reiniciar panel
        grafica_jpa.repaint();////////////////////////////////////////////////////
    }

    public void pruebaresul() {

        VistaGrafica p = new VistaGrafica(grafica_jpa);
        p.dibujarEjes();
        if (vistaDato_tb.getRowCount() > 1) {
            int sumax = sumatoriax();
            int sumay = sumatoriay();
            String r = "" + resultante();

            p.dibujarResultante(sumax, sumay, r);

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
        jPanel5 = new javax.swing.JPanel();
        reiniciar = new javax.swing.JLabel();
        grafica_jpa = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(48, 74, 110));
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
        datosEntrada_tb.setFillsViewportHeight(true);
        datosEntrada_tb.setFocusCycleRoot(true);
        datosEntrada_tb.setIntercellSpacing(new java.awt.Dimension(0, 0));
        datosEntrada_tb.setRowHeight(25);
        datosEntrada_tb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                datosEntrada_tbKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(datosEntrada_tb);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(48, 74, 110));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Fuerza");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Angulo");

        fuerza_txf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fuerza_txfActionPerformed(evt);
            }
        });
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

        enter_btt.setBackground(new java.awt.Color(15, 28, 48));
        enter_btt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        enter_btt.setForeground(new java.awt.Color(255, 255, 255));
        enter_btt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Enter_2.png"))); // NOI18N
        enter_btt.setToolTipText("");
        enter_btt.setBorder(null);
        enter_btt.setFocusPainted(false);
        enter_btt.setInheritsPopupMenu(true);
        enter_btt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_bttActionPerformed(evt);
            }
        });

        error_lb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        error_lb.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(angulo_txf, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                        .addComponent(fuerza_txf)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel2))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(error_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(enter_btt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(fuerza_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel2))
                    .addComponent(enter_btt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(angulo_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(error_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBackground(new java.awt.Color(48, 74, 110));
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
        vistaDato_tb.setFillsViewportHeight(true);
        vistaDato_tb.setFocusCycleRoot(true);
        vistaDato_tb.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jScrollPane2.setViewportView(vistaDato_tb);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(48, 74, 110));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fuerza Resultante :  ");

        Fuerza_lb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Fuerza_lb.setForeground(new java.awt.Color(255, 255, 255));
        Fuerza_lb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Angulo Resultante :");

        angulos_lb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        angulos_lb.setForeground(new java.awt.Color(255, 255, 255));
        angulos_lb.setText(" ");
        angulos_lb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jPanel5.setBackground(new java.awt.Color(15, 28, 48));

        reiniciar.setBackground(new java.awt.Color(255, 0, 0));
        reiniciar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        reiniciar.setForeground(new java.awt.Color(255, 255, 255));
        reiniciar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reiniciar.setText("Reiniciar");
        reiniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        reiniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reiniciarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reiniciar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(reiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addGap(122, 122, 122))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(Fuerza_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(angulos_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addComponent(Fuerza_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(angulos_lb)
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        grafica_jpa.setBackground(new java.awt.Color(255, 255, 255));
        grafica_jpa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(48, 74, 110));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("X");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(48, 74, 110));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout grafica_jpaLayout = new javax.swing.GroupLayout(grafica_jpa);
        grafica_jpa.setLayout(grafica_jpaLayout);
        grafica_jpaLayout.setHorizontalGroup(
            grafica_jpaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grafica_jpaLayout.createSequentialGroup()
                .addContainerGap(607, Short.MAX_VALUE)
                .addGroup(grafica_jpaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        grafica_jpaLayout.setVerticalGroup(
            grafica_jpaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grafica_jpaLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grafica_jpa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(grafica_jpa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void eliminar() {
        int fila = datosEntrada_tb.getSelectedRow();
        tbEntrada.removeRow(fila);
        fuerzaResultante();
    }

    private void datosEntrada_tbKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datosEntrada_tbKeyTyped
        int w = grafica_jpa.getWidth();
        int h = grafica_jpa.getHeight();
        VistaGrafica p = new VistaGrafica(grafica_jpa);
        Graphics2D g = (Graphics2D) grafica_jpa.getGraphics();

        char tecla = evt.getKeyChar();
        int filaseleccionada = datosEntrada_tb.getSelectedRow();

        if (tecla == KeyEvent.VK_DELETE) {
            /*
     double   fx=Double.parseDouble(tbsuma.getValueAt(filaseleccionada,1).toString());
     double fy=Double.parseDouble(tbsuma.getValueAt(filaseleccionada,2).toString());
     int fx1=(int)fx;
     int fy1=(int)fy;
      
      g.setColor(Color.black);
      g.clearRect(w/2,h/2, fx1, fy1);
             */

            eliminar();
            tbsuma.removeRow(filaseleccionada);
            prueba();
            fuerzaResultante();
            //Fuerza_lb.setText("");
            //angulos_lb.setText("");
        }

    }//GEN-LAST:event_datosEntrada_tbKeyTyped

    private void angulo_txfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_angulo_txfKeyTyped
        char tecla = evt.getKeyChar();
        if (tecla == KeyEvent.VK_ENTER) {
            enter_btt.doClick();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_angulo_txfKeyTyped

    private void fuerza_txfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fuerza_txfKeyTyped
        String fuerza, angulo;
        fuerza = fuerza_txf.getText();
        angulo = angulo_txf.getText();

        if (fuerza.isEmpty() == true) {
            error_lb.setText("Verifique los datos");
        }

        char tecla = evt.getKeyChar();
        if (tecla == KeyEvent.VK_ENTER) {
            enter_btt.doClick();
        }
    }//GEN-LAST:event_fuerza_txfKeyTyped

    private void fuerza_txfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fuerza_txfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fuerza_txfActionPerformed

    private void reiniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reiniciarMouseClicked
        trash();
    }//GEN-LAST:event_reiniciarMouseClicked
    /**/
    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        jPanel6.setBackground(new Color(15, 28, 48));
        jLabel4.setForeground(Color.WHITE);
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        jPanel6.setBackground(Color.WHITE);
        jLabel4.setForeground(new Color(15, 28, 48));
    }//GEN-LAST:event_jLabel4MouseExited

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        jPanel7.setBackground(Color.WHITE);
        jLabel7.setForeground(new Color(15, 28, 48));
        SetImageLabel(jLabel7, "src/resources/expandir.png");
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        jPanel7.setBackground(new Color(15, 28, 48));
        jLabel7.setForeground(Color.white);
        SetImageLabel(jLabel7, "src/resources/expandir_Blanco.png");
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        this.setExtendedState(this.MAXIMIZED_BOTH);
        if (!jLabel7.getText().equals("-")) {
            jLabel7.setText("-");
            jLabel7.setForeground(new Color(15, 28, 48));
            jLabel7.setHorizontalTextPosition((int) CENTER_ALIGNMENT);
        } else {
            this.setSize(950, 550);
            this.setLocationRelativeTo(null);
            jLabel7.setText("");
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void enter_bttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enter_bttActionPerformed
        String fuerzaE, anguloE;
        fuerzaE = fuerza_txf.getText();
        anguloE = angulo_txf.getText();

        if (fuerzaE.isEmpty() == false && anguloE.isEmpty() == false) {
            error_lb.setText("verifique los datos");
            boolean fu, an;
            fu = verificadorDatos(fuerzaE);
            an = verificadorDatos(anguloE);
            double angi = Double.parseDouble(anguloE);

            //envio de datos
            if (fu && an == true && angi < 361) {
                error_lb.setText(". . .");
                vaciar();
                graficar();

                Double fuerza = Double.parseDouble(fuerzaE), ang = Double.parseDouble(anguloE);
                agregarEntrada(fuerza, ang);
                agregaFxFy(fuerza, ang);

                prueba();
                fuerzaResultante();

            }

        } else {
            error_lb.setText("verifique los datos");
        }

        fuerza_txf.requestFocus();

        jLabel7.repaint();
        jLabel4.repaint();
    }//GEN-LAST:event_enter_bttActionPerformed

    private void SetImageLabel(JLabel LabelE, String root) {
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(LabelE.getWidth(), LabelE.getHeight(), Image.SCALE_DEFAULT));
        LabelE.setIcon(icon);
    }

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("resources/logoSumatoriaFuerzas.png"));
        return retValue;
    }

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel reiniciar;
    private javax.swing.JTable vistaDato_tb;
    // End of variables declaration//GEN-END:variables

    public class HeaderChangeColor extends DefaultTableCellHeaderRenderer {

        public HeaderChangeColor() {
            setOpaque(true);
        }

        //sobre escribimos el metodo
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            setBackground(new Color(15, 28, 48));
            setForeground(Color.WHITE);
            setFont(new Font("Thaoma", 1, 14));
            setHorizontalAlignment(CENTER);
            return this;
        }
    }
}
