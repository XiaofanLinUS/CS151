import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
   Tester for dial.
*/


public class Viewer
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      Icon icon = new Dial(300, true, Color.GREEN);
  
      final JLabel label = new JLabel(icon);
      frame.setLayout(new FlowLayout());
      frame.add(label);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
}

