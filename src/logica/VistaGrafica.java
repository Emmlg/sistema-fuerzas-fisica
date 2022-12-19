
package logica;


import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Emmanuel lopez
 */
public class VistaGrafica{
//atributos
public int w,h;
public JPanel p;

public VistaGrafica(JPanel p){
this.p=p;
w=p.getWidth();
h=p.getHeight();
}
public void dibujarEjes(){
    Graphics2D g=(Graphics2D)p.getGraphics();
    g.setColor(Color.black);
    g.drawLine(w/2,0,w/2,h);
    g.drawLine(0,h/2,w,h/2);
  //  g.drawLine(w/2,0,w/2-5,25);
    g.drawString("Y",(w/2)+8,15);
   g.drawString("-Y",(w/2)+8,h-8);
    g.drawString("-X",5,(h/2)-8);
    g.drawString("X",(w)-12,(h/2)-8);
}
//checar
public void dibujarFuerzas(int x,int y,String fN,String row){
Graphics2D g=(Graphics2D)p.getGraphics();
int xdigital=(w/2);
int ydigital=(h/2);
//g.fillOval(xdigital,ydigital,x, y);
g.setColor(Color.blue);
g.drawString("F"+row+"="+fN+" N",(xdigital+x)+4 , (ydigital-y)+5);
g.drawLine(xdigital,ydigital,xdigital+x,ydigital-y);

}
public void dibujarResultante(int x,int y,String R){
Graphics2D g=(Graphics2D)p.getGraphics();
int xdigital=(w/2);
int ydigital=(h/2);
//g.fillOval(xdigital,ydigital,x, y);
g.setColor(Color.green);
g.drawString("R ="+R+" °",(xdigital+x)+3, (ydigital-y)+3 );
g.drawLine(xdigital,ydigital,xdigital+x,ydigital-y);



}

    
    
}
