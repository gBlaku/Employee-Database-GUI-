package application;
/**
 * The Profile Class holds and manages information relevant to a person.
 * Specifically information that would be relevant to their company: Their name, their department, and the date they were hired.
 * @author Abdullah Salem, Gent Blaku
 */
public class Profile {
	private String name; //employee’s name in the form “lastname,firstname”
	private String department; //department code: CS, ECE, IT
	private Date dateHired;
	
	/**
	 * A constructor that creates a complete profile for a person.
	 * @param name is a String object representing a person's name.
	 * @param department is a String objecting representing which department of a company they were assigned.
	 * @param dateHired is an object of the Date class holding the date when they were hired.
	 */
	public Profile(String name, String department, Date dateHired) {
		this.name = name;
		this.department = department;
		this.dateHired = dateHired;
		
	}
	
	/**
	 * A method that fetches the date a person was hired.
	 * @return an object of the date class holding the date when they were hired.
	 */
	public Date getDate() {
		return this.dateHired;
	}
	
	/**
	 * A method that fetches a person's name.
	 * @return a String object representing a person's name.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * A method that fetches a person's department.
	 * @return a String object representing a person's department.
	 */
	public String getDepartment() {
		return this.department;
	}
	
	/**
	* Overriding the toString method of inherited from the object class.
	* @returns a string representing this object
	*/
	@Override
	public String toString() { 
		return this.name + "::" + this.department + "::" + this.dateHired;
		
	}
	
	/**
	 * Overriding the equals method inherited from the object class.
	 * Compares if this object is equal to another object based on certain criteria.
	 * @param obj is an object that is being compared to this object
	 * @return true if this object is equal to the other object, false if not equal to
	 */
	@Override
	public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			else if (obj instanceof Profile){
				Profile other = (Profile)obj;
				if (this.name.equals(other.name) && this.department.equals(other.department)&& this.dateHired.equals(other.dateHired))
					return true;
			}
			return false;
	} //compare name, department and dateHired
}
