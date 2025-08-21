package com.tienda.service.impl;

import com.tienda.dao.FacturaDao;
import com.tienda.dao.VentaDao;
import com.tienda.domain.Producto;
import com.tienda.domain.Usuario;
import com.tienda.domain.Factura;
import com.tienda.domain.Item;
import com.tienda.domain.Venta;
import com.tienda.service.UsuarioService;
import com.tienda.service.ItemService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.tienda.dao.ProductoDao;

@Service
public class ItemServiceImpl implements ItemService {
    
    /// Get todo el carrito todos los items
    @Override 
    public List<Item> gets() { 
        return listaItems;
    }

    ///Agrega un item
    @Override
    public void save(Item item) {
        boolean existe = false;
        for (Item i : listaItems) {
            
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) { /// Valida si existe
                
                if (i.getCantidad() < item.getExistencias()) { /// Revisa el seock
                    
                    i.setCantidad(i.getCantidad() + 1); /// Aumneta cantidad
                }
                existe = true;
                break;
            }
        }
        if (!existe) {     
            item.setCantidad(1);
            listaItems.add(item);
        }
    }

    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : listaItems) {
            ++posicion;
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                existe = true;
                break;
            }
        }
        if (existe) {
            listaItems.remove(posicion);
        }
    }

    //Get item para modificarlo
    @Override
    public Item get(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void actualiza(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
    }

    @Autowired
    private UsuarioService uuarioService;

    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private VentaDao ventaDao;
    @Autowired
    private ProductoDao productoDao;

    @Override
    public void facturar() {
        System.out.println("Facturando");

    
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) { /// Get usuario
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        if (username.isBlank()) {
            return;
        }

        Usuario uuario = uuarioService.getUsuarioPorUsername(username);

        if (uuario == null) {
            return;
        }

        Factura factura = new Factura(uuario.getIdUsuario());
        factura = facturaDao.save(factura);

        double total = 0;
        for (Item i : listaItems) {
            System.out.println("Producto: " + i.getDescripcion()
                    + " Cantidad: " + i.getCantidad()
                    + " Total: " + i.getPrecio() * i.getCantidad());
            Venta venta = new Venta(factura.getIdFactura(), i.getIdProducto(), i.getPrecio(), i.getCantidad());
            ventaDao.save(venta);
            Producto producto = productoDao.getReferenceById(i.getIdProducto());
            producto.setExistencias(producto.getExistencias()-i.getCantidad());
            productoDao.save(producto);
            total += i.getPrecio() * i.getCantidad();
        }
        factura.setTotal(total);
        facturaDao.save(factura);
        listaItems.clear();
    }
}

