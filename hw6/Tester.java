import java.awt.geom.Rectangle2D;

public class Tester
{
   public static void main(String[] args)
   {
      Rectangle2D.Double a = new Rectangle2D.Double(0, 0, 100, 100);
      Rectangle2D bound = a.getBounds2D();
      System.out.println(bound.getWidth() + "" + bound.getHeight() + "" + bound.getX() + "" + bound.getY());
   }
}
