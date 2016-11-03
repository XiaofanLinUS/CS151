import java.util.HashSet;
import java.util.Set;

public class Prog1
{
   public static void main(String[] args)
   {
      Set<Point> set = new HashSet<>();
      int n = 5;
      for (int i = 1; i <= n; i++)
         for (int j = 1; j <= n; j++)
         {
            Point a = new Point(i % 2, j % 2);
            set.add(a);
            System.out.println(a.hashCode());
         }
      System.out.println(set.size());
   }
}