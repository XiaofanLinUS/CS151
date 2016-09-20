import sun.nio.cs.ext.SJIS;

public class DayWithTime {
	/**
	 * Constructs a day with a given year, month, and day of the
	 * Julian/Gregorian calendar. The Julian calendar is used for all days
	 * before October 15, 1582
	 * 
	 * @param aYear
	 *            a year != 0
	 * @param aMonth
	 *            a month between 1 and 12
	 * @param aDayOfMonth
	 *            a day of the month between 1 and 31
	 * @param hours
	 *            the hours between 0 and 23
	 * @param minutes
	 *            the minutes between 0 and 59
	 */
	public DayWithTime(int aYear, int aMonth, int aDayOfMonth, int hours, int minutes) {
		year = aYear;
		month = aMonth;
		day = aDayOfMonth;
		hour = hours;
		minute = minutes;
		makeJulian();
	}

	private DayWithTime(int aJulian, int hours, int minutes) {
		julian = aJulian;
		hour = hours;
		minute = minutes;
		makeYmd();
	}

	/**
	 * Returns the year of this day and time
	 * 
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Returns the month of this day and time
	 * 
	 * @return the month
	 */
	public int getMonthValue() {
		return month;
	}

	/**
	 * Returns the day of the month of this day and time
	 * 
	 * @return the day of the month
	 */
	public int getDayOfMonth() {
		return day;
	}

	/**
	 * Returns the hours of this day and time
	 * 
	 * @return the hours
	 */
	public int getHours() {
		return hour;
	}

	/**
	 * Returns the minutes of this day and time
	 * 
	 * @return the minutes
	 */
	public int getMinutes() {
		return minute;
	}

	public String toString() {
		return String.format("%04d-%02d-%02d %02d:%2d", getYear(), getMonthValue(), getDayOfMonth(), getHours(),
				getMinutes());
	}

	/**
	 * Returns a day with time that is a certain number of days away from this
	 * day with time
	 * 
	 * @param n
	 *            the number of days, can be negative
	 * @return a day that is n days away from this one
	 */
	public DayWithTime plusDays(int n) {
		return new DayWithTime(n + julian, hour, minute);
	}

	/**
	 * Returns a day with time that is a certain number of days away from this
	 * day with time
	 * 
	 * @param n
	 *            the number of minutes, can be negative
	 * @return a day that is n days away from this one
	 */
	public DayWithTime plusMinutes(long n) {
		int mins = (int) (n % 60);
		int hours = (int) (((n - mins) / 60) % 24);
		int days = (int) ((n - mins - 60 * hours) / (24 * 60));
		int theMins = (mins + minute) % 60;
		int theHours = (((mins + minute) / 60) + (hours + hour)) % 24;
		days += (((mins + minute) / 60) + (hours + hour)) / 24;
		return new DayWithTime(julian + days, theHours, theMins);
	}

	/**
	 * Returns the number of minutes between this day with time and another day
	 * with time.
	 * 
	 * @param other
	 *            the other day with time
	 * @return the number of minutes that this day with time is away from the
	 *         other
	 */
	public long minutesFrom(DayWithTime other) {
		int days = daysFrom(other);
		int hours = getHours() - other.getHours();
		int mins = getMinutes() - other.getMinutes();
		return days * 24 * 60 + hours * 60 + mins;
	}

	/**
	 * Computes the Julian day number of this day if necessary
	 */
	private void makeJulian() {
		julian = toJulian(year, month, day);
	}

	/**
	 * Converts this Julian day mumber to a calendar date if necessary.
	 */
	private void makeYmd() {
		int[] ymdhm = fromJulian(julian);
		year = ymdhm[0];
		month = ymdhm[1];
		day = ymdhm[2];
	}

	/**
	 * Returns the number of days between this day with time and another day
	 * with time, rounded to the closest value. (Round any difference >= 12
	 * hours to the next day.)
	 * 
	 * @param other
	 *            the other day with time
	 * @return the number of days that this day is away from the other (>0 if
	 *         this day comes later)
	 */
	public int daysFrom(DayWithTime other) {
		int hours = getHours() - other.getHours();
		int mins = getMinutes() - other.getMinutes();
		int days = julian - other.julian;
		float minutes = hours * 60 + mins;
		minutes /= 60 * 24;
		if(days < 0) {
			days = -days;
			days += Math.round(-minutes);
			days = -days;
		}else {
			days += Math.round(minutes);
		}
		return days;
	}

	/**
	 * Computes the Julian day number of the given day.
	 * 
	 * @param year
	 *            a year
	 * @param month
	 *            a month
	 * @param day
	 *            a day of the month
	 * @return The Julian day number that begins at noon of the given day
	 *         Positive year signifies CE, negative year BCE. Remember that the
	 *         year after 1 BCE was 1 CE.
	 * 
	 *         A convenient reference point is that May 23, 1968 noon is Julian
	 *         day number 2440000.
	 * 
	 *         Julian day number 0 is a Monday.
	 * 
	 *         This algorithm is from Press et al., Numerical Recipes in C, 2nd
	 *         ed., Cambridge University Press 1992
	 */
	   private static int toJulian(int year, int month, int date)
	   {  
	      int jy = year;
	      if (year < 0) jy++;
	      int jm = month;
	      if (month > 2) jm++;
	      else
	      {  
	         jy--;
	         jm += 13;
	      }
	      int jul = (int) (java.lang.Math.floor(365.25 * jy) 
	            + java.lang.Math.floor(30.6001 * jm) + date + 1720995.0);

	      int IGREG = 15 + 31 * (10 + 12 * 1582);
	         // Gregorian Calendar adopted Oct. 15, 1582

	      if (date + 31 * (month + 12 * year) >= IGREG)
	         // Change over to Gregorian calendar
	      {  
	         int ja = (int) (0.01 * jy);
	         jul += 2 - ja + (int) (0.25 * ja);
	      }
	      return jul;
	   }
	/**
	 * Converts a Julian day number to a calendar date.
	 * 
	 * This algorithm is from Press et al., Numerical Recipes in C, 2nd ed.,
	 * Cambridge University Press 1992
	 * 
	 * @param j
	 *            the Julian day number
	 * @return an array whose 0 entry is the year, 1 the month, and 2 the day of
	 *         the month.
	 */
	   private static int[] fromJulian(int j)
	   {  
	      int ja = j;
	   
	      int JGREG = 2299161;
	         // The Julian day number of the adoption of the Gregorian calendar    

	      if (j >= JGREG)
	         // Cross-over to Gregorian Calendar produces this correction
	      {  
	         int jalpha = (int) (((float) (j - 1867216) - 0.25) 
	             / 36524.25);
	         ja += 1 + jalpha - (int) (0.25 * jalpha);
	      }
	      int jb = ja + 1524;
	      int jc = (int) (6680.0 + ((float) (jb - 2439870) - 122.1)
	          / 365.25);
	      int jd = (int) (365 * jc + (0.25 * jc));
	      int je = (int) ((jb - jd) / 30.6001);
	      int date = jb - jd - (int) (30.6001 * je);
	      int month = je - 1;
	      if (month > 12) month -= 12;
	      int year = jc - 4715;
	      if (month > 2) --year;
	      if (year <= 0) --year;
	      return new int[] { year, month, date };
	   }

	// Private stuff
	private int hour;
	private int minute;
	private int year;
	private int month;
	private int day;
	private int julian;
}