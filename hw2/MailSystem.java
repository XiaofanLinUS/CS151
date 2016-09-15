import java.util.ArrayList;

/**
   A system of mail boxes.
*/
public class MailSystem
{
   /**
      Constructs a mail system
   */
   public MailSystem()
   {
	  
      mailboxes = new ArrayList<Mailbox>();
      users = new ArrayList<User>();
   }

   /**
    * find the user's account with the account's name
    * @param name the account's name
    * @return the user's account
    */
   public User findUser(String name)
   {
      for(User e : users) {
    	  if (e.getName().equals(name)) {
    		  return e;
    	  }
      }
      return null;
   }
   
   /**
    * Add the given Message to the given receiver's mailbox
    * @param receiver the given receiver
    * @param theMessage the given Message
    * @return false if there is no such user else true
    */
   public boolean sendMessage(String receiver, String theMessage) {
	   User theReceiver = findUser(receiver);
	   if(theReceiver==null) {
		   return false;
	   }
	   Message aMessage = new Message(theMessage, currentUser.getName());
	   theReceiver.getmailbox().addMessage(aMessage);
	   return true;
   }
   
   /**
    * Log in the account given its info
    * @param theUsername the user name of the account
    * @param thePassword the password of the account
    * @return -2 if no such user -1 if password is wrong 0 then everything is fine
    */
   public int logIn(String theUsername, String thePassword) {
	   User theUser = findUser(theUsername);
	   if(theUser == null) {
		   return -2;
	   }
	   if(!theUser.validate(thePassword)) {
		   return -1;
	   }else {
		   currentUser = theUser;
		   return 0;
	   }
   }
   /**
    * Log out the user, Remove the current user from this system
    */
   public void logOut() {
	   currentUser = null;
   }
   
   /**
    * Sign up a new user with a given info and add it into the arraylist users
    * @param aUsername the account name
    * @param aPassword the account password
    * @return false if there is an account with the same name else true
    */
   public boolean register(String aUsername, String aPassword) {
	   if(findUser(aUsername) != null) {
		   return false;
	   }
	   User aUser = new User(aUsername, aPassword);
	   users.add(aUser);
	   mailboxes.add(aUser.getmailbox());
	   return true;
   }
   
   /**
    * Get the current user
    * @return the current user
    */
   public User getCurrentUser() {
	   return currentUser;
   }
   
   /**
    * Read the message from the user's mailbox
    * @return an empty string if there is no message else a string contains 
    * the content of messages in user's mailbox
    */
   public String readMessage() {
	   Message currentMessage = currentUser.getmailbox().getCurrentMessage();
	   if(currentMessage==null) {
		   return "";
	   }
	   String sender = currentMessage.getOwner();
	   String content = currentMessage.getText();
	   String text = "From: " + sender + "\n" + content;
	   return text;
   }
   
   /**
    * Keep the reading message
    */
   public void keepMessage() {
	   currentUser.getmailbox().saveCurrentMessage();
   }
   
   /**
    * Erase the reading message
    */
   public void eraseMessage() {
	   currentUser.getmailbox().removeCurrentMessage();
   }
   
   private ArrayList<Mailbox> mailboxes;
   private ArrayList<User> users;
   private User currentUser;
}
