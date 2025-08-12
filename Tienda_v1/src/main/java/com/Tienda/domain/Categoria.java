package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;
import java.util.List;
 

@Data
@Entity
@Table(name="categoria")

public class Categoria {
    
    private static final long SerialVersionUID = 1L; 
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name="id_categoria")
    private Long idCategoria;
    private String descripcion;
    private String rutaImagen;
    private boolean activo;
    
    @OneToMany
    @JoinColumn(name="id_categoria")
    List<Producto> productos;
  

    public Categoria() {
    }

    public Categoria(String categoria, boolean activo) {
        this.descripcion = categoria;
        this.activo = activo;
   }   
}
