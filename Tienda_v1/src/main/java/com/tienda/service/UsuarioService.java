package com.tienda.service;

import com.tienda.domain.Usuario;
import java.util.List;

public interface UsuarioService {
    
    public List<Usuario> getUsuarios(); /// Get de lista de usuarios
    
    public Usuario getUsuario(Usuario usuario); /// Get usuario por id
    
    public Usuario getUsuarioPorUsername(String username); /// Get usuario por usernmae

    public Usuario getUsuarioPorUsernameYPassword(String username, String password); /// Get usuario por usernmae y contrasena

    public Usuario getUsuarioPorUsernameOCorreo(String username, String correo); ///Get usuario por correo y username
    
    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo); /// Valida usuario si exitste
    

    public void save(Usuario usuario,boolean crearRolUser); /// Se crea o actualiza un usauario y su id si es null
    
    public void delete(Usuario usuario);
    
}