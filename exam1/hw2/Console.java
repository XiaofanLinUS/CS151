import java.io.PrintWriter;
import java.util.Scanner;

/**
   Connects a phone to the mail system. The purpose of this
   class is to keep track of the state of a connection, since
   the phone itself is just a source of individual key presses.
*/
public class Console
{
   /**
      Construct a Connection object.
      @param s a MailSystem object
      @param in a scanner for reading input
      @param out a writer for printing output
   */
   public Console(MailSystem s, Scanner in, PrintWriter out)
   {
      system = s;
      this.in = in;
      this.out = out;
   }
   
   /**
    * Runs the user interaction.
    */
   public void run()
   {
      while (true)
      {
         out.println("Welcome to TextChat.");
         String ch = menu("[L]ogin  [N]ew user  [Q]uit");
         if (ch.equals("N")) newUser();
         else if (ch.equals("L")) login();
         else return;
      }
   }
      
   /**
    * Gets a menu option from the user.
    * @param options a string describing the options, with each option enclosed in []
    * @return the selected option
    */
   private String menu(String options)
   {
      while (true)
      {
         out.println(options + ":");
         String input = in.nextLine().toUpperCase();
         if (options.contains("[" + input + "]")) return input;
      }
   }
   
   /**
    * Reads one-line input from the user.
    * @param prompt the prompt string
    * @return the line that the user entered.
    */
   private String readInput(String prompt)
   {
      out.println(prompt + ":");
      return in.nextLine();
   }
   
   /**
    * Reads multi-line input from the user.
    * @param prompt the prompt string
    * @return the lines that the user entered.
    */
   private String readText(String prompt)
   {
      out.println(prompt + ", . to end:");
      String result = "";
      while (true)
      {
         String input = in.nextLine();
         if (input.equals(".")) return result;
         else result += input + "\n";
      }
   }
   
   /**
    * User interaction for creating a new user.
    */
   private void newUser()
   {
      String username = readInput("Select a user name");
      String password = readInput("Select a password");
      if (system.addUser(username, password))
         System.out.println("User added.");
      else
         System.out.println("User not added.");
   }
   
   /**
    * User interaction for logging in and carrying out tasks.
    */
   private void login()
   {
      String username = readInput("User name");
      String password = readInput("Password");
      User user = system.findUser(username, password);
      if (user != null)
      {
         System.out.println("Welcome " + user.getUsername() + ".");
         while (true)
         {
            String input = menu("[S]end message  [R]ead messages  [L]ogout");
            if (input.equals("S")) sendMessage(user);
            else if (input.equals("R")) readMessages(user);
            else return;
         }
      }
   }

   /**
    * User interaction for sending a message.
    * @param sender the user who is sending the message
    */
   private void sendMessage(User sender)
   {
      String recipientName = readInput("To");
      String body = readText("Message text");
      if (system.sendMessage(new Message(sender.getUsername(), body), recipientName))
         out.println("Message sent.");
      else
         out.println("Message not sent.");
   }
   
   /**
    * User interaction for reading messages.
    * @param user the user who is reading messages
    */
   private void readMessages(User user)
   {
      Mailbox mailbox = user.getMailbox();
      while (true)
      {
         Message message = mailbox.getCurrentMessage();
         if (message == null) return; 
         out.print(message.getText());
         String input = menu("[K]eep  [E]rase");
         if (input.equals("K")) mailbox.saveCurrentMessage();
         else mailbox.removeCurrentMessage();
         input = menu("[N]ext  [D]one with messages");
         if (input.equals("D")) return;
      }
   }
   
   private Scanner in;
   private PrintWriter out;
   private MailSystem system;
}
