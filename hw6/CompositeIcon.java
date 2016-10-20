import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.Icon;

/**
 * An icon that is composed of several individual icons.
 * 
 * @author linxiaofan
 *
 */
public class CompositeIcon implements Icon
{

   /**
    * Construct an empty composite icon
    */
   public CompositeIcon()
   {
      icons = new ArrayList<Icon>();
   }

   @Override
   public void paintIcon(Component c, Graphics g, int x, int y)
   {
      for (Icon e : icons)
      {
         e.paintIcon(c, g, x, y);

      }

   }

   @Override
   public int getIconWidth()
   {
      int max = 0;
      for (Icon e : icons)
      {
         if (max < e.getIconWidth())
         {
            max = e.getIconWidth();
         }
      }
      return max;
   }

   @Override
   public int getIconHeight()
   {
      int max = 0;
      for (Icon e : icons)
      {
         if (max < e.getIconHeight())
         {
            max = e.getIconHeight();
         }
      }
      return max;
   }

   /**
    * Add an Icon into an arraylist of icons
    * 
    * @param anIcon
    *           the icon
    */
   public void add(Icon anIcon)
   {
      icons.add(anIcon);
   }

   private ArrayList<Icon> icons;

}
