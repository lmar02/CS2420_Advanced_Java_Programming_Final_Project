/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marier.UI;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import marier.business.Customer;
import marier.business.CustomerDAO;
import marier.business.DAOFactory;

/**
 *
 * @author linma
 */
//Necessary variables 
public class CTabelModel extends AbstractTableModel {
private ArrayList<Customer> customers;
private CustomerDAO customer;
private final String[] names = {"Email","First Name","Last Name"};



public CTabelModel()
{
    customer = DAOFactory.getCustomerDAO();
    customers = customer.getCustomers();
}
    @Override
    public int getRowCount() {
        return customers.size();
    }

    @Override
    public int getColumnCount() {
       return names.length;
    }
    public String getColumnName(int columnIndex)
    {
        return names[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)
        {
            case 0:
                return customers.get(rowIndex);
            case 1:
                return customers.get(rowIndex);
            case 2:
                return customers.get(rowIndex);
            default:
                return null;
        }
    }
    
}
