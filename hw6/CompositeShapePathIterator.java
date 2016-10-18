import java.awt.geom.PathIterator;
import java.util.ArrayList;

public class CompositeShapePathIterator implements PathIterator
{

   public CompositeShapePathIterator()
   {
      currentIndex = 0;
      iterators = new ArrayList<>();
   }

   @Override
   public int getWindingRule()
   {
      return iterators.get(currentIndex).getWindingRule();
   }

   @Override
   public boolean isDone()
   {
      boolean result = true;
      for (PathIterator e : iterators)
      {
         result &= e.isDone();
      }
      return result;
   }

   @Override
   public void next()
   {
      PathIterator currentIterator = iterators.get(currentIndex);
      if (!currentIterator.isDone())
      {
         currentIterator.next();
      } else
      {
         currentIndex++;
         iterators.get(currentIndex).next();
      }

   }

   @Override
   public int currentSegment(float[] coords)
   {
      PathIterator currentIterator = iterators.get(currentIndex);
      if (!currentIterator.isDone())
      {
         return currentIterator.currentSegment(coords);
      } else
      {
         currentIndex++;
         return iterators.get(currentIndex).currentSegment(coords);
      }
   }

   @Override
   public int currentSegment(double[] coords)
   {
      PathIterator currentIterator = iterators.get(currentIndex);
      if (!currentIterator.isDone())
      {
         return currentIterator.currentSegment(coords);
      } else
      {
         currentIndex++;
         return iterators.get(currentIndex).currentSegment(coords);
      }
   }

   public void add(PathIterator pathIterator)
   {
      iterators.add(pathIterator);
   }

   private int currentIndex;
   private ArrayList<PathIterator> iterators;
}
