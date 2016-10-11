import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
   This program tests the mail system. A single phone
   communicates with the program through System.in/System.out.
*/
public class MailSystemTester
{
   public static void main(String[] args) throws IOException
   {
      MailSystem system = new MailSystem();
      Console console = new Console(system, new Scanner(System.in), new PrintWriter(System.out, true));
      console.run();
   }
}
