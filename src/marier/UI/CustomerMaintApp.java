package marier.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import marier.DB.CustomerDB;
import marier.business.Customer;
import marier.business.CustomerDAO;
import marier.business.*;
import static marier.business.CustomerConstants.emailSize;
import static marier.business.CustomerConstants.firstNameSize;
import static marier.business.CustomerConstants.lastNameSize;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author linma
 */
//Main application page. 
public class CustomerMaintApp extends JFrame{
  //necessary variables
static CustomerDAO customers;
static JFrame frame = new JFrame();
static JPanel panel = new JPanel();
static JPanel butPanel = new JPanel();
static JButton add = new JButton("Add");
static JButton edit = new JButton("Edit");
static JButton delete = new JButton("Delete");
static JButton help = new JButton("Help");
static JTable table = new JTable();
static JScrollPane scroll = new JScrollPane(table);
 
    /**
     *
     * @param args
     */
   


    
    public static void main(String[] args) {
        
        // TODO code application logic here
        
        
        GUI();
        tab();
    }
    public static void GUI()
    {
        Dimension d = new Dimension(600,400);
        frame.setSize(600, 600);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(butPanel,BorderLayout.SOUTH);
       // frame.setPreferredSize(d);
        frame.setMinimumSize(d);
        butPanel.add(add);
        butPanel.add(edit);
        butPanel.add(delete);
        butPanel.add(help);
        table.setModel(new DefaultTableModel(
        new Object[][] {},
        new String[] {"Email","First Name","Last Name"}
        ));
        table.setFillsViewportHeight(true);
        table.setRowHeight(20);
        
        panel.add(table.getTableHeader());
        
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       panel.add(scroll);
        frame.add(panel);
        add.addActionListener((ActionEvent e) -> {
           addButton(); 
        });
        edit.addActionListener((ActionEvent e)->{
        editButton();
        });
        delete.addActionListener((ActionEvent e) ->{
        deleteButton();
        });
        table.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent e){
            tableMouseClicked(e);
        }
        });
        
        
        help.addActionListener((ActionEvent e) -> {
            helpButton();
        });
        
       
        
        
        frame.setVisible(true);
    }
    
    
    public static void tableMouseClicked(MouseEvent e)
    {
        int i = table.getSelectedRow();
        TableModel m = table.getModel();
    }
    
    
    public static void deleteButton()
    {
        int i = table.getSelectedRow();
        if(i == -1)
        {
            JOptionPane.showMessageDialog(frame, "Did not select a row","Error", JOptionPane.WARNING_MESSAGE);
        }else
        {
           String email = table.getValueAt(i, 0).toString();
           Customer c = customers.getCustomer(email);
           if(c!=null)
           {
               customers.deleteCustomer(c);
               tab();
           }else 
               System.out.println("No match.");
        }
    }
    
    
    public static void editButton()
    {
        int i  = table.getSelectedRow();
        if(i == -1)
        {
            JOptionPane.showMessageDialog(frame, "Did not select a row","Error",JOptionPane.INFORMATION_MESSAGE);
        }else
        {
            String email = table.getValueAt(i,0).toString();
           Customer c = addeditButton();
           if(c.getEmail()!=null)
           {
               customers.updateCustomer(email, c);
               tab();
           }else{
               JOptionPane.showMessageDialog(frame, "No Email selected","Error",JOptionPane.INFORMATION_MESSAGE);
           }
           tab();
        }
    }
    
   //pulls up the pop up for the user to enter the required information. 
    public static void addButton()
    {
       Customer c = addeditButton(); 
       Customer t = customers.getCustomer(c.getEmail());
       
      //makes sure that the information was not already added. 
       if(t != null)
       {
           JOptionPane.showMessageDialog(frame, "There is already a customer with that email.","Error", JOptionPane.WARNING_MESSAGE);
           
       }else if(c.getEmail() != null)
       {
           customers.addCustomer(c);
           tab();
       }
    }
    
    //pulls and verifies the informaiton from the pop up tomake sure the information that the user enters is valid
    public static Customer addeditButton()
    {
        //text field for email
        JTextField tEmail = new JTextField();
        StringsUtils su = new StringsUtils();
        tEmail.setColumns(20);
        //text field for first name
        JTextField tFName = new JTextField();
        tFName.setColumns(20);
        //text field for last name
        JTextField tLName = new JTextField();
        tLName.setColumns(20);
        //Labels for each text field
        JLabel lEmail = new JLabel("Email: ");
        JLabel lFName = new JLabel("First Name: ");
        JLabel lLName = new JLabel("Last Name: ");
       
        
        //Panel creation
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5,5,0,5);
        g.gridx = 0; g.gridy = 0; g.anchor = GridBagConstraints.LINE_START;
        panel.add(lEmail, g);
        g.gridx=1; g.gridy =0; g.anchor = GridBagConstraints.LINE_START;
        panel.add(tEmail,g);
        g.gridx=0; g.gridy=1; g.anchor = GridBagConstraints.LINE_START;
        panel.add(lFName,g);
        g.gridx = 1; g.gridy = 1; g.anchor = GridBagConstraints.LINE_START;
        panel.add(tFName,g);
        g.gridx = 0; g.gridy = 2; g.anchor = GridBagConstraints.LINE_START;
        panel.add(lLName,g);
        g.gridx = 1; g.gridy = 2; g.anchor = GridBagConstraints.LINE_START;
        panel.add(tLName,g);
        
        
        int result = JOptionPane.showConfirmDialog(null, panel, "add", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        //Variables that store user inputted data
        String email;
        String firstName;
        String lastName;
        if(result != JOptionPane.OK_CANCEL_OPTION)
        {
            //gets the inputted data from the user and stores it on the correct variables
           email = tEmail.getText();
           firstName = tFName.getText();
           lastName = tLName.getText();
           
           //makes sure that there are no empty areas in the program, then continues to store the data in customers
           boolean noEmp = Validator.notEmpty(email, firstName, lastName);
           if(noEmp ==true)
           {
               boolean forvali = Validator.formatEmail(email);
               if(forvali == true)
               {
                   if(email.length()> emailSize )
                   {
                       su.shorter(email,emailSize);
                   }else if(email.length()< emailSize)
                   {
                       su.padSpaces(email, emailSize);
                   }if(firstName.length()> firstNameSize)
                   {
                       su.shorter(firstName,firstNameSize);
                   }else if(firstName.length()< firstNameSize)
                   {
                       su.padSpaces(firstName, firstNameSize);
                   }if(lastName.length()> lastNameSize )
                   {
                       su.shorter(lastName,lastNameSize);
                   }else if(lastName.length()< lastNameSize)
                   {
                       su.padSpaces(lastName, lastNameSize);
                   }
               }
               Customer c = new Customer(email, firstName, lastName);
               
               return c;
           }
        }
        //correct error pop up for the user
        JOptionPane.showMessageDialog(frame, "No Email, First name, or Last name entered, please try again. ","Error",JOptionPane.INFORMATION_MESSAGE);
        return null;
    }
    
    //Help button gives a pop up that helps the user understand what each button does in the program. 
    public static void helpButton()
    {
        String message = "Add - Allows you to add a customer to the table \n"
                       + "Update - Allows you to change customer data\n"
                       + "Delete - Allows you to delete a customer\n";
        
        JOptionPane.showMessageDialog(frame, message, "Help", JOptionPane.INFORMATION_MESSAGE);
                       
                
    }
    
    //sets up the different rows and columns of the list on the main page. 
    public static void tab()
    {
       customers = DAOFactory.getCustomerDAO();
       ArrayList<Customer> ca = customers.getCustomers();
       DefaultTableModel dtm =(DefaultTableModel)table.getModel();
       dtm.setRowCount(0);
       Object[] row = new Object[3];
       for(int i = 0; i<ca.size();i++)
       {
           row[0]=ca.get(i).getEmail();
           row[1]=ca.get(i).getFirstName();
           row[2]=ca.get(i).getLastName();
           dtm.addRow(row);
       }
        
        
    }
    
    
}
