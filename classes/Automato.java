/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;


import classes.auxiliar.BiDimensionalMap;
import classes.exceptions.EstadoNaoPertenceAoAutomato;
import classes.exceptions.EventoNaoPertenceAoAutomato;
import classes.exceptions.QuantidadeDeItensIncompativel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yuri
 */
public class Automato {
    
     /**
     * f = <evento, <estado, estado>>
     */
    final private BiDimensionalMap f;
    final private LinkedHashMap<Integer, Estado> estados;
    final private LinkedHashMap<Character, Evento> eventos;
    final private Integer x0id;
    final private HashSet<Integer> xmId;
    
    public Automato( Estado x0id) throws EventoNaoPertenceAoAutomato{
    
        estados = new LinkedHashMap<>();
        eventos = new LinkedHashMap<>();
        f = new BiDimensionalMap();
        this.x0id = x0id.id;
        xmId = new HashSet<>();
        
        try {
            
            this.putEstado(x0id, new LinkedHashMap<Character, Integer>());
            
        } catch (QuantidadeDeItensIncompativel ex) {} catch (EstadoNaoPertenceAoAutomato ex) {
            Logger.getLogger(Automato.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public Automato( Estado[] x, Evento[] e, BiDimensionalMap fn, int x0Index ) throws EventoNaoPertenceAoAutomato, QuantidadeDeItensIncompativel, EstadoNaoPertenceAoAutomato{
        
        this( x[x0Index] );
        
        Estado x0 = x[x0Index];
        
        LinkedHashMap<Integer, Integer> dummy1 = new LinkedHashMap<>();
        
        for(Evento eTemp:e){
            
            dummy1.put(x0.id, x0.id);
            this.putEvento(eTemp, dummy1);
            
        }
        
        LinkedHashMap<Character, Integer> dummy2 = new LinkedHashMap<>();
        
        for(Evento eTemp:e){
                
                dummy2.put(eTemp.id, x0.id);
                
        }
        
        for(Estado xn:x){
            
            this.putEstado(xn, dummy2);
            
        }
        
        Remap(fn);
        
    }
    
    public Automato( Automato ge ){
        
        this.estados = ge.estados;
        this.eventos = ge.eventos;
        this.f = ge.f;
        this.x0id = ge.x0id;
        this.xmId = ge.xmId;
        
    }
    
    /**
     *
     * @param estado
     * @param mapaEventoEstados
     * 
     * mapaEventoEstados[e][i] onde 'e' é id do evento que já deve estar contida
     * em eventos e 'e' o id do estado imagem para qual o 'estado' dominio vai apontar após 'e' ocorrer
     * 
     * mapaEventoEstados.length = funcoes.length, caso o contrário excessões serão executadas
     * 
     * @throws classes.exceptions.QuantidadeDeItensIncompativel
     * @throws classes.exceptions.EstadoNaoPertenceAoAutomato
     * @throws classes.exceptions.EventoNaoPertenceAoAutomato
     */
    public final void putEstado( Estado estado, LinkedHashMap<Character, Integer> mapaEventoEstados ) throws QuantidadeDeItensIncompativel, EstadoNaoPertenceAoAutomato, EventoNaoPertenceAoAutomato{
        
        if( mapaEventoEstados.size() != eventos.size() ){
            
            throw new QuantidadeDeItensIncompativel("");
            
        }
        
        Estado x;
        Evento y;
        Integer inId;
        Set<Character> s = mapaEventoEstados.keySet();
        
        for( Character id:s ){
            
            inId = mapaEventoEstados.get(id);
            x = idParaEstado(inId);
            y = idParaEvento(id);
            
            if( x == null && inId != estado.id){
            
                throw new EstadoNaoPertenceAoAutomato("Id:"+inId);
            
            }
            
            if( y == null ){
                
                throw new EventoNaoPertenceAoAutomato("Id:"+id);
                
            }
            
            f.add(id, estado.id, inId);
            
        }
        
        this.estados.put(estado.id, estado);
        
        if( estado.isMarcado() ){
            
            xmId.add(estado.id);
            
        }
        
    }
    
    /**
     *
     * @param evento
     * @param mapaEstadosEstados
     * 
     * mapaEstadosEstados[d][i] onde 'd' é id do estado dominio que já deve estar contida
     * em estados e 'i' o id do estado imagem para qual o 'd' vai apontar após fn ocorrer
     * 
     * mapaEstadosEstados.length = funcoes.length , caso o contrário excessões serão executadas
     * 
     * @throws classes.exceptions.QuantidadeDeItensIncompativel
     * @throws classes.exceptions.EstadoNaoPertenceAoAutomato
     */
    public final void putEvento( Evento evento, LinkedHashMap<Integer, Integer> mapaEstadosEstados) throws QuantidadeDeItensIncompativel, EstadoNaoPertenceAoAutomato{
        
        if( mapaEstadosEstados.size() != estados.size() ){
            
            throw new QuantidadeDeItensIncompativel("");
            
        }
        
        Estado x, y;
        Integer inId;
        Set<Integer> s = mapaEstadosEstados.keySet();
        
        for( Integer id:s ){
            
            inId = mapaEstadosEstados.get(id);
            x = idParaEstado(inId);
            y = idParaEstado(id);
            
            if( x == null ){
            
                throw new EstadoNaoPertenceAoAutomato("Id:"+inId);
            
            }
            
            if( y == null ){
                
                throw new EstadoNaoPertenceAoAutomato("Id:"+id);
                
            }
            
            f.add(evento.id, id, inId);
            
        }
        
        this.eventos.put(evento.id, evento);
    }

    public Estado idParaEstado( Integer id ){
        
        return estados.get(id);
        
    }
    
    public Evento idParaEvento( Character id ){
        
        return eventos.get(id);
        
    }
    
    public final void Remap( BiDimensionalMap fn ) throws QuantidadeDeItensIncompativel, EstadoNaoPertenceAoAutomato{
        
        for( Character c:fn.keySet() ){
            
            this.putEvento(this.idParaEvento(c), fn.get(c) );
            
        }
        
        
        
    }

    @Override
    public String toString() {
        return "Automato{" +
                "\nf = " + f + 
                "\n estados = " + Tools.toString(estados) + 
                "\n funcoes = " + Tools.toString(eventos) + 
                "\n x0id = " + x0id + 
                "\n xmId = " + Tools.toString(xmId) + 
                
            "\n}";
    }

    public BiDimensionalMap getF() {
        return f;
    }

    public LinkedHashMap<Integer, Estado> getEstados() {
        return estados;
    }

    public LinkedHashMap<Character, Evento> getEventos() {
        return eventos;
    }

    public Integer getX0id() {
        return x0id;
    }
    
    public Estado getX0() {
        return this.idParaEstado(x0id);
    }

    public HashSet<Integer> getXmId() {
        return xmId;
    }
}