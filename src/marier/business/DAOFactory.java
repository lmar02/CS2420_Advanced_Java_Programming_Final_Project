/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marier.business;

import marier.DB.CustomerDB;

/**
 *
 * @author linma
 */
public class DAOFactory {
    public static CustomerDAO getCustomerDAO()
    {
        CustomerDAO c = new CustomerDB();
        return c;
    }
}
