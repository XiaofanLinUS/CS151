
public class PlainText implements Text
{

   public PlainText(String aContent)
   {
      content = aContent;
   }

   public String getContent()
   {
      return content;
   }

   private String content;

}
