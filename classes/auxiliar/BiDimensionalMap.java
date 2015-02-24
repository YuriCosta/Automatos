/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes.auxiliar;

import java.util.LinkedHashMap;

/**
 *
 * @author Yuri
 */
public class BiDimensionalMap extends LinkedHashMap<Character, LinkedHashMap<Integer, Integer>>{
    
    
    public void add( Character i, Integer j, Integer item){
        
        LinkedHashMap<Integer, Integer> temp = this.get( i );
        
        if( temp != null){
            
            temp.put( j, item);
            
        } else {
            
            temp = new LinkedHashMap<>();
            temp.put(j, item);
            this.put(i, temp);
            
        }
        
        
        
    }
    
    public Integer get(Character c, Integer i){
        
        LinkedHashMap<Integer, Integer> temp = this.get( c );
        
        return temp.get(i);
        
    }
    
    @Override
    public String toString(){
        
        String s = "\n";
        LinkedHashMap<Integer, Integer> temp;
        Integer j;
        
        for( Character c:this.keySet()){
            
            temp = this.get(c);
            for( Integer i:temp.keySet() ){
                
                j = temp.get(i);
                
                s = s+c+"( "+i+" ) = "+j+";\n";
                
            }
            
        }
        
        return s;
    }

    
}
