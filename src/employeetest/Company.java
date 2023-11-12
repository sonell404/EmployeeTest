package employeetest;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * SBA22075 - Sonel Ali
 */
public class Company 
{
    // Instance fields
    String companyName;
    ArrayList<Employee> staff;

    // Constructors
    public Company() 
    {
       companyName = "Default Inc."; 
       staff = new ArrayList<>();
    }
    public Company(String companyName) 
    {
        this.companyName = companyName;
        staff = new ArrayList<>();
    }
    
    // Method to add new employee objects to Employee list
    void addNewStaff(Employee e)
    {
        if (e != null)
        {
            staff.add(e);
        }
    }
    // Method to get number of employees from Employee list
    int getStaffNumber()
    {
        return staff.size();
    }
    // Method to print all Employee objects above given number
    void listEmployees(int i)
    {
        Iterator<Employee> it = staff.iterator();
        
        System.out.println();
        while (it.hasNext())
        {
            Employee e = it.next();
            
            if (e.getEmpNum() > i)
            {
                System.out.println(e.toString() + "\n");
            }
        }
    }
}
