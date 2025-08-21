package com.tienda.service;

import com.tienda.domain.Item;
import java.util.ArrayList;
import java.util.List;

public interface ItemService {     
    List<Item> listaItems = new ArrayList<>();
    
    public List<Item> gets();
    
    public Item get(Item item); /// Get iditem 
    
    public void delete(Item item); /// Borra iditem
    
    public void save(Item item); /// actualiza o crea un item en la tabla
    
    public void actualiza(Item item);
    
    public void facturar();
}

