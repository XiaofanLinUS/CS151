import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Primes
{
   public static Runnable printPrimes(BigInteger start, long length)
   {
      return () -> {
         BigInteger n = start;
         for (long i = 0; i < length; i++)
         {
            if (n.isProbablePrime(100))
               System.out.println(n);

            n = n.add(BigInteger.ONE);
         }
      };
   }

   public static Callable<Long> countPrimes(BigInteger start, long length)
   {
      return () -> {
         Long total = (long) 0;
         BigInteger n = start;
         for (long i = 0; i < length; i++)
         {
            if (n.isProbablePrime(100))
               total += 1;
            else
               nonprime++;
            n = n.add(BigInteger.ONE);
         }
         System.out.println(nonprime);
         return total;
      };
   }

   public static void main(String[] args) throws InterruptedException, ExecutionException
   {
      /*
       * Runnable r1 = printPrimes(new BigInteger("1000000000000000"), 10000);
       * Runnable r2 = printPrimes(new BigInteger("2000000000000000"), 10000);
       */
      Callable<Long> c1 = countPrimes(new BigInteger("1000000000000000"), 500_000);
      Callable<Long> c2 = countPrimes(new BigInteger("1000000000500000"), 500_000);
      ExecutorService service = Executors.newFixedThreadPool(1);
      /*
       * service.execute(r1); service.execute(r2);
       */

      long start = System.currentTimeMillis();
      Future<Long> f1 = service.submit(c1);
      Future<Long> f2 = service.submit(c2);
      System.out.println(f1.get());
      System.out.println(f2.get());
      long end = System.currentTimeMillis();
      System.out.println("Milliseconds: " + (end - start));
      service.shutdown();
   }

   private static long nonprime = 0;
}
