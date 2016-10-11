/**
 * A message from a user.
 */
public class Message
{
   /**
    * Construct a Message object.
    * 
    * @param sender
    *           the username of the sender //TODO: Or the User to make Reply
    *           simple???
    * @param messageText
    *           the message text
    */
   public Message(String sender, Text messageText)
   {
      this.sender = sender;
      text = messageText;
   }

   /**
    * Get the message text.
    * 
    * @return message text
    */
   public String getText()
   {
      return "From: " + sender + "\n" + text;
   }

   private String sender;
   private Text text;
}
