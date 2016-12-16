import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class DiamondNode implements Node
{

   public DiamondNode(int Size)
   {
      this.size = Size;
   }

   @Override
   public void draw(Graphics2D g2)
   {
      Line2D.Double l1 = new Line2D.Double(x + size / 2, y, x, y + size / 2);

      Line2D.Double l2 = new Line2D.Double(x + size / 2, y, x + size / 2, y + size / 2);

      Line2D.Double l3 = new Line2D.Double(x, y + size / 2, x + size / 2, y + size);

      Line2D.Double l4 = new Line2D.Double(x + size / 2, y + size, x + size, y + size / 2);

      g2.draw(l1);
      g2.draw(l2);
      g2.draw(l3);
      g2.draw(l4);

   }

   @Override
   public void translate(double dx, double dy)
   {
      x = x + dx;
      y = y + dy;

   }

   @Override
   public boolean contains(Point2D aPoint)
   {

      return getBounds().contains(aPoint);
   }

   @Override
   public Point2D getConnectionPoint(Point2D other)
   {
      double centerX = x + size / 2;
      double centerY = y + size / 2;
      double dx = other.getX() - centerX;
      double dy = other.getY() - centerY;
      double distance = Math.sqrt(dx * dx + dy * dy);
      if (distance == 0)
         return other;
      else if (dx < dy && dx >= -dy)
      {
         return new Point2D.Double(x + size / 2, y);
      } else if (dx >= dy && dx >= -dy)
      {
         return new Point2D.Double(x + size, y + size / 2);
      } else if (dx < -dy && dx < dy)
      {
         return new Point2D.Double(x, y + size / 2);
      } else
      {
         return new Point2D.Double(x + size / 2, y + size);
      }

      return null;
   }

   @Override
   public Rectangle2D getBounds()
   {
      return new Rectangle2D.Double(x, y, size, size);
   }

   private double x;
   private double y;
   private double size;

}
