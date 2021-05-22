/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marier.UI;

/**
 *
 * @author linma
 */
public class StringsUtils {
    
    public static String padSpaces(String s, int l)
    {
        if (s.length()< l)
        {
            StringBuilder ss = new StringBuilder(s);
            while(ss.length()<l)
            {
                ss.append(" ");
            }return ss.toString();
        }else
        {
            return s.substring(0, l);
        }
    }
    
    
    public static String shorter (String s, int l)
    {
        StringBuilder ss = new StringBuilder(s);
        ss.setLength(l);
        return ss.toString();
    }
    
}
