package application;
import java.util.StringTokenizer;
import java.util.Calendar;

/**
 *A class that hold data for a date with methods to gain more information.
 *Also provides utility to check for valid dates(accounting for leap years and other parameters)
 *@author Abdullah Salem, Gent Blaku
 */
public class Date implements Comparable<Date> {
	private int year;
	private int month;
	private int day;
	private static final int MINYEAR = 1900;
	private static final int QUARTER  = 4, CENTURY = 100, QUADRICENTENNIAL = 400;
	private static final int MAXMONTH = 12, MINMONTH = 1;
	
	/**
	 * Creates a object of the Date Class
	 * @param date in a "mm/dd/yyyy" string format
	 */
	public Date(String date) {	//taking mm/dd/yyyy and create a Date object
		StringTokenizer tokenizer = new StringTokenizer(date, "-");
		this.year  = Integer.parseInt(tokenizer.nextToken());
		this.month = Integer.parseInt(tokenizer.nextToken());
		this.day   = Integer.parseInt(tokenizer.nextToken());
			
		
	} 
	
	/**
	 * Creates a object of the Date Class that corresponds to today's date
	 */
	public Date() { 
		Calendar today = Calendar.getInstance();
		this.year  = today.get(Calendar.YEAR);
		this.month = today.get(Calendar.MONTH)+1;
		this.day   = today.get(Calendar.DATE);
		
		
	} //create an object with today’s date (see Calendar class)
	
	
	
	/**
	 * A helper method to verify if a year was a leap year
	 * @param bookYear to be checked
	 * @return true if it is a leapyear, false if not
	 */
	private boolean checkForLeapYear(int hireYear) {
		if (hireYear % QUARTER == 0 ) {
			if(hireYear % CENTURY == 0) {
				if (hireYear % QUADRICENTENNIAL == 0) {
					return true;
				}
				else {
					return false;
				}
			}
			else { 
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * A method that checks if the date object has a valid date/
	 * A valid date is one that is both possible, not in the future, and not in the distant past.
	 * @return true if the date is valid, false if it is not
	 */
	public boolean isValid() { 
		//System.out.println(this.toString());
		if (this.day < 1) {
			return false;
		}
		
		
		if (this.year < MINYEAR) {
			
			return false;		
		}
		
		if (this.month > MAXMONTH) {
			
			return false;
		}
		else if (this.month<MINMONTH) {
			
			return false;
		}
		
		Date today = new Date();
		if (this.year > today.year) {
			
			return false;
		}
		else if (this.year == today.year) {
			
			if (this.month > today.month ) {
				//System.out.println(this.month);
				//System.out.println(today.month);
				//System.out.println(today.toString());
				return false;
			}
			else if (this.month == today.month) {
				
				if (this.day > today.day) {
					return false;
				}
			}
		}
		
		
		if (this.month == Months.JANUARY) {
			if (this.day > Months.MaxDaysPerMonth(Months.JANUARY)) {
				return false;
			}
		}
		
		else if (this.month == Months.FEBRUARY) {
			
			boolean leapYearCheck = checkForLeapYear(this.year);
			if (this.day > Months.MaxDaysPerMonth(Months.FEBRUARY, leapYearCheck)) {
				return false;
			}
		}
		else if (this.month == Months.MARCH) {
			if (this.day > Months.MaxDaysPerMonth(Months.MARCH)) {
				return false;
			}
		}
		else if (this.month == Months.APRIL) {
			if (this.day > Months.MaxDaysPerMonth(Months.APRIL)) {
				return false;
			}
		}
		else if (this.month == Months.MAY) {
			if (this.day > Months.MaxDaysPerMonth(Months.MAY)) {
				return false;
			}
		}
		else if (this.month == Months.JUNE) {
			if (this.day > Months.MaxDaysPerMonth(Months.JUNE)) {
				return false;
			}
		}
		else if (this.month == Months.JULY) {
			if (this.day > Months.MaxDaysPerMonth(Months.JULY)) {
				return false;
			}
		}
		else if (this.month == Months.AUGUST) {
			if (this.day > Months.MaxDaysPerMonth(Months.AUGUST)) {
				return false;
			}
		}
		else if (this.month == Months.SEPTEMBER) {
			if (this.day > Months.MaxDaysPerMonth(Months.SEPTEMBER)) {
				return false;
			}
			
		}
		else if (this.month == Months.OCTOBER) {
			if (this.day > Months.MaxDaysPerMonth(Months.OCTOBER)) {
				return false;
			}
		}
		else if (this.month == Months.NOVEMBER) {
			if (this.day > Months.MaxDaysPerMonth(Months.NOVEMBER)) {
				return false;
			}
		}
		else if (this.month == Months.DECEMBER) {
			if (this.day > Months.MaxDaysPerMonth(Months.DECEMBER)) {
				return false;
			}
		}
		
		return true;
	}
	
	
	
	/**
	 * A method that checks if the date of the object is chronologically before the date of the other object.
	 * @param other is the object of the date class to be measure against
	 * @return true if this object is older, false if it is younger
	 */
	public boolean isOlderThan(Date other) {
		if (this.year > other.year) {
			return false;
		}
		else if (this.year == other.year) {
			if (this.month > other.month ) {
				return false;
			}
			else if (this.month == other.month) {
				if (this.day > other.day) {
					return false;
				}
			}
		}
		if (this.equals(other)) {
			return false;
		}
		return true;
	}

	/**
	 * A method that checks if the date of the object is chronologically after the date of the other object.
	 * @param other is the object of the date class to be measure against
	 * @return true if this object is younger, false if it is older
	 */
	public boolean isYoungerThan(Date other) {
		if (this.year > other.year) {
			return true;
		}
		else if (this.year == other.year) {
			if (this.month > other.month ) {
				return true;
			}
			else if (this.month == other.month) {
				if (this.day > other.day) {
					return true;
				}
			}
		}
		if (this.equals(other)) {
			return false;
		}
		return false;
	}
	
	
	
	
	/**
	 * Overriding the equals method inherited from the object class.
	 * Compares if this object is equal to another object based on certain criteria.
	 * @param obj is an object that is being compared to this object
	 * @return true if this object is equal to the other object, false if not equal to
	 */
	@Override
	public boolean equals(Object obj){
		if (obj == null) {
			return false;
		}
		else if (obj instanceof Date){
			Date other = (Date)obj;
			if (this.day == other.day && this.month == other.month && this.year == other.year)
				return true;
		}
		return false;
	}
	
	/**
	 * Overriding the toString method of inherited from the object class.
	 * @returns a string representing this object
	 */
	@Override
	public String toString() {
		return String.valueOf(this.month) + "/" + String.valueOf(this.day) + "/" + String.valueOf(this.year);
	}
	
	/**
	 * Overidding the compareTo method from the comparable interface
	 * @return 0 if this date is equal to the other date, -1 if this date is before the other date, 1 if this date is after the other date
	 */
	@Override
	public int compareTo(Date date) {
		if (this.isOlderThan(date)) {
			return -1;
		}
		if (this.isYoungerThan(date)) {
			return 1;
		}
		return 0;
	} //return 1, 0, or -1
	
}
