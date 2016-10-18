import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class BoxedShape implements Shape
{

   public BoxedShape(Shape aShape, int aPadding)
   {
      shape = aShape;
      padding = aPadding;
   }

   @Override
   public Rectangle getBounds()
   {
      Rectangle oldBound = shape.getBounds();
      Rectangle bound = new Rectangle((int) oldBound.getX() - padding, (int) oldBound.getY() - padding,
            (int) oldBound.getWidth() + 2 * padding, (int) oldBound.getHeight() + 2 * padding);
      return bound;
   }

   @Override
   public Rectangle2D getBounds2D()
   {
      return (Rectangle2D) getBounds();
   }

   @Override
   public boolean contains(double x, double y)
   {
      Rectangle2D.Double bound2D = (Rectangle2D.Double) getBounds2D();
      return bound2D.contains(x, y);
   }

   @Override
   public boolean contains(Point2D p)
   {
      return contains(p.getX(), p.getY());
   }

   @Override
   public boolean intersects(double x, double y, double w, double h)
   {
      Rectangle2D.Double bound2D = (Rectangle2D.Double) getBounds2D();
      return bound2D.intersects(x, y, w, h);
   }

   @Override
   public boolean intersects(Rectangle2D r)
   {
      return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
   }

   @Override
   public boolean contains(double x, double y, double w, double h)
   {
      Rectangle2D.Double bound2D = (Rectangle2D.Double) getBounds2D();
      return bound2D.contains(x, y, w, h);
   }

   @Override
   public boolean contains(Rectangle2D r)
   {
      return contains(r.getX(), r.getY(), r.getWidth(), r.getHeight());
   }

   @Override
   public PathIterator getPathIterator(AffineTransform at)
   {
      return new BoxedShapePathIterator(shape, padding, at);
   }

   @Override
   public PathIterator getPathIterator(AffineTransform at, double flatness)
   {
      return new BoxedShapePathIterator(shape, padding, at, flatness);
   }

   private Shape shape;
   private int padding;
}
