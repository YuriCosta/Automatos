/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.util.HashSet;
import java.util.LinkedHashMap;

/**
 *
 * @author Yuri
 */
public class Tools {
    
    public static String toString( LinkedHashMap lhm){
        
        String s = "\n";
        
        for( Object o:lhm.keySet() ){
            
            s = s+"ID = "+ o.toString()+ "--> "+lhm.get(o)+"\n";
            
        }
        
        return s;
    }
    
    public static String toString( HashSet hs){
        
        String s = "\n";
        
        for( Object o:hs ){
            
            s = s+"ID = "+ o.toString() +"\n";
            
        }
        
        return s;
    }
    
}
