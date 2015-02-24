/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 *
 * @author Yuri
 * 
 * idValor: identificação e valor do estado
 */
public class Estado{
    
    public final int id;
    private final boolean marcado;
    private final String descricao;
    
    public Estado( int id, boolean marcado, String descricao){
        
        this.id = id;
        this.marcado = marcado;
        this.descricao = descricao;
        
    }
    
    public Estado( int id, boolean marcado){
        
        this.id = id;
        this.marcado = marcado;
        this.descricao = "";
        
    }

    public boolean isMarcado() {
        return marcado;
    }

    @Override
    public String toString() {
        
        String d = descricao.equals("")?""+id:descricao;
        String m = marcado?"Marcado":"Não Marcado";
        return "Estado "+ d +" ( "+ m +" )";
    }
}
