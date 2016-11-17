import javax.swing.*;

/**
   A program for editing UML diagrams.
*/
public class NetworkGraphEditor
{
   public static void main(String[] args)
   {
      JFrame frame = new GraphFrame(new NetworkGraph());
      frame.setVisible(true);
   }
}

