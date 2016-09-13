import java.util.ArrayList;
import java.util.Scanner;

/**
   A telephone that takes simulated keystrokes and voice input
   from the user and simulates spoken text.
*/
public class Console
{
   /**
      Construct phone object.
      @param aScanner that reads text from a character-input stream
   */
   public Console(MailSystem aSys)
   {
	  sys = aSys;
      buff =new ArrayList<>();
   }

   /**
      Speak a message to System.out.
      @param output the text that will be "spoken"
   */
   public void display(String output)
   {
      System.out.println(output);
   }

  
   public void run()
   {
      boolean more = true;
	  Scanner scanner = new Scanner(System.in);
      while (more)
      {
         more = execute(scanner);
      }
   }
   
   
   public boolean execute(Scanner theInput) {
	   boolean more = true;
	   String input = "";
	   if(state == MAIN_MENU) {
		   display("Welcome to TextChat.");
		   display("[L]ogin  [N]ew user  [Q]uit:");
		   input = theInput.next();
		   if(input.equalsIgnoreCase("L")) {
			   state = SIGNIN;
		   }else if(input.equalsIgnoreCase("N")) {
			   state = SIGNUP;
		   }else if(input.equalsIgnoreCase("Q")) {
			   more = false;
		   }
	   }else if(state == USER_MENU) {
		   display(sys.getCurrentUser().getGreeting());
		   if(input.equalsIgnoreCase("S")) {
		   }else if(input.equalsIgnoreCase("R")) {
		   }else if(input.equalsIgnoreCase("L")) {
			   sys.logOut();
			   state = MAIN_MENU;
		   }
	   }else if(state == SIGNIN) {
		   signIn(theInput);
	   }else if(state == SIGNUP) {
		   signUp(input);
	   }
	   
	   return more;
   }

   private void signUp(String input) {
	   if(step == 0) {
		   display("Select a user name:");
		   buff.add(input);
	   }else if(step == 1) {
		   display("Select a password:");
		   buff.add(input);
	   }else if(step == 2) {
		   display("User added.");
		   sys.register(buff.get(0), buff.get(1));
		   buff = new ArrayList<>();
		   step = 0;
		   state = MAIN_MENU;
	   }
   }

private void signIn(String input) {
	 if(step == 0) {
		   display("User name:");
		   buff.add(input);
	   }else if(step == 1) {
		   display("Password:");
		   buff.add(input);
		   sys.logIn(buff.get(0), buff.get(1));
		   buff = new ArrayList<>();
		   step = 0;
		   state = USER_MENU;
	   }
	}

   private MailSystem sys;
   private ArrayList<String> buff;
   
   private static final int MAIN_MENU = 1;
   private static final int USER_MENU = 2;
   private static final int MESSAGE_MENU1 = 3;
   private static final int MESSAGE_MENU2 = 4;
   private static final int SIGNIN = 5;
   private static final int SIGNUP = 6;
   private static final int SENDMESSAGE = 7;
   
   private int state = 1;
   private int step = 0;
}
