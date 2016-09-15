/**
   A message left by the caller.
*/
public class Message
{
   /**
      Construct a Message object.
      @param messageText the message text
   */
   public Message(String messageText, String theOwner)
   {
      text = messageText;
      owner = theOwner;
   }

   /**
      Get the message text.
      @return message text
   */
   public String getText()
   {
      return text;
   }
   
   /**
    * Get the creator's name of this message
    * @return the creator's name
    */
   public String getOwner()
   {
      return owner;
   }


   private String text;
   private String owner;
}
