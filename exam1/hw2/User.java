/**
 * A user in a messaging system.
 */
public class User 
{
   /**
    * Creates a user with an empty mailbox.
    * @param username the name of this user
    * @param password the password of this user
    */
   public User(String username, String password)
   {
      this.username = username;
      this.password = password;
      mailbox = new Mailbox();
   }
   
   /**
    * Checks whether this user has the given password.
    * @param password the password to check against
    * @return true if the passwords match
    */
   public boolean checkPassword(String password)
   {
      return this.password.equals(password);
   }

   /**
    * Gets the name of this user.
    * @return the user name
    */
   public String getUsername()
   {
      return username;
   }
   
   /**
    * Gets the mailbox of this user.
    * @return the mailbox
    */
   public Mailbox getMailbox() { return mailbox; }
   
   private String username;
   private String password;   
   private Mailbox mailbox;
}