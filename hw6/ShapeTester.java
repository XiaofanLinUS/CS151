import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.geom.Ellipse2D;

public class ShapeTester
{
   public static void main(String[] args)
   {
      CompositeShape shape = new CompositeShape();
      shape.add(TextShapeMaker.makeShape("Hello", new Font("Serif", Font.PLAIN, 50)));
      shape.add(HouseShapeMaker.makeShape(20, 20));
      shape.add(new BoxedShape(new Ellipse2D.Double(105, 110, 20, 30), 10));
      JOptionPane.showMessageDialog(
            null, 
            "",
            "",
            JOptionPane.INFORMATION_MESSAGE,
            new ShapeIcon(shape));
      System.exit(0);
   }
}