package com.tienda.dao;

import com.tienda.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
/// DAO == Data access object
public interface CategoriaDao extends JpaRepository <Categoria,Long> {
    
}
