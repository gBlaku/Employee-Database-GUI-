package application;

import javafx.fxml.FXML; 
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
//import application.Main;
//import view.Alert;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;
import javafx.scene.control.DatePicker;


/**
 * Controller class for the company registry GUI.
 * @author Abdullah Salem, Gent Blaku
 */
public class Controller {
	private static final int MAX_PARTTIME_HOURS = 100;
	private static final int MAXINPUTS = 6;
	private static final int FIRSTINDEX = 0;
	private static final int SECONDINDEX = 1;
	private static final int THIRDINDEX = 2;
	private static final int FOURTHINDEX = 3;
	private static final int FIFTHINDEX = 4;
	private static final int SIXTHINDEX = 5;
	
	Company company;
	/**
	 * Creates an object of the company class.
	 */
	public Controller() {
		company = new Company();
	}
	
	/**
	 * Clicking the Import button will trigger this method. This method imports an employee database from a text file.
	 */
	@FXML
	private void importDatabase() {
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);
		
		String line;
		Scanner input;
		try {
			input = new Scanner(selectedFile);
		}
		catch(FileNotFoundException e) {
			outputArea.appendText("File not found.\n");
			return;
		}
		catch(Exception e) {
			return;
		}
		
		StringTokenizer tokens;
		String tokensArray[];
		
		
		while(input.hasNextLine()){ //Running until the user quits
			line = input.nextLine();
			tokens = new StringTokenizer(line, ",");
			tokensArray = tokenizedInput(tokens);
			
			String date = tokensArray[FOURTHINDEX];
			StringTokenizer tokenizeDate  = new StringTokenizer(date,"/");
			String month = tokenizeDate.nextToken();
			String day = tokenizeDate.nextToken();
			String year= tokenizeDate.nextToken();
			tokensArray[FOURTHINDEX] = year+"-"+month+"-"+day;
			
			
			try {
				//System.out.println("Made it into try statement");
			
			switch(tokensArray[FIRSTINDEX]) 
			{
				case "P": //Adding part-time employee
					addParttime(tokensArray);
					break;
				case "F": //Add full time employee
					addFulltime(tokensArray);
					break;
				case "M": //Add management employee
					addManagement(tokensArray);
					break;
			}
		}
			catch (Exception e) {
				//System.out.println(e);
			}
		}
		
		input.close();
		
		outputArea.appendText("Employee database imported.\n");
		return;
		
	}
	
	
	/**
	 * Clicking the Export button will trigger this method. This method exports an employee database to a text file using the Company class exportDatabase function.
	 */
	@FXML
	private void exportDatabase() {
		if (listCheck() == true) {
			return;
		}
			
		
		company.exportDatabase();
		outputArea.appendText("Employee Database exported.\n");
	}
	
	
	/**
	 * A helper method that converts proper user input into a more usable form
	 * @param token is a stringTokenizer object containing the segmented user input
	 * @return an array that contains the user input separated appropriately 
	 */
	@FXML
	private String[] tokenizedInput(StringTokenizer token) {
		String tokens[] = new String[MAXINPUTS];
		for (int i=0; i<MAXINPUTS; i++) {
			if (token.hasMoreTokens()) {
				tokens[i] = token.nextToken();
			}
		}
		return tokens;
	}
	
	/**
	 * This method adds a Management employee to the database, from an imported text file.
	 * @param tokens Array containing employee information.
	 */
	@FXML
	private void addManagement(String[] tokens) {
		Management newEmployee = new Management(new Profile(tokens[SECONDINDEX], tokens[THIRDINDEX], new Date(tokens[FOURTHINDEX])), 
																	Double.parseDouble(tokens[FIFTHINDEX] ), Integer.parseInt(tokens[SIXTHINDEX]));
	checkEmployee(newEmployee, tokens);
	}
	
	/**
	 * This method adds a Parttime employee to the database, from an imported text file.
	 * @param tokens Array containing employee information.
	 */
	@FXML
	private void addParttime(String[] tokens) {
		Parttime newEmployee = new Parttime(new Profile(tokens[SECONDINDEX], tokens[THIRDINDEX], new Date(tokens[FOURTHINDEX])), 
																	Double.parseDouble(tokens[FIFTHINDEX] ));
	checkEmployee(newEmployee, tokens);
	}
	/**
	 * This method adds a Fulltime employee to the database, from an imported text file.
	 * @param tokens Array containing employee information.
	 */
	@FXML
	private void addFulltime(String[] tokens) {
		Fulltime newEmployee = new Fulltime(new Profile(tokens[SECONDINDEX], tokens[THIRDINDEX], new Date(tokens[FOURTHINDEX])), 
																	Double.parseDouble(tokens[FIFTHINDEX] ));
	checkEmployee(newEmployee, tokens);
	}
	
	
	
	
	
	@FXML
	private Label nameLabel;
	@FXML
	private HBox roleBox;
	@FXML
	private RadioButton partTimeButton;
	@FXML
	private RadioButton fullTimeButton;
	@FXML
	private RadioButton managementButton;
	@FXML
	private HBox departmentBox;
	@FXML
	private RadioButton itButton;
	@FXML
	private RadioButton csButton;
	@FXML
	private RadioButton eceButton;
	@FXML
	private RadioButton directorButton;
	@FXML
	private RadioButton depHeadButton;
	@FXML
	private RadioButton managerButton;
	@FXML
	private DatePicker dateHired;
	@FXML
	private TextField employeeSalary;
	@FXML
	private TextField employeeName;
	@FXML
	private TextField hoursWorked;
	@FXML
	private TextField hourlyRate;
	@FXML
	private Button addEmployeeButton;
	@FXML
	private Button removeEmployeeButton;
	@FXML
	private Button setHoursButton;
	@FXML
	private TextArea outputArea;
	@FXML
	private Button clearButton;

	// Event Listener on Button[#setHoursButton].onAction

	
	/**
	 * This function is triggered when clicking the Add Employee button. This will check which type of employee is to be added (according to role), and then attempt to add them to the database.
	 * @param event Clicking the Add Employee button.
	 */
	@FXML
	private void onMouseClick(ActionEvent event)  {
		// TODO Autogenerated
		if (partTimeButton.isSelected()) {
			addParttime();
		}
		else if (fullTimeButton.isSelected()) {
			addFulltime();
		}
		
		else if (managementButton.isSelected()) {
			addManagement();
		}
		
	}
	
	
	/**
	 * This function is triggered when picking Parttime radio button. Disables irrelevant input options that are impossible for a parttime employee to possess (e.g annual salary)
	 * @param event Clicking the Parttime radio button.
	 */
	@FXML
	private void pickedParttime() {
		if (partTimeButton.isSelected()) {
			employeeSalary.setDisable(true);
			directorButton.setDisable(true);
			depHeadButton.setDisable(true);
			managerButton.setDisable(true);
			
			addEmployeeButton.setDisable(false);
			removeEmployeeButton.setDisable(false);
			hoursWorked.setDisable(false);
			hourlyRate.setDisable(false);
			setHoursButton.setDisable(false);
		}
	}
	/**
	 * This function is triggered when picking Fulltime radio button. Disables irrelevant input options that are impossible for a Fulltime employee to possess (e.g hourly rate, hours worked)
	 * @param event Clicking the Fulltime radio button.
	 */
	@FXML
	private void pickedFulltime() {
		if (fullTimeButton.isSelected()) {
			employeeSalary.setDisable(false);
			addEmployeeButton.setDisable(false);
			removeEmployeeButton.setDisable(false);
			
			directorButton.setDisable(true);
			depHeadButton.setDisable(true);
			managerButton.setDisable(true);
			hoursWorked.setDisable(true);
			hourlyRate.setDisable(true);
			setHoursButton.setDisable(true);
		}
	}
	
	/**
	 * This function is triggered when picking Management radio button. Disables irrelevant input options that are impossible for a Management employee to possess (e.g hourly rate, hours worked)
	 * @param event Clicking the Management radio button.
	 */
	@FXML
	private void pickedManagement() {
		if (managementButton.isSelected()) {
			employeeSalary.setDisable(false);
			directorButton.setDisable(false);
			depHeadButton.setDisable(false);
			managerButton.setDisable(false);
			addEmployeeButton.setDisable(false);
			removeEmployeeButton.setDisable(false);
			
			
			hoursWorked.setDisable(true);
			hourlyRate.setDisable(true);
			setHoursButton.setDisable(true);
		}
	}
	
	
	/**
	 * This function is triggered when clicking the Set Hours button. It sets the hours worked for a parttime employee.
	 * @param event Clicking the Set Hours button.
	 */
	@FXML
	private void setHours(ActionEvent event)  {
		// TODO Autogenerated
		if (!partTimeButton.isSelected()) {
			outputArea.appendText("You can only set hours for a part-time employee.\n");
			return;
		}
		
		
		String department ="";
		if (itButton.isSelected()) {
			department = "IT";
		}
		else if (csButton.isSelected()) {
			department = "CS";
		}
		else if (eceButton.isSelected()) {
			department = "ECE";
		}
		else {
			outputArea.appendText("Please pick a department.\n");
		}
		
	
		
		if (listCheck() == true) {
			return;
		}
		
		
		if (hoursWorked.getText().isEmpty() ) {
			outputArea.appendText("Please enter hours worked.\n");
			return;
		}
		
		try {
			if (Integer.parseInt(hoursWorked.getText()) > MAX_PARTTIME_HOURS) {
				outputArea.appendText("Invalid Hours: over 100.\n");
				return;
			}
		
			if (Integer.parseInt(hoursWorked.getText()) < 0) {
				outputArea.appendText("Working hours cannot be negative.\n");
				return;
			}
		}
		catch(NumberFormatException e) {
			outputArea.appendText("Enter a number for hours worked.\n");
			return;
		}
		
		
		Parttime temp = new Parttime(new Profile(employeeName.getText(), department, new Date(dateHired.getValue().toString())), 0);
		temp.setHours(Integer.parseInt(hoursWorked.getText()));
		if (!company.setHours(temp)) {
			outputArea.appendText("Employee does not exist.\n");
			return;
		}
		outputArea.appendText("Working hours set.\n");
		
	}
	
	/**
	 * This function is triggered when clicking the Remove Employee button. It removes an employee from the database (if they exist).
	 * @param event Clicking the Remove Employee button.
	 */
	@FXML
	private void remove(ActionEvent event) {
		if (listCheck() == true) {
			return;
		}
		
		
		String department ="";
		if (itButton.isSelected()) {
			department = "IT";
		}
		else if (csButton.isSelected()) {
			department = "CS";
		}
		else if (eceButton.isSelected()) {
			department = "ECE";
		}
		else {
			outputArea.appendText("Please pick a department.\n");
		}
		
		
		
		
		
		if (company.remove(new Employee(new Profile(employeeName.getText(), department, new Date(dateHired.getValue().toString()))))) {
			outputArea.appendText("Employee removed.\n");
		}
	
		else {
			outputArea.appendText("Employee does not exist.\n");
			return;
		}
		
	}
	
	/**
	 * This function adds a parttime employee to the database.
	 */
	@FXML
	private void addParttime() {
		String department ="";
		if (itButton.isSelected()) {
			department = "IT";
		}
		else if (csButton.isSelected()) {
			department = "CS";
		}
		else if (eceButton.isSelected()) {
			department = "ECE";
		}
		else {
			outputArea.appendText("Please pick a department.\n");
			return;
		}
		
		if (hourlyRate.getText().isEmpty() ) {
			outputArea.appendText("Parttime employees must have an hourly pay rate.\n");
			return;
		}
		
		try {
			if (Double.parseDouble(hourlyRate.getText()) < 0) {
				outputArea.appendText("Hourly rate cannot be negative.\n");
				return;
			}
		}
		catch(NumberFormatException e) {
			outputArea.appendText("Enter a number for hourly rate.\n");
			return;
		}
		
		
		
		if (dateHired.getValue() == null) {
			outputArea.appendText("Please enter a date.\n");
			return;
		}
		Parttime newEmployee = new Parttime(new Profile(employeeName.getText(), department, new Date(dateHired.getValue().toString())), Double.parseDouble(hourlyRate.getText() ));
		
		boolean isSuccess = checkEmployee(newEmployee);
		
	}
	/**
	 * This function adds a fulltime employee to the database.
	 */
	@FXML
	private void addFulltime() {
		String department ="";
		if (itButton.isSelected()) {
			department = "IT";
		}
		else if (csButton.isSelected()) {
			department = "CS";
		}
		else if (eceButton.isSelected()) {
			department = "ECE";
		}
		else {
			outputArea.appendText("Please pick a department.\n");
			return;
		}
		
		if (employeeSalary.getText().isEmpty() ) {
			outputArea.appendText("Fulltime/Management employees must have a salary.\n");
			return;
		}
		try {
			if (Double.parseDouble(employeeSalary.getText()) < 0) {
				outputArea.appendText("Salary cannot be negative.\n");
				return;
			}
		}
		catch(NumberFormatException e){
			outputArea.appendText("Enter a number for the salary.\n");
			return;
		}
		
		if (dateHired.getValue() == null) {
			outputArea.appendText("Please enter a date.\n");
			return;
		}
		Fulltime newEmployee = new Fulltime(new Profile(employeeName.getText(), department, new Date(dateHired.getValue().toString())), Double.parseDouble(employeeSalary.getText() ));
		
		
		
		
		
		boolean isSuccess = checkEmployee(newEmployee);
		
		
	}
	/**
	 * This function adds a management employee to the database.
	 */
	@FXML
	private void addManagement() {
		String department ="";
		int managementRole =0;
		
		if (itButton.isSelected()) {
			department = "IT";
		}
		else if (csButton.isSelected()) {
			department = "CS";
		}
		else if (eceButton.isSelected()) {
			department = "ECE";
		}
		else {
			outputArea.appendText("Please pick a department.\n");
			return;
		}
		
		
		if (directorButton.isSelected()) {
			managementRole = 3;
		}
		else if (depHeadButton.isSelected()) {
			managementRole = 2;
		}
		else if (managerButton.isSelected()) {
			managementRole = 1;
		}
		else {
			outputArea.appendText("Please pick a management role for this management employee.\n");
			return;
		}
		
		if (employeeSalary.getText().isEmpty() ) {
			outputArea.appendText("Fulltime/Management employees must have a salary.\n");
			return;
		}
		
		try {
			if (Double.parseDouble(employeeSalary.getText()) < 0) {
				outputArea.appendText("Salary cannot be negative.\n");
				return;
			}
		}
		catch(NumberFormatException e){
			outputArea.appendText("Enter a number for the salary.\n");
			return;
		}
		
		if (dateHired.getValue() == null) {
			outputArea.appendText("Please enter a date.\n");
			return;
		}
		
		Management newEmployee = new Management(new Profile(employeeName.getText(), department, new Date(dateHired.getValue().toString())), Double.parseDouble(employeeSalary.getText()), managementRole );
		
		boolean isSuccess = checkEmployee(newEmployee);
		
		
	}
	
	
	
	/**
	 * This overloaded method checks the validity of an employee before adding them to the company database. Used for employees imported from text file.
	 * @param newEmployee Employee to be checked
	 * @param tokens Array of employees
	 * @return false if the employee cannot be added
	 * @return true if the employee is added
	 */
	private boolean checkEmployee(Employee newEmployee, String[] tokens) {
		if(company.add(newEmployee) != true) {
			outputArea.appendText("Employee is already in the list.\n");
			return false;
		}
		else {
			outputArea.appendText("Employee added.\n");
		}
		
		return true;
	}
	
	/**
	 * This method checks the validity of an employee before adding them to the company database. 
	 * @param newEmployee Employee to be checked
	 * @return false if the employee cannot be added,true if the employee is added
	 */
	@FXML
	private boolean checkEmployee(Employee newEmployee) {
		if (employeeName.getText().isEmpty()) {
			outputArea.appendText("Please enter a name for the employee!\n");
			return false;
		}
		
		
		
		
		Date newDate = (newEmployee.getDate());
		//outputArea.appendText(newDate.toString() + "\n");
		
		if (!newDate.isValid()) {
			outputArea.appendText("Please enter a valid date.\n");
			return false;
		}
			
		
		
		
		if(company.add(newEmployee) != true) {
			outputArea.appendText("Employee is already in the list.\n");
			return false;
		}
		else {
			outputArea.appendText("Employee added.\n");
		}
		
		return true;
	}
	
	/**
	 * This method checks if the company database is currently empty.
	 * @return true if the database is empty, false if the database is NOT empty
	 */
	@FXML
	private boolean listCheck() {
		if (company.isEmpty()) {
			outputArea.appendText("Employee database is empty.\n");
			return true;
		}
		return false;
	}
	
	/**
	 * This method is triggered when the Compute Payments button is clicked. It computes the payments for employees in the current pay period.
	 */
	@FXML
	private void computePayments() {
		if (listCheck() == false) {
			company.processPayments();
			outputArea.appendText("Payments calculated.\n");
		}
		
	}
		
	
	
	
	
	
	
	/**
	 * This method is triggered when the Print menu button option is selected. It prints the earnings statements for all employees.
	 */
	@FXML
	private void print() {
		if (listCheck() == false) {
			outputArea.appendText("--Printing earning statements for all employees--\n");
			company.print(outputArea);
		}
	}
	/**
	 * This method is triggered when the PrintByDepartment menu button option is selected. It prints the earnings statements for all employees in order of department.
	 */
	@FXML
	private void printByDep() {
		if (listCheck() == false) {
			outputArea.appendText("--Printing earning statements by department--\n");
			company.printByDepartment(outputArea);
		}
	}
	/**
	 * This method is triggered when the PrintByDateHired menu button option is selected. It prints the earnings statements for all employees in order of date hired.
	 */
	@FXML
	private void printByDateHired() {
		if (listCheck() == false) {
			outputArea.appendText("--Printing earning statements by date hired--\n");
			company.printByDate(outputArea);
		}
	}
}
