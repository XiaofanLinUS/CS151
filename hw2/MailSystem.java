import java.util.ArrayList;

/**
   A system of voice mail boxes.
*/
public class MailSystem
{
   /**
      Constructs a mail system with a given number of mailboxes
      @param mailboxCount the number of mailboxes
   */
   public MailSystem()
   {
	  
      mailboxes = new ArrayList<Mailbox>();
      users = new ArrayList<User>();
   }

   public User findUser(String name)
   {
      for(User e : users) {
    	  if (e.getName().equals(name)) {
    		  return e;
    	  }
      }
      return null;
   }
   
   public boolean sendMessage(String receiver, String theMessage) {
	   User theReceiver = findUser(receiver);
	   if(theReceiver==null) {
		   return false;
	   }
	   Message aMessage = new Message(theMessage, currentUser.getName());
	   theReceiver.getmailbox().addMessage(aMessage);
	   return true;
   }
   
   public int logIn(String theUsername, String thePassword) {
	   User theUser = findUser(theUsername);
	   if(theUser == null) {
		   return -2;
	   }
	   if(theUser.validate(thePassword)) {
		   return -1;
	   }else {
		   currentUser = theUser;
		   return 0;
	   }
   }
   
   public void logOut() {
	   currentUser = null;
   }
   
   public void register(String aUsername, String aPassword) {
	   User aUser = new User(aUsername, aPassword);
	   users.add(aUser);
	   mailboxes.add(aUser.getmailbox());
   }
   
   public User getCurrentUser() {
	   return currentUser;
   }
   
   public String readMessage() {
	   String sender = currentUser.getmailbox().getCurrentMessage().getOwner();
	   String content = currentUser.getmailbox().getCurrentMessage().getText();
	   String text = "From: " + sender + "\n" + content;
	   return text;
   }
   
   public void keepMessage() {
	   currentUser.getmailbox().saveCurrentMessage();
   }
   
   public void eraseMessage() {
	   currentUser.getmailbox().removeCurrentMessage();
   }
   
   private ArrayList<Mailbox> mailboxes;
   private ArrayList<User> users;
   private User currentUser;
}
