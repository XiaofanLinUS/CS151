public class Point2
{
   private int x;
   private int y;

   public Point2(int x, int y)
   {
      this.x = x;
      this.y = y;
   }

   public String toString()
   {
      return getClass().getName() + "[x=" + x + ",y=" + y + "]";
   }

   public int hashCode()
   {
      return x + y;
   }
}