import java.awt.*;
import javax.swing.*;

public class ShiftedIcon implements Icon
{
   public ShiftedIcon(Icon icon, int x, int y)
   {
      this.icon = icon;
      this.shiftx = x;
      this.shifty = y;
   }

   public int getIconWidth()
   {
      return icon.getIconWidth() + shiftx;
   }

   public int getIconHeight()
   {
      return icon.getIconHeight() + shifty;
   }

   public void paintIcon(Component c, Graphics g, int x, int y)
   {
      icon.paintIcon(c, g, x + shiftx, y + shifty);
   }

   private Icon icon;
   private int shiftx;
   private int shifty;
}
