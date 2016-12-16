import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * A shape that boxes another shape.
 */
public class BoxedShape implements Shape, Cloneable, Serializable
{
   /**
    * Constructs a boxed shape.
    * 
    * @param shape
    *           the shape to box
    * @param padding
    *           the space around the shape
    */
   public BoxedShape(Shape shape, int padding) throws java.io.IOException
   {
      this.shape = shape;
      this.padding = padding;
   }

   public boolean contains(double x, double y)
   {
      return getBounds2D().contains(x, y);
   }

   public boolean contains(double x, double y, double w, double h)
   {
      return getBounds2D().contains(x, y, w, h);
   }

   public boolean contains(Point2D p)
   {
      return getBounds2D().contains(p);
   }

   public boolean contains(Rectangle2D r)
   {
      return getBounds2D().contains(r);
   }

   public Rectangle getBounds()
   {
      Rectangle bounds = shape.getBounds();
      return new Rectangle((int) bounds.getX() - padding - 1, (int) bounds.getY() - padding - 1,
            (int) bounds.getWidth() + 2 * padding + 2, (int) bounds.getHeight() + 2 * padding + 2);
   }

   public Rectangle2D getBounds2D()
   {
      Rectangle2D bounds = shape.getBounds2D();
      return new Rectangle2D.Double(bounds.getX() - padding - 1, bounds.getY() - padding - 1,
            bounds.getWidth() + 2 * padding + 2, bounds.getHeight() + 2 * padding + 2);
   }

   public PathIterator getPathIterator(AffineTransform at)
   {
      return new BoxedShapePathIterator(shape, padding, at);
   }

   public PathIterator getPathIterator(AffineTransform at, double flatness)
   {
      return new BoxedShapePathIterator(shape, padding, at, flatness);
   }

   public boolean intersects(double x, double y, double w, double h)
   {
      return getBounds().intersects(x, y, w, h);
   }

   public boolean intersects(Rectangle2D r)
   {
      return getBounds2D().intersects(r);
   }

   public BoxedShape clone() throws CloneNotSupportedException
   {
      BoxedShape cloned = (BoxedShape) super.clone();
      cloned.shape = shape.clone();
      cloned.padding = padding;

      return cloned;
   }

   private Shape shape;
   private int padding;
}
