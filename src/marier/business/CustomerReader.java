/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marier.business;

import java.util.ArrayList;

/**
 *
 * @author linma
 */
public interface CustomerReader {
    Customer getCustomer(String email);
    String getCustomerString();
    ArrayList<Customer> getCustomers();
    
}
