// https://en.wikipedia.org/wiki/Stopwatch

import java.awt.*;
import java.time.*;

public class Stopwatch implements MoveableShape 
{
   public Stopwatch(double radius)
   {
      seconds = new Dial(radius, true, Color.RED);
      minutes = new Dial(radius * 0.4, false, Color.MAGENTA);
      this.radius = radius;
      minutes.setAngle(90);
   }

   public void draw(Graphics2D g2)
   {
      seconds.paintIcon(null, g2, 0, 0);
      minutes.paintIcon(null, g2, (int)(radius * 0.6), (int)(radius * 0.35));
   }

   public void move()
   {
      Instant now = Instant.now();
      if (startTime != null && !frozen)
      {
         Duration elapsed = Duration.between(startTime, now);
         long nanos = elapsed.toNanos();
         int milliseconds = (int)(nanos / 1000000);
         double elapsedMinutes = milliseconds / 60000.0;
         milliseconds %= 60000;
         minutes.setAngle(elapsedMinutes * 360 / 60);
         seconds.setAngle(milliseconds * 360.0 / 60000);
      }
   }

   public void topButtonPressed()
   {
      if (startTime == null)
      {
         startTime = Instant.now();
      }
      else
      {
         startTime = null;
      }
   }

   public void secondButtonPressed()
   {
      if (startTime == null)
      {
         minutes.setAngle(0);
         seconds.setAngle(0);
      }
      else
      {
         frozen = !frozen;
      }
   }

   private double radius;
   private Dial seconds;
   private Dial minutes;
   private Instant startTime;
   private boolean frozen;
}
