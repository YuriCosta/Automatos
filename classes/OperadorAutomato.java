/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import classes.exceptions.EventoNaoPertenceAoAutomato;
import java.util.LinkedHashMap;

/**
 *
 * @author Yuri
 * Esta classe recebe um autômato como contrutor e manuseia ele simulando o sistema
 */
public class OperadorAutomato {
    
    final private Automato a;
    private String eventosOcorridos;
    private Estado estadoAtual;
    
    
    public OperadorAutomato( Automato a ){
        
        this.a = a;
        estadoAtual = a.getX0();
        eventosOcorridos = "";
        
    }
    
    public boolean contemEvento( Character c ){
        
        Evento e = a.getEventos().get(c);
        
        return e != null;
        
    }
    
    public void ocorreEvento( Character c) throws EventoNaoPertenceAoAutomato{
        
        if(contemEvento(c)){
            
            estadoAtual = a.idParaEstado(a.getF().get(c, estadoAtual.id));
            eventosOcorridos = eventosOcorridos + c;
            
        } else {
            
            throw new EventoNaoPertenceAoAutomato("");
            
        }
        
    }
    
    public void ocorreSequenciaDeEventos( String s ) throws EventoNaoPertenceAoAutomato{
    
        for( Character c:s.toCharArray() ){
            
            ocorreEvento(c);
            
        }
    
    }

    @Override
    public String toString() {
        return "OperadorAutomato{\n" + a + "\n eventosOcorridos = " + eventosOcorridos + "\n Estado Atual = " + estadoAtual + '}';
    }

    public Estado getEstadoAtual() {
        return estadoAtual;
    }

    public Automato getA() {
        return a;
    }

    public String getEventosOcorridos() {
        return eventosOcorridos;
    }
    
}
