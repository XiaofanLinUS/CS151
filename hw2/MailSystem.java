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
   
   public int logIn(String theUsername, String thePassword) {
	   User theUser = findUser(theUsername);
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
   private ArrayList<Mailbox> mailboxes;
   private ArrayList<User> users;
   private User currentUser;
}
