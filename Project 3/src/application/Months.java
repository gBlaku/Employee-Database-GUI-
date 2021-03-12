package application;
/**
 * A class that holds relevant information regarding the months of the gregorian calender.
 * @author Abdullah Salem, Gent Blaku
 */
public class Months {

	public static final int JANUARY =1 ;
	public static final int FEBRUARY=2 ;
	public static final int MARCH= 3;
	public static final int APRIL= 4;
	public static final int MAY= 5;
	public static final int JUNE= 6;
	public static final int JULY= 7;
	public static final int AUGUST= 8;
	public static final int SEPTEMBER= 9;
	public static final int OCTOBER= 10;
	public static final int NOVEMBER= 11;
	public static final int DECEMBER= 12;
	public static final int NOTFOUND = -1;
	public static final int STANDARDLENGTHMONTH 	 = 30;
	public static final int EXTENDEDBYONEDAYMONTH    = 31;
	public static final int NOTLEAP = 28;
	public static final int LEAP    = 29;
	
	/**
	 * A function that fetches the maximum possible days of any given month.
	 * @param month is the number representing the order of the months.
	 * @param isLeapYear is a boolean to account for a leap year
	 * @return an integer holding the maximum possible days of the given month
	 */
	public static int MaxDaysPerMonth(int month, boolean isLeapYear) {
		switch(month) {
			case (JANUARY):
				return EXTENDEDBYONEDAYMONTH;
			case (FEBRUARY):
				if (isLeapYear) { 
					return LEAP;
				}
				else	
					return NOTLEAP;
			case (MARCH):
				return EXTENDEDBYONEDAYMONTH;
			case (APRIL):
				return STANDARDLENGTHMONTH;
			case (MAY):
				return EXTENDEDBYONEDAYMONTH;
			case (JUNE):
				return STANDARDLENGTHMONTH;
			case (JULY):
				return EXTENDEDBYONEDAYMONTH;
			case (AUGUST):
				return EXTENDEDBYONEDAYMONTH;
			case (SEPTEMBER):
				return STANDARDLENGTHMONTH;
			case (OCTOBER):
				return EXTENDEDBYONEDAYMONTH;
			case (NOVEMBER):
				return STANDARDLENGTHMONTH;
			case (DECEMBER):
				return EXTENDEDBYONEDAYMONTH;
		}
		return NOTFOUND;
	}
	
	/**
	 * A function that fetches the maximum possible days of any given month.
	 * @param month is the number representing the order of the months.
	 * @return an integer holding the maximum possible days of the given month
	 */
	public static int MaxDaysPerMonth(int month) {
		return MaxDaysPerMonth(month, false);
	}
	
	
}
