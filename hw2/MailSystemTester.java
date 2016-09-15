import java.util.Scanner;

/**
   This program tests the mail system.
*/
public class MailSystemTester
{
   public static void main(String[] args)
   {
      MailSystem system = new MailSystem();
      Scanner console = new Scanner(System.in);
      Console p = new Console(system, console);
      p.run();
   }

}
