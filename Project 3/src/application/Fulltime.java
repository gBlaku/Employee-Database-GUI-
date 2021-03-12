package application;
/**
 * A Class that manages and stores the information of fulltime employees. 
 * This is a subclass of the Employee class.
 * @author Abdullah Salem, Gent Blaku
 */
public class Fulltime extends Employee {
	private double salary; //salary of the fulltime employee.
	private static final int PAYPERIODSPERYEAR = 26;
	
	/**
	 * Creates an object of the Fulltime Class
	 * @param newHire profile of an employee
	 * @param newSalary double representation of the employee's salary 
	 */
	public Fulltime(Profile newHire, double newSalary) {
		super(newHire);
		this.salary = newSalary;
	}

	/**
	 * A method that sets the salary for this employee.
	 * @param newSalary double representation of this employee's salary.
	 */
	public void setSalary(double newSalary) {
		this.salary = newSalary;
	}

	/**
	 *Overriding the calculatePayment method inherited from the Employee class.
	 * A method that calculates payments for employees in the current pay period.
	 */
	@Override
	public void calculatePayment() {
		double payment = salary / PAYPERIODSPERYEAR;
		super.setPayPerPeriod(payment);
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

		return super.toString() + "::FULL TIME::Annual Salary " + super.formatPayments(this.salary);
	}
	
	
	
	
	
	
	
}
