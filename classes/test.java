/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import classes.auxiliar.BiDimensionalMap;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 *
 * @author Yuri
 */
public class test {
    
    
    public static void main(String[] s) throws Throwable{
        
        /*
        LinkedHashMap<Integer, Integer> map1 = new LinkedHashMap<>(),
                                        map2 = new LinkedHashMap<>();
        LinkedHashMap<Character, Integer> mapFa = new LinkedHashMap<>();
        
        Estado x0 = new Estado(0, true, "Desligado"),
               x1 = new Estado(1, false, "Ligado");
        
        Automato a = new Automato(x0);
        
        
        map1.put(x0.id, x0.id);
        
        map2.put(x0.id, x1.id);
        map2.put(x1.id, x0.id);
        
        mapFa.put(f1.id, x0.id);
        
        a.putEvento(f1, map1);
        a.putEstado(x1, mapFa);
        a.putEvento(f2, map2);
        
        OperadorAutomato oa = new OperadorAutomato(a);
        oa.ocorreSequenciaDeEventos("abababaaba");
        System.out.println(oa);
        
        
        */
        
        Estado [] x = new Estado[5];
        Evento[] e = new Evento[10];
        BiDimensionalMap bdm = new BiDimensionalMap();
        
        char a = 'a';
        String data = ""+a;
        for( int i = 0; i<e.length; i++){
            
            data = data+a;
            e[i] = new Evento(a);
            a++;
            
        }
        
        for( int i = 0; i<x.length; i++){
            
            x[i] = new Estado(i, i%4 == 0 );
            
        }

        int l = x.length-1;
        for( Evento en:e){
            for( Estado xn:x){
                
                bdm.add(en.id, xn.id, l-xn.id);
                
            }
        }        
        
        Automato t = new Automato( x, e, bdm, 0);
        //System.out.println(t);
        OperadorAutomato oa = new OperadorAutomato(t);
        oa.ocorreSequenciaDeEventos(data);
        System.out.println(oa);
    }
}
