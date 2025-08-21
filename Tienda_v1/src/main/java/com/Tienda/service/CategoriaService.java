
package com.tienda.service;

import com.tienda.domain.Categoria; 
import java.util.List; 
/// Se declaran los metodos que se van a desarrollar y utilizar en ServIMPL
/// Lo mismo ocurre en todo Serv
public interface CategoriaService {
    
    
    public List<Categoria> getCategorias(boolean activos);
    
    public Categoria getCategoria(Categoria categoria);
    
    public void save (Categoria categoria);
    
     public void delete (Categoria categoria);
}
 
