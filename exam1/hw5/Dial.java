import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

// I made the dial implement Icon for easy testing

public class Dial implements Icon
{
   public Dial(double radius, boolean drawShortLines, Color handColor)
   {
      this.radius = radius;
      this.drawShortLines = drawShortLines;
      this.handColor = handColor;
   }

   public void paintIcon(Component c, Graphics g, int x, int y)
   {
      Graphics2D g2 = (Graphics2D) g;
      paintTicks(g2, x, y, 12, LONG_LINES);
      paintTicks(g2, x, y, 12 * 5, MEDIUM_LINES);
      if (drawShortLines) paintTicks(g2, x, y, 12 * 5 * 5, SHORT_LINES);

      for (int i = 0; i < 12; i++)
      {
         double angle = Math.toRadians(270 + i * 360.0 / 12);
         double xmid = Math.cos(angle) * radius * NUMBER_CENTER + radius + x;
         double ymid = Math.sin(angle) * radius * NUMBER_CENTER + radius + y;

         JLabel label = new JLabel("" + (5 * i));
         label.setFont(new Font("Sans", Font.BOLD, (int)(radius * 12 / 100)));
         Dimension dim = label.getPreferredSize();
         double xleft = xmid - dim.width / 2;
         double ytop = ymid - dim.height / 2;
         label.setBounds(0, 0, dim.width, dim.height);
         g2.translate(xleft, ytop);
         label.paint(g2);
         g2.translate(-xleft, -ytop);
      }
      paintHand(g2, x, y);
   }

   private void paintTicks(Graphics2D g2, int x, int y, int n, double length)
   {
      for (int i = 0; i < n; i++)
      {
         double angle = Math.toRadians(i * 360.0 / n);
         double xstart = Math.cos(angle);
         double ystart = Math.sin(angle);
         double xend = xstart * (1 - length);
         double yend = ystart * (1 - length);
         Line2D.Double tick = new Line2D.Double(x + radius + xstart * radius,
               y + radius + ystart * radius,
               x + radius + xend * radius,
            y + radius + yend * radius);
         g2.draw(tick);
      }      
   }

   private void paintHand(Graphics2D g2, int x, int y)
   {
      double handAngle = Math.toRadians(270 + angle);
      double handAngleCos = Math.cos(handAngle) * radius;
      double handAngleSin = Math.sin(handAngle) * radius;
      
      
      double xend = x + radius + handAngleCos * HAND_LENGTH;
      double yend = y + radius + handAngleSin * HAND_LENGTH;

      double xstart = x + radius + handAngleCos * (HAND_LENGTH - TRI_HEIGHT);
      double ystart = y + radius + handAngleSin * (HAND_LENGTH - TRI_HEIGHT);

      double dx = (xend - xstart) * 0.5;
      double dy = (yend - ystart) * 0.5;

      double x1 = xstart - dy;
      double y1 = ystart + dx;

      double x2 = xstart + dy;
      double y2 = ystart - dx;

      g2.setPaint(handColor);
      g2.draw(new Line2D.Double(x + radius, y + radius,
            xend, yend));
      GeneralPath path = new GeneralPath();
      path.moveTo(x1, y1);
      path.lineTo(x2, y2);
      path.lineTo(xend, yend);
      path.lineTo(x1, y1);
      path.closePath();
      g2.fill(path);
      g2.setPaint(Color.BLACK);      
   }

   public int getIconHeight()
   {
      return (int)(2 * radius);
   }

   public int getIconWidth()
   {
      return (int)(2 * radius);
   }

   // in degrees; 0 = top, 90 = right
   public void setAngle(double angle)
   {
      this.angle = angle;
   }

   private double LONG_LINES = 0.15;
   private double MEDIUM_LINES = 0.1;
   private double SHORT_LINES = 0.05;
   private double NUMBER_CENTER = 0.75;
   private double HAND_LENGTH = 0.95;
   private double TRI_HEIGHT = 0.05;
   
   private double radius;
   private boolean drawShortLines;
   private double angle;
   private Color handColor;
}
