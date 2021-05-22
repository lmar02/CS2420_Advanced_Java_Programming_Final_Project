/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marier.UI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author linma
 * This section on the programs full function is to make sure that each input from the user is correct, and void of errors. 
 */
public class Validator {
    private static Pattern one;
    private static Matcher two;
    private static final String symbols = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$";
    
    public static boolean notEmpty(String email, String firstName, String lastName)
    {
        if(email.equals("") || email.equals(" ") ||email.equals(null))
        {
            return false;
        } if(firstName.equals("") || firstName.equals(" ") ||firstName.equals(null))
        {
            return false;
        } if(lastName.equals("")|| firstName.equals(" ") ||firstName.equals(null))
        {
            return false;
        }
        return true;
    }
    public static boolean formatEmail(String email)
    {
        one = Pattern.compile(symbols, Pattern.CASE_INSENSITIVE);
        two = one.matcher(email);
        return two.matches();
    }
}
