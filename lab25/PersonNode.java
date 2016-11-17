import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;

public class PersonNode extends CircleNode

{

   public PersonNode()
   {
      super(Color.WHITE);
      super.setSize(40);
      setImageURL("http://horstmann.com/sjsu/fall2016/cs151/lecture25/default.png");
   }

   public String getImageURL()
   {
      return imageURL;
   }

   public void setImageURL(String imageURL)
   {
      this.imageURL = imageURL;
      try
      {
         icon = new ImageIcon(new URL(imageURL));
         image = icon.getImage();
      } catch (IOException ex)
      {
      }
   }

   public void draw(Graphics2D g2)
   {
      super.draw(g2);
      double width = image.getWidth(null);
      double height = image.getHeight(null);
      Rectangle2D bounds = getBounds();
      double scale = Math.min(bounds.getWidth() / width, bounds.getHeight() / height);
      width = scale * width;
      height = scale * height;

      Shape oldClip = g2.getClip();
      g2.setClip(new Ellipse2D.Double(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()));

      if (icon != null)
      {
         g2.drawImage(image, (int) (bounds.getX() + bounds.getWidth() - width),
               (int) (bounds.getY() + bounds.getHeight() - height), (int) width, (int) height, null);
      }
      g2.setClip(oldClip);
   }

   private String imageURL;
   private ImageIcon icon;
   private Image image;

}
