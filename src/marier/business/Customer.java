/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marier.business;

/**
 *
 * @author linma
 * 
 */
public class Customer {
    String emailID;
    String firstName;
    String lastName;
    
    //default constructor 
    public Customer ()
    {
        
    }
    
    //custom constructor
    public Customer(String email, String firstName, String lastName)
    {
        this.emailID = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
   //adds email to the customer variable email
    public void setEmail(String email)
    {
        this.emailID=email;
    }
    
    //accesses the email variable and returns it. 
    public String getEmail()
    {
        return emailID;
    }
    
    //sets the first name variable
    public void setFirstName(String firstName)
    {
        this.firstName=firstName;
    }
    
    //gets the first name variableand returns it
    public String getFirstName()
    {
        return firstName;
    }
    
    //sets last name variable
    public void setLastName(String lastName)
    {
        this.lastName= lastName;
    }
    
    //gets last name variable and returns it
    public String getLastName()
    {
        return this.lastName;
    }
    
}
