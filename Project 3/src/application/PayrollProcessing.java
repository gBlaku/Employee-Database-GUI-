package application;
import java.util.Scanner;

import java.util.StringTokenizer;

/**
 * A class that facilitates the input and output of the program -- The User Interface.
 * This simulates a company's payroll.
 * @author Abdullah Salem, Gent Blaku
 */
public class PayrollProcessing {
	private static final int FIRSTINDEX = 0;
	private static final int SECONDINDEX = 1;
	private static final int THIRDINDEX = 2;
	private static final int FOURTHINDEX = 3;
	private static final int FIFTHINDEX = 4;
	private static final int SIXTHINDEX = 5;
	private static final int MAXINPUTS = 6;
	private static final int MANAGER = 1;
	private static final int DEPARTMENT_HEAD = 2;
	private static final int DIRECTOR = 3;
	private static final int MAX_PARTTIME_HOURS = 100;
	private static final String DEPARTMENT_CS = "CS";
	private static final String DEPARTMENT_ECE = "ECE";
	private static final String DEPARTMENT_IT = "IT";
	
	
	/**
	 * A method that handles the user input and gives appropriate output.
	 * It creates objects of the other classes and calls their public methods to handle data.
	 */
	public void run() {
		String line;
		boolean Quit = false;
		
		
		
		
		
		
		System.out.println("Payroll Processing starts.");
		Company company = new Company();
		Scanner input = new Scanner(System.in);
		StringTokenizer tokens;
		String tokensArray[];
		
		
		while(!Quit){ //Running until the user quits
			line = input.nextLine();
			tokens = new StringTokenizer(line, " ");
			tokensArray = this.tokenizedInput(tokens);
			
			
			try {
			
			switch(tokensArray[FIRSTINDEX]) 
			{
				case "AP": //Adding part-time employee
					AP(tokensArray, company);
					break;
				case "AF": //Add full time employee
					AF(tokensArray, company);
					break;
				case "AM": //Add management employee
					AM(tokensArray, company);
					break;
				case "R"://Remove employee from company
					this.R(tokensArray, company);
					break;
				case "C"://Calculate payments for every employee
					this.C(tokensArray, company);
					break;
				case "S"://Set the working hours for a part-time employee
					this.S(tokensArray, company);
					break;
				case "PA"://Print the earnings statements for each employee
					this.PA(tokensArray, company);
					break;
				case "PH"://Print the earnings statements for each employee, in order of date hired
					this.PH(tokensArray, company);
					break;
				case "PD"://Print the earnings statements for each employee, grouped by department
					this.PD(tokensArray, company);
					break;
					
				case "Q"://Quits the program
					Quit = true;
					break;
				default: //Any other user input
					System.out.println("Command '" + tokensArray[0] + "' not supported!");
					break;
			}
		}
			catch (Exception e) {
				//System.out.println("MismatchException");
			}
		}
		
		input.close();
		System.out.println("Payroll Processing completed.");
		return;
	}
	
	/**
	 * This method completes the roll of the AP command: Adding part-time employee.
	 * @param tokensArray contains all the user input.
	 * @param company is an Object of the Company class that is being added to.
	 */
	private void AP(String tokensArray[], Company company) {
		if (Double.parseDouble(tokensArray[FIFTHINDEX]) < 0) {
			System.out.println("Pay rate cannot be negative.");
			return;
		}
		Parttime newEmployee = new Parttime(new Profile(tokensArray[SECONDINDEX], tokensArray[THIRDINDEX], new Date(tokensArray[FOURTHINDEX])), Double.parseDouble(tokensArray[FIFTHINDEX]));
		addCheck(newEmployee, company, tokensArray);
	}
	
	/**
	 * This method completes the roll of the AM command: Add Management employee.
	 * @param tokensArray contains all the user input.
	 * @param company is an Object of the Company class that is being added to.
	 */
	private void AM(String tokensArray[], Company company) {
		if (Integer.parseInt(tokensArray[SIXTHINDEX]) != MANAGER && Integer.parseInt(tokensArray[SIXTHINDEX]) != DEPARTMENT_HEAD && Integer.parseInt(tokensArray[SIXTHINDEX]) != DIRECTOR) {
			System.out.println("Invalid management code.");
			return;
		}
		
		if (Double.parseDouble(tokensArray[FIFTHINDEX]) < 0) {
			System.out.println("Salary cannot be negative.");
			return;
		}
		
		Management newEmployee = new Management(new Profile(tokensArray[SECONDINDEX], tokensArray[THIRDINDEX], new Date(tokensArray[FOURTHINDEX])), Double.parseDouble(tokensArray[FIFTHINDEX]), Integer.parseInt(tokensArray[SIXTHINDEX]));
		addCheck(newEmployee, company, tokensArray);
	}
	
	/**
	 * This method completes the roll of the AF command: Add full time employee.
	 * @param tokensArray contains all the user input.
	 * @param company is an Object of the Company class that is being added to.
	 */
	private void AF(String tokensArray[], Company company) {
		if (Double.parseDouble(tokensArray[FIFTHINDEX]) < 0) {
			System.out.println("Salary cannot be negative.");
			return;
		}
		Fulltime newEmployee = new Fulltime(new Profile(tokensArray[SECONDINDEX], tokensArray[THIRDINDEX], new Date(tokensArray[FOURTHINDEX])), Double.parseDouble(tokensArray[FIFTHINDEX]));
		addCheck(newEmployee, company, tokensArray);
	}
	
	/**
	 * This method completes the roll of the R command: remove employee.
	 * @param tokensArray contains all the user input.
	 * @param company is an Object of the Company class that is being subtracted from.
	 */
	private void R(String tokensArray[], Company company) {
		if (listCheck(company) == true) {
			return;
		}
		
		if (company.remove(new Employee(new Profile(tokensArray[SECONDINDEX], tokensArray[THIRDINDEX], new Date(tokensArray[FOURTHINDEX]))))) {
			System.out.println("Employee removed.");
		}
	
		else {
			System.out.println("Employee does not exist.");
			return;
		}
	}
	
	/**
	 * This method completes the roll of the C command: calculating the payroll.
	 * @param tokensArray contains all the user input.
	 * @param company is an Object of the Company class whose payroll is being calculated.
	 */
	private void C(String tokensArray[], Company company) {
		if (listCheck(company) == true) {
			return;
		}
		company.processPayments();
		System.out.println("Calculation of employee payments is done.");
	}
	
	/**
	 * This method completes the roll of the R command: setting employee hours.
	 * @param tokensArray contains all the user input.
	 * @param company is an Object of the Company class that is being updated.
	 */
	private void S(String tokensArray[], Company company) {
		if (listCheck(company) == true) {
			return;
		}
		
		if (Integer.parseInt(tokensArray[FIFTHINDEX]) > MAX_PARTTIME_HOURS) {
			System.out.println("Invalid Hours: over 100.");
			return;
		}
		
		if (Integer.parseInt(tokensArray[FIFTHINDEX]) < 0) {
			System.out.println("Working hours cannot be negative.");
			return;
		}
		Parttime temp = new Parttime(new Profile(tokensArray[SECONDINDEX], tokensArray[THIRDINDEX], new Date(tokensArray[FOURTHINDEX])), 0);
		temp.setHours(Integer.parseInt(tokensArray[FIFTHINDEX]));
		if (!company.setHours(temp)) {
			System.out.println("Employee does not exist.");
			return;
		}
		System.out.println("Working hours set.");
	}
	
	/**
	 * This method completes the roll of the PA command: Print All Employee Earnings.
	 * @param tokensArray contains all the user input.
	 * @param company is an Object of the Company class.
	 */
	private void PA(String tokensArray[], Company company) {
		if (this.listCheck(company)) {
			return;
		}
		System.out.println("--Printing earning statements for all employees--");
		company.print();
	}
	
	/**
	 * This method completes the roll of the PA command: Print All Employee Earnings by Date Hired.
	 * @param tokensArray contains all the user input.
	 * @param company is an Object of the Company class.
	 */
	private void PH(String tokensArray[], Company company) {
		if (this.listCheck(company)) {
			return;
		}
		System.out.println("--Printing earning statements by date hired--");
		company.printByDate();
	}
	
	/**
	 * This method completes the roll of the PA command: Print All Employee Earnings by Date Hired.
	 * @param tokensArray contains all the user input.
	 * @param company is an Object of the Company class.
	 */
	private void PD(String tokensArray[], Company company) {
		if (this.listCheck(company)) {
			return;
		}
		System.out.println("--Printing earning statements by department--");
		company.printByDepartment();
	}
	
	/**
	 * A helper method that checks the company's array contains employees.
	 * @param company that is being checked.
	 * @return true if the company is empty, false otherwise.
	 */
	private boolean listCheck(Company company) {
		if (company.isEmpty()) {
			System.out.println("Employee database is empty.");
			return true;
		}
		return false;
	}

	/**
	 * A helper method that checks the user input when adding a new employee to the company.
	 * @param newEmployee is an object of the employee class that is being added.
	 * @param company is an object of the company class that an employee is joining.
	 * @param tokensArray is an array of the String object containing all the user inputs.
	 */
	private void addCheck(Employee newEmployee, Company company, String tokensArray[]) {
		if (!tokensArray[THIRDINDEX].equals(DEPARTMENT_ECE) && !tokensArray[THIRDINDEX].equals(DEPARTMENT_CS) && !tokensArray[THIRDINDEX].equals(DEPARTMENT_IT)) {
			System.out.println("'" + tokensArray[THIRDINDEX] + "' is not a valid department code.");
			return;
		}
		
		Date newDate = new Date(tokensArray[FOURTHINDEX]);
		if (!newDate.isValid()) {
			System.out.println(newDate.toString() + " is not a valid date!");
			return;
		}
		
		
		
		if(company.add(newEmployee) != true) {
			System.out.println("Employee is already in the list.");
		}
		else {
			System.out.println("Employee added.");
		}
	}
	
	
	
	
	/**
	 * A helper method that converts proper user input into a more usable form
	 * @param token is a stringTokenizer object containing the segmented user input
	 * @return an array that contains the user input separated appropriately 
	 */
	private String[] tokenizedInput(StringTokenizer token) {
		String tokens[] = new String[MAXINPUTS];
		for (int i=0; i<MAXINPUTS; i++) {
			if (token.hasMoreTokens()) {
				tokens[i] = token.nextToken();
			}
		}
		return tokens;
	}
	
}
