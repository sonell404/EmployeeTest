package employeetest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * SBA22075 - Sonel Ali
 */
public final class Employee 
{
    // Instance fields
    protected String name;
    private String email;
    private final int empNum;
    
    // Static fields
    private static int nextEmpNum = 1;
    
    // Constructors
    
    public Employee()
    {
        name = "John Jones";
        email = "john@johnjonesworld.ie";
        empNum = nextEmpNum;
        
        nextEmpNum++;
    }
    public Employee(String name, String email) 
    {
        setName(name);
        setEmail(email);
        empNum = nextEmpNum;
        
        nextEmpNum++;
    }

    // Getters
    
    public String getName() 
    {
        return name;
    }
    public String getEmail() 
    {
        return email;
    }
    public int getEmpNum() 
    {
        return empNum;
    }
    public static int getNextEmpNum() 
    {
        return nextEmpNum;
    }

    // Setters
    
    protected void setEmail(String email) 
    {
        // Email is only valid if length is greater than 3 and passes regex check
        if (email.length() > 3 && isValidEmail(email))
        {
            this.email = email;
        }
        else
        {
            this.email = "Invalid Email";
        }
    }
    protected void setName(String name)
    {
        // Name is only valid if it is alphabetic, with the exception of hyphens, spaces and apostrophes
        if (isAlpha(name))
        {
            this.name = name;
        }
        else
        {
            this.name = "Invalid input";
        }
    }
    
    // Validation Methods
    
    // Method to compare email against RFC 5322 standard
    public static boolean isValidEmail(String email)
    {
        String regex = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        
        return matcher.matches();
    }
    // Method to check if String is alphabetical
    public static boolean isAlpha(String s)
    {
        // Regex pattern allows letters, hyphens and apostrophes
        return s.matches("^[a-zA-Z\\-' ]*$");
    }
    
    
    // toString() method to display employee details
    @Override
    public String toString()
    {
        return "Name: " + getName() + "\nE-mail: " + getEmail() + "\nEmployee Number: " + getEmpNum();
    }
}
