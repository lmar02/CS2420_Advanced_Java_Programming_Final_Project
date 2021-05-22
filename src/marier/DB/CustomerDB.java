/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marier.DB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.util.Arrays.stream;
import java.util.logging.Level;
import java.util.logging.Logger;

import marier.business.Customer;
import marier.business.CustomerDAO;

/**
 *
 * @author linma 
 */
public class CustomerDB implements CustomerDAO {
    //necessary variables 
    private ArrayList<Customer> customers;
    private final String FIELD_SEP = "\t";
    Path customerPath = Paths.get("Customer.txt");
    File customerFile = customerPath.toFile();
    
    //constructor for the CustomerDB class. Makes sure that if their is a customer file it is not making another one. 
    public CustomerDB()
    {
        if(Files.notExists(customerPath))
        {
            File file = new File("Customer.txt");
            try {
                file.createNewFile();
            } catch (IOException ex) {
               System.out.println(ex);
            }
        } else
       customers = getCustomers(); 
        
    }

   //saves customers to the proper file. 
    public boolean saveCustomers(Customer c)
    {
        //checks to make sure that the file exists, if it doesn't it makes a new one. 
       if(Files.notExists(customerPath))
        {
            File file = new File("Customer.txt");
            try {
                file.createNewFile();
            } catch (IOException ex) {
               System.out.println(ex);
            }
        } else
           //write the saved customers to the file.
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Customer.txt",true))))
        {
            
            
                out.print(c.getEmail()+FIELD_SEP);
                out.print(c.getFirstName()+FIELD_SEP);
                out.print(c.getLastName()+FIELD_SEP);
                out.append(System.lineSeparator());
                out.close();
            
        } catch (IOException ex) {
           System.out.println(ex);
        }
        return true;
    }
//addes a customer to the file. 
   // @Override
    public boolean addCustomer(Customer c) {
        boolean doesExist = false;
         if(Files.notExists(customerPath))
        {
            File file = new File("Customer.txt");
            try {
                file.createNewFile();
            } catch (IOException ex) {
               System.out.println(ex);
            }
        } else
        for(Customer cus: customers)
        {
            if(cus.getEmail().equalsIgnoreCase(c.getEmail()))
            {
                doesExist = true;
            }
        }if(doesExist == true)
        {
            System.out.println("Customer already exists.");
            return false;
        }else
        {
            customers.add(c);
            return saveCustomers(c);
        }
    }
    
// deletes a customer from the file. 
   // @Override
    public boolean deleteCustomer(Customer c) {
         if(Files.notExists(customerPath))
        {
            File file = new File("Customer.txt");
            try {
                file.createNewFile();
            } catch (IOException ex) {
               System.out.println(ex);
            }
        } else
         {
             customers.remove(c);
             try {
                 Files.deleteIfExists(customerPath);
                 File file = new File("Customer.txt");
                 file.createNewFile();
                 
                 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Customer.txt")));
                 for(Customer cs:customers)
                 {
                 out.print(cs.getEmail()+FIELD_SEP);
                out.print(cs.getFirstName()+FIELD_SEP);
                out.print(cs.getLastName()+FIELD_SEP);
                out.append(System.lineSeparator());
                out.close();
                 }
                 
                 
                 
             } catch (IOException ex) {
                 Logger.getLogger(CustomerDB.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
        
                 return true;
    }
//updates a customers email informaiton on the file.
  //  @Override
    public boolean updateCustomer(String email, Customer c) {
        
        
        Customer old = getCustomer(email);
        
       
        customers.remove(old); 
        
        try {
                 Files.deleteIfExists(customerPath);
                 File file = new File("Customer.txt");
                 file.createNewFile();
                 
                 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Customer.txt")));
                 
                 for(Customer cs:customers)
                 {
                 out.print(cs.getEmail()+FIELD_SEP);
                out.print(cs.getFirstName()+FIELD_SEP);
                out.print(cs.getLastName()+FIELD_SEP);
                out.append(System.lineSeparator());
                 }
                 
                
                 
                  out.close();
             } catch (IOException ex) {
                 Logger.getLogger(CustomerDB.class.getName()).log(Level.SEVERE, null, ex);
             }
        return saveCustomers(c);
    }
//gets customer information from array list customers
   // @Override
    public Customer getCustomer(String email) {
        for (Customer c: customers)
        {
            if(c.getEmail().equals(email))
            {
                return c;
            }
        }
        return null;
    }
    
   // @Override
    public String getCustomerString() {
        Customer c;
        String s = " ";
        for(int i = 0; i<customers.size(); i++)
        {
            c = customers.get(i);
            String one = padWithSpace(c.getEmail(),emailSize + 5);
            String two = padWithSpace(c.getFirstName(),firstNameSize+5);
            s = one + two + c.getLastName() + "\n";
        }
        return s;
    }
    
    
    public static String padWithSpace(String s, int l)
    {
        if (s.length()<l)
        {
            StringBuilder ss = new StringBuilder(s);
            while(ss.length()<l)
            {
                ss.append(" ");
            }
            return ss.toString();
        }else{
            return s.substring(0,l);
        }
        
    }
//creates an Arraylist of Customer, if it is not created yet, pulls from file or returns an already created arraylist
   // @Override
    public ArrayList<Customer> getCustomers() {
        if(customers!=null)
        {
            return customers;
        }
        customers = new ArrayList<>();
        if(Files.exists(customerPath))
        {
            try (BufferedReader in = new BufferedReader(new FileReader("Customer.txt")))
            {
                String line = in.readLine();
                while(line!=null)
                {
                    String[] colomns = line.split(FIELD_SEP);
                    String email = colomns[0];
                    String firstName = colomns[1];
                    String lastName = colomns[2];
                    Customer c = new Customer();
                    c.setEmail(email);
                    c.setFirstName(firstName);
                    c.setLastName(lastName);
                    customers.add(c);
                    line = in.readLine();
                            
                             
                }
                
            } catch (FileNotFoundException ex) {
               System.out.println(ex);
            }catch(IOException e)
            {
                System.out.println(e);
        }
    }
        return customers;
    
}
}


