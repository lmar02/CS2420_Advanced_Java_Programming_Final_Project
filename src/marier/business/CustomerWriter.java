/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marier.business;

/**
 *
 * @author linma
 */
public interface CustomerWriter {
    boolean addCustomer(Customer c);
    boolean deleteCustomer( Customer c);
    boolean updateCustomer(String email, Customer c);
    
}
