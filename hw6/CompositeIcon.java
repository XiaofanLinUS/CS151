import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.Icon;

public class CompositeIcon implements Icon
{

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
      int max = icons.get(0).getIconWidth();
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
      int max = icons.get(0).getIconHeight();
      for (Icon e : icons)
      {
         if (max < e.getIconHeight())
         {
            max = e.getIconHeight();
         }
      }
      return max;
   }

   public void add(Icon anIcon)
   {
      icons.add(anIcon);
   }

   private ArrayList<Icon> icons;

}
