import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * This program implements an animation that moves a stop watch.
 */
public class AnimationTester
{
   /**
    * A main method
    * 
    * @param args
    *           a thing
    */
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      int radius = Integer.parseInt(args[0]);
      Stopwatch stopwatch = new Stopwatch(radius);

      ShapeIcon icon = new ShapeIcon(stopwatch, 2 * radius, 2 * radius);

      JButton top = new JButton("Top");
      top.addActionListener(event -> stopwatch.topButtonPressed());
      JButton second = new JButton("Second");
      second.addActionListener(event -> stopwatch.secondButtonPressed());

      JLabel label = new JLabel(icon);
      frame.setLayout(new FlowLayout());
      frame.add(top);
      frame.add(second);
      frame.add(label);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);

      final int DELAY = 100;
      // Milliseconds between timer ticks
      Timer t = new Timer(DELAY, event -> {
         stopwatch.move();
         label.repaint();
      });
      t.start();
   }

}
