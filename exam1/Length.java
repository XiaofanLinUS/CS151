/**
   Length, measured the good old American way, in feet and inches.
*/
public class Length
{
   /**
      Constructs a Length object.
      @param feet the given number of feet, >= 0
      @param inches the given number of inches, >= 0
    */
   public Length(int feet, int inches)
   {
      assert feet >= 0;
      assert inches >= 0;

      this.feet = feet + inches / 12;
      this.inches = inches % 12;
   }

   /**
      The number of feet in this length, when normalized so
      that the number of inches is < 12.
      @return the number of feet
   */
   public int getFeet()
   {
      return feet;
   }

   /**
      The number of inches in this length, when normalized so
      that the number of inches is < 12.
      @return the number of inches
   */
   public int getInches()
   {
      return inches;
   }

   /**
      Adds two lengths.
      @param other another length
      @param the sum of this and other
   */
   public Length sum(Length other)
   {
      return new Length(feet + other.feet, inches + other.inches);     
   }

   /**
      Subtracts two lengths.
      @param other another length
      @param the result of subtracting the smaller of the two
      lengths from the larger
   */
   public Length difference(Length other)
   {
      if (feet > other.feet)
      {
         if (inches >= other.inches)
            return new Length(feet - other.feet, inches - other.inches);
         else
            return new Length(feet - other.feet - 1, inches - other.inches + 12);
      }
      else if (feet == other.feet)
      {
         if (inches >= other.inches)
            return new Length(0, inches - other.inches);
         else
            return new Length(0, other.inches - inches);
      }
      else
      {
         if (inches >= other.inches)
            return new Length(other.feet - feet - 1, other.inches - inches + 12);
         else
            return new Length(other.feet - feet, other.inches - inches);
      }      
   }
   
   private int inches;
}
