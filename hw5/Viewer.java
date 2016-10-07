import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Tester for dial.
 */

public class Viewer
{
   /**
    * A main method
    * 
    * @param args
    *           just a thing
    */
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      Icon icon = new Dial(400, false, Color.BLACK);

      final JLabel label = new JLabel(icon);
      frame.setLayout(new FlowLayout());
      frame.add(label);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
}
