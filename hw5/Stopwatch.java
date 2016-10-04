import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.Icon;

import com.sun.prism.paint.Stop;

/**
   A car that can be moved around.
*/
public class Stopwatch implements MoveableShape
{
   /**
      Constructs a car item.
      @param x the left of the bounding rectangle
      @param y the top of the bounding rectangle
      @param width the width of the bounding rectangle
   */
   public Stopwatch(int x, int y, int width)
   {
      this.x = x;
      this.y = y;
      this.width = width;
      this.smallWidth = width / 3;
      this.dial1 = new Dial(width, true, Color.BLACK);
      this.dial2 = new Dial(smallWidth, false, Color.BLACK);
      this.degree = 0;
      this.smallDegree = 0;
   }

   public Stopwatch(int width) {
	   x = 0;
	   y = 0;
	   this.width = width;
	   this.smallWidth = width / 3;
	   this.dial1 = new Dial(width, true, Color.BLACK);
	   this.dial2 = new Dial(smallWidth, false, Color.BLACK);
	   this.degree = 0;
	   this.smallDegree = 0;
   }

   public void move() {
	   degree += (0.1/60)*(2*Math.PI); 
   }	
   
   public void draw(Graphics2D g2)
   {
	   double midX = x + width;
	   double midY = y + width;
	   double endX = x + width + Math.sin(degree)*width; 
	   double endY = y + (1 - Math.cos(degree))*width;
	   Graphics g = (Graphics) g2;
	   
	   Line2D.Double bigLine = new Line2D.Double(midX, midY, endX, endY);
	   
	   g2.draw(bigLine);
	   dial1.paintIcon(null, g, x, y);
	   dial2.paintIcon(null, g, x + width - smallWidth, y + width/2);
	   
   }
   
   private int x;
   private int y;
   private int width;
   private int smallWidth;
   private double degree;
   private double smallDegree;
   private Icon dial1;
   private Icon dial2;
}
