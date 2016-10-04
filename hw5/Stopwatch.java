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
	  running = true;
	  frozen = false;
      this.x = x;
      this.y = y;
      this.width = width;
      this.smallWidth = width / 3;
      this.dial1 = new Dial(width, true, Color.BLACK);
      this.dial2 = new Dial(smallWidth, false, Color.BLACK);
      this.actualDegree = 0;
      this.actualSmallDegree = 0;
      this.degree = 0;
      this.smallDegree = 0;
   }

   public Stopwatch(int width) {
	   running = true;
	   frozen = false;
	   x = 0;
	   y = 0;
	   this.width = width;
	   this.smallWidth = width / 3;
	   this.dial1 = new Dial(width, true, Color.BLACK);
	   this.dial2 = new Dial(smallWidth, false, Color.BLACK);
	   this.actualDegree = 0;
	   this.actualSmallDegree = 0;
	   this.degree = 0;
	   this.smallDegree = 0;
   }

   public void reset() {
	   running = true;
	   frozen = false;
	   actualDegree = 0;
	   actualSmallDegree = 0;
	   degree = 0;
	   smallDegree = 0;
   }
   public void move() {
	   if(running) {
		   actualDegree += (0.1/60)*(2*Math.PI);
		   actualSmallDegree += (0.1/(60*60))*(2*Math.PI);
		   if(!frozen) {
			   degree = actualDegree;
			   smallDegree = actualSmallDegree;
		   }
	   }else {
		   if(frozen) {
			   reset();
		   }
	   }
   }	
   
   public void draw(Graphics2D g2)
   {
	   int smallX = x + width - smallWidth;
	   int smallY = y + width/2;
	   double midX = x + width;
	   double midY = y + width;
	   double endX = x + width + Math.sin(degree)*width; 
	   double endY = y + (1 - Math.cos(degree))*width;
	   double sMidX = smallX + smallWidth;
	   double sMidY = smallY + smallWidth;
	   double sEndX = smallX + smallWidth + Math.sin(smallDegree)*smallWidth; 
	   double sEndY = smallY + (1 - Math.cos(smallDegree))*smallWidth;
	   Graphics g = (Graphics) g2;
	   
	   Line2D.Double bigLine = new Line2D.Double(midX, midY, endX, endY);
	   Line2D.Double smallLine = new Line2D.Double(sMidX, sMidY, sEndX, sEndY);
	   g2.draw(bigLine);
	   g2.draw(smallLine);
	   dial1.paintIcon(null, g, x, y);
	   dial2.paintIcon(null, g, smallX, smallY);
	   
   }
   
   public void topButtonPressed() {
	   running = !running;
   }
   
   public void secondButtonPressed() {
	   frozen = !frozen;
   }
   
   private boolean running;
   private boolean frozen;
   private int x;
   private int y;
   private int width;
   private int smallWidth;
   private double degree;
   private double smallDegree;
   private double actualDegree;
   private double actualSmallDegree;
   private Icon dial1;
   private Icon dial2;
}
