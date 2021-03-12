package application;
/**
 * A class that simulates the functionality of a company's register of employees.
 * @author Abdullah Salem, Gent Blaku
 */
public class Company {
	private Employee[] employList;
	private int numEmployee;
	private static final int FOUR = 4;
	private static final int NOTFOUND = -1;
	
	/**
	 * A constructor that creates an object of the Company class.
	 * The company has a beginning capacity of four and has zero employees.
	 */
	public Company() {
		employList = new Employee[FOUR];
		numEmployee = 0;
	}
	
	/**
	 * A helper method that finds the first available slot in the array.
	 * @return the index of the slot if found, NOTFOUND if no spots are available
	 */
	private int firstOpenSlot() {
		for (int i = 0; i < employList.length; i++) {
			if (employList[i] == null) {
				return i;
			}
		}
		return NOTFOUND;
	}

	/**
	 * A helper method that closes any gaps formed when removing a employee from the array
	 * @param empytySlot is the slot that has to be closed
	 */
	private void shiftUp(int empytySlot) {
		for (int i = empytySlot; i < employList.length-1; i++) {
			employList[i] = employList[i+1];
			employList[i+1] = null;
		}
	}
	
	/**
	 * A method that searches through the employee list for a specific employee
	 * @param employee that is being searched for
	 * @return the index of the employee in the array if found, NOTFOUND if it is not found
	 */
	private int find(Employee employee) { 
		for (int i=0; i<numEmployee; i++) {
			if ( employList[i].equals(employee) ) {
				return i;
			}
		}
		return NOTFOUND;
	}
	
	
	/**
	 * A helper method that sorts the employees in the array by their Department.
	 */
	private void sortByDepartment() {
		int EmployeeIndex;
		for (int i = 0; i < numEmployee -1; i++){
			EmployeeIndex = i;
			for (int j = i+1; j < numEmployee; j++) {
				if (employList[j].getDepartment().compareTo(employList[EmployeeIndex].getDepartment()) < 0) {
					EmployeeIndex = j;
					}
				}
			Employee tempEmployeePointer = employList[i];
			employList[i] = employList[EmployeeIndex];
			employList[EmployeeIndex] = tempEmployeePointer;
		}
	}
	
	/**
	 * A helper method that sorts the employees in the array by their hire date(ascending).
	 */
	private void sortByDate() {
		int oldestEmployeeIndex;
		for (int i = 0; i < numEmployee -1; i++){
			oldestEmployeeIndex = i;
			for (int j = i+1; j < numEmployee; j++) {
				if (employList[j].getDate().compareTo(employList[oldestEmployeeIndex].getDate()) < 0) {
					oldestEmployeeIndex = j;
				}
				else if (employList[j].getDate().equals(employList[oldestEmployeeIndex].getDate())) {
					if (employList[j].getName().compareTo(employList[oldestEmployeeIndex].getName()) < 0) {
						oldestEmployeeIndex = j;
					}
				}
			}
			Employee tempEmployeePointer = employList[i];
			employList[i] = employList[oldestEmployeeIndex];
			employList[oldestEmployeeIndex] = tempEmployeePointer;
		}
	}
	
	/**
	 * A method that increases the capacity of the employee list by four after reaching it's limit
	 */
	private void grow() {
		Employee[] listClone;
		listClone = new Employee[employList.length];
		
		for (int i=0; i<employList.length; i++) {
			listClone[i] = employList[i];
		}
		
		employList = new Employee[employList.length+FOUR];
		
		for (int i=0; i<listClone.length; i++) {
			employList[i] = listClone[i];
		}
	}
	
	/**
	 * A method to tell if the company's array has no employees
	 * @return true if there are no employees, true if some exist
	 */
	public boolean isEmpty() {
		if (this.numEmployee < 1 )
			return true;
		else
			return false;
	}
	
	/**
	 * A method that adds an object of the employee class into the company's array
	 * @param employee is the object meant to be added
	 * @return true if the employee is added, false if the employee already exists
	 */
	public boolean add(Employee employee) {
		if (this.find(employee) == NOTFOUND) {
			int firstOpenSlot = this.firstOpenSlot();
			if (firstOpenSlot != NOTFOUND) {
				employList[firstOpenSlot] = employee;
			}
			else {
				this.grow();
				firstOpenSlot = this.firstOpenSlot();
				employList[firstOpenSlot] = employee;
			}
			numEmployee++;
			return true;
		}
		return false;
	} //check the profile before adding
	
	/**
	 * A method that removes an object of the employee class from the company's array.
	 * @param employee is the object meant to be removed
	 * @return true if successfully removed, false otherwise
	 */
	public boolean remove(Employee employee) {
		int employeeToBeRemoved = find(employee);
		if (employeeToBeRemoved == NOTFOUND) {
			return false;
		}
		employList[employeeToBeRemoved] = null;
		this.shiftUp(employeeToBeRemoved);
		numEmployee -= 1;
		return true;
	} //maintain the original sequence
	
	/**
	 * 
	 * @param employee is the part-time employee with their new hours
	 * @return true if hours are changed, false if not
	 */
	public boolean setHours(Employee employee) {
		int employeeToChangeHours = find(employee);
		if (employeeToChangeHours == NOTFOUND) {
			return false;
		}
		if (employee instanceof Parttime) {
			Parttime partTimer = (Parttime) employee;
			Parttime pointerToPartTimer = (Parttime)employList[employeeToChangeHours];
			pointerToPartTimer.setHours(partTimer.getHours());
			return true;
		}
		
		return false;
	} //set working hours for a part time
	
	/**
	 * A function that processes payments for all employees in the current Pay-Period.
	 */
	public void processPayments() { 
		for (int i=0; i<numEmployee; i++) {
			employList[i].calculatePayment();
		}
	} //process payments for all employees
	
	/**
	 * A method that prints the contents of the Company's array in a list
	 */
	public void print() {
		for (int i = 0; i < numEmployee; i++) {
			if (employList[i] != null) {
				System.out.println(employList[i].toString());
			}
		}
	}//print earning statements for all employees
	
	/**
	 * A method that prints the contents of the Company's array in a list ordered by Department
	 */
	public void printByDepartment() {
		this.sortByDepartment();
		this.print();
	} //print earning statements by department
	

	/**
	 * A method that prints the contents of the Company's array in a list ordered by date(ascending)
	 */
	public void printByDate() {
		this.sortByDate();
		this.print();
	} //print earning statements by date hired
}
