package application;
/**
 * A Class that manages and stores the information of parttime employees. 
 * This is a subclass of the Employee class.
 * @author Abdullah Salem, Gent Blaku
 */
public class Parttime extends Employee {
	private double hourlyRate; //hourly pay of the parttime employee.
	private int hoursWorked;   //hours worked this pay period by the parttime employee.
	private static final int STANDARDHOURSPERPERIOD = 80;
	
	private static final double OVERTIMERATE = 1.5;
	private static final int NOHOURSWORKED = 0;
	
	/**
	 * Creates an object of the Parttime Class
	 * @param profile profile of the parttime employee
	 * @param rate hourly pay of the parttime employee
	 */
	public Parttime(Profile profile,double rate) {
		super(profile);
		this.hourlyRate = rate;
		this.hoursWorked = NOHOURSWORKED;
	}
	/**
	 * A method that sets the hours worked by this employee in the current pay period.
	 * @param newHours integer representation of the hours worked.
	 */
	public void setHours(int newHours) {
		this.hoursWorked = newHours;
	}
	/**
	 * A method that fetches the hours worked by an employee, in the current pay period.
	 * @return an integer representing the employee's hours worked for this pay period.
	 */
	public int getHours() {
		return this.hoursWorked;
	}
	
	/**
	 *Overriding the calculatePayment method inherited from the Employee class.
	 * A method that calculates payments for employees in the current pay period.
	 */
	@Override
	public void calculatePayment() {
		if (hoursWorked < STANDARDHOURSPERPERIOD) {
			super.setPayPerPeriod(hourlyRate*hoursWorked);
		}
		else {
			double payment = (hourlyRate*STANDARDHOURSPERPERIOD);
			int overtimeHours = hoursWorked - STANDARDHOURSPERPERIOD;
			payment += overtimeHours*hourlyRate*OVERTIMERATE;
			super.setPayPerPeriod(payment);
		}
		
	}
	
	
	

	/**
	 * Overriding the equals method inherited from the object class.
	 * Compares if this object is equal to another object based on certain criteria.
	 * @param obj is an object that is being compared to this object
	 * @return true if this object is equal to the other object, false if not equal to
	 */
	@Override
	public boolean equals(Object obj){
		return	super.equals(obj);
	}
	
	/**
	 * Overriding the toString method of inherited from the object class.
	 * @return a string representing this object
	 */
	@Override
	public String toString() {
		
		return super.toString() + "::PART TIME::Hourly Rate " + super.formatPayments(this.hourlyRate) + "::Hours worked this period: " + this.hoursWorked;
	}
	
	
	
}
