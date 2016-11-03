import java.util.HashSet;
import java.util.Set;

public class Prog2
{
   public static void main(String[] args)
   {
      Set<Point2> set = new HashSet<>();
      int n = 10;
      for (int i = 1; i <= n; i++)
         for (int j = 1; j <= n; j++)
            set.add(new Point2(i % 2, j % 2));
      System.out.println(set.size());
   }
}