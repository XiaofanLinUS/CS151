
public class OneTimeText implements Text
{

   public OneTimeText(String aContent)
   {
      content = aContent;
      firstTime = true;
   }

   @Override
   public String getContent()
   {
      return firstTime ? content : "";
   }

   private String content;
   private boolean firstTime;
}
