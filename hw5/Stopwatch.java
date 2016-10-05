import java.awt.*;
import java.awt.geom.*;
import java.time.Duration;
import java.time.Instant;
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
	  running = false;
	  frozen = false;
	  totalDelay = 0;
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
	   totalDelay = 0;
	   running = false;
	   frozen = false;
	   x = 0;
	   y = 0;
	   this.width = width;
	   this.smallWidth = width / 3;
	   this.dial1 = new Dial(width, true, Color.BLACK);
	   this.dial2 = new Dial(smallWidth, false, Color.BLACK);
	   this.degree = 0;
	   this.smallDegree = 0;
   }

   public void reset() {
	   totalDelay = 0;
	   running = false;
	   frozen = false;
	   degree = 0;
	   smallDegree = 0;
   }
   
   public void move() {
	   if(running) {
		   Instant moment = Instant.now();
		   Duration delay = Duration.between(thisMoment, moment);
		   totalDelay += delay.getNano()/Math.pow(10, 9);
		   if (!frozen) {
			   thisMoment = moment;
			   degree = ((totalDelay/60))*(2*Math.PI);
			   smallDegree += (totalDelay/(60*60))*(2*Math.PI);
			   degree %= 2*Math.PI;
			   smallDegree %= 2*Math.PI;
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
	   g2.setColor(Color.RED);
	   g2.draw(bigLine);
	   g2.setColor(Color.PINK);
	   g2.draw(smallLine);
	   dial1.paintIcon(null, g, x, y);
	   dial2.paintIcon(null, g, smallX, smallY);
	   
   }
   
   public void topButtonPressed() {
	   
	   if(!running) { 
		   thisMoment = Instant.now();
		   running = true;
	   }else if(frozen) {
		   frozen = !frozen;
	   }else {
		   running = false;
	   }
   }
   
   public void secondButtonPressed() {
	   frozen = !frozen;
   }
   
   private double totalDelay;
   private boolean running;
   private boolean frozen;
   private int x;
   private int y;
   private int width;
   private int smallWidth;
   private double degree;
   private double smallDegree;
   private Icon dial1;
   private Icon dial2;
   private Instant thisMoment;
}
