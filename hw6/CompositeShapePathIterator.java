import java.awt.geom.PathIterator;
import java.util.ArrayList;

/**
 * An iterator that is composed of several individual iterator.
 * 
 * @author linxiaofan
 *
 */
public class CompositeShapePathIterator implements PathIterator
{

   /**
    * Constructs an empty iterator
    */
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
      return iterators.size() == 0 ? true : iterators.get(iterators.size() - 1).isDone();
   }

   @Override
   public void next()
   {
      if (isDone())
      {
         return;
      }
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

   /**
    * Add an iterator to a list of iterators
    * 
    * @param pathIterator
    *           the given iterator
    */
   public void add(PathIterator pathIterator)
   {
      iterators.add(pathIterator);
   }

   private int currentIndex;
   private ArrayList<PathIterator> iterators;
}
