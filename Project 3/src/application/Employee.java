package application;
import java.text.DecimalFormat;

/**
 * A Class that manages and stores the information of an employee. 
 * This is the superclass for all types of employees at a company.
 * @author Abdullah Salem, Gent Blaku
 */
public class Employee {
	private Profile employeeProfile; //profile of each employee, using the profile class.
	private double payPerPeriod;     //payment earned by employees in the current pay period.
	
	
	/**
	 * Creates an object of the Employee Class
	 * @param newHire profile of an employee
	 */
	public Employee(Profile newHire) {
		this.employeeProfile = newHire;
		
	}
	
	
	/**
	 * A method that fetches the hire date for an employee.
	 * @return an object of the date class representing when this employee was hired.
	 */
	public Date getDate() {
		return this.employeeProfile.getDate();
	}
	/**
	 * A method that fetches the name of an employee.
	 * @return an object of the String class representing this employee's name.
	 */
	public String getName() {
		return this.employeeProfile.getName();
	}
	/**
	 * A method that fetches the department for an employee.
	 * @return an object of the String class representing this employee's department.
	 */
	public String getDepartment() {
		return this.employeeProfile.getDepartment();
	}
	
	
	
	/**
	 * A method that sets the payment amount for an employee in the current pay period.
	 * @param payment the payment for this pay period.
	 */
	public void setPayPerPeriod(double payment ) {
		this.payPerPeriod = payment;
	}
	
	/**
	 * A method that fetches the payment for an employee, in the current pay period.
	 * @return a double representing the employee's pay for this pay period.
	 */
	public double getPayPerPeriod() {
		return this.payPerPeriod;
	}
	
	/**
	 * A method that calculates payments for employees in the current pay period.
	 */
	public void calculatePayment() {
		
	}
	/**
	 * A method that formats payments into correct currency format, using the DecimalFormat class.
	 * @param payment double representation of payment for this pay period.
	 * @return formatted string version of the payment.
	 */
	public String formatPayments(double payment) {
		DecimalFormat df = new DecimalFormat("$#,##0.00");
		String formattedPay = df.format(payment);
		return formattedPay;
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
		else if (obj instanceof Employee){
			Employee other = (Employee)obj;
			if (this.employeeProfile.equals(other.employeeProfile)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Overriding the toString method of inherited from the object class.
	 * @return a string representing this object
	 */
	@Override
	public String toString() {
		
		return this.employeeProfile.toString()+ "::Payment " + formatPayments(this.payPerPeriod);  
		
	}
}
