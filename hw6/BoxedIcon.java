import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;

/**
 * A boxed icon with a padding
 * 
 * @author linxiaofan
 *
 */
public class BoxedIcon implements Icon
{

   /**
    * Construct a boxed icon with a given padding and a given icon
    * 
    * @param anIcon
    *           the icon
    * @param i
    *           the padding
    */
   public BoxedIcon(Icon anIcon, int i)
   {
      icon = anIcon;
      padding = i;
   }

   @Override
   public void paintIcon(Component c, Graphics g, int x, int y)
   {

      Graphics2D g2 = (Graphics2D) g;
      Stroke oldStroke = g2.getStroke();
      g2.setStroke(new BasicStroke(1));
      g2.draw(new Rectangle2D.Double(x, y, getIconWidth() - 2 * STROKE, getIconHeight() - 2 * STROKE));
      g2.setStroke(oldStroke);
      icon.paintIcon(c, g, x + padding + 1, y + padding + 1);
   }

   @Override
   public int getIconWidth()
   {
      return icon.getIconWidth() + 2 * padding + 2 * STROKE;
   }

   @Override
   public int getIconHeight()
   {
      return icon.getIconHeight() + 2 * padding + 2 * STROKE;
   }

   private int padding;
   private Icon icon;
   private static final int STROKE = 1;
}
