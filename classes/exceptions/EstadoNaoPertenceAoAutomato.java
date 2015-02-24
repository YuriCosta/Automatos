/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes.exceptions;

/**
 *
 * @author Yuri
 */
public class EstadoNaoPertenceAoAutomato extends Throwable{
    
    public EstadoNaoPertenceAoAutomato(String sms){
        
        super("Estado não pertence ao Automato " +sms);
        
    }
    
}
