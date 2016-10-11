import java.util.ArrayList;

/**
   A system of users who send messages to each other.
*/
public class MailSystem
{
   /**
      Constructs a mail system with no users.
   */
   public MailSystem()
   {
      users = new ArrayList<>();
   }

   /**
    * Add a user.
    * @param username the user name 
    * @param password the password
    * @return true if the user was successfully added, false if
    * there already was a user with the same name.
    */
   public boolean addUser(String username, String password)
   {
      if (findUser(username) != null) return false;
      users.add(new User(username, password));
      return true;
   }
   
   /**
    * Locate a user.
    * @param username the user name 
    * @param password the password
    * @return the user or null if not found
    */
   public User findUser(String username, String password)
   {
      User u = findUser(username);
      if (u != null && u.checkPassword(password))
         return u;         
      else
         return null;
   }
   
   /**
    * Locate a user.
    * @param username the user name 
    * @return the user or null if not found
    */
   private User findUser(String username)
   {
      for (User u : users)
      {
         if (u.getUsername().equals(username))
            return u;         
      }
      return null;
   }

   /**
    * Sends a message to a user.
    * @param message the message to be sent
    * @param recipientName the name of the user receiving the message
    * @return
    */
   public boolean sendMessage(Message message, String recipientName)
   {
      User recipient =  findUser(recipientName);
      if (recipient == null) return false;
      recipient.getMailbox().addMessage(message);
      return true;
   }

   private ArrayList<User> users;
}
