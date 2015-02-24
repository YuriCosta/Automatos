/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 *
 * @author Yuri
 */
public class Evento {
    
    final public char id;
    final private String descricao;
    
    public Evento(char id, String descricao){
        
        this.id = id;
        this.descricao = descricao;
        
    }

     public Evento(char id){
        
        this.id = id;
        this.descricao = "";
        
    }
    @Override
    public String toString() {
        
        String d = descricao.equals("")?""+id:descricao;
        return "Evento " + d;
    }
    
}
