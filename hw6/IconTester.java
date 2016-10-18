import java.awt.Font;

import javax.swing.*;

public class IconTester
{
   public static void main(String[] args)
   {
      CompositeIcon icon = new CompositeIcon();
      icon.add(new MarsIcon(60));
      icon.add(new CarIcon(100));
      String size = icon.getIconWidth() + " x " + icon.getIconHeight();
      icon.add(
         new ShiftedIcon(
            new BoxedIcon(
               new BoxedIcon(
                  new BoxedIcon(
                     new ShapeIcon(
                        TextShapeMaker.makeShape(size, new Font("Serif", Font.PLAIN, 50))), 
            5), 1), 0), 0, 60));
      
      JOptionPane.showMessageDialog(
            null, 
            "",
            "",
            JOptionPane.INFORMATION_MESSAGE,
            icon);
      System.exit(0);
   }
}

