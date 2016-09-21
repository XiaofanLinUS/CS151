/**
 * Some codes came from the book written by Professor Horstmann 
 * source url: https://bitbucket.org/cayhorstmann/oodp3code/src/0e2bd7f0bd4ed0ec83f7265817bc49e93e33f586/ch03/day1/?at=master
 * @author linxiaofan
 *
 */
public class MutableDay {
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
	 */
	public MutableDay(int aYear, int aMonth, int aDay) {
		year = aYear;
		month = aMonth;
		day = aDay;
	}

	/**
	 * Returns the year of this day
	 * 
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Returns the month of this day
	 * 
	 * @return the month
	 */
	public int getMonthValue() throws IllegalStateException {
		if (day <= 0) {
			throw new IllegalStateException("No such day");
		} else if (day > daysPerMonth(year, month)) {
			throw new IllegalStateException("No such day");
		}
		return month;
	}

	/**
	 * Returns the day of the month of this day
	 * 
	 * @return the day of the month
	 */
	public int getDayOfMonth() throws IllegalStateException {
		if (day <= 0) {
			throw new IllegalStateException("No such day");
		} else if (day > daysPerMonth(year, month)) {
			throw new IllegalStateException("No such day");
		}
		return day;
	}

	/**
	 * Set the year with a given year
	 * @param year the given year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Set the month with a given month
	 * @param month the given month
	 */
	public void setMonthValue(int month) {
		this.month = month;
	}

	/**
	 * Set the day with a given day
	 * @param day the given day
	 */
	public void setDayOfMonth(int day) {
		this.day = day;
	}

	/**
	 * Returns a day that is a certain number of days away from this day
	 * 
	 * @param n the number of days, can be negative
	 */
	public void plusDays(int n) {
		MutableDay result = this;
		while (n > 0) {
			result = result.nextDay();
			n--;
		}
		while (n < 0) {
			result = result.previousDay();
			n++;
		}

		this.year = result.getYear();
		this.month = result.getMonthValue();
		this.day = result.getDayOfMonth();
	}

	/**
	 * Returns the number of days between this day and another day
	 * 
	 * @param other  the other day
	 * @return the number of days that this day is away from the other (>0 if
	 *         this day comes later)
	 */
	public int daysFrom(MutableDay other) {
		int n = 0;
		MutableDay d = this;
		while (d.compareTo(other) > 0) {
			d = d.previousDay();
			n++;
		}
		while (d.compareTo(other) < 0) {
			d = d.nextDay();
			n--;
		}
		return n;
	}

	/**
	 * Compares this day with another day.
	 * 
	 * @param other the other day
	 * @return a positive number if this day comes after the other day, a
	 *         negative number if this day comes before the other day, and zero
	 *         if the days are the same
	 */
	private int compareTo(MutableDay other) {
		if (year > other.year)
			return 1;
		if (year < other.year)
			return -1;
		if (month > other.month)
			return 1;
		if (month < other.month)
			return -1;
		return day - other.day;
	}

	/**
	 * Computes the next day.
	 * 
	 * @return the day following this day
	 */
	private MutableDay nextDay() {
		int y = year;
		int m = month;
		int d = day;

		if (y == GREGORIAN_START_YEAR && m == GREGORIAN_START_MONTH && d == JULIAN_END_DAY)
			d = GREGORIAN_START_DAY;
		else if (d < daysPerMonth(y, m))
			d++;
		else {
			d = 1;
			m++;
			if (m > DECEMBER) {
				m = JANUARY;
				y++;
				if (y == 0)
					y++;
			}
		}
		return new MutableDay(y, m, d);
	}

	/**
	 * Computes the previous day.
	 * 
	 * @return the day preceding this day
	 */
	private MutableDay previousDay() {
		int y = year;
		int m = month;
		int d = day;

		if (y == GREGORIAN_START_YEAR && m == GREGORIAN_START_MONTH && d == GREGORIAN_START_DAY)
			d = JULIAN_END_DAY;
		else if (d > 1)
			d--;
		else {
			m--;
			if (m < JANUARY) {
				m = DECEMBER;
				y--;
				if (y == 0)
					y--;
			}
			d = daysPerMonth(y, m);
		}
		return new MutableDay(y, m, d);
	}

	/**
	 * Gets the days in a given month
	 * 
	 * @param y the year
	 * @param m the month
	 * @return the last day in the given month
	 */
	private static int daysPerMonth(int y, int m) {
		int days = DAYS_PER_MONTH[m - 1];
		if (m == FEBRUARY && isLeapYear(y))
			days++;
		return days;
	}

	/**
	 * Tests if a year is a leap year
	 * 
	 * @param y the year
	 * @return true if y is a leap year
	 */
	private static boolean isLeapYear(int y) {
		if (y % 4 != 0)
			return false;
		if (y < GREGORIAN_START_YEAR)
			return true;
		return (y % 100 != 0) || (y % 400 == 0);
	}

	public String toString() throws IllegalStateException {
		if (day <= 0) {
			throw new IllegalStateException("No such day");
		} else if (day > daysPerMonth(year, month)) {
			throw new IllegalStateException("No such day");
		}
		return String.format("%04d-%02d-%02d", year, month, day);
	}

	private int year;
	private int month;
	private int day;

	private static final int[] DAYS_PER_MONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	private static final int GREGORIAN_START_YEAR = 1582;
	private static final int GREGORIAN_START_MONTH = 10;
	private static final int GREGORIAN_START_DAY = 15;
	private static final int JULIAN_END_DAY = 4;

	private static final int JANUARY = 1;
	private static final int FEBRUARY = 2;
	private static final int DECEMBER = 12;
}
