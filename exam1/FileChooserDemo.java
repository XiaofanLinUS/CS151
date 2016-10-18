import javax.swing.*;
import javax.swing.filechooser.*;

public class FileChooserDemo
{
   public static void main(String[] args)
   {
      JFileChooser chooser = new JFileChooser(".");
      FileFilter filter = new FileNameExtensionFilter("Source files", "java", "cpp");
      
      chooser.setFileFilter(filter);
      int returnVal = chooser.showOpenDialog(null);
      if(returnVal == JFileChooser.APPROVE_OPTION)
      {
         System.out.println("You chose to open this file: " +
            chooser.getSelectedFile().getName());
      }
   }
}
