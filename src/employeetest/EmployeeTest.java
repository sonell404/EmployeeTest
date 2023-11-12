package employeetest;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * SBA22075 - Sonel Ali
 * 
 * The purpose of this program is to allow the user to manage employee information
 * working for a given company, also created by the user. The program can also 
 * create a group of 3 default employees.
 * 
 * Functionality includes 
 *      Creation of default value employees
 *      Creation of custom employees
 *      Edit employee information - Employee name, Employee email
 *      Display employee information
 *      Display amount of employees
 * 
 * The default employees are stored in a fixed-size array, whereas any employees 
 * created by the user are stored in an ArrayList. The advantage of using 
 * an ArrayList over an array is that the ArrayList can grow or shrink dynamically,
 * which is more efficient in terms of memory usage, compared to an array which 
 * has a fixed length and cannot be changed once it has been created. For employee 
 * administration software, it is important to be able to add employees with ease 
 * and without fear of having enough space to hold them. An ArrayList may lack the 
 * ability to hold primitive data types, but for this particular project, it is an 
 * object that is needed, so using an ArrayList is not an issue. The extra 
 * functionality that comes with an ArrayList, such as indexOf() and remove(), 
 * provides more flexibility when working with Employee objects. The ArrayList
 * implements the Iterator interface which is useful if we want to modify the 
 * list while we are traversing it, e.g. if we want to remove an element.
 * 
 */
public class EmployeeTest 
{
    // CLASS SCANNER
    static final Scanner INPUT = new Scanner(System.in);
    // DEFAULT COMPANY VARIABLES
    static Employee defaultEmp;
    static Employee[] projectGroup;
    // USER DEFINED COMPANY VARIABLE
    static Company userCompany;

    public static void main(String[] args) 
    {
        startProgram();
    } // MAIN METHOD
    
    
    // Method to start program
    static void startProgram()
    {
        // Loop whil program is in use
        do
        {
            // Display menu
            displayMenu();
            
            // Retrieve menu selection from user and perform appropriate action
            // A limit of 9 corresponds to the amount of options available
            initialiseSelection(getMenuSelection(9));
        }
        while (yesOrNo("\nContinue to Main Menu?"));
        
        System.out.println("Goodbye!");
    } // startProgram
    
    
    
    // METHODS FOR MENUS & INTERACTIONS WITH MENUS
    
    // Method for displaying menu
    static void displayMenu()
    {
        System.out.println();
        System.out.println("1. Create Default Company");
        System.out.println("2. List Default Employees");
        System.out.println("3. Print Next Employee Number");
        System.out.println("4. Create New Company");
        System.out.println("5. Add Employee");
        System.out.println("6. Edit Employee Data");
        System.out.println("7. List Your Employees ");
        System.out.println("8. Get number of employees");
        System.out.println("9. Quit");
        System.out.println();
    } // displayMenu
    
    // Method for displaying 'edit' menu
    static void displayEditMenu()
    {
        System.out.println();
        System.out.println("1. Edit Employee Name");
        System.out.println("2. Edit Employee Email");
        System.out.println();
    } // displayEditMenu
    
    // Method for getting users menu selection
    static int getMenuSelection(int limit)
    {
        String response;
        
        // Loop until valid selection has been retrieved
        do
        {
            // Prompt user for input
            System.out.println("\nPlease enter the number of your menu selection");
            System.out.print(">");
            response = INPUT.nextLine();
            
            // Check response is numeric and also corresponding to menu options
            if (isNumeric(response) && isValidSelection(response, limit))
            {
                // Once validated, break from loop
                break;
            }
            else
            {
                // Inform user input must be valid
                System.out.println("\nMenu selection must correspond to menu item!");
            }
        }
        while(true);
        
        return Integer.parseInt(response);
    } // getMenuSelection
    
    // Method for executing users selection
    static void initialiseSelection(int x)
    {
        // Perform action corresponding to users selection
        switch (x)
        {
            case 1 -> populateEmployeeArray();
            case 2 -> displayEmployees(getNumericInput("\nEnter employee number to display from", projectGroup));
            case 3 -> printNextEmpNum();
            case 4 -> createNewCompany();
            case 5 -> addEmployee();
            case 6 -> editEmployeeData();
            case 7 -> displayCompanyEmployees(
                    getNumericInput("\nEnter employee number to display from"));
            case 8 -> getNumOfEmployees();
            case 9 -> quitProgram();
        }
    } // initialiseSelection
    
    
    
    // CUSTOM COMPANY METHODS - SPECIFICATION 2
    
    // Method to create new company
    static void createNewCompany()
    {
        String response;
        
        do
        {
            // Prompt user
            System.out.println("\nPlease enter a name for your company");
            System.out.print(">");

            response = INPUT.nextLine();
            
            // Check response is not blank
            if (!response.equals(""))
            {
                // Initialise new Company object
                userCompany = new Company(response);
                
                // Inform user object was created and prompt them to create an employee
                System.out.println("\nSuccess! '" + userCompany.companyName + "' has been created.");
                System.out.println("\nCompany must be initiated with at least 1 employee.");
                addEmployee();
                break;
            }
            else
            {
                System.out.println("\nYou must enter a name!");
            }
        }
        while (true);
    } // createNewCompany
    
    // Method to add employee to user company
    static void addEmployee()
    {
        String name;
        String email;
        
        // Execute function if there is a company
        if (userCompany != null)
        {
            // Loop while user is adding employees
            do
            {
                // Loop until a valid name has been retrieved
                do
                {
                    // Prompt user
                    System.out.println("\nPlease enter employee name");
                    System.out.print(">");

                    name = INPUT.nextLine();
                    
                    // If response is not valid, inform user
                    if (!Employee.isAlpha(name))
                    {
                        System.out.println("\nPlease enter a valid name");
                        System.out.println("Name is only valid if it is alphabetic, with the exception of hyphens and spaces");
                    }
                    // Break from loop if valid response has been retrieved
                    else
                    {
                        break;
                    }
                }
                while (true);
                // Loop unitl a valid email has been retrieved
                do
                {
                    System.out.println("\nPlease enter employee email");
                    System.out.print(">");

                    email = INPUT.nextLine();
                    
                    // If response is not valid, inform user
                    if (!Employee.isValidEmail(email))
                    {
                        System.out.println("\nPlease enter a valid email address");
                        System.out.println("Example: default@default.ie");
                    }
                    // Break from loop if valid response has been retrieved
                    else
                    {
                        break;
                    }
                }
                while (true);
                
                // Once input has been validated, create and add new employee to staff list
                userCompany.addNewStaff(new Employee(name, email));
            }
            // Ask user if they want to add another employee
            while (yesOrNo("\nWould you like to add another employee?"));
        }
        // Create a company if there isn't one already
        else
        {
            createNewCompany();
        }
        
        // Once user is finished adding employees, display all employees
        System.out.println("\nYour company now has the following employees\n");
        userCompany.listEmployees(0);
    } // addEmployee
    
    // Method to edit employee data
    static void editEmployeeData()
    {
        String response;
        
        // Execute function if there is a company and ArrayList is not empty
        if (userCompany != null)
        {
            // Loop while editing employee data
            do
            {
                // Show user available employees
                displayCompanyEmployees(0);

                // Prompt user to enter number of employee they want to edit
                System.out.println("Please enter employee number");
                System.out.print(">");

                response = INPUT.nextLine();

                // Execute functionality if response is valid
                if (isNumeric(response))
                {
                    // Loop until corresponding employee is found
                    for (Employee e : userCompany.staff)
                    {
                        // If employee is found
                        if (e.getEmpNum() == Integer.parseInt(response))
                        {
                            // Show user available options
                            displayEditMenu();

                            // Retrieve user selection and execute appropriate function
                            switch (getMenuSelection(2))
                            {
                                case 1 -> editEmployeeName(e);
                                case 2 -> editEmployeeEmail(e);
                            }
                            
                            // Break from loop when done
                            break;
                        }
                        else
                        {
                            // Inform user if employee does not exist and return to start
                            System.out.println("\nThat employee doesn't exist. Please try again.");
                            break;
                        }
                    }
                }
                // Inform user if input is invalid
                else
                {
                    System.out.println("Invalid input. Input must correspond to employee number.");
                }
            }
            // Ask user if they would like to edit another employee
            while (yesOrNo("\nEdit data of another employee?"));
        }
        // If company does not exist, ask user if they would like to create one
        else if (yesOrNo("\nCompany has no staff. Would you like to create a company?"))
        {
            createNewCompany();
        }
    }
    
    // Method to edit employee name
    static void editEmployeeName(Employee emp)
    {
        String response;
        
        // Loop until valid input has been retrieved
        do
        {
            // Prompt user
            System.out.println("\nCurrent employee name: " + emp.getName());
            System.out.println("\nPlease enter new name");
            System.out.print(">");
            
            response = INPUT.nextLine();
            
            // Check if response is valid, inform user if not
            if (!Employee.isAlpha(response))
            {
                System.out.println("\nPlease enter a valid name");
                System.out.println("Name is only valid if it is alphabetic, with the exception of hyphens, spaces and apostrophes");
            }
            // Set new name if response is valid, and display to user
            else
            {
                emp.setName(response);
                System.out.println("\nNew Employee name: " + emp.getName());
                break;
            }
        }
        while (true);
    } // editEmployeeName
    
    // Method to edit employee email
    static void editEmployeeEmail(Employee emp)
    {
        String response;
        
        // Loop until valid input has been retrieved
        do
        {
            // Prompt user
            System.out.println("\nCurrent employee email: " + emp.getEmail());
            System.out.println("\nPlease enter new email");
            System.out.print(">");
            
            response = INPUT.nextLine();
            
            // Check if response is valid, inform user if not
            if (!Employee.isValidEmail(response))
            {
                System.out.println("\nPlease enter a valid email address");
                System.out.println("Example: default@default.ie");
            }
            // Set new email if response is valid, and display to user
            else
            {
                emp.setEmail(response);
                System.out.println("\nNew Employee Email: " + emp.getEmail());
                break;
            }
        }
        while (true);
    } // editEmployeeEmail
    
    // Method to list company employees
    static void displayCompanyEmployees(int employeeNum)
    {
        // If error value, signalling no company, has been received, ask if user would like to create a company
        if (employeeNum == -1 && yesOrNo("\nThere are no employees. Would you like to create a company?"))
        {
            createNewCompany();
        }
        // Else list employees
        else
        {
            userCompany.listEmployees(employeeNum);
        }
    } // listCompanyEmployees
    
    // Method to get number of employees
    static void getNumOfEmployees()
    {
        // If company is not empty, retrieve and display size of staff list
        if (userCompany != null)
        {
            System.out.println("\nStaff size: " + userCompany.getStaffNumber());
        }
        // If there is no company, ask user if they would like to create one
        else if (yesOrNo("\nThere are no employees. Would you like to create a company?"))
        {
            createNewCompany();
        }
    } // getNumOfEmployees
    
    
    
    // DEFAULT EMPLOYEE METHODS - SPECIFICATION 1
    
    // Method to create and fill array with default employees
    static void populateEmployeeArray()
    {
        // Declare and initialise array
        projectGroup = new Employee[3];
        
        // Assign employees to array
        projectGroup[0] = new Employee("Joe Bloggs", "ab@gmail.com");
        projectGroup[1] = new Employee("Ann Banana", "ab@gmail.com");
        projectGroup[2] = new Employee("Tom Thumb", "tb@gmail.com");
        
        // Inform user array has been created
        System.out.println("\nSuccess! Default employee array has been created");
    } // populateEmployeeArray
    
    // Method to print the next available employee number
    static void printNextEmpNum()
    {
        System.out.println("\nNext Employee number: " + Employee.getNextEmpNum());
    } // printNextEmpNum
    
    // Method to display default employees
    static void displayEmployees(int m)
    {
        // Display employees who have a number above the value given
        if (m > 0)
        {
            System.out.println();
            
            // Loop through array
            for (Employee e : projectGroup)
            {
                // If the employee number is greater than the given value, display employee
                if (e.getEmpNum() > m)
                {
                    System.out.println(e.toString() + "\n");
                }
            }
        }
        // Display all employees if a value of 0 was given
        else if (m == 0)
        {
            System.out.println();
            
            for (Employee e : projectGroup)
            {
                System.out.println(e.toString() + "\n");
            }
        }
        // Any other value will display a default employee
        else
        {
            defaultEmp = new Employee();
            
            System.out.println("\nDEFAULT EMPLOYEE");
            System.out.println("\n" + defaultEmp.toString());
        }
    } // displayEmployees
    
    
    
    // VALIDATION & PROMPT METHODS
    
    // Method for validating menu selection
    static boolean isValidSelection(String str, int limit)
    {
        int selection;
        
        // Check if String is numeric, assign to int variable if it is, return false if not
        if (isNumeric(str))
        {
            selection = Integer.parseInt(str);
        }
        else
        {
            return false;
        }
        
        // Return true if selection is within range of menu options
        return selection > 0 && selection <= limit;
    } // isValidSelection
    
    // Method for asking user YES or NO questions
    static boolean yesOrNo(String question)
    {
        String response;
        
        // Loop until valid input has been retrieved
        while(true)
        {
            // Present given question to user
            System.out.println(question + "\nY/N");
            System.out.print(">");
            
            // Retrieve response
            response = INPUT.nextLine();
            
            // If response is 'Y', return true
            if (response.equalsIgnoreCase("Y"))
            {
                return true;
            }
            // If response is 'N', return false
            else if (response.equalsIgnoreCase("N"))
            {
                return false;
            }
            // If response is anything else, inform user of invalid input
            else
            {
                System.out.println("Invalid input!");
            }
        }
    } // yesOrNo
    
    // Overloaded method for getting valid numeric input - array version
    static int getNumericInput(String prompt, Employee[] defaultEmpArray)
    {
        String response;
        
        // Only execute if array is not null
        if (defaultEmpArray != null)
        {
            // Loop until valid input has been retrieved
            while (true)
            {
                // Prompt user
                System.out.println(prompt);
                System.out.println("\nNumber must be " 
                        + defaultEmpArray[0].getEmpNum() 
                        + " or lower than " 
                        + defaultEmpArray[defaultEmpArray.length - 1].getEmpNum());
                System.out.println("or enter 0 to display all");
                System.out.print(">");
                
                response = INPUT.nextLine();
                
                // Validate repsonse is numeric or not
                if (!isNumeric(response))
                {
                    System.out.println("\nInput must be numeric.");
                }
                // Make sure value is a valid employee number
                else if (Integer.parseInt(response) > defaultEmpArray[defaultEmpArray.length - 1].getEmpNum()
                        || Integer.parseInt(response) < defaultEmpArray[0].getEmpNum() && Integer.parseInt(response) != 0
                        || Integer.parseInt(response) == defaultEmpArray[defaultEmpArray.length - 1].getEmpNum())
                {
                    System.out.println("\nInvalid Input!");
                    System.out.println("Number must be " + defaultEmpArray[0].getEmpNum() 
                            + " or lower than " 
                            + defaultEmpArray[defaultEmpArray.length - 1].getEmpNum());
                }
                // If response is valid, return response
                else
                {
                    return Integer.parseInt(response);
                }
            }
        }
        // Return error value signalling an empty array
        else
        {
            return -1;
        }
    } // getNumericInput - array version
    
    // Overloaded method for getting valid numeric input - ArrayList version
    static int getNumericInput(String prompt)
    {
        String response;
        
        // Check if user instantiation of Company class exists
        if (userCompany != null)
        {
            // Initialise Company class ArrayList
            ArrayList<Employee> e = userCompany.staff;
            
            // Loop until valid input has been retrieved
            while (true)
            {
                // Prompt user, inform user of valid parameters
                System.out.println(prompt);
                System.out.println("\nNumber must be " 
                        + e.get(0).getEmpNum() 
                        + " or lower than " 
                        + e.get(e.size() - 1).getEmpNum());
                System.out.println("or enter 0 to display all");
                System.out.print(">");
                // Retrieve input
                response = INPUT.nextLine();
                
                // Check if input is numeric or not, inform user of invalid input if not
                if (!isNumeric(response))
                {
                    System.out.println("Input must be numeric.");
                }
                // Check if input is within range of available employee numbers, inform user if not
                else if (Integer.parseInt(response) > e.get(e.size() - 1).getEmpNum() 
                        || Integer.parseInt(response) < e.get(0).getEmpNum() && Integer.parseInt(response) != 0
                        || Integer.parseInt(response) == e.get(e.size() - 1).getEmpNum())
                {
                    System.out.println("\nInvalid Input!");
                    System.out.println("Number must be " + e.get(0).getEmpNum() 
                            + " or lower than " 
                            + e.get(e.size() - 1).getEmpNum());
                }
                // return integer value of response once it passes all validation
                else
                {
                    return Integer.parseInt(response);
                }
            }
        }
        // Return error value if ArrayList is empty
        else
        {
            return -1;
        }
    } // getNumericInput - ArrayList version
    
    // Method for validating numeric input
    static boolean isNumeric(String str)
    {
        // Try convert String to integer
        try
        {
            Integer.valueOf(str);
        }
        // Return false if String is not numeric
        catch(NumberFormatException e)
        {
            return false;
        }
        // Return true once validated
        return true;
    } // isNumeric
    
    
    
    // EXIT METHOD
    
    // Method to quit program with a 'goodbye' message
    static void quitProgram()
    {
        System.out.println("\nGoodbye!");
        System.exit(0);
    } // quitProgram
} // CLASS
